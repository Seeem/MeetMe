/**
 * 
 */
package de.hfu.meetme.model.network;

import java.net.InetAddress;

import de.hfu.meetme.views.UserListFragment;
import android.os.AsyncTask;

/**
 * @author Simeon Sembach
 *
 */
public class MMNetworkTask extends AsyncTask<MMNetworkTaskType, Void, Void>
{

	// Class-Members:
	
	/** */
	private static MMMessageManager messageManager;
		
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
	public static void initialize(UserListFragment anUserListFragment)
	{
		setMessageManager(new MMMessageManager(anUserListFragment));
	}
	
	/** */
	public static void startNetworkTask(MMNetworkTaskType aNetworkTasktype)
	{
		if (aNetworkTasktype == null)
			throw new NullPointerException("network task type is null.");
		
		if (getMessageManager() == null)
			throw new IllegalStateException("message manager not initialized.");
				
		new MMNetworkTask().execute(aNetworkTasktype);
	}
	
	/** */
	public static void sendMeetMeMessage(InetAddress anInetAddress)
	{
		new MMNetworkTask(anInetAddress).execute(MMNetworkTaskType.MEET_ME);
	}
	
	// Implementors:
	
	/** */
	@Override protected Void doInBackground(MMNetworkTaskType... someParameters)
	{
		if (someParameters == null) 
			return null;
		
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
			getMessageManager().refreshUsers();
		}
		
		return null;
	}

	// Accesors:
	
	/**
	 * @return the messageManager
	 */
	private static MMMessageManager getMessageManager()
	{
		return messageManager;
	}

	/**
	 * @param aMessageManager the messageManager to set
	 */
	private static void setMessageManager(MMMessageManager aMessageManager)
	{
		if (aMessageManager == null)
			throw new NullPointerException("message manager is null.");
		
		messageManager = aMessageManager;
	}

	
	/**
	 * @return the inetAddress
	 */
	public InetAddress getInetAddress()
	{
		return inetAddress;
	}
	

	/**
	 * @param inetAddress the inetAddress to set
	 */
	public void setInetAddress(InetAddress inetAddress)
	{
		this.inetAddress = inetAddress;
	}

}
