/**
 * 
 */
package de.hfu.meetme.model.network.message;

/**
 * @author Simeon Sembach
 *
 */
public enum MMMessageTargetType
{
	BROADCAST, SINGLE;
	
	public boolean isBroadcast()
	{
		return this == BROADCAST;
	}
	
	public boolean isSingle()
	{
		return this == SINGLE;
	}
	
}
