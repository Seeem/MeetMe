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

	/** The unique identification-key of an user */
	private String id;
	
	/** The gender of an user */
	private MMGender gender;
	
	/** The username of an user */
	private String username;
	
	/** The first name of an user */
	private String firstName;
	
	/** The second name of an user */
	private String lastName;
	
	/** The birthday of an user */
	private Calendar birthday;
	
	/** The additional description of an user */
	private String description;
	
	// Class-Members:
	
	/** The serial version UID of an MMUser-object */
	private static final long serialVersionUID = 4091994199398642117L;
	
	/** The users-"list" as HashMap */
	private static HashMap<String, MMUser> users = new HashMap<String,MMUser>();
	
	// Constructor:
	
	/**
	 * The MMUser Constructor.
	 * @param aGender the users gender to set
	 * @param aUsername the username to set
	 * @param aFirstName the first name to set
	 * @param aLastName the last name to set
	 * @param aBirthday the birthday to set
	 * @param aDescription the description to set
	 */
	public MMUser(String aId, MMGender aGender, String aUsername, String aFirstName, String aLastName, Calendar aBirthday, String aDescription)
	{
		setId(aId); // TODO
		setGender(aGender);
		setUsername(aUsername);
		setFirstName(aFirstName);
		setLastName(aLastName);
		setBirthday(aBirthday);
		setDescription(aDescription);
	}
	
	/** Creates a MMUser with an empty description */
	public MMUser(String aId, MMGender aGender, String aUsername, String aFirstName, String aLastName, Calendar aBirthday)
	{
		this(aId, aGender, aUsername, aFirstName, aLastName, aBirthday, "");
	}
	
	// MM-API (Instance):
	
	/**
	 * Returns whether the user is a man.
	 * @return true if the users gender is male, false otherwise
	 */
	public boolean isMan()
	{
		return getGender() == MMGender.MALE;
	}
	
	/**
	 * Returns whether the user is a woman.
	 * @return true if the users gender is female, false otherwise
	 */
	public boolean isWoman()
	{
		return getGender() == MMGender.FEMALE;
	}
	
	// MM-API (Class):
	
	/** Initializes the user-map */
	public static void initializeUsers()
	{
		setUsers(new HashMap<String, MMUser>());
	}
	
	/**
	 * Returns whether the user-map contains the specified user.
	 * @param aUser the user to search for
	 * @return true if the user-map contains the specified user, false otherwise
	 */
	public static boolean containsUser(MMUser aUser)
	{
		return MMUser.getUsers().containsKey(aUser.getId());
	}
	
	/**
	 * Returns the number of users in the user-map.
	 * @return the number of users in the user-map
	 */
	public static int size()
	{
		return MMUser.getUsers().size();
	}
	
	/**
	 * Adds an specified user to the user-map.
	 * @param aUser the user to add
	 */
	public static void addUser(MMUser aUser)
	{
		if (aUser == null)
			throw new IllegalArgumentException("user to add is null");
		
		if (MMUser.containsUser(aUser))
			throw new IllegalArgumentException("user already exists");
		
		MMUser.getUsers().put(aUser.getId(), aUser);
	}
	
	/**
	 * Removes an single user based on the associated user-id.
	 * @param aUser the user to remove
	 */
	public static void removeUser(MMUser aUser)
	{
		if (aUser == null)
			throw new IllegalArgumentException("user to remove is null");
		
		if (!MMUser.containsUser(aUser))
			throw new IllegalArgumentException("user does not exist");
		
		MMUser.getUsers().remove(aUser.getId());
	}
	
	/**
	 * Removes all users from the user-map, the result will be an empty user-map.
	 */
	public static void removeAllUsers()
	{
		/* 
		 * According to the documentation, it's more efficient to iterate through the HashMap
		 * instead of removing every single entry based on the associated key
		 */
		for (Iterator<String> theIterator = MMUser.getUsers().keySet().iterator(); theIterator.hasNext();)
		{
			theIterator.next();
			theIterator.remove();
		}
	}
	
	// Accessors (Instance):
	
	/**
	 * @return the gender
	 */
	public MMGender getGender()
	{
		return gender;
	}

	/**
	 * @param aGender the gender to set
	 */
	public void setGender(MMGender aGender)
	{
		MMUserValidation.isValidGender(aGender).generateExceptionIfNotValid();

		this.gender = aGender;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param aUsername the username to set
	 */
	public void setUsername(String aUsername)
	{
		MMUserValidation.isValidUsername(aUsername).generateExceptionIfNotValid();
		
		this.username = aUsername;
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
	
	/**
	 * @param aHashMap the users as HashMap to set
	 */
	public static void setUsers(HashMap<String, MMUser> aHashMap)
	{
		if (aHashMap == null)
			throw new IllegalArgumentException("hashmap is null");
		
		users = aHashMap;
	}
	
	/** 
	 * @return the users as HashMap
	 */
	public static HashMap<String, MMUser> getUsers()
	{
		return users;
	}
	
	// Print:
	
	/**
	 * @return user-relevant facts like Gender, username, first name, last name, birthday and description
	 */
	@Override public String toString()
	{
		SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		
		StringBuffer theStringBuffer = new StringBuffer();
		
		theStringBuffer.append("ID: ");
		theStringBuffer.append(getId());
		theStringBuffer.append("; Gender: ");
		theStringBuffer.append(getGender());
		theStringBuffer.append("; Username: ");
		theStringBuffer.append(getUsername());
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
