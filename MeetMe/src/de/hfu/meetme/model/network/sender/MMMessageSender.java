/**
 * 
 */
package de.hfu.meetme.model.network.sender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.model.network.message.MMMessageType;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageSender
{
	
	// MM-API:
	
	/** 
	 * Sends a String as UDP broadcast message.
	 * @param aMessage the message to send
	 * @throws RuntimeException
	 */
	public void sendUDPBroadcastMessage(String aMessage)
	{
		sendUDPMessage(MMNetworkUtil.getBroadcastAddress(), MMNetworkUtil.UDP_BROADCAST_PORT, aMessage, MMMessageType.MESSAGE);
	}
	
	/**
	 * Sends a String with a specific {@link MMMessageType} as UDP broadcast message.
	 * @param aMessageType the message type
	 * @param aMessage the message to send
	 */
	public void sendUDPBroadcastMessage(MMMessageType aMessageType, String aMessage)
	{
		sendUDPMessage(MMNetworkUtil.getBroadcastAddress(), MMNetworkUtil.UDP_BROADCAST_PORT, aMessage, aMessageType);
	}
		
	/**
	 * 
	 * @param aMessageType
	 * @param anUser
	 */
	public void sendUDPBroadcastMessage(MMMessageType aMessageType, MMUser anUser)
	{
		sendUDPMessage(MMNetworkUtil.getBroadcastAddress(), MMNetworkUtil.UDP_BROADCAST_PORT, anUser.toUdpMessage(), aMessageType);
	}
	
	/**
	 * Sends a String as single UDP Message to a given Internet address.
	 * @param anInetAdress the receiver Internet address
	 * @param aMessage the message to send
	 * @throws RuntimeException
	 */
	public void sendUDPMessage(MMUser anUser, String aMessage)
	{
		sendUDPMessage(anUser.getIpAddress(), MMNetworkUtil.UDP_PORT, aMessage, MMMessageType.MESSAGE);
	}
	
	/**
	 * 
	 * @param aMessageType
	 * @param anUser
	 */
	public void sendUDPMessage(MMUser anTargetUser, MMMessageType aMessageType, MMUser anUser)
	{
		sendUDPMessage(anTargetUser.getIpAddress(), MMNetworkUtil.UDP_PORT, anUser.toUdpMessage(), aMessageType);
	}
	
	// Internals:
	
	/**
	 * Sends a String as UDP Message to a given Internet address and port.
	 * @param aInetAdress the receiver Internet address
	 * @param aPort the port where the receiver is listening
	 * @param aMessage the message to send
	 * @throws RuntimeException
	 */
	private void sendUDPMessage(InetAddress aInetAdress, int aPort, String aMessage, MMMessageType aMessageType)
	{
		if (aInetAdress == null)
			throw new NullPointerException("inetAdress is null.");
		
		if(aMessage == null)
			throw new NullPointerException("message is null.");
		
		if(aPort < 0)
			throw new IllegalArgumentException("port is smaller than 0.");
			
		DatagramSocket udpSocket = null;
		
		try
		{
			String theMessage = aMessageType.toString()+":"+aMessage;
			udpSocket = new DatagramSocket();	
			byte[] theByteArray = theMessage.getBytes();	
			DatagramPacket theDatagramPacket = new DatagramPacket(theByteArray, theByteArray.length, aInetAdress, aPort);			
			udpSocket.send(theDatagramPacket);
		}
		catch (Exception anException)
		{
			throw new RuntimeException(anException.getMessage());
		}
		finally
		{
			if (udpSocket != null) udpSocket.close();
		}		
	}
	
}
