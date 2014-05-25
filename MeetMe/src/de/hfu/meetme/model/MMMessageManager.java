/**
 * 
 */
package de.hfu.meetme.model;

import de.hfu.meetme.model.message.MMUserMessage;
import de.hfu.meetme.model.network.MMMessageEvent;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageProtocol;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageType;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageManager implements MMMessageListener
{
	
	/** */
	private MMMessageSender messageSender;
	
	/** */
	private MMMessageReceiver messageReceiver;
	
	/** */
	private long refreshingTime;
	
	// Constructor:
	
	/** */
	public MMMessageManager()
	{
		this(5000);
	}
	
	/** */
	public MMMessageManager(long aRefreshingTime)
	{
		setMessageSender(new MMMessageSender());
		setMessageReceiver(new MMMessageReceiver());
		setRefreshingTime(aRefreshingTime);
	}
	
	// MM-API:
	
	/** */
	public void refreshUsers()
	{
		MMUser.removeAllUsers();
		getMessageSender().sendUDPBroadcastMessage(MMNetworkUtil.UDP_MESSAGE_PING);	
		try {Thread.sleep(getRefreshingTime());} catch (InterruptedException e){e.printStackTrace();}
	}
	
	/** */
	public void startListening()
	{
		getMessageReceiver().addMessageListener(this);
		getMessageReceiver().startReceiver();
	}
	
	/** */
	public void stopListening()
	{
		getMessageReceiver().removeMessageListener(this);
		getMessageReceiver().stopReceiver();
	}
	
	// Implementors:
	
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{
		if (MMNetworkUtil.isMyAddress(aMessageEvent.getSenderAddress())) return;
	
		if (aMessageEvent.getMessageProtocol() == MMMessageProtocol.UDP)
		{
			if (aMessageEvent.getMessageType() == MMMessageType.BROADCAST)
			{
				if (aMessageEvent.getMessageAsString().equals(MMNetworkUtil.UDP_MESSAGE_PING))
				{
					if (MMUser.getMyself() != null)
						getMessageSender().sendTCPMessage(aMessageEvent.getSenderAddress(), new MMUserMessage(MMUser.getMyself()));	
				}
			}
		}
		else if (aMessageEvent.getMessageProtocol() == MMMessageProtocol.TCP)
		{
			if (aMessageEvent.getMessage() instanceof MMUserMessage)						
				MMUser.addUserIfNotAlreadyAdded(((MMUserMessage) aMessageEvent.getMessage()).getUser());		
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
	 * @return the refreshingTime
	 */
	public long getRefreshingTime()
	{
		return refreshingTime;
	}

	
	/**
	 * @param refreshingTime the refreshingTime to set
	 */
	public void setRefreshingTime(long refreshingTime)
	{
		this.refreshingTime = refreshingTime;
	}

}
