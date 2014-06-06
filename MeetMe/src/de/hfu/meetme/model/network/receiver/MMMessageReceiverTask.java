/**
 * 
 */
package de.hfu.meetme.model.network.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import de.hfu.meetme.model.network.message.MMMessageTargetType;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageReceiverTask extends Thread
{

	// Instance-Members:
	
	/** The {@link MMMessageReceiver} for message handling. */
	private MMMessageReceiver messageReceiver;
	
	/** The {@link DatagramSocket} which receives the data. */
	private DatagramSocket datagramSocket;
	
	/** The port on which the socket is bound. */
	private int datagramPort;
	
	/** The {@link MMMessageTargetType} (BROADCAST or SINGLE). */
	private MMMessageTargetType messageTargetType;
	
	// Class-Members:
	
	/** The maximum number of bytes an UDP receiver can receive in one UDP message. */
	private static final int MAXIMUM_UDP_SIZE_IN_BYTES = 512;
	
	// Constructor:
	
	/**
	 * Creates new {@link MMMessageReceiverTask} instance.
	 * 
	 * @param aMessageReceiver the {@link MMMessageReceiver}
	 * @param aMessageTargetType the {@link MMMessageTargetType}
	 * @param aPort the Port
	 */
	public MMMessageReceiverTask(MMMessageReceiver aMessageReceiver, MMMessageTargetType aMessageTargetType, int aPort)
	{
		setMessageReceiver(aMessageReceiver);
		setMessageTargetType(aMessageTargetType);
		setDatagramPort(aPort);
	}
	
	// MM-API:
	
	/**
	 * Interrupts the thread and closes the associated {@link DatagramSocket}.
	 */
	@Override public void interrupt()
	{	
		super.interrupt();
		getDatagramSocket().close();
	};
	
	// Implementors: 
	
	/**
	 * Creates a new {@link DatagramSocket} which is receiving incoming UDP-messages on the bound port.
	 * If a new message is received it will call the <code>handleUDPMessage()</code>-method from the {@link MMMessageReceiver}.
	 * If a {@link SocketException} occurs the thread will shutdown.
	 */
	@Override public void run()
	{
		try
		{
			// TODO if you feel lucky reuse the socket
			setDatagramSocket(new DatagramSocket(getDatagramPort()));
		} 
		catch (SocketException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(!interrupted())
		{		
			try
			{
				DatagramPacket thePacket = new DatagramPacket(new byte[MAXIMUM_UDP_SIZE_IN_BYTES], MAXIMUM_UDP_SIZE_IN_BYTES);
				getDatagramSocket().receive(thePacket);
				getMessageReceiver().handleUDPMessage(thePacket, getMessageTargetType());
			} 
			catch (SocketException aSocketException)
			{
				Thread.currentThread().interrupt();
			}
			catch (IOException e)
			{		
				e.printStackTrace();
			}	
		}
	}
	
	// MM-API:

	/**
	 * @return the messageReceiver
	 */
	private MMMessageReceiver getMessageReceiver()
	{
		return messageReceiver;
	}

	/**
	 * @param aMessageReceiver the messageReceiver to set
	 */
	private void setMessageReceiver(MMMessageReceiver aMessageReceiver)
	{
		this.messageReceiver = aMessageReceiver;
	}

	/**
	 * @return the datagramSocket
	 */
	private DatagramSocket getDatagramSocket()
	{
		return datagramSocket;
	}
	
	/**
	 * @param datagramSocket the datagramSocket to set
	 */
	private void setDatagramSocket(DatagramSocket datagramSocket)
	{
		this.datagramSocket = datagramSocket;
	}

	/**
	 * @return the messageTargetType
	 */
	private MMMessageTargetType getMessageTargetType()
	{
		return messageTargetType;
	}
	
	/**
	 * @param messageTargetType the messageTargetType to set
	 */
	private void setMessageTargetType(MMMessageTargetType messageTargetType)
	{
		this.messageTargetType = messageTargetType;
	}

	/**
	 * @return the datagramPort
	 */
	private int getDatagramPort()
	{
		return datagramPort;
	}
	
	/**
	 * @param datagramPort the datagramPort to set
	 */
	private void setDatagramPort(int datagramPort)
	{
		this.datagramPort = datagramPort;
	}

}
