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
public class MMisValidLastNameTest
{

	// Instance-Members:
	
	/** */
	private String validLastName = new String(new char[MMUserValidation.MINIMUM_LENGHT_OF_A_LASTNAME]);
	
	/** */
	private String tooShortLastName = new String(new char[MMUserValidation.MINIMUM_LENGHT_OF_A_LASTNAME-1]);
	
	/** */
	private String tooLongLastName = new String(new char[MMUserValidation.MAXIMUM_LENGHT_OF_A_LASTNAME+1]);
	
	/** */
	private String nullLastName = null;
	
	// Tests:
	
	@Test
	public void testLastNameIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isValidLastName(validLastName).generateExceptionIfNotValid();
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
			MMUserValidation.isValidLastName(nullLastName).generateExceptionIfNotValid();
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
			MMUserValidation.isValidLastName(tooShortLastName).generateExceptionIfNotValid();
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
			MMUserValidation.isValidLastName(tooLongLastName).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
