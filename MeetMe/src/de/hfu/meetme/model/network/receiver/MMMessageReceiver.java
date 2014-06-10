/**
 * 
 */
package de.hfu.meetme.model.network.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import de.hfu.meetme.model.network.message.MMMessageEvent;
import de.hfu.meetme.model.network.message.MMMessageListener;
import de.hfu.meetme.model.network.message.MMMessageProtocol;
import de.hfu.meetme.model.network.message.MMMessageTargetType;
import de.hfu.meetme.model.network.message.MMMessageType;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageReceiver
{
	
	// Instance-Members:
	
	/** The isStarted boolean. */
	private boolean isStarted = false;
	
	/** The messageListener-list. */
	private Vector<MMMessageListener> messageListener = new Vector<MMMessageListener>();

	/** The executor service which handles the message receiving threads. */
	private ThreadPoolExecutor threadPoolExecutor;

	/** The {@link Runnable} which is responsible for receiving UDP broadcast messages. */
	private Thread ReceiveUdpBroadcastThread;
	
	/** The {@link Runnable} which is responsible for receiving single UDP messages. */
	private Thread ReceiveUdpSingleThread;
	
	// MM-API:
	
	/**
	 * Adds a message listener to the message receiver.
	 * @param aMessageListener the message listener to add
	 */
	public synchronized void addMessageListener(MMMessageListener aMessageListener)
	{
		if (aMessageListener == null)
			throw new NullPointerException("messagelistener is null.");
		
		getMessageListener().add(aMessageListener);
	}
	
	/**
	 * Removes a message listener from the message receiver.
	 * @param aMessageListener the message listener to remove 
	 */
	public synchronized void removeMessageListener(MMMessageListener aMessageListener)
	{
		if (aMessageListener == null)
			throw new NullPointerException("messagelistener is null.");
		
		getMessageListener().remove(aMessageListener);
	}
	
	/**
	 * Removes all message listeners from the message receiver.
	 */
	public synchronized void removeAllMessageListeners()
	{
		getMessageListener().removeAllElements();
	}
	
	/**
	 * Starts the message receiver.
	 * If the message receiver is already started nothing will happen.
	 * @throws IOException 
	 */
	public synchronized void startReceiver()
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
	public synchronized void stopReceiver()
	{
		if (!isStarted()) return;	
		interruptReceivingThreads();
		setStarted(false);
	}
	
	// Constructor:
	
	/** */
	public MMMessageReceiver(int anUdpBroadcastPort, int anUdpPort)
	{
		setReceiveUdpBroadcastThread(new MMMessageReceiverTask(this, MMMessageTargetType.BROADCAST, anUdpBroadcastPort));
		setReceiveUdpSingleThread(new MMMessageReceiverTask(this, MMMessageTargetType.SINGLE, anUdpPort));
		setThreadPoolExecutor((ThreadPoolExecutor) Executors.newFixedThreadPool(2));
	}
	
	// Internals:

	/**
	 * Initializes all relevant Sockets and starts all receiving-threads.
	 * @throws IOException
	 */
	private void executeReceivingThreads()
	{
		if(getReceiveUdpBroadcastThread().isAlive())
			throw new IllegalStateException("UDP broadcast receiver is still alive.");
		
		if(getReceiveUdpSingleThread().isAlive())
			throw new IllegalStateException("UDP single receiver is still alive.");
		
		getThreadPoolExecutor().execute(getReceiveUdpBroadcastThread());
		getThreadPoolExecutor().execute(getReceiveUdpSingleThread());
	}

	/** 
	 * Shutdown all receiving-threads and closes all relevant sockets.
	 * @throws IOException 
	 */
	private void interruptReceivingThreads()
	{
		getReceiveUdpBroadcastThread().interrupt();
		getReceiveUdpSingleThread().interrupt();
	}

	/**
	 * Converts the data from the given {@link DatagramPacket} into a {@link String} and
	 * splits it on the first found ":", the first substring is the MessageType the second one
	 * the message content. If there is a problem at splitting or the message type is not valid
	 * the message type will be UNKNOWN. Finally the message will be pushed.
	 * @param aDatagramPacket the {@link DatagramPacket} with the basic message information
	 * @param aMessageTargetType the {@link MMMessageTargetType} of the message
	 */
	void handleUDPMessage(DatagramPacket aDatagramPacket, MMMessageTargetType aMessageTargetType)
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
				pushMessageEvent(aDatagramPacket, aMessageTargetType, MMMessageType.valueOf(theSplittedString[0]), theMessageAsString.substring(theMessageAsString.indexOf(":")+1));
			}
			catch (Exception anException) // valueOf, not valid MMMessageType
			{
				pushMessageEvent(aDatagramPacket, aMessageTargetType, MMMessageType.UNKNOWN, theSplittedString[1]);
			}
		}	
	}
	
	/**
	 * Finalizes the message by wrapping it into a {@link MMMessageEvent}
	 * which will be pushed to all {@link MMMessageListener}.
	 * @param aDatagramPacket the {@link DatagramPacket}
	 * @param aMessageTargetType the {@link MMMessageTargetType}
	 * @param theMessageType the {@link MMMessageType}
	 * @param aMessageContent the message content as {@link String}
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

	/**
	 * @return the threadPoolExecutor
	 */
	public ThreadPoolExecutor getThreadPoolExecutor()
	{
		return threadPoolExecutor;
	}
	
	/**
	 * @param threadPoolExecutor the threadPoolExecutor to set
	 */
	public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor)
	{
		this.threadPoolExecutor = threadPoolExecutor;
	}

	/**
	 * @return the receiveUdpBroadcastThread
	 */
	public Thread getReceiveUdpBroadcastThread()
	{
		return ReceiveUdpBroadcastThread;
	}

	/**
	 * @param receiveUdpBroadcastThread the receiveUdpBroadcastThread to set
	 */
	public void setReceiveUdpBroadcastThread(Thread receiveUdpBroadcastThread)
	{
		ReceiveUdpBroadcastThread = receiveUdpBroadcastThread;
	}

	/**
	 * @return the receiveUdpSingleThread
	 */
	public Thread getReceiveUdpSingleThread()
	{
		return ReceiveUdpSingleThread;
	}

	/**
	 * @param receiveUdpSingleThread the receiveUdpSingleThread to set
	 */
	public void setReceiveUdpSingleThread(Thread receiveUdpSingleThread)
	{
		ReceiveUdpSingleThread = receiveUdpSingleThread;
	}

}
