/**
 * 
 */
package de.hfu.meetme.model;

import de.hfu.meetme.model.network.MMMessageEvent;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageProtocol;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageTargetType;
import de.hfu.meetme.model.network.MMMessageType;
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.views.UserListFragment;

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
	private UserListFragment userListFragment;
	
	// Constructor:
	
	/** */
	public MMMessageManager()
	{
		this(null);
	}
	
	/** */
	public MMMessageManager(UserListFragment anUserListFragment)
	{
		setMessageSender(new MMMessageSender());
		setMessageReceiver(new MMMessageReceiver());
		setUserListFragment(anUserListFragment);
	}
	
	// MM-API:
	
	/** */
	public void refreshUsers()
	{
		MMUser.removeAllUsers();
		if (getUserListFragment() != null)
			getUserListFragment().updateView();
		if (MMUser.getMyself() != null)
			getMessageSender().sendUDPBroadcastMessage(MMMessageType.CONNECT, MMUser.getMyself());	
	}
	
	/** */
	public void startListening()
	{
		if (isStarted()) return;
		getMessageReceiver().addMessageListener(this);
		getMessageReceiver().startReceiver();
		setStarted(true);
	}
	
	/** */
	public void stopListening()
	{
		if (!isStarted()) return;
		getMessageReceiver().removeMessageListener(this);
		getMessageReceiver().stopReceiver();
		setStarted(false);
	}
	
	// Implementors:
	
	/** */
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{	
		if (MMNetworkUtil.isMyLanAddress(aMessageEvent.getSenderAddress())) return;
	
		if (aMessageEvent.getMessageProtocol() == MMMessageProtocol.UDP &&
			aMessageEvent.getMessageTargetType() == MMMessageTargetType.BROADCAST &&
			aMessageEvent.getMessageType() == MMMessageType.CONNECT &&
			MMUser.getMyself() != null)
		{
			MMUser.addUserIfNotAlreadyAdded(MMUser.valueOf(aMessageEvent.getMessageAsString()));			
			getMessageSender().sendUDPMessage(aMessageEvent.getSenderAddress(), MMMessageType.CONNECT, MMUser.getMyself());

			if (getUserListFragment() != null)
				getUserListFragment().updateView();			
		}
		else if (aMessageEvent.getMessageTargetType() == MMMessageTargetType.SINGLE && 
				aMessageEvent.getMessageType() == MMMessageType.CONNECT)
		{
			MMUser.addUserIfNotAlreadyAdded(MMUser.valueOf(aMessageEvent.getMessageAsString()));
			
			if (getUserListFragment() != null)
				getUserListFragment().updateView();								
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
	 * @return the userListFragment
	 */
	private UserListFragment getUserListFragment()
	{
		return userListFragment;
	}
	
	/**
	 * @param userListFragment the userListFragment to set
	 */
	private void setUserListFragment(UserListFragment userListFragment)
	{
		this.userListFragment = userListFragment;
	}

}
