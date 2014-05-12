/**
 * 
 */
package de.hfu.meetme.model.validation;

import java.util.Calendar;

import de.hfu.meetme.model.MMGender;

/**
 * @author Simeon Sembach
 *
 */
public final class MMUserValidation
{

	// Class-Members:
	
	/** The minimum String-lenght of a username */
	public static final int MINIMUM_LENGHT_OF_A_USERNAME = 3;
	
	/** The maximum String-lenght of a username */
	public static final int MAXIMUM_LENGHT_OF_A_USERNAME = 6;
	
	/** The minimum String-lenght of a first name */
	public static final int MINIMUM_LENGHT_OF_A_FIRSTNAME = 1;
	
	/** The maximum String-lenght of a first name */
	public static final int MAXIMUM_LENGHT_OF_A_FIRSTNAME = 10;
	
	/** The minimum String-lenght of a last name */
	public static final int MINIMUM_LENGHT_OF_A_LASTNAME = 1;
	
	/** The maximum String-lenght of a last name */
	public static final int MAXIMUM_LENGHT_OF_A_LASTNAME = 10;
	
	/** The minimum String-lenght of a description */
	public static final int MINIMUM_LENGHT_OF_A_DESCRIPTION = 0;
	
	/** The maximum String-lenght of a description */
	public static final int MAXIMUM_LENGHT_OF_A_DESCRIPTION = 100;
	
	// Internals (Class):
	
	/** 
	 * Returns a valid MMValidation-Object if the given String is between the given minimum-lenght and maximum-lenght,
	 * otherwise it returns a not valid MMValidation-Object.
	 * 
	 * @param aString the String to validate
	 * @param aMinimumStringLenght the minimum String-lenght
	 * @param aMaximumStringLenght the maximum String-lenght
	 * @return a MMValidation-Object
	 */
	private static MMValidation isStringInRange(String aString, int aMinimumStringLenght, int aMaximumStringLenght)
	{
		if (aString == null)
			return new MMValidation("String is null");
		
		if (aString.length() < aMinimumStringLenght)
			return new MMValidation("String-Lenght is smaller than " + aMinimumStringLenght);
		
		if (aString.length() > aMaximumStringLenght)
			return new MMValidation("String-Lenght is bigger than " + aMaximumStringLenght);
		
		return new MMValidation();
	}
	
	// MM-API:
	
	/** TODO */
	public static MMValidation isMan(MMGender aGender)
	{
		if (aGender == MMGender.MAN)
			return new MMValidation();
		
		if (aGender == MMGender.WOMAN)
			return new MMValidation("It's a woman");
		
		return new MMValidation("Unknown Gender");
	}
	
	/** TODO */
	public static MMValidation isWoman(MMGender aGender)
	{
		if (aGender == MMGender.WOMAN)
			return new MMValidation();
		
		if (aGender == MMGender.MAN)
			return new MMValidation("It's a man");
		
		return new MMValidation("Unknown Gender");
	}
	
	/**
	 * Returns a valid MMValidation-Object if the given username-String is correct
	 * otherwise it returns a not valid MMValidation-Object.
	 * @param aUsername the username to validate 
	 * @return a MMValidation-Object
	 */
	public static MMValidation isValidUsername(String aUsername)
	{
		return isStringInRange(aUsername, MINIMUM_LENGHT_OF_A_USERNAME, MAXIMUM_LENGHT_OF_A_USERNAME);
	}
	
	/**
	 * Returns a valid MMValidation-Object if the given first-name-String is correct
	 * otherwise it returns a not valid MMValidation-Object.
	 * @param aFirstName the first name to validate
	 * @return a MMValidation-Object
	 */
	public static MMValidation isValidFirstName(String aFirstName)
	{
		return isStringInRange(aFirstName, MINIMUM_LENGHT_OF_A_FIRSTNAME, MAXIMUM_LENGHT_OF_A_FIRSTNAME);
	}
	
	/**
	 * Returns a valid MMValidation-Object if the given last-name-String is correct
	 * otherwise it returns a not valid MMValidation-Object.
	 * @param aLastName the last name to validate
	 * @return a MMValidation-Object
	 */
	public static MMValidation isValidLastName(String aLastName)
	{
		return isStringInRange(aLastName, MINIMUM_LENGHT_OF_A_LASTNAME, MAXIMUM_LENGHT_OF_A_LASTNAME);
	}
	
	/**
	 * Returns a valid MMValidation-Object if the given description-String is correct
	 * otherwise it returns a not valid MMValidation-Object.
	 * @param aDescription the description-text to validate
	 * @return a MMValidation-Object
	 */
	public static MMValidation isValidDescription(String aDescription)
	{
		return isStringInRange(aDescription, MINIMUM_LENGHT_OF_A_DESCRIPTION, MAXIMUM_LENGHT_OF_A_DESCRIPTION);
	}

	/**
	 * Returns a valid MMValidation-Object if the given date is correct
	 * otherwise it returns a not valid MMValidation-Object
	 * @param aBirthday
	 * @return
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
