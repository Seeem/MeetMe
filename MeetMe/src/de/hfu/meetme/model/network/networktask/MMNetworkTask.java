/**
 * 
 */
package de.hfu.meetme.model.network.networktask;

import java.net.InetAddress;

import de.hfu.meetme.model.MMUser;
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
	
	/** The {@link MMMessageManager}. */
	private static final MMMessageManager messageManager = new MMMessageManager();
	
	// Instance-Members:
	
	/** The target {@link MMUser} */
	private MMUser userTarget;
	
	/** The chat message. */
	private String chatMessage;
	
	// Constructor:
	
	/**
	 * Creates a new {@link MMNetworkTask} instance with a specific {@link InetAddress}.
	 * @param anInetAddress the {@link InetAddress} to set
	 */
	private MMNetworkTask(MMUser aTargetUser)
	{
		setUserTarget(aTargetUser);
	}
	
	/**
	 * Creates a new {@link MMNetworkTask} instance with a specific {@link InetAddress} and a chat message.
	 * @param anInetAddress the {@link InetAddress} to set
	 * @param aChatMessage the chat message to set
	 */
	private MMNetworkTask(MMUser aTargetUser, String aChatMessage)
	{
		setUserTarget(aTargetUser);
		setChatMessage(aChatMessage);
	}
	
	/**
	 * Creates a new {@link MMNetworkTask} instance.
	 */
	private MMNetworkTask(){};

	// MM-API:

	/**
	 * Starts a new START_LISTENING {@link MMNetworkTask}.
	 */
	public static void startListening()
	{
		startNetworkTask(MMNetworkTaskType.START_LISTENING);
	}
	
	/**
	 * Starts a new STOP_LISTENING {@link MMNetworkTask}.
	 */
	public static void stopListening()
	{
		startNetworkTask(MMNetworkTaskType.STOP_LISTENING);
	}
	
	/**
	 * Starts a new REFRESH_USERLIST {@link MMNetworkTask}.
	 */
	public static void refreshUserlist()
	{
		startNetworkTask(MMNetworkTaskType.REFRESH_USERLIST);
	}
	
	/**
	 * Starts a new MEET_ME {@link MMNetworkTask} with a specific {@link InetAddress}.
	 * @param anInetAddress the {@link InetAddress} to set
	 */
	public static void sendMeetMeMessage(MMUser aTargetUser)
	{
		new MMNetworkTask(aTargetUser).execute(MMNetworkTaskType.MEET_ME);
	}
	
	/**
	 * Starts a new CHAT_MESSAGE {@link MMNetworkTask} with a specific {@link InetAddress} and a chat message.
	 * @param anInetAddress the {@link InetAddress} to set
	 * @param aChatMessage the chat message to set
	 */
	public static void sendChatMessage(MMUser aTargetUser, String aChatMessage)
	{
		new MMNetworkTask(aTargetUser, aChatMessage).execute(MMNetworkTaskType.CHAT_MESSAGE);
	}
		
	/**
	 * Starts a new UPDATE {@link MMNetworkTask}.
	 */
	public static void sendUpdate()
	{
		new MMNetworkTask().execute(MMNetworkTaskType.UPDATE);
	}
	
	/**
	 * Adds a {@link MMMessageManagerListener} to the {@link MMMessageManagerListener}.
	 * @param aMessageManagerListener the {@link MMMessageManagerListener} to add
	 */
	public static void addMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		getMessageManager().addMessageManagerListener(aMessageManagerListener);
	}
	
	/**
	 * Removes a {@link MMMessageManagerListener} from the {@link MMMessageManagerListener}.
	 * @param aMessageManagerListener the {@link MMMessageManagerListener} to remove
	 */
	public static void removeMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		getMessageManager().removeMessageManagerListener(aMessageManagerListener);
	}
	
	// Internals:
	
	/**
	 * Starts a new {@link MMNetworkTask}.
	 * @param aNetworkTasktype the {@link MMNetworkTask} to start
	 */
	private static void startNetworkTask(MMNetworkTaskType aNetworkTasktype)
	{
		if (aNetworkTasktype == null)
			throw new NullPointerException("network task type is null.");
		
		if (getMessageManager() == null)
			throw new IllegalStateException("message manager not initialized.");
				
		new MMNetworkTask().execute(aNetworkTasktype);
	}
	
	// Implementors:
	
	/**
	 * Handles the {@link MMNetworkTask}.
	 */
	@Override protected Void doInBackground(MMNetworkTaskType... someParameters)
	{
		if (someParameters == null) return null;
		
		if (someParameters[0].isStartListening())
		{
			getMessageManager().startListening();
		} 
		else if (someParameters[0].isStopListening())
		{
			getMessageManager().stopListening();
		} 
		else if (someParameters[0].isRefreshUserlist())
		{
			getMessageManager().refreshUsers();
		}
		else if (someParameters[0].isMeetMe())
		{
			getMessageManager().sendMeetMeMessage(getUserTarget());
		}
		else if (someParameters[0].isUpdate())
		{
			getMessageManager().sendUpdate();
		}
		else if (someParameters[0].isChatMessage())
		{
			getMessageManager().sendChatMessage(getUserTarget(), getChatMessage());
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
	 * @return the chatMessage
	 */
	private String getChatMessage()
	{
		return chatMessage;
	}

	
	
	/**
	 * @param chatMessage the chatMessage to set
	 */
	private void setChatMessage(String chatMessage)
	{
		this.chatMessage = chatMessage;
	}

	/**
	 * @return the userTarget
	 */
	public MMUser getUserTarget()
	{
		return userTarget;
	}

	/**
	 * @param userTarget the userTarget to set
	 */
	public void setUserTarget(MMUser userTarget)
	{
		this.userTarget = userTarget;
	}

}
