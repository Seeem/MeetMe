/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import de.hfu.meetme.validation.MMValidationUser;

/**
 * @author Simeon Sembach
 *
 */
public final class MMTestsSupport
{

	// Valid Values:
	
	/** */
	public static String getValidNickname()
	{
		return "Sem";
	}

	/** */
	public static String getValidFirstName()
	{
		return "Simeon";
	}
	
	/** */
	public static String getValidLastName()
	{
		return "Sembach";
	}
	
	/** TODO */
	public static Date getValidDate()
	{
		return null;
	}
	
	/** */
	public static String getValidDescription()
	{
		return "Hi there I want to meet you!";
	}
	
	// Not Valid Values:
	
	/** */
	public static String getTooShortNickname()
	{
		return new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_NICKNAME-1]);
	}
	
	/** */
	public static String getTooLongNickname()
	{
		return new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_NICKNAME+1]);
	}
		
	/** */
	public static String getTooShortFirstName()
	{
		return new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_FIRSTNAME-1]);
	}
	
	/** */
	public static String getTooLongFirstName()
	{
		return new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_FIRSTNAME+1]);
	}
	
	/** */
	public static String getTooShortLastName()
	{
		return new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_LASTNAME-1]);
	}
	
	/** */
	public static String getTooLongLastName()
	{
		return new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_LASTNAME+1]);
	}
	
	/** */
	public static String getTooLongDescription()
	{		
		return new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_DESCRIPTION+1]);
	}
	
	// Tests:
	
	/** */
	public static void testNotValidNickname(String aNotValidNickname)
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidNickname(aNotValidNickname).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	/** */
	public static void testNotValidFirstName(String aNotValidFirstName)
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidFirstName(aNotValidFirstName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	/** */
	public static void testNotValidLastName(String aNotValidLastName)
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidLastName(aNotValidLastName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	/** */
	public static void testNotValidDescription(String aNotValidDescription)
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidDescription(aNotValidDescription).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	/** */
	public static void testValidDescription(String aValidDescription)
	{
		boolean isExpected = true;
		
		try
		{
			MMValidationUser.isValidDescription(aValidDescription).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	/** */
	public static void testNotValidBirthday(Calendar aNotValidBirthday)
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidBirthday(aNotValidBirthday).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	
}
