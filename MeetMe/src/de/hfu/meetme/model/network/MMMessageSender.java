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
import java.net.SocketException;

import de.hfu.meetme.model.message.MMMessage;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageSender
{

	// Instance-Members:
	
	private DatagramSocket datagramSocket;
	
	// Constructor:
	
	/** */
	public MMMessageSender() throws SocketException
	{
		setDatagramSocket(new DatagramSocket());
	}
	
	// MM-API:
	
	/** */
	public void sendTCPMessage(InetAddress aInetAdress, MMMessage aMessage) throws IOException
	{
		Socket theSocket = new Socket(aInetAdress, MMNetworkUtil.TCP_PORT);
		new ObjectOutputStream(theSocket.getOutputStream()).writeObject(aMessage);		
		theSocket.close();
	}
	
	/** */
	public void sendUDPBroadcastMessage(String aMessage) throws IOException
	{
		sendUDPMessage(MMNetworkUtil.getBroadcastAddress(), MMNetworkUtil.BROADCAST_PORT, aMessage);
	}
	
	/** */
	public void sendUDPMessage(InetAddress aInetAdress, String aMessage) throws IOException
	{
		sendUDPMessage(aInetAdress, MMNetworkUtil.SINGLE_PORT, aMessage);
	}
	
	// Internals:
	
	/** */
	private void sendUDPMessage(InetAddress aInetAdress, int aPort, String aMessage) throws IOException
	{
		byte[] theByteArray = aMessage.getBytes();	
		DatagramPacket theDatagramPacket = new DatagramPacket(theByteArray, theByteArray.length, aInetAdress, aPort);
		getDatagramSocket().send(theDatagramPacket);
	}
	
	// Accessors:

	/**
	 * @return the datagramSocket
	 */
	public DatagramSocket getDatagramSocket()
	{
		return datagramSocket;
	}

	/**
	 * @param datagramSocket the datagramSocket to set
	 */
	public void setDatagramSocket(DatagramSocket datagramSocket)
	{
		this.datagramSocket = datagramSocket;
	}
	
}
