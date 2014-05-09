/**
 * 
 */
package de.hfu.meetme.validation;

/**
 * @author Simeon Sembach
 *
 */
public final class MMValidationUser
{

	// Class-Members:
	
	/** */
	public static final int MINIMUM_LENGHT_OF_A_NICKNAME = 3;
	
	/** */
	public static final int MAXIMUM_LENGHT_OF_A_NICKNAME = 6;
	
	/** */
	public static final int MINIMUM_LENGHT_OF_A_FIRSTNAME = 1;
	
	/** */
	public static final int MAXIMUM_LENGHT_OF_A_FIRSTNAME = 10;
	
	/** */
	public static final int MINIMUM_LENGHT_OF_A_LASTNAME = 1;
	
	/** */
	public static final int MAXIMUM_LENGHT_OF_A_LASTNAME = 10;

	/** */
	private static final String STRING_IS_NULL = "String is null";
	
	/** */
	private static final String STRING_IS_SMALLER = "String-Size is smaller than ";
	
	/** */
	private static final String STRING_IS_BIGGER = "String-Size is bigger than ";
	
	// MM-API:
	
	/** */
	public static MMValidation isValidNickName(String aNickName)
	{
		if (aNickName == null)
			return new MMValidation(STRING_IS_NULL);
		
		if (aNickName.length() < MINIMUM_LENGHT_OF_A_NICKNAME)
			return new MMValidation(STRING_IS_SMALLER+MINIMUM_LENGHT_OF_A_NICKNAME);
		
		if (aNickName.length() > MAXIMUM_LENGHT_OF_A_NICKNAME)
			return new MMValidation(STRING_IS_BIGGER+MAXIMUM_LENGHT_OF_A_NICKNAME);
		
		return new MMValidation();
	}
	
	/** */
	public static MMValidation isValidFirstName(String aFirstName)
	{
		if (aFirstName == null)
			return new MMValidation(STRING_IS_NULL);	
		
		if (aFirstName.length() < MINIMUM_LENGHT_OF_A_FIRSTNAME)
			return new MMValidation(STRING_IS_SMALLER+MINIMUM_LENGHT_OF_A_FIRSTNAME);
		
		if (aFirstName.length() > MAXIMUM_LENGHT_OF_A_FIRSTNAME)
			return new MMValidation(STRING_IS_BIGGER+MAXIMUM_LENGHT_OF_A_FIRSTNAME);
			
		return new MMValidation();
	}
	
	/** */
	public static MMValidation isValidLastName(String aLastName)
	{
		if (aLastName == null)
			return new MMValidation(STRING_IS_NULL);
		
		if (aLastName.length() < MINIMUM_LENGHT_OF_A_LASTNAME)
			return new MMValidation(STRING_IS_SMALLER+MINIMUM_LENGHT_OF_A_LASTNAME);
		
		if (aLastName.length() > MAXIMUM_LENGHT_OF_A_LASTNAME)
			return new MMValidation(STRING_IS_BIGGER+MAXIMUM_LENGHT_OF_A_LASTNAME);
		
		return new MMValidation();
	}

}
