/**
 * 
 */
package de.hfu.meetme.model.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Simeon Sembach
 *
 */
public final class MMMessageReceiver
{
	
	// Instance-Members:
	
	/** The isStarted boolean. */
	private boolean isStarted = false;
	
	/** The messageListener-list. */
	private Vector<MMMessageListener> messageListener = new Vector<MMMessageListener>();
	
	/** The UDP Socket for receiving broadcast messages. */
	private DatagramSocket udpBroadcastSocket;
		
	/** The UDP Socket for receiving single messages. */
	private DatagramSocket udpSocket;

	/** The executor service which handles the message receiving threads. */
	private ExecutorService executorService;

	/** The thread which is responsible for receiving UDP broadcast messages. */
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
	
	/** The thread which is responsible for receiving single UDP messages. */
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
	
	/** The maximum number of bytes an UDP receiver can receive in one UDP message. */
	private static final int MAXIMUM_UDP_SIZE_IN_BYTES = 512;
	
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
		} 
		catch (Exception anException)
		{
			if (getUdpBroadcastSocket() != null)
				getUdpBroadcastSocket().close();
			
			if (getUdpSocket() != null)
				getUdpSocket().close();
			
			throw new RuntimeException(anException.getMessage());
		}
		
		setExecutorService(Executors.newFixedThreadPool(2));
		getExecutorService().execute(RECEIVE_UDP_BROADCAST_MESSAGE_THREAD);
		getExecutorService().execute(RECEIVE_UDP_SINGLE_MESSAGE_THREAD);
	}

	/** 
	 * Shutdown all receiving-threads and closes all relevant sockets.
	 * @throws IOException 
	 */
	private void shutdownReceivingThreads()
	{
		getExecutorService().shutdownNow();	
		getUdpBroadcastSocket().close();
		getUdpSocket().close();
	}
	
	/** 
	 * Waits for a UDP broadcast message.
	 */
	private void receiveUDPBroadcastMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[MAXIMUM_UDP_SIZE_IN_BYTES], MAXIMUM_UDP_SIZE_IN_BYTES);
		getUdpBroadcastSocket().receive(thePacket);		
		handleUDPMessage(thePacket, MMMessageTargetType.BROADCAST);	
	}
	
	/** 
	 * Waits for a single UDP message.
	 */
	private void receiveUDPSingleMessage() throws IOException
	{
		DatagramPacket thePacket = new DatagramPacket(new byte[MAXIMUM_UDP_SIZE_IN_BYTES], MAXIMUM_UDP_SIZE_IN_BYTES);
		getUdpSocket().receive(thePacket);		
		handleUDPMessage(thePacket, MMMessageTargetType.SINGLE);
	}

	/**
	 * TODO
	 */
	private void handleUDPMessage(DatagramPacket aDatagramPacket, MMMessageTargetType aMessageTargetType)
	{
		String theMessageAsString = new String(aDatagramPacket.getData(), 0, aDatagramPacket.getLength());		
		String theSplittedString[] = theMessageAsString.split(":");	
		
		if (theSplittedString.length < 2)
		{
			pushMessageEvent(aDatagramPacket, aMessageTargetType, MMMessageType.UNKNOWN, theMessageAsString);
		}
		else
		{
			try
			{
				pushMessageEvent(aDatagramPacket, aMessageTargetType, MMMessageType.valueOf(theSplittedString[0]), theSplittedString[1]);
			}
			catch (Exception anException) // valueOf
			{
				pushMessageEvent(aDatagramPacket, aMessageTargetType, MMMessageType.UNKNOWN, theSplittedString[1]);
			}
		}	
	}
	
	/**
	 * TODO 
	 */
	private void pushMessageEvent(DatagramPacket aDatagramPacket, MMMessageTargetType aMessageTargetType, MMMessageType theMessageType, String aMessageContent)
	{
		MMMessageEvent theMessageEvent = new MMMessageEvent(
				aDatagramPacket.getAddress(), 
				aMessageContent, 
				aDatagramPacket.getPort(),
				Calendar.getInstance(), 
				MMMessageProtocol.UDP, 
				aMessageTargetType,
				theMessageType);
		
		for (MMMessageListener aMessageListener : getMessageListener())	
			aMessageListener.messageReceived(theMessageEvent);
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
