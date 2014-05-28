/**
 * 
 */
package de.hfu.meetme.model.network;

import android.os.AsyncTask;

/**
 * @author Simeon Sembach
 *
 */
public class MMNetworkTask extends AsyncTask<MMNetworkTaskType, Void, Void>
{

	// Instance-Members:
	
	/** */
	private MMMessageManager messageManager;
	
	// Constructor:
	
	/** */
	private MMNetworkTask(MMMessageManager aMessageManager)
	{
		setMessageManager(aMessageManager);
	}
	
	// MM-API:
	
	/** */
	public static void startNetworkTask(MMMessageManager aMessageManager, MMNetworkTaskType aNetworkTasktype)
	{
		new MMNetworkTask(aMessageManager).execute(aNetworkTasktype);
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
		
		return null;
	}

	// Accesors:
	
	/**
	 * @return the messageManager
	 */
	private MMMessageManager getMessageManager()
	{
		return messageManager;
	}

	/**
	 * @param aMessageManager the messageManager to set
	 */
	private void setMessageManager(MMMessageManager aMessageManager)
	{
		if (aMessageManager == null)
			throw new NullPointerException("message manage is null");
		
		this.messageManager = aMessageManager;
	}

}
