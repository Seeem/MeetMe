/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import static org.junit.Assert.assertTrue;

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
	public static String getValidNickName()
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
	public static String getTooShortNickName()
	{
		return "";
	}
	
	/** */
	public static String getTooLongNickName()
	{
		return "ThisIsAToLongNickName";
	}
		
	/** */
	public static String getTooShortFirstName()
	{
		return "";
	}
	
	/** */
	public static String getTooLongFirstName()
	{
		return "ThisIsAToLongFirstName";
	}
	
	/** */
	public static String getTooShortLastName()
	{
		return "";
	}
	
	/** */
	public static String getTooLongLastName()
	{
		return "ThisIsAToLongLastName";
	}
	
	// Tests:
	
	/** */
	public static void testNotValidNickName(String aNotValidNickName)
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidNickName(aNotValidNickName).generateExceptionIfNotValid();
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
	
}
