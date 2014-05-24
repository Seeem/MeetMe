/**
 * 
 */
package de.hfu.meetme.model.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import de.hfu.meetme.model.message.MMMessage;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageSender
{
	
	// MM-API:
	
	/** 
	 * Sends a {@link MMMessage} as TCP message to a given Internet address.
	 * It will just send the object, then immediately close the connection.
	 * @throws RuntimeException
	 */
	public void sendTCPMessage(InetAddress aInetAdress, MMMessage aMessage)
	{
		if (aInetAdress == null)
			throw new NullPointerException("inetAdress is null.");
		
		if(aMessage == null)
			throw new NullPointerException("message is null.");
		
		Socket theSocket = null;
		ObjectOutputStream theObjectOutputStream = null;
		
		try
		{
			theSocket = new Socket(aInetAdress, MMNetworkUtil.TCP_PORT);
			theObjectOutputStream = new ObjectOutputStream(theSocket.getOutputStream());
			theObjectOutputStream.writeObject(aMessage);
		} 
		catch (Exception anException)
		{
			throw new RuntimeException(anException.getMessage());
		}
		finally
		{
			if (theObjectOutputStream != null)
				try{theObjectOutputStream.close();} catch (IOException anIOException){anIOException.printStackTrace();}
			
			if (theSocket != null)
				try{theSocket.close();} catch (IOException anIOException){anIOException.printStackTrace();}
		}
	}
	
	/** 
	 * Sends a String as UDP broadcast message.
	 * @param aMessage the message to send
	 * @throws RuntimeException
	 */
	public void sendUDPBroadcastMessage(String aMessage)
	{
		sendUDPMessage(MMNetworkUtil.getBroadcastAddress(), MMNetworkUtil.UDP_BROADCAST_PORT, aMessage);
	}
	
	/**
	 * Sends a String as single UDP Message to a given Internet address.
	 * @param aInetAdress the receiver Internet address
	 * @param aMessage the message to send
	 * @throws RuntimeException
	 */
	public void sendUDPMessage(InetAddress aInetAdress, String aMessage)
	{
		sendUDPMessage(aInetAdress, MMNetworkUtil.UDP_PORT, aMessage);
	}
	
	// Internals:
	
	/**
	 * Sends a String as UDP Message to a given Internet address and port.
	 * @param aInetAdress the receiver Internet address
	 * @param aPort the port where the receiver is listening
	 * @param aMessage the message to send
	 * @throws RuntimeException
	 */
	private void sendUDPMessage(InetAddress aInetAdress, int aPort, String aMessage)
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
			udpSocket = new DatagramSocket();	
			byte[] theByteArray = aMessage.getBytes();	
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
