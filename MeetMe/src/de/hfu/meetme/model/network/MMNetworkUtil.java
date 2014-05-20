/**
 * 
 */
package de.hfu.meetme.model.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.hfu.meetme.model.message.MMMessage;

/**
 * @author Simeon Sembach
 *
 */
public final class MMNetworkUtil
{

	public static final int BROADCAST_PORT = 9000;
	
	public static final int SINGLE_PORT = 9001;
	
	public static final int TCP_PORT = 9002;
	
	public static final String NEW_IN_THE_NETWORK = "1";
	
	public static final String ADD_ME = "2";
	
	
	public static InetAddress getBroadcastAddress() throws UnknownHostException
	{
		return InetAddress.getByName("255.255.255.255");
	}
	
	public static DatagramPacket messageToDatagramPacket(MMMessage aMessage, InetAddress aInetAddress, int aPort) throws IOException
	{
		byte[] theByteArray = messageToByteArray(aMessage);
		
		System.out.println(theByteArray.length);
		
		return new DatagramPacket(theByteArray, theByteArray.length, aInetAddress, aPort);
	}
	
	private static byte[] messageToByteArray(MMMessage aMessage) throws IOException
	{
		ByteArrayOutputStream theOutputStream = new ByteArrayOutputStream();		
		ObjectOutputStream theObjectOutputStream = new ObjectOutputStream(theOutputStream);
		
		theObjectOutputStream.writeObject(aMessage);
		theObjectOutputStream.close();
		
		return theOutputStream.toByteArray();
	}
	
}
