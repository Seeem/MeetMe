/**
 * 
 */
package de.hfu.meetme.model.network.messagemanager;

/**
 * @author Simeon Sembach
 *
 */
public enum MMMessageManagerEventType
{

	USER_ADDED, USER_REMOVED, USER_WANTS_A_MEETING, USER_UPDATED;
	
	/** */
	public boolean isUserAdded()
	{
		return this == USER_ADDED;
	}
	
	/** */
	public boolean isUserRemoved()
	{
		return this == USER_REMOVED;
	}
	
	/** */
	public boolean isUserWantsAMeeting()
	{
		return this == USER_WANTS_A_MEETING;
	}
	
	/** */
	public boolean isUserUpdated()
	{
		return this == USER_UPDATED;
	}
	
}
