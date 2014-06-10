/**
 * 
 */
package de.hfu.meetme.model.validation;

import java.util.Calendar;
import java.util.regex.Pattern;

import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;

/**
 * This class supports some validations for the {@link MMUser}-class.
 * 
 * @author Simeon Sembach
 */
public final class MMUserValidation
{

	// Class-Members:
	
	/** the minimum age of an user */
	public static final int MINIMUM_AGE_OF_AN_USER = 12;
	
	/** the maximum age of an user */
	public static final int MAXIMUM_AGE_OF_AN_USER = 80;
	
	/** the username pattern: <br>
	 * Content: any captial letter, lower case letter and number. <br>
	 * Minimum string-lenght: 2. <br>
	 * Maximum string-lenght: 20.
	 */
	public static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9]{3,19}");
	
	/** The first name pattern: <br>
	 * Content: First Character have to be a capital letter, following letters have to be lower case letters. <br>
	 * Minimum string-lenght: 2. <br>
	 * Maximum string-lenght: 20.
	 */
	public static final Pattern FIRSTNAME_PATTERN = Pattern.compile("[A-Z][a-z]{1,19}");
	
	/** 
	 * the last name pattern: <br>
	 * Content: First Character have to be a capital letter, following letters have to be lower case letters. <br>
	 * Minimum string-lenght: 2. <br>
	 * Maximum string-lenght: 20.
	 */
	public static final Pattern LASTNAME_PATTERN = Pattern.compile("[A-Z][a-z]{1,19}");
	
	/** 
	 * the description pattern: <br>
	 * Content: Can be any character. <br>
	 * Minimum string-lenght: 0. <br>
	 * Maximum string-lenght: 100.
	 */
	public static final Pattern DESCRIPTION_PATTERN = Pattern.compile(".{0,100}");
	
	// TODO no >> ; << allowed
	
	// MM-API (Class):
	
	/**
	 * Returns a valid {@link MMValidation} if the given gender is male or female,
	 * otherwise it returns a not valid {@link MMValidation}.
	 * @param aGender the gender to valid
	 * @return a {@link MMValidation} object
	 */
	public static MMValidation isValidGender(MMGender aGender)
	{
		if (aGender == null)
			return new MMValidation("gender is null");
		
		if (aGender != MMGender.FEMALE && aGender != MMGender.MALE)
			return new MMValidation("unknown Gender");
		
		return new MMValidation();
	}
	
	/**
	 * Returns a valid {@link MMValidation} if the given username-String is correct
	 * otherwise it returns a not valid {@link MMValidation}.
	 * @param aUsername the username to validate 
	 * @return a {@link MMValidation}
	 */
	public static MMValidation isValidUsername(String aUsername)
	{
		if (aUsername == null)
			return new MMValidation("username is null");
		
		if (!USERNAME_PATTERN.matcher(aUsername).matches())
			return new MMValidation("username does not match");
		
		return new MMValidation();
	}
	
	/**
	 * Returns a valid {@link MMValidation} if the given first-name-String is correct
	 * otherwise it returns a not valid {@link MMValidation}.
	 * @param aFirstName the first name to validate
	 * @return a {@link MMValidation}
	 */
	public static MMValidation isValidFirstName(String aFirstName)
	{
		if (aFirstName == null)
			return new MMValidation("first name is null");
		
		if (!FIRSTNAME_PATTERN.matcher(aFirstName).matches())
			return new MMValidation("first name does not match");
		
		return new MMValidation();
	}
	
	/**
	 * Returns a valid {@link MMValidation} if the given last-name-String is correct
	 * otherwise it returns a not valid {@link MMValidation}.
	 * @param aLastName the last name to validate
	 * @return a {@link MMValidation}
	 */
	public static MMValidation isValidLastName(String aLastName)
	{
		if (aLastName == null)
			return new MMValidation("last name is null");
		
		if (!LASTNAME_PATTERN.matcher(aLastName).matches())
			return new MMValidation("last name does not match");
		
		return new MMValidation();
	}
	
	/**
	 * Returns a valid {@link MMValidation} if the given description-String is correct
	 * otherwise it returns a not valid {@link MMValidation}.
	 * @param aDescription the description-text to validate
	 * @return a {@link MMValidation}
	 */
	public static MMValidation isValidDescription(String aDescription)
	{
		if (aDescription == null)
			return new MMValidation("description is null");
		
		if (!DESCRIPTION_PATTERN.matcher(aDescription).matches())
			return new MMValidation("description does not match");
		
		return new MMValidation();
	}

	/**
	 * Returns a valid {@link MMValidation} if the given birthday-date is correct
	 * otherwise it returns a not valid {@link MMValidation}
	 * @param aBirthday the birthday to valid
	 * @return {@link MMValidation}
	 */
	public static MMValidation isValidBirthday(Calendar aBirthday)
	{
		if (aBirthday == null)
			return new MMValidation("birthday is null");
		
		Calendar theTodayDate = Calendar.getInstance();
		
		if (aBirthday.compareTo(theTodayDate) == 1)
			return new MMValidation("birthday is in the future");
		
		int theDeltaYears  = theTodayDate.get(Calendar.YEAR) - aBirthday.get(Calendar.YEAR);
		int theDeltaMonths = theTodayDate.get(Calendar.MONTH) - aBirthday.get(Calendar.MONTH);
		int theDeltaDays   = theTodayDate.get(Calendar.DAY_OF_MONTH) - aBirthday.get(Calendar.DAY_OF_MONTH);
		
		if (theDeltaYears < MINIMUM_AGE_OF_AN_USER || (theDeltaYears == MINIMUM_AGE_OF_AN_USER && (theDeltaMonths > 0 || (theDeltaMonths == 0 && (theDeltaDays > 0)))))
			return new MMValidation("user is not old enough");
				
		if (theDeltaYears > MAXIMUM_AGE_OF_AN_USER || (theDeltaYears == MAXIMUM_AGE_OF_AN_USER && (theDeltaMonths < 0 || (theDeltaMonths == 0 && (theDeltaDays < 0)))))
			return new MMValidation("user is too old");
		
		return new MMValidation();
	}
	
}
