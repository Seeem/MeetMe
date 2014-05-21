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
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hfu.meetme.model.message.MMMessage;

/**
 * @author Simeon Sembach
 *
 */
public final class MMMessageReceiver
{
	
	// Class-Members:
	
	/** The isStarted boolean */
	private static boolean isStarted = false;
	
	/** The messageListener-list */
	private static Vector<MMMessageListener> messageListener = new Vector<MMMessageListener>();
	
	/** The TCP Socket for receiving {@link MMMessage} */
	private static ServerSocket tcpSocket;
	
	/** The UDP Socket for receiving broadcast messages */
	private static DatagramSocket udpBroadcastSocket;
		
	/** The UDP Socket for receiving single messages */
	private static DatagramSocket udpSocket;

	/** The executor service which handles the message receiving threads*/
	private static ExecutorService executorService;

	/** The thread which is responsible for receiving TCP messages */
	private static final Runnable RECEIVE_TCP_MESSAGE_THREAD = new Runnable()
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
	
	/** The thread which is responsible for receiving UDP broadcast messages */
	private static final Runnable RECEIVE_UDP_BROADCAST_MESSAGE_THREAD = new Runnable()
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
	
	/**The thread which is responsible for receiving single UDP messages */
	private static final Runnable RECEIVE_UDP_SINGLE_MESSAGE_THREAD = new Runnable()
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
	
	// MM-API:
	
	/**
	 * Adds a message listener to the message receiver.
	 * @param aMessageListener the message listener to add
	 */
	public static void addMessageListener(MMMessageListener aMessageListener)
	{
		if (aMessageListener == null)
			throw new NullPointerException("messagelistener is null.");
		
		getMessageListener().add(aMessageListener);
	}
	
	/**
	 * Removes a message listener from the message receiver.
	 * @param aMessageListener the message listener to remove 
	 */
	public static void removeMessageListener(MMMessageListener aMessageListener)
	{
		if (aMessageListener == null)
			throw new NullPointerException("messagelistener is null.");
		
		getMessageListener().remove(aMessageListener);
	}
	
	/**
	 * Removes all message listeners from the message receiver.
	 */
	public static void removeAllMessageListeners()
	{
		getMessageListener().removeAllElements();
	}
	
	/**
	 * Starts the message receiver.
	 * If the message receiver is already started nothing will happen.
	 * @throws IOException 
	 */
	public static void startReceiver() throws IOException
	{
		if (isStarted()) return;
		executeReceivingThreads();
		setStarted(true);
	}
	
	/**
	 * Stops the message receiver.
	 * If the message receiver is already stopped or was never started nothing will happen.
	 * @throws IOException
	 */
	public static void stopReceiver() throws IOException
	{
		if (!isStarted()) return;	
		shutdownReceivingThreads();
		setStarted(false);
	}
	
	// Internals (Class):

	/**
	 * Initializes all relevant Sockets and starts all receiving-threads.
	 * @throws IOException
	 */
	private static void executeReceivingThreads() throws IOException
	{
		setUdpBroadcastSocket(new DatagramSocket(MMNetworkUtil.UDP_BROADCAST_PORT));
		setUdpSocket(new DatagramSocket(MMNetworkUtil.UDP_PORT));
		setTcpSocket(new ServerSocket(MMNetworkUtil.TCP_PORT));
		
		setExecutorService(Executors.newFixedThreadPool(3));	
		getExecutorService().execute(RECEIVE_UDP_BROADCAST_MESSAGE_THREAD);
		getExecutorService().execute(RECEIVE_UDP_SINGLE_MESSAGE_THREAD);
		getExecutorService().execute(RECEIVE_TCP_MESSAGE_THREAD);
	}

	/** 
	 * Shutdown all receiving-threads and closes all relevant sockets.
	 * @throws IOException 
	 */
	private static void shutdownReceivingThreads() throws IOException
	{
		getExecutorService().shutdownNow();
		getTcpSocket().close();		
		getUdpBroadcastSocket().close();
		getUdpSocket().close();
	}
	
