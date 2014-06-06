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

	USER_ADDED, USER_REMOVED, USER_WANTS_A_MEETING, USER_UPDATED, USER_MESSAGE;
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_ADDED.
	 * @return true if the {@link MMMessageManagerEventType} is USER_ADDED, false otherwise
	 */
	public boolean isUserAdded()
	{
		return this == USER_ADDED;
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_REMOVED.
	 * @return true if the {@link MMMessageManagerEventType} is USER_REMOVED, false otherwise
	 */
	public boolean isUserRemoved()
	{
		return this == USER_REMOVED;
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_WANTS_A_MEETING.
	 * @return true if the {@link MMMessageManagerEventType} is USER_WANTS_A_MEETING, false otherwise
	 */
	public boolean isUserWantsAMeeting()
	{
		return this == USER_WANTS_A_MEETING;
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_UPDATED.
	 * @return true if the {@link MMMessageManagerEventType} is USER_UPDATED, false otherwise
	 */
	public boolean isUserUpdated()
	{
		return this == USER_UPDATED;
	}
	
	/**
	 * Returns whether the {@link MMMessageManagerEventType} is USER_MESSAGE.
	 * @return true if the {@link MMMessageManagerEventType} is USER_MESSAGE, false otherwise
	 */
	public boolean isUserMessage()
	{
		return this == USER_MESSAGE;
	}
	
}
