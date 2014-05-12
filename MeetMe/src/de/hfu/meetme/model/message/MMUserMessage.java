/**
 * 
 */
package de.hfu.meetme.model.message;

import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMUserMessage extends MMMessage
{

	// Class-Members:
	
	/** */
	private static final long serialVersionUID = -6729471138678940987L;

	// Constructor:
	
	/**
	 * 
	 * @param aUser the user to send
	 */
	public MMUserMessage(MMUser aUser)
	{
		setUser(aUser);
	}

	// Accessors (Instance):

	/** */
	public void setUser(MMUser aUser)
	{
		super.setMessage(aUser);
	}
	
	/** */
	public MMUser getUser()
	{
		return (MMUser) super.getMessage();
	}

}