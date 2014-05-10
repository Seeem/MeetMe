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
public class MMisValidLastName
{

	// Instance-Members:
	
	/** */
	private String validLastName = new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_LASTNAME]);
	
	/** */
	private String tooShortLastName = new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_LASTNAME-1]);
	
	/** */
	private String tooLongLastName = new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_LASTNAME+1]);
	
	/** */
	private String nullLastName = null;
	
	// Tests:
	
	@Test
	public void testLastNameIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMValidationUser.isValidLastName(validLastName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testLastNameIsNull_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidLastName(nullLastName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testLastNameIsTooShort_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidLastName(tooShortLastName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testLastNameIsTooLong_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMValidationUser.isValidLastName(tooLongLastName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
