/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation.mmuser;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import de.hfu.meetme.validation.MMValidationUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMisValidDate
{

	/** */
	private Calendar nullBirthday = null;
	
	@Test
	public void testBirthdayIsNull()
	{
		boolean isExpected = true;
		
		try
		{
			MMValidationUser.isValidBirthday(nullBirthday).generateExceptionIfNotValid();
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	

}
