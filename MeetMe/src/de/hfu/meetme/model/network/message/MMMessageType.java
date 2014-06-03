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
	
	public boolean isConnect()
	{
		return this == CONNECT;
	}
	
	public boolean isDisconnect()
	{
		return this == DISCONNECT;
	}
	
	public boolean isUpdate()
	{
		return this == UPDATE;
	}
	
	public boolean isMessage()
	{
		return this == MESSAGE;
	}
	
	public boolean isMeetMe()
	{
		return this == MEETME;
	}
	
	public boolean isUnknown()
	{
		return this == UNKNOWN;
	}
	
}
