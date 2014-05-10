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
public class MMisValidDescription
{

	// Instance-Members:
	
	/** */
	private String validDescription = new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_DESCRIPTION]);
	
	/** */
	private String tooLongDescription = new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_DESCRIPTION+1]);
	
	/** */
	private String nullDescription = null;
	
	// Tests:
	
	@Test
	public void testDescriptionIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMValidationUser.isValidDescription(validDescription).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
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
			MMValidationUser.isValidDescription(nullDescription).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
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
			MMValidationUser.isValidDescription(tooLongDescription).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
