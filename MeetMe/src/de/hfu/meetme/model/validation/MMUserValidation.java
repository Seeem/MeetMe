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
 * Every available validation-method returns a {@link MMValidation}-Object.
 * 
 * @author Simeon Sembach
 */
public final class MMUserValidation
{

	// Class-Members:
	
	/** the username pattern */
	public static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{2,14}");
	
	/** The first name pattern */
	public static final Pattern FIRSTNAME_PATTERN = Pattern.compile("[A-Z][a-z]{1,19}");
	
	/** the last name pattern */
	public static final Pattern LASTNAME_PATTERN = Pattern.compile("[A-Z][a-z]{1,19}");
	
	/** the description pattern */
	public static final Pattern DESCRIPTION_PATTERN = Pattern.compile(".{0,100}");

	// MM-API (Class):
	
	/**
	 * Returns a valid {@link MMValidation} if the given gender is man or woman,
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
			return new MMValidation("Calendar is null");
		
		// TODO Geburtsdatum optional? Dann null erlaubt?
		// TODO User muss mindestens x Jahre alt sein
		// TODO Datum darf nicht in der Zukunft liegen
		
		return new MMValidation();
	}
	
}
