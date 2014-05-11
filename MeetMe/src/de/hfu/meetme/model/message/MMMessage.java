/**
 * 
 */
package de.hfu.meetme.model.message;

import java.io.Serializable;

/**
 * @author Simeon Sembach
 *
 */
public abstract class MMMessage implements Serializable
{

	// Instance-Members:
	
	/** */
	private Object message;
	
	// Class-Members:
	
	/** */
	private static final long serialVersionUID = -8112935432792602575L;
	
	// Constructor:
	
	/**
	 * The empty MMMessage constructor for subclasses
	 */
	protected MMMessage(){}
	
	/**
	 * The MMMessage constructor
	 * @param aMessage the message to set
	 */
	public MMMessage(Object aMessage)
	{
		setMessage(aMessage);
	}

	// Accessors (Instance):
	
	/**
	 * @return the message
	 */
	protected Object getMessage()
	{
		return message;
	}

	/**
	 * @param aMessage the message to set
	 */
	protected void setMessage(Object aMessage)
	{
		this.message = aMessage;
	}
	
}
