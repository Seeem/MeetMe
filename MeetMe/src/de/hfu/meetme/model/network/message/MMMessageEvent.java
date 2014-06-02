/**
 * 
 */
package de.hfu.meetme.model.network.message;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageEvent
{

	// Instance-Members:
	
	/** The send Message. */
	private final String message;
	
	/** The time/moment the message was received. */
	private final Calendar timestamp;
	
	/** The time/moment the message was received as a {@link String} */
	private final String timestampAsString;
	
	/** The sender Internet Address. */
	private final InetAddress senderAddress;
		
	/** The port of the sender. */
	private final int senderPort;
	
	/** The used message protocol (UDP or TCP). */
	private final MMMessageProtocol messageProtocol;
	
	/** The message target type (single- or broadcast message). */
	private final MMMessageTargetType messageTargetType;

	/** The message type. */
	private final MMMessageType messageType;
	
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
	public MMMessageEvent(InetAddress aSenderAddress, String aMessage, int aSenderPort, Calendar aTimestamp, MMMessageProtocol aMessageProtocoll, MMMessageTargetType aMessageTargetType, MMMessageType aMessageType)
	{
		this.senderAddress	   = aSenderAddress;
		this.message		   = aMessage;
		this.senderPort		   = aSenderPort;
		this.timestamp		   = aTimestamp;
		this.timestampAsString = (((SimpleDateFormat) SimpleDateFormat.getDateInstance()).format(getTimestamp().getTime()));
		this.messageProtocol   = aMessageProtocoll;
		this.messageTargetType = aMessageTargetType;
		this.messageType	   = aMessageType;
	}
	
	// Is-Methods:
	
	/**
	 * Returns whether the used message protocol is UDP.
	 * @return true if the used message protocol is UDP, false otherwise
	 */
	public boolean isUdpProtocol()
	{
		return getMessageProtocol() == MMMessageProtocol.UDP;
	}
	
	/**
	 * Returns whether the target of this message is a single.
	 * @return true if the target is a single, false otherwise
	 */
	public boolean isSingleMessage()
	{
		return getMessageTargetType() == MMMessageTargetType.SINGLE;
	}
	
	/**
	 * Returns whether the target of this message is a broadcast.
	 * @return true if the target is a broadcast, false otherwise.
	 */
	public boolean isBroadcastMessage()
	{
		return getMessageTargetType() == MMMessageTargetType.BROADCAST;
	}
	
	/**
	 * Returns whether the message type is CONNECT.
	 * @return true if the message type is CONNECT, false otherwise
	 */
	public boolean isConnectMessage()
	{
		return getMessageType() == MMMessageType.CONNECT;
	}
	
	/**
	 * Returns whether the message type is DISCONNECT.
	 * @return true if the message type is DISCONNECT, false otherwise
	 */
	public boolean isDisconnectMessage()
	{
		return getMessageType() == MMMessageType.DISCONNECT;
	}
	
	/**
	 * Returns whether the message type is MEETME.
	 * @return true if the message type is MEETME, false otherwise
	 */
	public boolean isMeetMeMessage()
	{
		return getMessageType() == MMMessageType.MEETME;
	}
	
	/**
	 * Returns whether the message type is UNKNOWN
	 * @return true if the message type is UNKNOWN, false otherwise
	 */
	public boolean isUnknownMessage()
	{
		return getMessageType() == MMMessageType.UNKNOWN;
	}
	
	/**
	 * Returns whether the message is from this device.
	 * @return true if the message is from this device, false otherwise
	 */
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
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @return the senderPort
	 */
	public int getSenderPort()
	{
		return senderPort;
	}

	/**
	 * @return the time stamp
	 */
	public Calendar getTimestamp()
	{
		return timestamp;
	}

	/**
	 * @return the messageProtocol
	 */
	private MMMessageProtocol getMessageProtocol()
	{
		return messageProtocol;
	}

	/**
	 * @return the messageType
	 */
	private MMMessageTargetType getMessageTargetType()
	{
		return messageTargetType;
	}

	/**
	 * @return the messageType
	 */
	private MMMessageType getMessageType()
	{
		return messageType;
	}
	
	/**
	 * @return the timestampAsString
	 */
	public String getTimestampAsString()
	{
		return timestampAsString;
	}
	

}
