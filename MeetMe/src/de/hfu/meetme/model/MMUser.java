package de.hfu.meetme.model;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * 
 * @author Simeon Sembach
 *
 */
public class MMUser implements Serializable
{

	// Instance-Members:
	
	/** The unique identification-key of an user. */
	private String id;
	
	/** The gender of an user. */
	private MMGender gender;
	
	/** The user name of an user. */
	private String username;
	
	/** The first name of an user. */
	private String firstName;
	
	/** The second name of an user. */
	private String lastName;
	
	/** The birthday of an user. */
	private Calendar birthday;
	
	/** The additional description of an user. */
	private String description;
	
	// Class-Members:
	
	/** The serial version UID of an MMUser-object. */
	private static final long serialVersionUID = 4091994199398642117L;

	/** The users-"list" as HashMap. */
	private static ConcurrentHashMap<String, MMUser> users = new ConcurrentHashMap<String,MMUser>();
	
	/** The user associated with this device. */
	private static MMUser myself;
	
	// Constructor:
	
	/**
	 * The MMUser Constructor.
	 * @param aId the users Id
	 * @param aGender the users gender to set
	 * @param aUsername the user name to set
	 * @param aFirstName the first name to set
	 * @param aLastName the last name to set
	 * @param aBirthday the birthday to set
	 * @param aDescription the description to set
	 */
	public MMUser(String aId, MMGender aGender, String aUsername, String aFirstName, String aLastName, Calendar aBirthday, String aDescription)
	{
		setId(aId);
		setGender(aGender);
		setUsername(aUsername);
		setFirstName(aFirstName);
		setLastName(aLastName);
		setBirthday(aBirthday);
		setDescription(aDescription);
	}
	
	/**
	 * Creates a MMUser with an empty description.
	 * @param aId the users Id
	 * @param aGender the users gender to set
	 * @param aUsername the user name to set
	 * @param aFirstName the first name to set
	 * @param aLastName the last name to set
	 * @param aBirthday the birthday to set
	 */
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
	
	/**
	 * Returns the birthday date as easy readable {@link String}.
	 * @return the birthday as string
	 */
	public String getBirthdayAsString()
	{		
		return ((SimpleDateFormat) SimpleDateFormat.getDateInstance()).format(getBirthday().getTime());
	}
	
	// MM-API (Class):
	
	/**
	 * Initializes the user-map.
	 */
	public static void initializeUsers()
	{
		setUsers(new ConcurrentHashMap<String, MMUser>());
	}
	
	/**
	 * Returns whether the user-map contains the specified user.
	 * @param anUser the user to search for
	 * @return true if the user-map contains the specified user, false otherwise
	 */
	public static boolean containsUser(MMUser anUser)
	{
		if (anUser == null)
			throw new NullPointerException("user to search is null");
		
		return MMUser.getUsers().containsKey(anUser.getId());
	}
	
