/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation.mmuser;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMisValidUser
{
	// Tests:
	
	@Test
	public void testUserIsValid_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			new MMUser(
					"Sem",
					"Simeon",
					"Sembach",
					Calendar.getInstance(),
					"Hi I want to meet you!");
		} 
		catch (IllegalArgumentException e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
}
