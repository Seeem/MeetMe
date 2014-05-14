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
public class MMValidFirstNameTest
{

	// Instance-Members:
	
	/** */
	private String validFirstName = "Simeon";
	
	/** */
	private String tooShortFirstName = "";
	
	/** */
	private String tooLongFirstName = "Thisisamuchtoolongfirstname";
	
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
