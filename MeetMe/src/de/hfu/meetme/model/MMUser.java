package de.hfu.meetme.model;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * 
 * @author Simeon Sembach
 *
 */
public class MMUser implements Serializable
{

	// Instance-Members:

	/** The local ID of an user */
	private String id;
	
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
	
	// Class-Members:
	
	/** */
	private static final long serialVersionUID = 4091994199398642117L;
	
	/** */
	private static HashMap<String, MMUser> users = new HashMap<String,MMUser>();
	
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
	
	// MM-API (Class):
	
	/** */
	public static void addUser(MMUser aUser)
	{
		// TODO Validation
		
		MMUser.getUsers().put(aUser.getId(), aUser);
	}
	
	/** */
	public static void removeUser(MMUser aUser)
	{
		// TODO Validation
		
		MMUser.getUsers().remove(aUser.getId());
	}
	
	/** */
	public static void removeAllUsers()
	{
		// According to the documentation, it's more efficient to iterate through the HashMap
		// instead of removing every single entry based on the associated key
		for (Iterator<String> theIterator = MMUser.getUsers().keySet().iterator(); theIterator.hasNext();)
		{
			theIterator.next();
			theIterator.remove();
		}
	}
	
	// Accessors (Instance):
	
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
		MMUserValidation.isValidNickname(aNickname).generateExceptionIfNotValid();
		
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
		MMUserValidation.isValidFirstName(aFirstName).generateExceptionIfNotValid();
		
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
		MMUserValidation.isValidLastName(aLastName).generateExceptionIfNotValid();
		
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
		MMUserValidation.isValidBirthday(aBirthday).generateExceptionIfNotValid();
		
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
		MMUserValidation.isValidDescription(aDescription).generateExceptionIfNotValid();
		
		this.description = aDescription;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		// TODO Validation
		
		this.id = id;
	}
	
	// Accessors (Class):
	
	/** */
	public static void setUsers(HashMap<String, MMUser> aHashMap)
	{
		// TODO Validation
		
		users = aHashMap;
	}
	
	/** */
	public static HashMap<String, MMUser> getUsers()
	{
		return users;
	}
	
	// Print:
	
	/** */
	@Override public String toString()
	{
		SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		
		StringBuffer theStringBuffer = new StringBuffer();
		
		theStringBuffer.append("Nickname: ");
		theStringBuffer.append(getNickname());
		theStringBuffer.append("; FirstName: ");
		theStringBuffer.append(getFirstName());
		theStringBuffer.append("; LastName: ");
		theStringBuffer.append(getLastName());
		theStringBuffer.append("; Birthday: ");
		theStringBuffer.append(theDateFormat.format(getBirthday().getTime()));
		theStringBuffer.append("; Description: ");
		theStringBuffer.append(getDescription());
		
		return theStringBuffer.toString();
	}
	
}
