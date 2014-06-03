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
	
	/** */
	private final MMMessageManagerEventType messageManagerEventType;
	
	/** */
	private final MMUser user;
	
	// Constructor:
	
	/**
	 * 
	 */
	private MMMessageManagerEvent(MMMessageManagerEventType aMessageManagerEventType, MMUser anUser)
	{
		if (aMessageManagerEventType == null)
			throw new NullPointerException("message manager event type is null.");
		
		if (anUser == null)
			throw new NullPointerException("user is null.");
		
		this.messageManagerEventType = aMessageManagerEventType;
		this.user = anUser;
	}
	
	// Factory:
	
	/** */
	private static MMMessageManagerEvent getInstance(MMMessageManagerEventType aMessageManagerEventType, MMUser anUser)
	{
		try
		{
			return new MMMessageManagerEvent(aMessageManagerEventType, anUser);
		} 
		catch (NullPointerException anException)
		{
			return null;
		}
	}
	
	/** */
	public static MMMessageManagerEvent getUserAddedInstance(MMUser anUser)
	{
		return getInstance(MMMessageManagerEventType.USER_ADDED, anUser);
	}
	
	/** */
	public static MMMessageManagerEvent getUserRemovedInstance(MMUser anUser)
	{
		return getInstance(MMMessageManagerEventType.USER_REMOVED, anUser);
	}

	/** */
	public static MMMessageManagerEvent getUserWantsAMeetingInstance(MMUser anUser)
	{
		return getInstance(MMMessageManagerEventType.USER_WANTS_A_MEETING, anUser);
	}
	
	// Is-Methods:
	
	/** */
	public boolean isUserAdded()
	{
		return getMessageManagerEventType().isUserAdded();
	}
	
	/** */
	public boolean isUserRemoved()
	{
		return getMessageManagerEventType().isUserRemoved();
	}
	
	/** */
	public boolean isUserWantsAMeeting()
	{
		return getMessageManagerEventType().isUserWantsAMeeting();
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
	
}
