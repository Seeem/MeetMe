/**
 * 
 */
package de.hfu.meetme.junittests;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;

/**
 * @author Simeon Sembach
 *
 */
public class MMValidUserTest
{
	// Tests:
	
	@Test
	public void testUserIsValid_ShouldPass()
	{
		MMTestSupport.createANewValidUser();
	}
	
}
