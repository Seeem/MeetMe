/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMMessageManager;

/**
 * @author Simeon Sembach
 *
 */
public class MMRefreshUsersExample
{
	
	// Example as test:

	// TODO
	@Test public void testExample() throws InterruptedException
	{
		MMUser.setMyself(MMTestSupport.createANewValidUser());		
		MMMessageManager theMessageManager = new MMMessageManager();		
		theMessageManager.startListening();
		theMessageManager.refreshUsers();
		Thread.sleep(1000);
		theMessageManager.stopListening();			
		MMUser.printUsers();
	}

}
