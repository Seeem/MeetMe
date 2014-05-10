package de.hfu.meetme.model;



import java.util.Calendar;

import de.hfu.meetme.validation.MMValidationUser;

/**
 * 
 * @author Simeon Sembach
 *
 */
public class MMUser
{

	// Instance-Members:
	
	/** The Nickname of an user */
	private String nickname;
	
	/** The first name of an user */
	private String firstName;
	
	/** The second name of an user */
	private String lastName;
	
	/** The birthday of an user */
	private Calendar birthday;
	
	/** The additional description of an user */
	private String description;

	// Constructor:
	
	/**
	 * The MMUser Constructor
	 * @param aNickname the nickname to set
	 * @param aFirstName the first name to set
	 * @param aLastName the last name to set
	 * @param aBirthday the birthday to set
	 * @param aDescription the description to set
	 */
	public MMUser(String aNickname, String aFirstName, String aLastName, Calendar aBirthday, String aDescription)
	{
		setNickname(aNickname);
		setFirstName(aFirstName);
		setLastName(aLastName);
		setBirthday(aBirthday);
		setDescription(aDescription);
	}
	
	// Accessors:
	
	/**
	 * @return the nickname
	 */
	public String getNickname()
	{
		return nickname;
	}

	/**
	 * @param aNickname the nickname to set
	 */
	public void setNickname(String aNickname)
	{
		MMValidationUser.isValidNickname(aNickname).generateExceptionIfNotValid();
		
		this.nickname = aNickname;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param aFirstName the firstName to set
	 */
	public void setFirstName(String aFirstName)
	{
		this.firstName = aFirstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * @param aLastName the lastName to set
	 */
	public void setLastName(String aLastName)
	{
		this.lastName = aLastName;
	}

	/**
	 * @return the birthday
	 */
	public Calendar getBirthday()
	{
		return birthday;
	}

	/**
	 * @param aBirthday the birthday to set
	 */
	public void setBirthday(Calendar aBirthday)
	{
		this.birthday = aBirthday;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param aDescription the description to set
	 */
	public void setDescription(String aDescription)
	{
		this.description = aDescription;
	}
		
}
