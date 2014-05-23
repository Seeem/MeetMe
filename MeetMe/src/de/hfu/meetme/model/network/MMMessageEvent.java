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
	
	/** */
	private InetAddress senderAddress;
	
	/** */
	private Object message;
	
	/** */
	private int senderPort;
	
	/** */
	private Calendar timestamp;
	
	/** */
	private MMMessageProtocoll messageProtocoll;
	
	/** */
	private MMMessageType messageType;

	// Constructor:
	
	/**
	 * 
	 */
	public MMMessageEvent(InetAddress aSenderAddress, Object aMessage, int aSenderPort, 
			Calendar aTimestamp, MMMessageProtocoll aMessageProtocoll, MMMessageType aMessageType)
	{
		setSenderAddress(aSenderAddress);
		setMessage(aMessage);
		setSenderPort(aSenderPort);
		setTimestamp(aTimestamp);
		setMessageProtocoll(aMessageProtocoll);
		setMessageType(aMessageType);
	}
	
	// MM-API:
	
	/** */
	public String getMessageAsString()
	{
		if (getMessage() instanceof String)
		{
			return (String) getMessage();
		}
		return getMessage().toString();
	}
	
	/** */
	public String getTimestampAsString()
	{		
		return ((SimpleDateFormat) SimpleDateFormat.getDateInstance()).format(getTimestamp().getTime());
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
	 * @param senderAddress the senderAddress to set
	 */
	private void setSenderAddress(InetAddress senderAddress)
	{
		this.senderAddress = senderAddress;
	}

	/**
	 * @return the message
	 */
	public Object getMessage()
	{
		return message;
	}

	/**
	 * @param message the message to set
	 */
	private void setMessage(Object message)
	{
		this.message = message;
	}

	/**
	 * @return the senderPort
	 */
	public int getSenderPort()
	{
		return senderPort;
	}

	/**
	 * @param senderPort the senderPort to set
	 */
	private void setSenderPort(int senderPort)
	{
		this.senderPort = senderPort;
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp()
	{
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	private void setTimestamp(Calendar timestamp)
	{
		this.timestamp = timestamp;
	}

	/**
	 * @return the messageProtocoll
	 */
	public MMMessageProtocoll getMessageProtocoll()
	{
		return messageProtocoll;
	}

	/**
	 * @param messageProtocoll the messageProtocoll to set
	 */
	private void setMessageProtocoll(MMMessageProtocoll messageProtocoll)
	{
		this.messageProtocoll = messageProtocoll;
	}

	/**
	 * @return the messageType
	 */
	public MMMessageType getMessageType()
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
