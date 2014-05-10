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
public class MMisValidNickname
{

	// Instance-Members:
	
	/** */
	private String validNickname = new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_NICKNAME]);
	
	/** */
	private String tooShortNickname = new String(new char[MMValidationUser.MINIMUM_LENGHT_OF_A_NICKNAME-1]);
	
	/** */
	private String tooLongNickname = new String(new char[MMValidationUser.MAXIMUM_LENGHT_OF_A_NICKNAME+1]);
	
	/** */
	private String nullNickname = null;
	
	// Tests:
	
	@Test
	public void testNicknameIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMValidationUser.isValidNickname(validNickname).generateExceptionIfNotValid();
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
			MMValidationUser.isValidNickname(nullNickname).generateExceptionIfNotValid();
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
			MMValidationUser.isValidNickname(tooShortNickname).generateExceptionIfNotValid();
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
			MMValidationUser.isValidNickname(tooLongNickname).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
