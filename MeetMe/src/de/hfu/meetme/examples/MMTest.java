/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMMessageManager;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageType;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMTest
{
	
	// Example as test:

	// TODO
	@Test public void testMain() throws InterruptedException
	{
		MMMessageSender theMessageSender = new MMMessageSender();
		
		theMessageSender.sendUDPBroadcastMessage(MMMessageType.CONNECT, MMTestSupport.createANewValidUser("sdfsldk"));
		
		
//		System.out.println(MMNetworkUtil.getMyLanAddressAsString());
			
//		MMUser.setMyself(MMTestSupport.createANewValidUser());		
//		MMMessageManager theMessageManager = new MMMessageManager();		
//		theMessageManager.startListening();
//		theMessageManager.refreshUsers();
//		Thread.sleep(1000);
//		theMessageManager.stopListening();			
//		MMUser.printUsers();
	}

}
