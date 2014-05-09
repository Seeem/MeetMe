package de.hfu.meetme.model;

import java.util.Date;

import de.hfu.meetme.utilities.MMValidation;
import de.hfu.meetme.utilities.MMValidationUtil;

/**
 * 
 * @author Simeon Sembach
 *
 */
public class MMUser
{

	// Members:
	
	/** The Nickname of an user */
	private String nickName;
	
	/** The first name of an user */
	private String firstName;
	
	/** The second name of an user */
	private String lastName;
	
	/** The birthday of an user */
	private Date birthday;
	
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
	public MMUser(String aNickname, String aFirstName, String aLastName, Date aBirthday, String aDescription)
	{
		setNickname(aNickname);
		setFirstName(aFirstName);
		setLastName(aLastName);
		setBirthday(aBirthday);
		setDescription(aDescription);
	}
	
	// Accessors:
	
	/**
	 * @return the nickName
	 */
	public String getNickname()
	{
		return nickName;
	}

	/**
	 * @param aNickName the nickName to set
	 */
	public void setNickname(String aNickName)
	{
		MMValidationUtil.isValidNickName(aNickName).ifAbsent();
		
		this.nickName = aNickName;
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
	public Date getBirthday()
	{
		return birthday;
	}

	/**
	 * @param aBirthday the birthday to set
	 */
	public void setBirthday(Date aBirthday)
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
