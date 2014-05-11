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
		message = aMessage;
	}
	
}
