package de.hfu.meetme;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import de.hfu.anybeam.networkCore.DeviceType;
import de.hfu.anybeam.networkCore.EncryptionType;
import de.hfu.anybeam.networkCore.NetworkEnvironment;
import de.hfu.anybeam.networkCore.NetworkEnvironmentListener;
import de.hfu.anybeam.networkCore.NetworkEnvironmentSettings;
import de.hfu.anybeam.networkCore.NetworkEnvironmentSettingsEditor;

public class NetworkEnvironmentManager extends BroadcastReceiver {

	private static NetworkEnvironment networkEnvironment;
	private static List<NetworkEnvironmentListener> listeners;
	private static WifiManager wifi;

	public synchronized static NetworkEnvironment getNetworkEnvironment(Context c) throws Exception {

		if(!getWifiManager(c).isWifiEnabled()) {
			disposeNetworkEnvironment();
			throw new Exception("Wifi is not available!");
		}

		if(networkEnvironment == null) {
			networkEnvironment = new NetworkEnvironment(loadNetworkEnvironmentSettings(c));

			if(listeners != null) {
				networkEnvironment.addAllNetworkEnvironmentListeners(listeners);
			} 
		}

		return networkEnvironment;
	}

	private static WifiManager getWifiManager(Context c) {
		if(wifi == null)
			wifi = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);

		return wifi;
	}

	public synchronized static void addNetworkEnvironmentListener(NetworkEnvironmentListener listenerToAdd) {
		if(listeners == null)
			listeners = new ArrayList<NetworkEnvironmentListener>();

		if(!listeners.contains(listenerToAdd))
			listeners.add(listenerToAdd);

		if(networkEnvironment != null)
			networkEnvironment.addNetworkEnvironmentListener(listenerToAdd);

	}

	public synchronized static void removeNetworkEnvironmentListener(NetworkEnvironmentListener listenerToRemove) {
		if(listeners != null)
			listeners.remove(listenerToRemove);

		if(networkEnvironment != null)
			networkEnvironment.removeNetworkEnvironmentListener(listenerToRemove);

	}

	public synchronized static void disposeNetworkEnvironment() throws Exception {
		if(networkEnvironment != null) {
			new Thread() {
				public void run() {
					try {
						synchronized (NetworkEnvironmentManager.class) {
							networkEnvironment.dispose();
							networkEnvironment = null;
						}


					} catch(Exception e) {
						e.printStackTrace();

					}
				}
			}.start();
		}
	}

	public static NetworkEnvironmentSettings loadNetworkEnvironmentSettings(Context c) {
		PreferenceManager.setDefaultValues(c.getApplicationContext(), R.xml.preferences, false);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
		
		String userName = prefs.getString("user_name", "Unknown");
		
		NetworkEnvironmentSettings s = new NetworkEnvironmentSettings(
				userName, //The device name (e.g. Galaxy S5)
				DeviceType.TYPE_UNKNOWN,  //The device type: laptop, desktop, smartphone...
				EncryptionType.NONE, //The encryption to use
				9001, //The port for data transmissions
				9000, //The port for brodcasts
				new byte[0] //The password to use
				);
		return s;
	}

	public synchronized static void updateNetworkEnvironment(Context c) throws Exception {
		NetworkEnvironmentSettingsEditor editor = new NetworkEnvironmentSettingsEditor(
				getNetworkEnvironment(c).getNetworkEnvironmentSettings());
		NetworkEnvironmentSettings newSettings = loadNetworkEnvironmentSettings(c);

		networkEnvironment = editor.applyAll(newSettings, networkEnvironment);
	}

	@Override
	public void onReceive(Context context, Intent intent) {     
		try {
			switch(getWifiManager(context).getWifiState()) {
			case WifiManager.WIFI_STATE_DISABLED:
			case WifiManager.WIFI_STATE_DISABLING: disposeNetworkEnvironment(); break;
			case WifiManager.WIFI_STATE_ENABLED: getNetworkEnvironment(context); break;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
	
		}

	}	
}
