/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * @author Simeon Sembach
 *
 */
public class MMgenderTest
{

	@Test
	public void testGenderIsMan_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isMan(MMGender.MAN).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

	@Test
	public void testGenderIsNoMan_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isMan(MMGender.WOMAN).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testGenderIsWoman_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isWoman(MMGender.WOMAN).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

	@Test
	public void testGenderIsNoWoman_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isWoman(MMGender.MAN).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
}
