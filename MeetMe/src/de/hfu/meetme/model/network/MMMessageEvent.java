/**
 * 
 */
package de.hfu.meetme.model.network;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageEvent
{

	// Instance-Members:
	
	/** The sender Internet Address. */
	private InetAddress senderAddress;
	
	/** The send Message. */
	private Object message;
	
	/** The port of the sender. */
	private int senderPort;
	
	/** The time/moment the message was received. */
	private Calendar timestamp;
	
	/** The used message protocol (UDP or TCP). */
	private MMMessageProtocol messageProtocol;
	
	/** The message target type (single- or broadcast message). */
	private MMMessageTargetType messageTargetType;

	/** */
	private MMMessageType messageType;
	
	// Constructor:
	
	/**
	 * Creates a new MMMessageEvent instance.
	 * @param aSenderAddress the sender address
	 * @param aMessage the message
	 * @param aSenderPort the sender port
	 * @param aTimestamp the time stamp
	 * @param aMessageProtocoll the message protocol
	 * @param aMessageType the message type
	 */
	public MMMessageEvent(InetAddress aSenderAddress, Object aMessage, int aSenderPort, Calendar aTimestamp, MMMessageProtocol aMessageProtocoll, MMMessageTargetType aMessageType, MMMessageType aMeesageType)
	{
		setSenderAddress(aSenderAddress);
		setMessage(aMessage);
		setSenderPort(aSenderPort);
		setTimestamp(aTimestamp);
		setMessageProtocol(aMessageProtocoll);
		setMessageTargetType(aMessageType);
		setMessageType(aMeesageType);
	}
	
	// MM-API:
	
	/**
	 * Returns the received message as @link {@link String}.
	 * @return the message as string
	 */
	public String getMessageAsString()
	{
		if (getMessage() instanceof String)
		{
			return (String) getMessage();
		}
		return getMessage().toString();
	}
	
	/**
	 * Returns the time stamp of the received message as easy readable {@link String}.
	 * @return the time stamp as string
	 */
	public String getTimestampAsString()
	{		
		return ((SimpleDateFormat) SimpleDateFormat.getDateInstance()).format(getTimestamp().getTime());
	}
	
	// Is-Methods:
	
	/** */
	public boolean isUdpProtocol()
	{
		return getMessageProtocol() == MMMessageProtocol.UDP;
	}
	
	/** */
	public boolean isSingleMessage()
	{
		return getMessageTargetType() == MMMessageTargetType.SINGLE;
	}
	
	/** */
	public boolean isBroadcastMessage()
	{
		return getMessageTargetType() == MMMessageTargetType.BROADCAST;
	}
	
	/** */
	public boolean isConnectMessage()
	{
		return getMessageType() == MMMessageType.CONNECT;
	}
	
	/** */
	public boolean isDisconnectMessage()
	{
		return getMessageType() == MMMessageType.DISCONNECT;
	}
	
	/** */
	public boolean isMeetMeMessage()
	{
		return getMessageType() == MMMessageType.MEETME;
	}
	
	/** */
	public boolean isUnknownMessage()
	{
		return getMessageType() == MMMessageType.UNKNOWN;
	}
	
	/** */
	public boolean isFromMe()
	{
		return MMNetworkUtil.isMyLanAddress(getSenderAddress());
	}
	
	// Accessors:
	
	/**
	 * @return the senderAddress
	 */
	public InetAddress getSenderAddress()
	{
		return senderAddress;
	}

	/**
	 * @param aSenderAddress the senderAddress to set
	 */
	private void setSenderAddress(InetAddress aSenderAddress)
	{
		this.senderAddress = aSenderAddress;
	}

	/**
	 * @return the message
	 */
	private Object getMessage()
	{
		return message;
	}

	/**
	 * @param aMessage the message to set
	 */
	private void setMessage(Object aMessage)
	{
		this.message = aMessage;
	}

	/**
	 * @return the senderPort
	 */
	public int getSenderPort()
	{
		return senderPort;
	}

	/**
	 * @param aSenderPort the senderPort to set
	 */
	private void setSenderPort(int aSenderPort)
	{
		this.senderPort = aSenderPort;
	}

	/**
	 * @return the time stamp
	 */
	private Calendar getTimestamp()
	{
		return timestamp;
	}

	/**
	 * @param aTimestamp the time stamp to set
	 */
	private void setTimestamp(Calendar aTimestamp)
	{
		this.timestamp = aTimestamp;
	}

	/**
	 * @return the messageProtocol
	 */
	private MMMessageProtocol getMessageProtocol()
	{
		return messageProtocol;
	}

	/**
	 * @param messageProtocoll the messageProtocol to set
	 */
	private void setMessageProtocol(MMMessageProtocol aMessageProtocol)
	{
		this.messageProtocol = aMessageProtocol;
	}

	/**
	 * @return the messageType
	 */
	private MMMessageTargetType getMessageTargetType()
	{
		return messageTargetType;
	}

	/**
	 * @param aMessageType the messageType to set
	 */
	private void setMessageTargetType(MMMessageTargetType aMessageType)
	{
		this.messageTargetType = aMessageType;
	}
	
	/**
	 * @return the messageType
	 */
	private MMMessageType getMessageType()
	{
		return messageType;
	}
	
	/**
	 * @param messageType the messageType to set
	 */
	private void setMessageType(MMMessageType messageType)
	{
		this.messageType = messageType;
	}
	
}
