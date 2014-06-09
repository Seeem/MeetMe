/**
 * 
 */
package de.hfu.meetme.model.network.networktask;

/**
 * @author Simeon Sembach
 *
 */
public enum MMNetworkTaskType
{
	
	START_LISTENING, STOP_LISTENING, REFRESH_USERLIST, MEET_ME, UPDATE, CHAT_MESSAGE;
	
	// MM-API:
	
	/**
	 * Returns whether the {@link MMNetworkTaskType} is START_LISTENING.
	 * @return true if the {@link MMNetworkTaskType} is START_LISTENING, false otherwise
	 */
	public boolean isStartListening()
	{
		return this == START_LISTENING;
	}
	
	/**
	 * Returns whether the {@link MMNetworkTaskType} is STOP_LISTENING.
	 * @return true if the {@link MMNetworkTaskType} is STOP_LISTENING, false otherwise
	 */
	public boolean isStopListening()
	{
		return this == STOP_LISTENING;
	}
	
	/**
	 * Returns whether the {@link MMNetworkTaskType} is REFRESH_USERLIST.
	 * @return true if the {@link MMNetworkTaskType} is REFRESH_USERLIST, false otherwise
	 */
	public boolean isRefreshUserlist()
	{
		return this == REFRESH_USERLIST;
	}
	
	/**
	 * Returns whether the {@link MMNetworkTaskType} is MEET_ME.
	 * @return true if the {@link MMNetworkTaskType} is MEET_ME, false otherwise
	 */
	public boolean isMeetMe()
	{
		return this == MEET_ME;
	}
	
	/**
	 * Returns whether the {@link MMNetworkTaskType} is UPDATE.
	 * @return true if the {@link MMNetworkTaskType} is UPDATE, false otherwise
	 */
	public boolean isUpdate()
	{
		return this == UPDATE;
	}
	
	/**
	 * Returns whether the {@link MMNetworkTaskType} is CHAT_MESSAGE.
	 * @return true if the {@link MMNetworkTaskType} is CHAT_MESSAGE, false otherwise
	 */
	public boolean isChatMessage()
	{
		return this == CHAT_MESSAGE;
	}

}
