/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMAddAndRemoveUsersTest
{

	@Test
	public void testAddAValidUser_ShouldPass()
	{
		MMUser.initializeUsers();
		MMUser theUser = MMTestSupport.createANewValidUser("ID1");
		int theOldSize = MMUser.size();
		MMUser.addUser(theUser);
		int theNewSize = MMUser.size();		
		assertTrue(theOldSize != theNewSize && MMUser.containsUser(theUser));
	}
	
	@Test
	public void testAdd_10_ValidUser_ShouldPass()
	{
		MMUser.initializeUsers();
		ArrayList<MMUser> theUsers = new ArrayList<>();
		for (int i=0 ; i<10 ; i++)
			theUsers.add(MMTestSupport.createANewValidUser("ID"+i));		
		int theOldSize = MMUser.size();
		for (MMUser aUser : theUsers)
			MMUser.addUser(aUser);
		int theNewSize = MMUser.size();
		boolean theHasErrorBoolean = false;
		for (MMUser aUser : theUsers)
		{
			if (!MMUser.containsUser(aUser))
			{
				theHasErrorBoolean = true;
				break;
			}
		}
		assertTrue(theOldSize != theNewSize && theNewSize == 10 && !theHasErrorBoolean);
	}
	
	@Test
	public void testAdd_100_ValidUser_ShouldPass()
	{
		MMUser.initializeUsers();
		ArrayList<MMUser> theUsers = new ArrayList<>();
		for (int i=0 ; i<100 ; i++)
			theUsers.add(MMTestSupport.createANewValidUser("ID"+i));		
		int theOldSize = MMUser.size();
		for (MMUser aUser : theUsers)
			MMUser.addUser(aUser);
		int theNewSize = MMUser.size();
		boolean theHasErrorBoolean = false;
		for (MMUser aUser : theUsers)
		{
			if (!MMUser.containsUser(aUser))
			{
				theHasErrorBoolean = true;
				break;
			}
		}
		assertTrue(theOldSize != theNewSize && theNewSize == 100 && !theHasErrorBoolean);
	}
	
	@Test
	public void testAddAAlreadyExistingUser_ShouldThrowException()
	{
		MMUser.initializeUsers();
		MMUser theUser = MMTestSupport.createANewValidUser("ID1");		
		MMUser.addUser(theUser);
		int theOldSize = MMUser.size();
		boolean theHasErrorBoolean = false;
		try
		{
			MMUser.addUser(theUser);
		}
		catch (Exception e)
		{
			theHasErrorBoolean = true;
		}
		int theNewSize = MMUser.size();		
		assertTrue(theOldSize == theNewSize && theHasErrorBoolean);
	}

	@Test
	public void testAddUserIfNotAlreadyAdded_ShouldPass()
	{	
		MMUser.initializeUsers();
		
		boolean theHasErrorBoolean = false;
		
		try
		{
			MMUser theUser = MMTestSupport.createANewValidUser();
			MMUser.addUserIfNotAlreadyAdded(theUser);
			MMUser.addUserIfNotAlreadyAdded(theUser);
		}
		catch (Exception anException)
		{
			anException.printStackTrace();
			theHasErrorBoolean = true;
		}

		assertTrue(MMUser.size() == 1 && !theHasErrorBoolean);
	}
	
	@Test
	public void testRemoveAUser_ShouldPass()
	{
		MMUser.initializeUsers();
		MMUser theUser = MMTestSupport.createANewValidUser("ID1");
		int theOldSize = MMUser.size();
		MMUser.addUser(theUser);
		MMUser.removeUser(theUser);
		int theNewSize = MMUser.size();		
		assertTrue(theOldSize == theNewSize && !MMUser.containsUser(theUser));
	}
	
	@Test
	public void testRemoveANotExistingUser_ShouldThrowException()
	{
		MMUser.initializeUsers();
		MMUser theUser = MMTestSupport.createANewValidUser("ID1");
		int theOldSize = MMUser.size();
		boolean theHasErrorBoolean = false;
		try
		{
			MMUser.removeUser(theUser);
		}
		catch (Exception e)
		{
			theHasErrorBoolean = true;
		}
		int theNewSize = MMUser.size();		
		assertTrue(theOldSize == theNewSize && theHasErrorBoolean);
	}
	
	@Test
	public void testAdd_10_UsersThenRemoveAll_ShouldPass()
	{
		MMUser.initializeUsers();
		ArrayList<MMUser> theUsers = new ArrayList<>();
		for (int i=0 ; i<10 ; i++)
			theUsers.add(MMTestSupport.createANewValidUser("ID"+i));		
		int theOldSize = MMUser.size();
		for (MMUser aUser : theUsers)
			MMUser.addUser(aUser);
		MMUser.removeAllUsers();
		int theNewSize = MMUser.size();
		boolean theHasErrorBoolean = false;
		for (MMUser aUser : theUsers)
		{
			if (MMUser.containsUser(aUser))
			{
				theHasErrorBoolean = true;
				break;
			}
		}
		assertTrue(theOldSize == theNewSize && theNewSize == 0 && !theHasErrorBoolean);
	}

}