	/**
	 * Sends a given {@link MMMessageEvent} to all message listeners.
	 * @param aMessageEvent the message event to send
	 */
	private static void triggerMessageEvent(MMMessageEvent aMessageEvent)
	{
		for (MMMessageListener aMessageListener : getMessageListener())	
			aMessageListener.messageReceived(aMessageEvent);
	}
	
	/** 
	 * Waits for a TCP connection from which a {@link MMMessage} may be received. 
	 */
	private static void receiveTCPMessage() throws IOException, ClassNotFoundException
	{
		Socket theClientSocket = getTcpSocket().accept();
		MMMessage theMessage = (MMMessage) new ObjectInputStream(theClientSocket.getInputStream()).readObject();
		theClientSocket.close();
		
		MMMessageEvent theMessageEvent = new MMMessageEvent(
				theClientSocket.getInetAddress(), 
				theMessage, 
				theClientSocket.getPort(), 
				Calendar.getInstance(), 
				MMMessageProtocoll.TCP, 
				MMMessageType.SINGLE);
		
		triggerMessageEvent(theMessageEvent);
	}
	
	/** 
	 * Waits for a UDP broadcast message.
	 */
	private static void receiveUDPBroadcastMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[1], 1);
		getUdpBroadcastSocket().receive(thePacket);
		
		MMMessageEvent theMessageEvent = new MMMessageEvent(
				thePacket.getAddress(), 
				thePacket.getData(), 
				thePacket.getPort(), 
				Calendar.getInstance(), 
				MMMessageProtocoll.UDP, 
				MMMessageType.BROADCAST);
		
		triggerMessageEvent(theMessageEvent);	
	}
	
	/** 
	 * Waits for a single UDP message.
	 */
	private static void receiveUDPSingleMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[1], 1);
		getUdpSocket().receive(thePacket);
		
		MMMessageEvent theMessageEvent = new MMMessageEvent(
				thePacket.getAddress(), 
				thePacket.getData(), 
				thePacket.getPort(), 
				Calendar.getInstance(), 
				MMMessageProtocoll.UDP, 
				MMMessageType.SINGLE);
		
		triggerMessageEvent(theMessageEvent);
	}

	// Accessors:
	
	/**
	 * @return the messageListener
	 */
	private static Vector<MMMessageListener> getMessageListener()
	{
		return messageListener;
	}

	/**
	 * @return the tcpSocket
	 */
	private static ServerSocket getTcpSocket()
	{
		return tcpSocket;
	}

	/**
	 * @param aTcpSocket the tcpSocket to set
	 */
	private static void setTcpSocket(ServerSocket aTcpSocket)
	{
		MMMessageReceiver.tcpSocket = aTcpSocket;
	}

	/**
	 * @return the udpBroadcastSocket
	 */
	private static DatagramSocket getUdpBroadcastSocket()
	{
		return udpBroadcastSocket;
	}

	/**
	 * @param aUdpBroadcastSocket the udpBroadcastSocket to set
	 */
	private static void setUdpBroadcastSocket(DatagramSocket aUdpBroadcastSocket)
	{
		MMMessageReceiver.udpBroadcastSocket = aUdpBroadcastSocket;
	}

	/**
	 * @return the udpSocket
	 */
	private static DatagramSocket getUdpSocket()
	{
		return udpSocket;
	}

	/**
	 * @param aUdpSocket the udpSocket to set
	 */
	private static void setUdpSocket(DatagramSocket aUdpSocket)
	{
		MMMessageReceiver.udpSocket = aUdpSocket;
	}

	/**
	 * @return the executorService
	 */
	private static ExecutorService getExecutorService()
	{
		return executorService;
	}

	/**
	 * @param anExecutorService the executorService to set
	 */
	private static void setExecutorService(ExecutorService anExecutorService)
	{
		MMMessageReceiver.executorService = anExecutorService;
	}

	
	/**
	 * @return the isStarted
	 */
	public static boolean isStarted()
	{
		return isStarted;
	}
	

	/**
	 * @param isStarted the isStarted to set
	 */
	public static void setStarted(boolean isStarted)
	{
		MMMessageReceiver.isStarted = isStarted;
	}
	
}
