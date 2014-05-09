/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidationutil;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidNickName_NickNameIsTooShort
{

	@Test
	public void test()
	{
		boolean isExpected = false;
		
		MMSupport theSupport = new MMSupport();
		
		try
		{
			new MMUser(theSupport.getTooShortNickname(),
					theSupport.getValidFirstName(),
					theSupport.getValidLastName(),
					theSupport.getValidDate(),
					theSupport.getValidDescription());
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}

}
