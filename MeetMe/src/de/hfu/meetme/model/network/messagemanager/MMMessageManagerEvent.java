/**
 * 
 */
package de.hfu.meetme.model.network.messagemanager;

import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageManagerEvent
{

	// Instance-Members:
	
	/** The {@link MMMessageManagerEventType}. */
	private final MMMessageManagerEventType messageManagerEventType;
	
	/** The {@link MMUser}. */
	private final MMUser user;
	
	/** */
	private final String message;
	
	// Constructor:
	
	/**
	 * Creates a new {@link MMMessageManagerEvent} instance.
	 * @param aMessageManagerEventType the {@link MMMessageManagerEventType} to set
	 * @param anUser the {@link MMUser} to set
	 */
	private MMMessageManagerEvent(MMMessageManagerEventType aMessageManagerEventType, MMUser anUser, String aMessage)
	{
		if (aMessageManagerEventType == null)
			throw new NullPointerException("message manager event type is null.");
		
		if (anUser == null)
			throw new NullPointerException("user is null.");
		
		this.messageManagerEventType = aMessageManagerEventType;
		this.user = anUser;
		this.message = aMessage;
	}
	
	// Factory:
	
	/**
	 * Gets a new {@link MMMessageManagerEvent} instance if possible,
	 * otherwise it will return null.
	 * @param aMessageManagerEventType the {@link MMMessageManagerEventType} to set
	 * @param anUser the {@link MMUser} to set
	 * @return a new {@link MMMessageManagerEvent} instance if possible, null otherwise
	 */
	private static MMMessageManagerEvent getInstance(MMMessageManagerEventType aMessageManagerEventType, MMUser anUser, String aMessage)
	{
		try
		{
			return new MMMessageManagerEvent(aMessageManagerEventType, anUser, aMessage);
		} 
		catch (NullPointerException anException)
		{
			return null;
		}
	}
	
	/**
	 * Gets a new {@link MMMessageManagerEvent} instance if possible,
	 * otherwise it will return null. The {@link MMMessageManagerEventType} will
	 * be USER_ADDED.
	 * @param anUser the {@link MMUser} to set
	 * @return a new {@link MMMessageManagerEvent} instance if possible, null otherwise
	 */
	public static MMMessageManagerEvent getUserAddedInstance(MMUser anUser)
	{
		return getInstance(MMMessageManagerEventType.USER_ADDED, anUser, null);
	}
	
	/**
	 * Gets a new {@link MMMessageManagerEvent} instance if possible,
	 * otherwise it will return null. The {@link MMMessageManagerEventType} will
	 * be USER_REMOVED.
	 * @param anUser the {@link MMUser} to set
	 * @return a new {@link MMMessageManagerEvent} instance if possible, null otherwise
	 */
	public static MMMessageManagerEvent getUserRemovedInstance(MMUser anUser)
	{
		return getInstance(MMMessageManagerEventType.USER_REMOVED, anUser, null);
	}

	/**
	 * Gets a new {@link MMMessageManagerEvent} instance if possible,
	 * otherwise it will return null. The {@link MMMessageManagerEventType} will
	 * be USER_WANTS_A_MEETING.
	 * @param anUser the {@link MMUser} to set
	 * @return a new {@link MMMessageManagerEvent} instance if possible, null otherwise
	 */
	public static MMMessageManagerEvent getUserWantsAMeetingInstance(MMUser anUser)
	{
		return getInstance(MMMessageManagerEventType.USER_WANTS_A_MEETING, anUser, null);
	}
	
	/**
	 * Gets a new {@link MMMessageManagerEvent} instance if possible,
	 * otherwise it will return null. The {@link MMMessageManagerEventType} will
	 * be USER_UPDATED.
	 * @param anUser the {@link MMUser} to set
	 * @return a new {@link MMMessageManagerEvent} instance if possible, null otherwise
	 */
	public static MMMessageManagerEvent getUserUpdatedInstance(MMUser anUser)
	{
		return getInstance(MMMessageManagerEventType.USER_UPDATED, anUser, null);
	}
	
	/**
	 * Gets a new {@link MMMessageManagerEvent} instance if possible,
	 * otherwise it will return null. The {@link MMMessageManagerEventType} will
	 * be USER_MESSAGE.
	 * @param anUser the {@link MMUser} to set
	 * @return a new {@link MMMessageManagerEvent} instance if possible, null otherwise
	 */
	public static MMMessageManagerEvent getUserMessageInstance(MMUser anUser, String anString)
	{
		return getInstance(MMMessageManagerEventType.USER_MESSAGE, anUser, anString);
	}
	
	// Is-Methods:
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_ADDED.
	 * @return true if the {@link MMMessageManagerEventType} is USER_ADDED, false otherwise
	 */
	public boolean isUserAdded()
	{
		return getMessageManagerEventType().isUserAdded();
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_REMOVED.
	 * @return true if the {@link MMMessageManagerEventType} is USER_REMOVED, false otherwise
	 */
	public boolean isUserRemoved()
	{
		return getMessageManagerEventType().isUserRemoved();
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_WANTS_A_MEETING.
	 * @return true if the {@link MMMessageManagerEventType} is USER_WANTS_A_MEETING, false otherwise
	 */
	public boolean isUserWantsAMeeting()
	{
		return getMessageManagerEventType().isUserWantsAMeeting();
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_UPDATED.
	 * @return true if the {@link MMMessageManagerEventType} is USER_UPDATED, false otherwise
	 */
	public boolean isUserUpdate()
	{
		return getMessageManagerEventType().isUserUpdated();
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_MESSAGE.
	 * @return true if the {@link MMMessageManagerEventType} is USER_MESSAGE, false otherwise
	 */
	public boolean isUserMessage()
	{
		return getMessageManagerEventType().isUserMessage();
	}
	
	// Accessors:
	
	/**
	 * @return the messageManagerEventType
	 */
	public MMMessageManagerEventType getMessageManagerEventType()
	{
		return messageManagerEventType;
	}

	/**
	 * @return the user
	 */
	public MMUser getUser()
	{
		return user;
	}

	
	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}
	
}
