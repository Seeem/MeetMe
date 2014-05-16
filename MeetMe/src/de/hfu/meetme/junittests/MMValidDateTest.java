/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * @author Simeon Sembach
 *
 */
public class MMValidDateTest
{

	/** */
	private Calendar nullBirthday = null;
	
	/** */
	private Calendar getValidBirthday_1()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MINIMUM_AGE_OF_AN_USER);
		return theBirthday;
	}
	
	/** */
	private Calendar getValidBirthday_2()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MINIMUM_AGE_OF_AN_USER-1);
		return theBirthday;
	}
	
	/** */
	private Calendar getValidBirthday_3()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MINIMUM_AGE_OF_AN_USER);
		if (theBirthday.get(Calendar.MONTH)  != theBirthday.getActualMaximum(theBirthday.get(Calendar.MONTH)))	
			theBirthday.set(Calendar.MONTH, theBirthday.get(Calendar.MONTH)+1);	
		return theBirthday;
	}
	
	/** */
	private Calendar getValidBirthday_4()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MINIMUM_AGE_OF_AN_USER);
		if (theBirthday.get(Calendar.DAY_OF_MONTH) != theBirthday.getActualMaximum(theBirthday.get(Calendar.DAY_OF_MONTH) ))
			theBirthday.set(Calendar.DAY_OF_MONTH, theBirthday.get(Calendar.DAY_OF_MONTH)+1);
		return theBirthday;
	}

	/** */
	private Calendar getValidBirthday_5()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MINIMUM_AGE_OF_AN_USER-1);
		if (theBirthday.get(Calendar.MONTH) != theBirthday.getActualMinimum(theBirthday.get(Calendar.MONTH)))
			theBirthday.set(Calendar.MONTH, theBirthday.get(Calendar.MONTH)-1);
		return theBirthday;
	}
	
	/** */
	private Calendar getValidBirthday_6()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MINIMUM_AGE_OF_AN_USER-1);
		if (theBirthday.get(Calendar.DAY_OF_MONTH) != theBirthday.getActualMinimum(theBirthday.get(Calendar.DAY_OF_MONTH)))
			theBirthday.set(Calendar.DAY_OF_MONTH, theBirthday.get(Calendar.DAY_OF_MONTH)-1);
		return theBirthday;
	}
	
	/** */
	private Calendar getBirthdayInTheFuture()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)+1);
		return theBirthday;
	}
	
	/** */
	private Calendar getTooYoungUserBirthday()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MINIMUM_AGE_OF_AN_USER+1);
		return theBirthday;
	}
	
	/** */
	private Calendar getTooOldUserBirthday()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-MMUserValidation.MAXIMUM_AGE_OF_AN_USER-1);
		return theBirthday;
	}
	
	// Tests:
	
	@Test
	public void testBirthdayIsNull_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidBirthday(nullBirthday).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testBirthdayIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			MMUserValidation.isValidBirthday(getValidBirthday_1()).generateExceptionIfNotValid();
			MMUserValidation.isValidBirthday(getValidBirthday_2()).generateExceptionIfNotValid();
			MMUserValidation.isValidBirthday(getValidBirthday_3()).generateExceptionIfNotValid();
			MMUserValidation.isValidBirthday(getValidBirthday_4()).generateExceptionIfNotValid();
			MMUserValidation.isValidBirthday(getValidBirthday_5()).generateExceptionIfNotValid();
			MMUserValidation.isValidBirthday(getValidBirthday_6()).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	@Test
	public void testUserBirthdayIsTooYoung_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidBirthday(getTooYoungUserBirthday()).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
		
	@Test
	public void testUserBirthdayIsTooOld_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidBirthday(getTooOldUserBirthday()).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
		
	@Test
	public void testUserBirthdayIsInTheFuture_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidBirthday(getBirthdayInTheFuture()).generateExceptionIfNotValid();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
}
