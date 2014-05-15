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
public class MMValidUsernameTest
{

	// Instance-Members:
	
	/** */
	private String validUsername = "Username";
	
	/** */
	private String tooShortUsername = "";
	
	/** */
	private String tooLongUsername = "ThisIsATooLongUserName";
	
	/** */
	private String nullUsername = null;
	
	// Tests:
	
	@Test
	public void testUsernameIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isValidUsername(validUsername).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testUsernameIsNull_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidUsername(nullUsername).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testUsernameIsTooShort_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidUsername(tooShortUsername).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testUsernameIsTooLong_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidUsername(tooLongUsername).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
