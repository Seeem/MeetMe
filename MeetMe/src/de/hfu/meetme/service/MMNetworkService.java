package de.hfu.meetme.service;

import de.hfu.meetme.model.network.messagemanager.MMMessageManagerEvent;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerListener;
import de.hfu.meetme.model.network.networktask.MMNetworkTask;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMNetworkService extends Service implements MMMessageManagerListener
{
	//Instance Members:
	
	private final IBinder binder = new MMNetworkServiceBinder();
	// public MMNetworkService()
	// {
	// }

	//Internals
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
	
	@Override
	public int onStartCommand (Intent intent, int flags, int startId) {
		MMNetworkTask.addMessageManagerListener(this);
		MMNetworkTask.startListening();
		Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		MMNetworkTask.removeMessageManagerListener(this);
		MMNetworkTask.stopListening();
		Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return binder;
	}

	public class MMNetworkServiceBinder extends Binder
	{
		//Interface Methods
	}

	@Override
	public void managerEventPerformed(MMMessageManagerEvent aMessageManagerEvent)
	{
		if (aMessageManagerEvent.isUserWantsAMeeting())
		{
			System.out.println("User wants a meeting: "+aMessageManagerEvent.getUser());
		}		
	}
}
