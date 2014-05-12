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
public class MMisValidNicknameTest
{

	// Instance-Members:
	
	/** */
	private String validNickname = new String(new char[MMUserValidation.MINIMUM_LENGHT_OF_A_USERNAME]);
	
	/** */
	private String tooShortNickname = new String(new char[MMUserValidation.MINIMUM_LENGHT_OF_A_USERNAME-1]);
	
	/** */
	private String tooLongNickname = new String(new char[MMUserValidation.MAXIMUM_LENGHT_OF_A_USERNAME+1]);
	
	/** */
	private String nullNickname = null;
	
	// Tests:
	
	@Test
	public void testNicknameIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isValidUsername(validNickname).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testNicknameIsNull_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidUsername(nullNickname).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testNicknameIsTooShort_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidUsername(tooShortNickname).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testNicknameIsTooLong_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidUsername(tooLongNickname).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
