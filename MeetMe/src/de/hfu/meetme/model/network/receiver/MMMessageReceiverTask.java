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
	
	/** */
	private MMMessageReceiver messageReceiver;
	
	/** */
	private DatagramSocket datagramSocket;
	
	/** */
	private int datagramPort;
	
	/** */
	private MMMessageTargetType messageTargetType;
	
	// Class-Members:
	
	/** The maximum number of bytes an UDP receiver can receive in one UDP message. */
	private static final int MAXIMUM_UDP_SIZE_IN_BYTES = 512;
	
	// Constructor:
	
	/** */
	private MMMessageReceiverTask(MMMessageReceiver aMessageReceiver, MMMessageTargetType aMessageTargetType, int aPort)
	{
		setMessageReceiver(aMessageReceiver);
		setMessageTargetType(aMessageTargetType);
		setDatagramPort(aPort);
	}
	
	// Factory:
	
	/** */
	public static MMMessageReceiverTask getInstance(MMMessageReceiver aMessageReceiver, MMMessageTargetType aMessageTargetType, int aPort)
	{
		return new MMMessageReceiverTask(aMessageReceiver, aMessageTargetType, aPort);
	}
	
	// MM-API:
	
	/** */
	public void interrupt()
	{
		super.interrupt();
		getDatagramSocket().close();
	};
	
	// Implementors: 
	
	@Override public void run()
	{
		try
		{
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
				break; // close()
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
