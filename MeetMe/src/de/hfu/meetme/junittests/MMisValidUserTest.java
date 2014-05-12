/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMisValidUserTest
{
	// Tests:
	
	@Test
	public void testUserIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			new MMUser(MMGender.MAN, "Sem", "Simeon", "Sembach", Calendar.getInstance(), "Hi I want to meet you!");
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
}
