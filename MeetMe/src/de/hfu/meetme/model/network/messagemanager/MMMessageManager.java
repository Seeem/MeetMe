/**
 * 
 */
package de.hfu.meetme.model.network.messagemanager;

import java.net.InetAddress;
import java.util.Vector;

import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.model.network.message.MMMessageEvent;
import de.hfu.meetme.model.network.message.MMMessageListener;
import de.hfu.meetme.model.network.message.MMMessageType;
import de.hfu.meetme.model.network.receiver.MMMessageReceiver;
import de.hfu.meetme.model.network.sender.MMMessageSender;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageManager implements MMMessageListener
{
	
	// Instance-Members:
	
	/** The {@link MMMessageSender} of the {@link MMMessageManager}. */
	private MMMessageSender messageSender;
	
	/** The {@link MMMessageReceiver} of the {@link MMMessageManager}. */
	private MMMessageReceiver messageReceiver;
	
	/** The isStarted Boolean of the {@link MMMessageManager}. */
	private boolean isStarted = false;
	
	/** The {@link MMMessageManagerListener} of the {@link MMMessageManager}. */
	private Vector<MMMessageManagerListener> messageManagerListeners;
	
	// Constructor:
	
	/**
	 * Creates a new {@link MMMessageManager} instance.
	 */
	public MMMessageManager()
	{
		setMessageSender(new MMMessageSender());
		setMessageReceiver(new MMMessageReceiver(MMNetworkUtil.UDP_BROADCAST_PORT, MMNetworkUtil.UDP_PORT));
		setMessageManagerListeners(new Vector<MMMessageManagerListener>());
	}
	
	// MM-API:
	
	/**
	 * Adds a {@link MMMessageManagerListener} to the {@link MMMessageManager}.
	 * @param aMessageManagerListener the {@link MMMessageManagerListener} to add
	 */
	public void addMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		if (aMessageManagerListener == null)
			throw new NullPointerException("message manager listener is null.");
		
		getMessageManagerListeners().add(aMessageManagerListener);
	}
	
	/**
	 * Removes a {@link MMMessageManagerListener} from the {@link MMMessageManager}.
	 * @param aMessageManagerListener the {@link MMMessageManagerListener} to remove
	 */
	public void removeMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		if (aMessageManagerListener == null)
			throw new NullPointerException("message manager listener is null.");
		
		getMessageManagerListeners().remove(aMessageManagerListener);
	}
	
	/**
	 * Removes all users from the {@link MMUser} and sends a new UDP broadcast message
	 * with {@link MMMessageType} CONNECT.
	 */
	public void refreshUsers()
	{
		if (!isStarted() || MMUser.getMyself() == null) return;
		MMUser.removeAllUsers();
		getMessageSender().sendUDPBroadcastMessage(MMMessageType.CONNECT, MMUser.getMyself());	
	}
	
	/**
	 * Starts the {@link MMMessageReceiver}.
	 */
	public void startListening()
	{
		if (MMUser.getMyself() == null)
			throw new IllegalStateException("myself is null.");
		
		if (isStarted()) return;
		getMessageReceiver().addMessageListener(this);
		getMessageReceiver().startReceiver();
		setStarted(true);
	}
	
	/**
	 * Stops the {@link MMMessageReceiver}.
	 */
	public void stopListening()
	{
		if (MMUser.getMyself() == null)
			throw new IllegalStateException("myself is null.");
		
		if (!isStarted()) return;
		getMessageSender().sendUDPBroadcastMessage(MMMessageType.DISCONNECT, MMUser.getMyself());
		getMessageReceiver().removeMessageListener(this);
		getMessageReceiver().stopReceiver();	
		setStarted(false);
	}
	
	/**
	 * Sends a MESSAGE message to a single target
	 * @param anInetAddress the {@link InetAddress} of the target
	 * @param cChatMessage the chat message to send
	 */
	public void sendChatMessage(InetAddress anInetAddress, String aChatMessage)
	{
		if (MMUser.getMyself() == null)
			throw new IllegalStateException("myself is null.");
		
		getMessageSender().sendUDPMessage(anInetAddress, aChatMessage);	
	}
	
	/**
	 * Sends a MEETME message to a single target
	 * @param anInetAddress the {@link InetAddress} of the target
	 */
	public void sendMeetMeMessage(InetAddress anInetAddress)
	{
		if (MMUser.getMyself() == null)
			throw new IllegalStateException("myself is null.");
		
		getMessageSender().sendUDPMessage(anInetAddress, MMMessageType.MEETME, MMUser.getMyself());	
	}
	
	/**
	 * Sends a UPDATE message as broadcast message.
	 */
	public void sendUpdate()
	{
		getMessageSender().sendUDPBroadcastMessage(MMMessageType.UPDATE, MMUser.getMyself());	
	}
	
	// Internals:
	
	/**
	 * Pushes a {@link MMMessageManagerEvent} to all {@link MMMessageManagerListener}.
	 * @param aMessageManagerEvent the {@link MMMessageManagerEvent} to push
	 */
	private void pushMessageManagerEvent(MMMessageManagerEvent aMessageManagerEvent)
	{
		for (MMMessageManagerListener aMessageManagerListener : getMessageManagerListeners())
		{
			aMessageManagerListener.managerEventPerformed(aMessageManagerEvent);
		}			
	}
	
	// Implementors:
	
	/**
	 * Handles a {@link MMMessageEvent}.
	 */
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{
		if (aMessageEvent == null)
			throw new NullPointerException("message event is null.");
		
		// Filter Messages from this device and unknown messages:
		if (aMessageEvent.isFromMe() || aMessageEvent.isUnknownMessage()) return;
		
		// Get the user:
		MMUser theUser = MMUser.valueOf(aMessageEvent.getMessage());
		
		// Search for silence changes and correct them
		// This security mechanism probably makes the update-functionality pointless in some ways.
		// If you feel lucky test it out
		if (theUser != null)
		{
			MMUser theUserToCompare = MMUser.getUserById(theUser.getId());
			
			if (theUserToCompare != null && !theUser.equals(theUserToCompare))
			{
				MMUser.updateUserIfAlreadyAdded(theUserToCompare);
			}
		}
		
		// Broadcast Messages:
		if (aMessageEvent.isUdpProtocol() && aMessageEvent.isBroadcastMessage())
		{			
			// User connects:
			if (aMessageEvent.isConnectMessage())
			{							
				if (MMUser.addUserIfNotAlreadyAdded(theUser))
					pushMessageManagerEvent(MMMessageManagerEvent.getUserAddedInstance(theUser));
				getMessageSender().sendUDPMessage(aMessageEvent.getSenderAddress(), MMMessageType.CONNECT, MMUser.getMyself());	
			}
			// User disconnects:
			else if (aMessageEvent.isDisconnectMessage())
			{					
				if (MMUser.removeUserIfAlreadyAdded(theUser))
					pushMessageManagerEvent(MMMessageManagerEvent.getUserRemovedInstance(theUser));
			}
			// User updates:
			else if (aMessageEvent.isUpdateMessage())
			{		
				if (MMUser.updateUserIfAlreadyAdded(theUser))
					pushMessageManagerEvent(MMMessageManagerEvent.getUserUpdatedInstance(theUser));
			}
		}
		// Single Messages:
		else if (aMessageEvent.isUdpProtocol() && aMessageEvent.isSingleMessage())
		{
			// User connects:
			if (aMessageEvent.isConnectMessage())
			{		
				if (MMUser.addUserIfNotAlreadyAdded(theUser))
					pushMessageManagerEvent(MMMessageManagerEvent.getUserAddedInstance(theUser));
			}
			// User wants a meeting:
			else if (aMessageEvent.isMeetMeMessage())
			{		
				pushMessageManagerEvent(MMMessageManagerEvent.getUserWantsAMeetingInstance(theUser));
			}
			// Chat message
			else if (aMessageEvent.isMessage())
			{
				pushMessageManagerEvent(MMMessageManagerEvent.getUserMessageInstance(MMUser.getUserById(aMessageEvent.getSenderAddress().getHostAddress()), aMessageEvent.getMessage()));
			}
		}
	}
	 
	// Accessors:
	
	/**
	 * @return the messageSender
	 */
	private MMMessageSender getMessageSender()
	{
		return messageSender;
	}

	/**
	 * @param aMessageSender the messageSender to set
	 */
	private void setMessageSender(MMMessageSender aMessageSender)
	{
		if (aMessageSender == null)
			throw new IllegalArgumentException("message sender is null.");
		
		this.messageSender = aMessageSender;
	}

	/**
	 * @return the messagereceiver
	 */
	private MMMessageReceiver getMessageReceiver()
	{
		return messageReceiver;
	}

	/**
	 * @param aMessageReceiver the messagereceiver to set
	 */
	private void setMessageReceiver(MMMessageReceiver aMessageReceiver)
	{
		if (aMessageReceiver == null)
			throw new IllegalArgumentException("message receiver is null.");
		
		this.messageReceiver = aMessageReceiver;
	}

	/**
	 * @return the isStarted
	 */
	public boolean isStarted()
	{
		return isStarted;
	}
	
	/**
	 * @param isStarted the isStarted to set
	 */
	private void setStarted(boolean isStarted)
	{
		this.isStarted = isStarted;
	}
	
	/**
	 * @return the messageManagerListeners
	 */
	public Vector<MMMessageManagerListener> getMessageManagerListeners()
	{
		return messageManagerListeners;
	}
	
	/**
	 * @param messageManagerListeners the messageManagerListeners to set
	 */
	public void setMessageManagerListeners(Vector<MMMessageManagerListener> messageManagerListeners)
	{
		this.messageManagerListeners = messageManagerListeners;
	}

}
