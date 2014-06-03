/**
 * 
 */
package de.hfu.meetme.model.network.networktask;

import java.net.InetAddress;

import de.hfu.meetme.model.network.messagemanager.MMMessageManager;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerListener;

import android.os.AsyncTask;

/**
 * @author Simeon Sembach
 *
 */
public class MMNetworkTask extends AsyncTask<MMNetworkTaskType, Void, Void>
{
	
	// Class-Members:
	
	/** */
	private static final MMMessageManager messageManager = new MMMessageManager();
	
	// Instance-Members:
	
	/** */
	private InetAddress inetAddress;
	
	// Constructor:
	
	/** */
	private MMNetworkTask(InetAddress anInetAddress)
	{
		setInetAddress(anInetAddress);
	}
	
	/** */
	private MMNetworkTask(){};

	// MM-API:

	/** */
	public static void startListening()
	{
		startNetworkTask(MMNetworkTaskType.START_LISTENING);
	}
	
	/** */
	public static void stopListening()
	{
		startNetworkTask(MMNetworkTaskType.STOP_LISTENING);
	}
	
	/** */
	public static void refreshUserlist()
	{
		startNetworkTask(MMNetworkTaskType.REFRESH_USERLIST);
	}
	
	/** */
	public static void sendMeetMeMessage(InetAddress anInetAddress)
	{
		new MMNetworkTask(anInetAddress).execute(MMNetworkTaskType.MEET_ME);
	}
	
	/** */
	public static void sendUpdate()
	{
		new MMNetworkTask().execute(MMNetworkTaskType.UPDATE);
	}
	
	/** */
	public static void addMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		getMessageManager().addMessageManagerListener(aMessageManagerListener);
	}
	
	/** */
	public static void removeMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		getMessageManager().removeMessageManagerListener(aMessageManagerListener);
	}
	
	// Internals:
	
	/** */
	private static void startNetworkTask(MMNetworkTaskType aNetworkTasktype)
	{
		if (aNetworkTasktype == null)
			throw new NullPointerException("network task type is null.");
		
		if (getMessageManager() == null)
			throw new IllegalStateException("message manager not initialized.");
				
		new MMNetworkTask().execute(aNetworkTasktype);
	}
	
	// Implementors:
	
	/** */
	@Override protected Void doInBackground(MMNetworkTaskType... someParameters)
	{
		if (someParameters == null) return null;
		
		if (someParameters[0] == MMNetworkTaskType.START_LISTENING)
		{
			getMessageManager().startListening();
		} 
		else if (someParameters[0] == MMNetworkTaskType.STOP_LISTENING)
		{
			getMessageManager().stopListening();
		} 
		else if (someParameters[0] == MMNetworkTaskType.REFRESH_USERLIST)
		{
			getMessageManager().refreshUsers();
		}
		else if (someParameters[0] == MMNetworkTaskType.MEET_ME)
		{
			getMessageManager().sendMeetMeMessage(getInetAddress());
		}
		else if (someParameters[0] == MMNetworkTaskType.UPDATE)
		{
			getMessageManager().sendUpdate();
		}
		
		return null;
	}

	// Accessors:
	
	/**
	 * @return the messageManager
	 */
	private static MMMessageManager getMessageManager()
	{
		return messageManager;
	}

	/**
	 * @return the inetAddress
	 */
	private InetAddress getInetAddress()
	{
		return inetAddress;
	}
	
	/**
	 * @param anInetAddress the inetAddress to set
	 */
	private void setInetAddress(InetAddress anInetAddress)
	{
		if (anInetAddress == null)
			throw new NullPointerException("internet address is null.");
		
		this.inetAddress = anInetAddress;
	}

}
