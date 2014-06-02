/**
 * 
 */
package de.hfu.meetme.model.network;

import java.net.InetAddress;

import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.receiver.MMMessageReceiver;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageManager implements MMMessageListener
{
	
	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender;
	
	/** */
	private MMMessageReceiver messageReceiver;
	
	/** */
	private boolean isStarted = false;
	
	/** */
	private MMNetworkTask networkTask;
	
	// Constructor:
	
	/** */
	public MMMessageManager(MMNetworkTask aNetworkTask)
	{
		setMessageSender(new MMMessageSender());
		setMessageReceiver(new MMMessageReceiver(MMNetworkUtil.UDP_BROADCAST_PORT, MMNetworkUtil.UDP_PORT));
		setNetworkTask(aNetworkTask);
	}
	
	// MM-API:
	
	/** */
	public void refreshUsers()
	{
		if (!isStarted() || MMUser.getMyself() == null) return;
		MMUser.removeAllUsers();
		getNetworkTask().updateUserListUi();
		getMessageSender().sendUDPBroadcastMessage(MMMessageType.CONNECT, MMUser.getMyself());	
	}
	
	/** */
	public void startListening()
	{
		if (isStarted() || MMUser.getMyself() == null) return;
		getMessageReceiver().addMessageListener(this);
		getMessageReceiver().startReceiver();
		setStarted(true);
	}
	
	/** */
	public void stopListening()
	{
		if (!isStarted() || MMUser.getMyself() == null) return;
		getMessageSender().sendUDPBroadcastMessage(MMMessageType.DISCONNECT, MMUser.getMyself());
		getMessageReceiver().removeMessageListener(this);
		getMessageReceiver().stopReceiver();	
		setStarted(false);
	}
	
	/** */
	public void sendMeetMeMessage(InetAddress anInetAddress)
	{
		if (MMUser.getMyself() == null) return;
		new MMMessageSender().sendUDPMessage(anInetAddress, MMMessageType.MEETME, MMUser.getMyself());	
	}
	
	// Implementors:
	
	/** */
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{	
		// Filter Messages from this device and unknown messages:
		if (aMessageEvent.isFromMe() || aMessageEvent.isUnknownMessage()) return;
		
		// Broadcast Messages:
		if (aMessageEvent.isUdpProtocol() && aMessageEvent.isBroadcastMessage())
		{
			// User connects:
			if (aMessageEvent.isConnectMessage())
			{
				MMUser.addUserIfNotAlreadyAdded(MMUser.valueOf(aMessageEvent.getMessage()));			
				getMessageSender().sendUDPMessage(aMessageEvent.getSenderAddress(), MMMessageType.CONNECT, MMUser.getMyself());	
			}
			// User disconnects:
			else if (aMessageEvent.isDisconnectMessage())
			{
				MMUser.removeUserIfAlreadyAdded(MMUser.valueOf(aMessageEvent.getMessage()));
			}
		}
		// Single Messages:
		else if (aMessageEvent.isUdpProtocol() && aMessageEvent.isSingleMessage())
		{
			// User connects:
			if (aMessageEvent.isConnectMessage())
			{
				MMUser.addUserIfNotAlreadyAdded(MMUser.valueOf(aMessageEvent.getMessage()));
			}
			// User wants a meeting:
			if (aMessageEvent.isMeetMeMessage())
			{		
				getNetworkTask().addNotification(MMUser.valueOf(aMessageEvent.getMessage()));
			}						
		}
		
		// Update user-list:
		getNetworkTask().updateUserListUi();
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
	 * @return the networkTask
	 */
	public MMNetworkTask getNetworkTask()
	{
		return networkTask;
	}

	/**
	 * @param aNetworkTask the networkTask to set
	 */
	public void setNetworkTask(MMNetworkTask aNetworkTask)
	{
		if (aNetworkTask == null)
			throw new NullPointerException("network task is null.");
		
		this.networkTask = aNetworkTask;
	}
	
}
