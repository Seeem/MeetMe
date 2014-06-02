/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMUserTest
{
	
	// Tests:
	
	@Test
	public void testUserIsValid_ShouldPass()
	{
		MMTestSupport.createANewValidUser();
		assertTrue(true);
	}
	
	@Test
	public void testUserAreEqual_ShouldPass()
	{
		MMUser theUser_1 = MMTestSupport.createANewValidUser();
		MMUser theUser_2 = MMTestSupport.createANewValidUser();
		MMUser theUser_3 = MMTestSupport.createANewValidUser();
		assertTrue(theUser_1.equals(theUser_2) && theUser_2.equals(theUser_1) && theUser_2.equals(theUser_3) && theUser_1.equals(theUser_3) && !theUser_1.equals(null));
	}
	
	@Test
	public void testUserToUdp_ShouldPass()
	{
		MMUser theUser_1 = MMTestSupport.createANewValidUser();
		MMUser theUser_2 = MMUser.valueOf(theUser_1.toUdpMessage());		
		assertTrue(theUser_1.equals(theUser_2));		
	}
	
	@Test
	public void testGetUserById_ShouldPass()
	{
		MMUser.initializeUsers();
		MMUser theUser_1 = MMTestSupport.createANewValidUser();	
		MMUser.addUserIfNotAlreadyAdded(theUser_1);
		MMUser theUser_2 = MMUser.getUserById(theUser_1.getId());
		MMUser theUser_3 = MMUser.getUserById("");
		assertTrue(theUser_1.equals(theUser_2) && theUser_3 == null);
	}
	
}
