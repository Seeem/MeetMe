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
	 * @return the broadcast address
	 * @throws UnknownHostException
	 */
	public static InetAddress getBroadcastAddress() throws UnknownHostException
	{
		return InetAddress.getByName("255.255.255.255");
	}
	
	/**
	 * Returns the local host address as {@link InetAddress}.
	 * @return the local host address
	 * @throws UnknownHostException
	 */
	public static InetAddress getLocalhostAddress() throws UnknownHostException
	{
		return InetAddress.getByName("localhost");
	}
	
	/**
	 * Returns the LAN IP-Address as String
	 * @return the LAN address
	 * @throws UnknownHostException
	 */
	public static String getLanAddressAsString() throws UnknownHostException
	{
		return InetAddress.getLocalHost().getHostAddress();
	}
	
}
