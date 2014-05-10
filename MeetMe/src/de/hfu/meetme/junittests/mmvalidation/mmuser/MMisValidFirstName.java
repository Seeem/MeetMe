/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation.mmuser;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hfu.meetme.validation.MMValidationUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMisValidFirstName
{

	// Instance-Members:
	
	/** */
	private String validFirstName = new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_FIRSTNAME]);
	
	/** */
	private String tooShortFirstName = new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_FIRSTNAME-1]);
	
	/** */
	private String tooLongFirstName = new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_FIRSTNAME+1]);
	
	/** */
	private String nullFirstName = null;
	
	// Tests:
	
	@Test
	public void testIsFirstNameIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMValidationUser.isValidFirstName(validFirstName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testFirstNameIsNull_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidFirstName(nullFirstName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testFirstNameIsTooShort_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidFirstName(tooShortFirstName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testFirstNameIsTooLong_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidFirstName(tooLongFirstName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
