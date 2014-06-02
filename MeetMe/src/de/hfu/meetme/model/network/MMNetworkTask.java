/**
 * 
 */
package de.hfu.meetme.model.network;

import java.net.InetAddress;

import de.hfu.meetme.model.MMUser;
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
	private static final MMMessageManager messageManager = new MMMessageManager(new MMNetworkTask());
		
	/** */
	private static UserListFragment userListFragment;
	
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
	public void updateUserListUi()
	{
		getUserListFragment().updateView();
	}
	
	/** */
	public void addNotification(MMUser anUser)
	{
		getUserListFragment().addNotification(anUser);
	}
	
	/** */
	public static void startListening(UserListFragment anUserListFragment)
	{
		setUserListFragment(anUserListFragment);
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

	/**
	 * @return the userListFragment
	 */
	private static UserListFragment getUserListFragment()
	{
		return userListFragment;
	}
	
	/**
	 * @param userListFragment the userListFragment to set
	 */
	private static void setUserListFragment(UserListFragment userListFragment)
	{
		if (userListFragment == null)
			throw new NullPointerException("user list fragment is null.");
			
		MMNetworkTask.userListFragment = userListFragment;
	}

}
