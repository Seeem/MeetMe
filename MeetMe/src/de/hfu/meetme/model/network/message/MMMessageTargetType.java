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
	
	// MM-API:
	
	/**
	 * Returns whether the {@link MMMessageTargetType} is BROADCAST.
	 * @return true if the {@link MMMessageTargetType} is BROADCAST, false otherwise
	 */
	public boolean isBroadcast()
	{
		return this == BROADCAST;
	}
	
	/**
	 * Returns whether the {@link MMMessageTargetType} is SINGLE.
	 * @return true if the {@link MMMessageTargetType} is SINGLE, false otherwise
	 */
	public boolean isSingle()
	{
		return this == SINGLE;
	}
	
}
