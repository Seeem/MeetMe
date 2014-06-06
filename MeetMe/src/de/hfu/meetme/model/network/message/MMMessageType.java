/**
 * 
 */
package de.hfu.meetme.model.network.message;

/**
 * @author Simeon Sembach
 *
 */
public enum MMMessageType 
{
	CONNECT, DISCONNECT, UPDATE, MESSAGE, MEETME, UNKNOWN;
	
	// MM-API:
	
	/**
	 * Returns whether the {@link MMMessageType} is CONNECT.
	 * @return true if the {@link MMMessageType} is CONNECT, false otherwise
	 */
	public boolean isConnect()
	{
		return this == CONNECT;
	}

	/**
	 * Returns whether the {@link MMMessageType} is DISCONNECT.
	 * @return true if the {@link MMMessageType} is DISCONNECT, false otherwise
	 */
	public boolean isDisconnect()
	{
		return this == DISCONNECT;
	}
	
	/**
	 * Returns whether the {@link MMMessageType} is UPDATE.
	 * @return true if the {@link MMMessageType} is UPDATE, false otherwise
	 */
	public boolean isUpdate()
	{
		return this == UPDATE;
	}
	
	/**
	 * Returns whether the {@link MMMessageType} is MESSAGE.
	 * @return true if the {@link MMMessageType} is MESSAGE, false otherwise
	 */
	public boolean isMessage()
	{
		return this == MESSAGE;
	}
	
	/**
	 * Returns whether the {@link MMMessageType} is MEETME.
	 * @return true if the {@link MMMessageType} is MEETME, false otherwise
	 */
	public boolean isMeetMe()
	{
		return this == MEETME;
	}
	
	/**
	 * Returns whether the {@link MMMessageType} is UNKNOWN.
	 * @return true if the {@link MMMessageType} is UNKNOWN, false otherwise
	 */
	public boolean isUnknown()
	{
		return this == UNKNOWN;
	}
	
}
