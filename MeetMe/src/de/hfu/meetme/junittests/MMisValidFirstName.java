/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * @author Simeon Sembach
 *
 */
public class MMisValidFirstName
{

	// Instance-Members:
	
	/** */
	private String validFirstName = new String(new char[MMUserValidation.MINIMUM_LENGHT_OF_A_FIRSTNAME]);
	
	/** */
	private String tooShortFirstName = new String(new char[MMUserValidation.MINIMUM_LENGHT_OF_A_FIRSTNAME-1]);
	
	/** */
	private String tooLongFirstName = new String(new char[MMUserValidation.MAXIMUM_LENGHT_OF_A_FIRSTNAME+1]);
	
	/** */
	private String nullFirstName = null;
	
	// Tests:
	
	@Test
	public void testFirstNameIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isValidFirstName(validFirstName).generateExceptionIfNotValid();
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
			MMUserValidation.isValidFirstName(nullFirstName).generateExceptionIfNotValid();
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
			MMUserValidation.isValidFirstName(tooShortFirstName).generateExceptionIfNotValid();
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
			MMUserValidation.isValidFirstName(tooLongFirstName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
