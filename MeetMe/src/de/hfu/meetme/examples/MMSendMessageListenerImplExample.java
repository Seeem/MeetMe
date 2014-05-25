/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMMessageManager;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendMessageListenerImplExample
{
	
	// Example as test:

	// TODO
	@Test public void testMain()
	{
		// Create and Start the message listener.
		MMMessageManager theMessageListenerImpl = new MMMessageManager();		
		theMessageListenerImpl.startListening();
			
		// Create some Users, first User is myself.
		MMUser theUser_1 = new MMUser(MMNetworkUtil.getMyLanAddressAsString(), MMGender.MALE, "ForzenAngel", "Simeon", "Sembach", MMTestSupport.getValidBirthday());
		MMUser theUser_2 = new MMUser("192.0.0.1", MMGender.MALE, "SexyHexy", "Gina", "Wild", MMTestSupport.getValidBirthday());
		MMUser theUser_3 = new MMUser("192.0.0.2", MMGender.MALE, "DeGangstaJoda", "Dominik", "Jung", MMTestSupport.getValidBirthday());
		
		// Set and refresh.
		MMUser.setMyself(theUser_1);			
		theMessageListenerImpl.refreshUsers();
		MMUser.setMyself(theUser_2);			
		theMessageListenerImpl.refreshUsers();
		MMUser.setMyself(theUser_3);			
		theMessageListenerImpl.refreshUsers();
		
		// Print all added users.
		MMUser.printUsers();
		
		// Stop the listener.
		theMessageListenerImpl.stopListening();
	}

}
