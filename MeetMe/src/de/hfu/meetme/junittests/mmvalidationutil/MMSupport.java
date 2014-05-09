/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidationutil;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import de.hfu.meetme.utilities.MMValidationUtil;

/**
 * @author Simeon Sembach
 *
 */
public final class MMSupport
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
	public static String getTooShortNickname()
	{
		return "Se";
	}
	
	/** */
	public static String getEmptyString()
	{
		return "";
	}
	
	/** */
	public static void testNotValidNickName(String aNotValidNickName)
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUtil.isValidNickName(aNotValidNickName);
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
//		boolean isExpected = false;
//		
//		try
//		{
//			MMValidationUtil.isValidNickName(aNotValidFirstName);
//		} 
//		catch (IllegalArgumentException e)
//		{
//			e.printStackTrace();
//			isExpected = !isExpected;
//		}
//		
//		assertTrue(isExpected);
	}
	
}
