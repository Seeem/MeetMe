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
public class MMisValidDate
{

	/** */
	private Calendar nullBirthday = null;
	
	@Test
	public void testBirthdayIsNull_ShouldThrowException()
	{
		boolean isExpected = false;
		
		try
		{
			MMUserValidation.isValidBirthday(nullBirthday).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	

}
