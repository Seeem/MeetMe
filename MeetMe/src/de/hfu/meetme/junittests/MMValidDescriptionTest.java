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
public class MMValidDescriptionTest
{

	// Instance-Members:
	
	/** */
	private String validDescription = "I want to meet you!";
	
	/** */
	private String tooLongDescription = new String(new char[101]);
	
	/** */
	private String nullDescription = null;
	
	// Tests:
	
	@Test
	public void testDescriptionIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isValidDescription(validDescription).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testDescriptionIsNull_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidDescription(nullDescription).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testDescriptionIsTooLong_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidDescription(tooLongDescription).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
