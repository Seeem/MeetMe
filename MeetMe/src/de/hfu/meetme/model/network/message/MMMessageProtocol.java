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
	
	public boolean isTcp()
	{
		return this == TCP;
	}

	public boolean isUdp()
	{
		return this == UDP;
	}
	
}
