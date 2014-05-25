/**
 * 
 */
package de.hfu.meetme.model.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Simeon Sembach
 *
 */
public final class MMNetworkUtil
{

	// Class-Members:
	
	/** The UDP broadcast port. */
	public static final int UDP_BROADCAST_PORT = 9000;
	
	/** The UDP port for single messages. */
	public static final int UDP_PORT = 9001;
	
	/** The TCP port. */
	public static final int TCP_PORT = 9002;
	
	/** The UDP ping message. */
	public static final String UDP_MESSAGE_PING = "";
	
	/** The UDP new in the network message */
	public static final String UDP_MESSAGE_NEW_IN_THE_NETWORK = "1";
	
	/** The UDP add me message */
	public static final String UDP_MESSAGE_ADD_ME = "2";
	
	// MM-API:
	
	/**
	 * Returns the broadcast address as {@link InetAddress}.
	 * If there is an {@link UnknownHostException} it will return null.
	 * @return the broadcast address
	 * @throws UnknownHostException
	 */
	public static InetAddress getBroadcastAddress()
	{
		try
		{
			return InetAddress.getByName("255.255.255.255");
		} 
		catch (UnknownHostException e)
		{
			return null;
		}
	}
	
	/**
	 * Returns the local host address as {@link InetAddress}.
	 * If there is an {@link UnknownHostException} it will return null.
	 * @return the local host address
	 * @throws UnknownHostException
	 */
	public static InetAddress getLocalhostAddress()
	{
		try
		{
			return InetAddress.getByName("localhost");
		}
		catch (UnknownHostException e)
		{
			return null;
		}
	}
	
	/**
	 * Returns the LAN IP-Address.
	 * If there is an {@link UnknownHostException} it will return null.
	 * @return the LAN address
	 */
	public static InetAddress getMyLanAddress()
	{
		try
		{
			return InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
		} 
		catch (UnknownHostException e)
		{
			return null;
		}
	}

	/**
	 * Returns the LAN IP-Address as String.
	 * If there is an {@link UnknownHostException} it will return null.
	 * @return the LAN address
	 */
	public static String getMyLanAddressAsString()
	{
		try
		{
			return InetAddress.getLocalHost().getHostAddress();
		} 
		catch (UnknownHostException e)
		{
			return null;
		}
	}
	
	/**
	 * Returns whether the given Internet Address is the Internet Address of the device who calls this method.
	 * @param anInetAddress the Internet Address to check
	 * @return true if the Internet Address is the Internet Address of the device who calls this method, false otherwise
	 */
	public static boolean isMyAddress(InetAddress anInetAddress)
	{
		return anInetAddress.getHostAddress().equals(getMyLanAddressAsString());
	}
	
}
