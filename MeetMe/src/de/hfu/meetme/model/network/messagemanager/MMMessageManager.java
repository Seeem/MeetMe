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
	
	/** */
	private MMMessageSender messageSender;
	
	/** */
	private MMMessageReceiver messageReceiver;
	
	/** */
	private boolean isStarted = false;
	
	/** */
	private Vector<MMMessageManagerListener> messageManagerListeners;
	
	// Constructor:
	
	/** */
	public MMMessageManager()
	{
		setMessageSender(new MMMessageSender());
		setMessageReceiver(new MMMessageReceiver(MMNetworkUtil.UDP_BROADCAST_PORT, MMNetworkUtil.UDP_PORT));
		setMessageManagerListeners(new Vector<MMMessageManagerListener>());
	}
	
	// MM-API:
	
	/** */
	public void addMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		if (aMessageManagerListener == null)
			throw new NullPointerException("message manager listener is null.");
		
		getMessageManagerListeners().add(aMessageManagerListener);
	}
	
	/** */
	public void removeMessageManagerListener(MMMessageManagerListener aMessageManagerListener)
	{
		if (aMessageManagerListener == null)
			throw new NullPointerException("message manager listener is null.");
		
		getMessageManagerListeners().remove(aMessageManagerListener);
	}
	
	/** */
	public void refreshUsers()
	{
		if (!isStarted() || MMUser.getMyself() == null) return;
		MMUser.removeAllUsers();
		getMessageSender().sendUDPBroadcastMessage(MMMessageType.CONNECT, MMUser.getMyself());	
	}
	
	/** */
	public void startListening()
	{
		if (MMUser.getMyself() == null)
			throw new IllegalStateException("myself is null.");
		
		if (isStarted()) return;
		getMessageReceiver().addMessageListener(this);
		getMessageReceiver().startReceiver();
		setStarted(true);
	}
	
	/** */
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
	
	/** */
	public void sendMeetMeMessage(InetAddress anInetAddress)
	{
		if (MMUser.getMyself() == null)
			throw new IllegalStateException("myself is null.");
		
		new MMMessageSender().sendUDPMessage(anInetAddress, MMMessageType.MEETME, MMUser.getMyself());	
	}
	
	// Internals:
	
	/** */
	private void pushMessageManagerEvents(MMMessageManagerEvent aMessageManagerEvent)
	{
		for (MMMessageManagerListener aMessageManagerListener : getMessageManagerListeners())
		{
			aMessageManagerListener.managerEventPerformed(aMessageManagerEvent);
		}			
	}
	
	// Implementors:
	
	/** */
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{
		if (aMessageEvent == null)
			throw new NullPointerException("message event is null.");
		
		// Filter Messages from this device and unknown messages:
		if (aMessageEvent.isFromMe() || aMessageEvent.isUnknownMessage()) return;
		
		// Broadcast Messages:
		if (aMessageEvent.isUdpProtocol() && aMessageEvent.isBroadcastMessage())
		{
			// User connects:
			if (aMessageEvent.isConnectMessage())
			{
				MMUser theUser = MMUser.valueOf(aMessageEvent.getMessage());			
				if (MMUser.addUserIfNotAlreadyAdded(theUser))
					pushMessageManagerEvents(MMMessageManagerEvent.getUserAddedInstance(theUser));
				getMessageSender().sendUDPMessage(aMessageEvent.getSenderAddress(), MMMessageType.CONNECT, MMUser.getMyself());	
			}
			// User disconnects:
			else if (aMessageEvent.isDisconnectMessage())
			{
				MMUser theUser = MMUser.valueOf(aMessageEvent.getMessage());	
				if (MMUser.removeUserIfAlreadyAdded(theUser))
					pushMessageManagerEvents(MMMessageManagerEvent.getUserRemovedInstance(theUser));
			}
		}
		// Single Messages:
		else if (aMessageEvent.isUdpProtocol() && aMessageEvent.isSingleMessage())
		{
			// User connects:
			if (aMessageEvent.isConnectMessage())
			{
				MMUser theUser = MMUser.valueOf(aMessageEvent.getMessage());			
				if (MMUser.addUserIfNotAlreadyAdded(theUser))
					pushMessageManagerEvents(MMMessageManagerEvent.getUserAddedInstance(theUser));
			}
			// User wants a meeting:
			if (aMessageEvent.isMeetMeMessage())
			{
				MMUser theUser = MMUser.valueOf(aMessageEvent.getMessage());		
				pushMessageManagerEvents(MMMessageManagerEvent.getUserWantsAMeetingInstance(theUser));
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
