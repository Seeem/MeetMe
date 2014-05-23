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
	
	/** The isSending boolean. */
	private boolean isStarted = false;
	
	/** The UDP Socket for sending UDP messages. */
	private DatagramSocket udpSocket;
	
	// MM-API:
	
	/**
	 * Starts the message sender.
	 * If the message sender is already started nothing will happen.
	 */
	public void startSender() throws SocketException
	{
		if (isStarted()) return;
		setUdpSocket(new DatagramSocket());
		setStarted(true);
	}
	
	/**
	 * Stops the message sender.
	 * If the message sender is already stopped or was never started nothing will happen.
	 */
	public void stopSender() throws SocketException
	{
		if (!isStarted()) return;
		udpSocket.close();
		setStarted(false);
	}
	
	/** 
	 * Sends a {@link MMMessage} as TCP message to a given Internet address.
	 * It will just send the object, then immediately close the connection.
	 */
	public void sendTCPMessage(InetAddress aInetAdress, MMMessage aMessage) throws IOException
	{
		if(!isStarted())
			throw new IllegalStateException("sender is not started.");
		
		if (aInetAdress == null)
			throw new NullPointerException("inetAdress is null.");
		
		if(aMessage == null)
			throw new NullPointerException("message is null.");
		
		Socket theSocket = new Socket(aInetAdress, MMNetworkUtil.TCP_PORT);
		new ObjectOutputStream(theSocket.getOutputStream()).writeObject(aMessage);		
		theSocket.close();
	}
	
	/** 
	 * Sends a String as UDP broadcast message.
	 * @param aMessage the message to send
	 * @throws IOException
	 */
	public void sendUDPBroadcastMessage(String aMessage) throws IOException
	{
		sendUDPMessage(MMNetworkUtil.getBroadcastAddress(), MMNetworkUtil.UDP_BROADCAST_PORT, aMessage);
	}
	
	/**
	 * Sends a String as single UDP Message to a given Internet address.
	 * @param aInetAdress the receiver Internet address
	 * @param aMessage the message to send
	 * @throws IOException
	 */
	public void sendUDPMessage(InetAddress aInetAdress, String aMessage) throws IOException
	{
		sendUDPMessage(aInetAdress, MMNetworkUtil.UDP_PORT, aMessage);
	}
	
	// Internals:
	
	/**
	 * Sends a String as UDP Message to a given Internet address and port.
	 * @param aInetAdress the receiver Internet address
	 * @param aPort the port where the receiver is listening
	 * @param aMessage the message to send
	 * @throws IOException
	 */
	private void sendUDPMessage(InetAddress aInetAdress, int aPort, String aMessage) throws IOException
	{
		if(!isStarted())
			throw new IllegalStateException("sender is not started.");
		
		if (aInetAdress == null)
			throw new NullPointerException("inetAdress is null.");
		
		if(aMessage == null)
			throw new NullPointerException("message is null.");
		
		if(aPort < 0)
			throw new IllegalArgumentException("port is smaller than 0.");
		
		byte[] theByteArray = aMessage.getBytes();	
		DatagramPacket theDatagramPacket = new DatagramPacket(theByteArray, theByteArray.length, aInetAdress, aPort);
		getUdpSocket().send(theDatagramPacket);
	}

	
	// Accessors:

	/**
	 * @return the udpSocket
	 */
	private DatagramSocket getUdpSocket()
	{
		return udpSocket;
	}

	/**
	 * @param udpSocket the udpSocket to set
	 */
	private void setUdpSocket(DatagramSocket udpSocket)
	{
		this.udpSocket = udpSocket;
	}

	/**
	 * @return the isStarted
	 */
	public boolean isStarted()
	{
		return isStarted;
	}

	/**
	 * @param isStarted the isStarted to set
	 */
	private void setStarted(boolean isStarted)
	{
		this.isStarted = isStarted;
	}

}