	/**
	 * Returns whether the user-map contains the specified user id.
	 * @param aUser the user id to search for
	 * @return true if the user-map contains the specified user id, false otherwise
	 */
	public static boolean containsUser(String anUserId)
	{
		if (anUserId == null)
			throw new NullPointerException("user ID to search is null");
		
		return MMUser.getUsers().containsKey(anUserId);
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
	 * @param anUser the user to add
	 */
	public static void addUser(MMUser anUser)
	{
		if (anUser == null)
			throw new NullPointerException("user to add is null");
		
		if (MMUser.containsUser(anUser))
			throw new IllegalArgumentException("user already added");
				
		MMUser.getUsers().put(anUser.getId(), anUser);
	}
	
	/**
	 * Adds an specified user to the user-map if it is not already added before.
	 * @param anUser the user to add
	 */
	public static void addUserIfNotAlreadyAdded(MMUser anUser)
	{
		if (anUser == null)
			throw new NullPointerException("user to add is null");
		
		if (!MMUser.containsUser(anUser))
			MMUser.addUser(anUser);
	}
	
	/**
	 * Removes an single user based on the associated user-id.
	 * @param aUser the user to remove
	 */
	public static void removeUser(MMUser anUser)
	{
		if (anUser == null)
			throw new NullPointerException("user to remove is null");
		
		if (!MMUser.containsUser(anUser))
			throw new IllegalArgumentException("user does not exist");
		
		MMUser.getUsers().remove(anUser.getId());
	}
	
	/**
	 * 
	 * @param aUser the user to remove
	 */
	public static void removeUserIfAlreadyAdded(MMUser anUser)
	{
		if (anUser == null)
			throw new NullPointerException("user to remove is null");
		
		if (MMUser.containsUser(anUser))	
			MMUser.removeUser(anUser);
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
	
	/**
	 * Returns all users as array.
	 * @return the users as array
	 */
	public static MMUser[] getUsersAsArray()
	{
		return MMUser.getUsers().values().toArray(new MMUser[MMUser.size()]);
	}
	
	/**
	 * Returns the user with the given key if it was added before,
	 * otherwise it will return null.
	 * @param anUserId the user ID
	 * @return the user with the given key if it was added before, null otherwise
	 */
	public static MMUser getUserById(String anUserId)
	{
		if (anUserId == null)
			throw new IllegalArgumentException("user id is null.");
		
		if (!MMUser.containsUser(anUserId))
			return null;
			
		return MMUser.getUsers().get(anUserId);
	}
	
	// Internals:
	
	/**
	 * Compares this user with the given user and returns true if they are equals.
	 * @param anUser the user to compare
	 * @return true if the compared users are equals, false otherwise
	 */
	public boolean equals(MMUser anUser)
	{
		if (anUser == null) return false;
		
		return 
				this.getId().equals(anUser.getId()) &&
				this.getUsername().equals(anUser.getUsername()) &&
				this.getFirstName().equals(anUser.getFirstName()) &&
				this.getLastName().equals(anUser.getLastName()) &&
				this.getGender() == anUser.getGender() &&
				this.getDescription().equals(anUser.getDescription()) &&
				this.getBirthdayAsString().equals(anUser.getBirthdayAsString());
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
	 * @return the user name
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param aUsername the user name to set
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
	public static void setUsers(ConcurrentHashMap<String, MMUser> aHashMap)
	{
		if (aHashMap == null)
			throw new IllegalArgumentException("hashmap is null");
		
		users = aHashMap;
	}
	
	/** 
	 * @return the users as HashMap
	 */
	public static ConcurrentHashMap<String, MMUser> getUsers()
	{
		return users;
	}
	
	/**
	 * @return the myself
	 */
	public static MMUser getMyself()
	{
		return myself;
	}
	
	/**
	 * @param anUser the myself to set
	 */
	public static void setMyself(MMUser anUser)
	{
		if (anUser == null)
			throw new IllegalArgumentException("user is null.");
		
		MMUser.myself = anUser;
	}
	
	// Conversion:
	
	/** */
	public String toUdpMessage()
	{	
		return new StringBuffer().
				append(getId()).append(";").
				append(getGender()).append(";").
				append(getUsername()).append(";").
				append(getFirstName()).append(";").
				append(getLastName()).append(";").
				append(getBirthdayAsString()).append(";").
				append(getDescription()).append(";").
				toString();
	}
	
	/** */
	public static MMUser valueOf(String anUDPMessageAsString)
	{		
		try
		{
			String theStrings[] = anUDPMessageAsString.split(";");
			Calendar theBirthday = Calendar.getInstance();
			theBirthday.setTime(((SimpleDateFormat) SimpleDateFormat.getDateInstance()).parse(theStrings[5]));	
			MMGender theGender = theStrings[1].equals(MMGender.MALE.toString()) ? MMGender.MALE : MMGender.FEMALE;
			if (theStrings.length == 7)
				return new MMUser(theStrings[0], theGender, theStrings[2], theStrings[3], theStrings[4], theBirthday, theStrings[6]);
			else
				return new MMUser(theStrings[0], theGender, theStrings[2], theStrings[3], theStrings[4], theBirthday);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	// Printers:
	
	/**
	 * @return user-relevant facts like Gender, user name, first name, last name, birthday and description
	 */
	@Override public String toString()
	{
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
		theStringBuffer.append(getBirthdayAsString());
		theStringBuffer.append("; Description: ");
		theStringBuffer.append(getDescription());
		
		return theStringBuffer.toString();
	}
	
	/**
	 * Prints this user.
	 */
	public void print()
	{
		System.out.println(this);
	}
	
	/**
	 * Prints all added users.
	 */
 	public static void printUsers()
	{
		for (MMUser anUser : MMUser.getUsersAsArray())
			anUser.print();
	}

	
}
