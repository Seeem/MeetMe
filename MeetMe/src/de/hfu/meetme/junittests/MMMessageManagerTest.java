/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMMessageManager;

/**
 * @author Simeon Sembach
 *
 */
public class MMMessageManagerTest
{

	@Test
	public void test() throws InterruptedException
	{
		MMUser.initializeUsers();
		MMUser.setMyself(MMTestSupport.createANewValidUser());		
		MMMessageManager theMessageManager = new MMMessageManager();		
		theMessageManager.startListening();
		theMessageManager.refreshUsers();
		Thread.sleep(100);
		theMessageManager.stopListening();		
		assertTrue(!MMUser.containsUser(MMUser.getMyself()));
	}

}
