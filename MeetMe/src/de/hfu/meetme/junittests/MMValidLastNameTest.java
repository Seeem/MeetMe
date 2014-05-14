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
public class MMValidLastNameTest
{

	// Instance-Members:
	
	/** */
	private String validLastName = "Sembach";
	
	/** */
	private String tooShortLastName = "";
	
	/** */
	private String tooLongLastName = "Thisisamuchtoolonglastname";
	
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
