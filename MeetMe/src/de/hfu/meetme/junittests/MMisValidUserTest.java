/**
 * 
 */
package de.hfu.meetme.junittests;

import org.junit.Test;

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
		MMTestSupport.createANewValidUser();
	}
	
}
