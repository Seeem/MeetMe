/**
 * 
 */
package de.hfu.meetme.validation;

/**
 * @author Simeon Sembach
 *
 */
public class MMValidation
{

	// Instance-Members:
	
	/** The validation Boolean */
	private boolean isValid;
	
	/** The validation message */
	private String message;

	// Class-Members:
	
	private static final String EMPTYMESSAGE = "";
 	
	// Constructors:
	
	/**
	 * 
	 */
	public MMValidation(boolean aIsValidBoolean, String aMessage)
	{
		setValid(aIsValidBoolean);
		setMessage(aMessage);
	}
	
	/**
	 * 
	 */
	public MMValidation(String aMessage)
	{
		this(false, aMessage);
	}
	
	/**
	 * 
	 */
	public MMValidation(boolean aIsValidBoolean)
	{
		this(aIsValidBoolean, EMPTYMESSAGE);
	}
	
	/**
	 * 
	 */
	public MMValidation()
	{
		this(true, EMPTYMESSAGE);
	}

	// MM-API
	
	/** */
	public void generateExceptionIfNotValid() throws IllegalArgumentException
	{
		if (!isValid())
			throw new IllegalArgumentException(getMessage());
	}
	
	// Accessors:
	
	/**
	 * @return the isValid
	 */
	public boolean isValid()
	{
		return isValid;
	}

	/**
	 * @param aIsValidBoolean the isValid to set
	 */
	public void setValid(boolean aIsValidBoolean)
	{
		this.isValid = aIsValidBoolean;
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param aMessage the message to set
	 */
	public void setMessage(String aMessage)
	{
		this.message = aMessage;
	}
	
}
