/**
 * 
 */
package de.hfu.meetme.model.message;


/**
 * @author Simeon Sembach
 *
 */
public class MMIWantToMeetYouMessage extends MMMessage
{

	// Class-Members:
	
	/** */
	private static final long serialVersionUID = -6729471138678940987L;

	// Constructor:

	/** */
	public MMIWantToMeetYouMessage(String aId)
	{
		setId(aId);
	}

	// Accessors (Instance):

	/** */
	public void setId(String aId)
	{
		super.setMessage(aId);
	}
	
	/** */
	public String getId()
	{
		return (String) super.getMessage();
	}

	/** */
	@Override public String toString()
	{
		return "ID: "+getId();
	}
	
}
