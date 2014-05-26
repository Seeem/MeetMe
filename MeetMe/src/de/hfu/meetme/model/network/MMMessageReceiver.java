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
	
	// Instance-Members:
	
	/** The isStarted boolean */
	private boolean isStarted = false;
	
	/** The messageListener-list */
	private Vector<MMMessageListener> messageListener = new Vector<MMMessageListener>();
	
	/** The TCP Socket for receiving {@link MMMessage} */
	private ServerSocket tcpSocket;
	
	/** The UDP Socket for receiving broadcast messages */
	private DatagramSocket udpBroadcastSocket;
		
	/** The UDP Socket for receiving single messages */
	private DatagramSocket udpSocket;

	/** The executor service which handles the message receiving threads*/
	private ExecutorService executorService;

	/** The thread which is responsible for receiving TCP messages */
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
				catch (IOException anException_1) 
				{
				}
				catch (Exception anException_2)
				{
					anException_2.printStackTrace();
				}
			}
		}
	};
	
	/** The thread which is responsible for receiving UDP broadcast messages */
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
				catch (IOException anIOException)
				{
					
				}
				catch (Exception anException)
				{
					anException.printStackTrace();
				}
			}
		}
	};
	
	/** The thread which is responsible for receiving single UDP messages */
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
				catch (IOException anIOException)
				{
					
				}
				catch (Exception anException)
				{
					anException.printStackTrace();
				}
			}
		}
	};
	
	// Class-Members:
	
	/** The maximum number of bytes a UDP receiver can receive in one UDP message */
	private static final int MAXIMUM_UDP_SIZE_IN_BYTES = 1024;
	
	// MM-API:
	
	/**
	 * Adds a message listener to the message receiver.
	 * @param aMessageListener the message listener to add
	 */
	public void addMessageListener(MMMessageListener aMessageListener)
	{
		if (aMessageListener == null)
			throw new NullPointerException("messagelistener is null.");
		
		getMessageListener().add(aMessageListener);
	}
	
	/**
	 * Removes a message listener from the message receiver.
	 * @param aMessageListener the message listener to remove 
	 */
	public void removeMessageListener(MMMessageListener aMessageListener)
	{
		if (aMessageListener == null)
			throw new NullPointerException("messagelistener is null.");
		
		getMessageListener().remove(aMessageListener);
	}
	
	/**
	 * Removes all message listeners from the message receiver.
	 */
	public void removeAllMessageListeners()
	{
		getMessageListener().removeAllElements();
	}
	
	/**
	 * Starts the message receiver.
	 * If the message receiver is already started nothing will happen.
	 * @throws IOException 
	 */
	public void startReceiver()
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
	public void stopReceiver()
	{
		if (!isStarted()) return;	
		shutdownReceivingThreads();
		setStarted(false);
	}
	
	// Internals:

	/**
	 * Initializes all relevant Sockets and starts all receiving-threads.
	 * @throws IOException
	 */
	private void executeReceivingThreads()
	{	
		try
		{
			setUdpBroadcastSocket(new DatagramSocket(MMNetworkUtil.UDP_BROADCAST_PORT));
			setUdpSocket(new DatagramSocket(MMNetworkUtil.UDP_PORT));
			setTcpSocket(new ServerSocket(MMNetworkUtil.TCP_PORT));
		} 
		catch (Exception anException)
		{
			if (getUdpBroadcastSocket() != null)
				getUdpBroadcastSocket().close();
			
			if (getUdpSocket() != null)
				getUdpSocket().close();
			
			if (getTcpSocket() != null)
				try{getTcpSocket().close();} catch (IOException anIOException){anIOException.printStackTrace();}
			
			throw new RuntimeException(anException.getMessage());
		}
		
		setExecutorService(Executors.newFixedThreadPool(3));	
		getExecutorService().execute(RECEIVE_UDP_BROADCAST_MESSAGE_THREAD);
		getExecutorService().execute(RECEIVE_UDP_SINGLE_MESSAGE_THREAD);
		getExecutorService().execute(RECEIVE_TCP_MESSAGE_THREAD);
	}

	/** 
	 * Shutdown all receiving-threads and closes all relevant sockets.
	 * @throws IOException 
	 */
	private void shutdownReceivingThreads()
	{
		getExecutorService().shutdownNow();
		
		try
		{
			getTcpSocket().close();
		} 
		catch (IOException anIOException)
		{
			throw new RuntimeException(anIOException.getMessage());
		}
			
		getUdpBroadcastSocket().close();
		getUdpSocket().close();
	}
	
	/**
	 * Sends a given {@link MMMessageEvent} to all message listeners.
	 * @param aMessageEvent the message event to send
	 */
	private void triggerMessageEvent(MMMessageEvent aMessageEvent)
	{
		for (MMMessageListener aMessageListener : getMessageListener())	
			aMessageListener.messageReceived(aMessageEvent);
	}
	
	/** 
	 * Waits for a TCP connection from which a {@link MMMessage} may be received. 
	 */
	private void receiveTCPMessage() throws IOException, ClassNotFoundException
	{
		Socket theClientSocket = getTcpSocket().accept();
		MMMessage theMessage = (MMMessage) new ObjectInputStream(theClientSocket.getInputStream()).readObject();
		theClientSocket.close();
		
		MMMessageEvent theMessageEvent = new MMMessageEvent(
				theClientSocket.getInetAddress(), 
				theMessage, 
				theClientSocket.getPort(), 
				Calendar.getInstance(), 
				MMMessageProtocol.TCP, 
				MMMessageTargetType.SINGLE,
				null);
		
		triggerMessageEvent(theMessageEvent);
	}
	
	/** 
	 * Waits for a UDP broadcast message.
	 */
	private void receiveUDPBroadcastMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[MAXIMUM_UDP_SIZE_IN_BYTES], MAXIMUM_UDP_SIZE_IN_BYTES);
		getUdpBroadcastSocket().receive(thePacket);		
		String theMessageAsString = new String(thePacket.getData(), 0, thePacket.getLength());
		
		MMMessageEvent theMessageEvent = new MMMessageEvent(
				thePacket.getAddress(), 
				theMessageAsString.split(":")[1], 
				thePacket.getPort(), 
				Calendar.getInstance(), 
				MMMessageProtocol.UDP, 
				MMMessageTargetType.BROADCAST,
				MMMessageType.valueOf(theMessageAsString.split(":")[0]));
		
		triggerMessageEvent(theMessageEvent);	
	}
	
	/** 
	 * Waits for a single UDP message.
	 */
	private void receiveUDPSingleMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[MAXIMUM_UDP_SIZE_IN_BYTES], MAXIMUM_UDP_SIZE_IN_BYTES);
		getUdpSocket().receive(thePacket);		
		String theMessageAsString = new String(thePacket.getData(), 0, thePacket.getLength());
		
		MMMessageEvent theMessageEvent = new MMMessageEvent(
				thePacket.getAddress(), 
				theMessageAsString.split(":")[1], 
				thePacket.getPort(), 
				Calendar.getInstance(), 
				MMMessageProtocol.UDP, 
				MMMessageTargetType.SINGLE,
				MMMessageType.valueOf(theMessageAsString.split(":")[0]));
		
		triggerMessageEvent(theMessageEvent);	
	}

	// Accessors:
	
	/**
	 * @return the messageListener
	 */
	private Vector<MMMessageListener> getMessageListener()
	{
		return messageListener;
	}

	/**
	 * @return the tcpSocket
	 */
	private ServerSocket getTcpSocket()
	{
		return tcpSocket;
	}

	/**
	 * @param aTcpSocket the tcpSocket to set
	 */
	private void setTcpSocket(ServerSocket aTcpSocket)
	{
		this.tcpSocket = aTcpSocket;
	}

	/**
	 * @return the udpBroadcastSocket
	 */
	private DatagramSocket getUdpBroadcastSocket()
	{
		return udpBroadcastSocket;
	}

	/**
	 * @param aUdpBroadcastSocket the udpBroadcastSocket to set
	 */
	private void setUdpBroadcastSocket(DatagramSocket aUdpBroadcastSocket)
	{
		this.udpBroadcastSocket = aUdpBroadcastSocket;
	}

	/**
	 * @return the udpSocket
	 */
	private DatagramSocket getUdpSocket()
	{
		return udpSocket;
	}

	/**
	 * @param aUdpSocket the udpSocket to set
	 */
	private void setUdpSocket(DatagramSocket aUdpSocket)
	{
		this.udpSocket = aUdpSocket;
	}

	/**
	 * @return the executorService
	 */
	private ExecutorService getExecutorService()
	{
		return executorService;
	}

	/**
	 * @param anExecutorService the executorService to set
	 */
	private void setExecutorService(ExecutorService anExecutorService)
	{
		this.executorService = anExecutorService;
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
	public void setStarted(boolean isStarted)
	{
		this.isStarted = isStarted;
	}

}
