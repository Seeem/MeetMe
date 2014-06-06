/**
 * 
 */
package de.hfu.meetme.model.network.message;


/**
 * @author Simeon Sembach
 *
 */
public enum MMMessageProtocol
{
	TCP, UDP;
	
	// MM-API:
	
	/**
	 * Returns whether the {@link MMMessageProtocol} is TCP.
	 * @return true if the {@link MMMessageProtocol} is TCP, false otherwise
	 */
	public boolean isTcp()
	{
		return this == TCP;
	}

	/**
	 * Returns whether the {@link MMMessageProtocol} is UDP.
	 * @return true if the {@link MMMessageProtocol} is UDP, false otherwise
	 */
	public boolean isUdp()
	{
		return this == UDP;
	}
	
}
