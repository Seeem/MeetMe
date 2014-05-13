/**
 * 
 */
package de.hfu.meetme.model.validation;

/**
 * 
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
	
	/** The EMPTYMESSAGE-constant */
	private static final String EMPTYMESSAGE = "";
 	
	// Constructors:
	
	/**
	 * Creates a MMValidation with a corresponding validation-boolean and a message.
	 * @param aIsValidBoolean the validation-boolean to set
	 * @param aMessage the message to set
	 */
	public MMValidation(boolean aIsValidBoolean, String aMessage)
	{
		setValid(aIsValidBoolean);
		setMessage(aMessage);
	}
	
	/**
	 * Creates a not valid MMValidation with a corresponding message.
	 * @param aMessage the message to set
	 */
	public MMValidation(String aMessage)
	{
		this(false, aMessage);
	}
	
	/**
	 * Creates a MMValidation with an empty message.
	 * @param aIsValidBoolean the validation-boolean to set
	 */
	public MMValidation(boolean aIsValidBoolean)
	{
		this(aIsValidBoolean, EMPTYMESSAGE);
	}
	
	/**
	 * Creates a valid MMValidation with an empty message.
	 */
	public MMValidation()
	{
		this(true, EMPTYMESSAGE);
	}

	// MM-API
	
	/**
	 * In case the object is not valid this method generates an {@link IllegalArgumentException}
	 * with its associated validation-message.
	 * @throws IllegalArgumentException the validation-message
	 */
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
