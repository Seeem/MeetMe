/**
 * 
 */
package de.hfu.meetme.model.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hfu.meetme.model.message.MMMessage;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageReceiver
{
	
	// Instance-Members:
	
	/** */
	private MMMessageListener messageListener;
	
	/** */
	private DatagramSocket broadcastDatagramSocket;
		
	/** */
	private DatagramSocket singleDatagramSocket;

	/** */
	private ExecutorService executorService;

	/** */
	private final Runnable RECEIVE_TCP_MESSAGE_THREAD = new Runnable()
	{		
		@Override public void run()
		{
			while(!Thread.interrupted())
			{
				try
				{
					receiveTCPMessage();
				} 
				catch (IOException | ClassNotFoundException e) {}
			}
		}
	};
	
	/** */
	private final Runnable RECEIVE_UDP_BROADCAST_MESSAGE_THREAD = new Runnable()
	{		
		@Override public void run()
		{
			while(!Thread.interrupted())
			{
				try
				{
					receiveUDPBroadcastMessage();
				} 
				catch (IOException e) {}
			}
		}
	};
	
	/** */
	private final Runnable RECEIVE_UDP_SINGLE_MESSAGE_THREAD = new Runnable()
	{		
		@Override public void run()
		{
			while(!Thread.interrupted())
			{
				try
				{
					receiveUDPSingleMessage();
				} 
				catch (IOException e) {}
			}
		}
	};
	
	// Constructor:
	
	/** */
	public MMMessageReceiver(MMMessageListener aMessageListener) throws SocketException
	{
		setMessageListener(aMessageListener);
		setBroadcastDatagramSocket(new DatagramSocket(MMNetworkUtil.BROADCAST_PORT));
		setSingleDatagramSocket(new DatagramSocket(MMNetworkUtil.SINGLE_PORT));		
		setExecutorService(Executors.newFixedThreadPool(3));
	}
	
	// MM-API:
	
	/** */
	public void startSearching()
	{
		getExecutorService().execute(RECEIVE_UDP_BROADCAST_MESSAGE_THREAD);
		getExecutorService().execute(RECEIVE_UDP_SINGLE_MESSAGE_THREAD);
		getExecutorService().execute(RECEIVE_TCP_MESSAGE_THREAD);
	}
	
	/** */
	public void stopSearching()
	{
		getExecutorService().shutdown();
	}

	/** */
	public void receiveTCPMessage() throws IOException, ClassNotFoundException
	{
		ServerSocket theServerSocket = new ServerSocket(MMNetworkUtil.TCP_PORT);		
		Socket theSocket = theServerSocket.accept();
		MMMessage theMessage = (MMMessage) new ObjectInputStream(theSocket.getInputStream()).readObject();
		theSocket.close();
		theServerSocket.close();
		getMessageListener().TCPMessageReceived(theMessage);	
	}
	
	/** */
	public void receiveUDPBroadcastMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[1024], 1024);
		getBroadcastDatagramSocket().receive(thePacket);
		getMessageListener().UDPBroadcastMessageReceived(thePacket);	
	}
	
	/** */
	public void receiveUDPSingleMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[1024], 1024);
		getSingleDatagramSocket().receive(thePacket);
		getMessageListener().UDPSingleMessageReceived(thePacket);
	}
	
	// Accessors:

	/**
	 * @return the broadcastDatagramSocket
	 */
	private DatagramSocket getBroadcastDatagramSocket()
	{
		return broadcastDatagramSocket;
	}

	/**
	 * @param aBroadcastDatagramSocket the broadcastDatagramSocket to set
	 */
	private void setBroadcastDatagramSocket(DatagramSocket aBroadcastDatagramSocket)
	{
		broadcastDatagramSocket = aBroadcastDatagramSocket;
	}

	/**
	 * @return the singleDatagramSocket
	 */
	private DatagramSocket getSingleDatagramSocket()
	{
		return singleDatagramSocket;
	}

	/**
	 * @param singleDatagramSocket the singleDatagramSocket to set
	 */
	private void setSingleDatagramSocket(DatagramSocket singleDatagramSocket)
	{
		this.singleDatagramSocket = singleDatagramSocket;
	}
	
	/**
	 * @return the executorService
	 */
	private ExecutorService getExecutorService()
	{
		return executorService;
	}

	/**
	 * @param aExecutorService the executorService to set
	 */
	private void setExecutorService(ExecutorService aExecutorService)
	{
		executorService = aExecutorService;
	}

	/**
	 * @return the messageListener
	 */
	private MMMessageListener getMessageListener()
	{
		return messageListener;
	}

	/**
	 * @param messageListener the messageListener to set
	 */
	private void setMessageListener(MMMessageListener messageListener)
	{
		this.messageListener = messageListener;
	}
	
}
