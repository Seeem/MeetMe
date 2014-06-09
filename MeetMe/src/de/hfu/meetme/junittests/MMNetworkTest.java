/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.model.network.message.MMMessageEvent;
import de.hfu.meetme.model.network.message.MMMessageListener;
import de.hfu.meetme.model.network.receiver.MMMessageReceiver;
import de.hfu.meetme.model.network.sender.MMMessageSender;

/**
 * @author Simeon Sembach
 *
 */
public class MMNetworkTest
{
	
	// Instance-Members:
	
	/** Needed for the testIsMyLanAdress-Test. */
	public boolean theHasErrorBoolean = false;

	// The Tests:
	
	/** 
	 * Network connection needed for this test!
	 */
	@Test public void testGetLocalHostAddress_ShouldPass()
	{		
		assertNotNull(MMNetworkUtil.getLocalhostAddress());
	}

	/** 
	 * Network connection needed for this test!
	 */
	@Test public void testGetBroadcastAddress_ShouldPass()
	{		
		assertNotNull(MMNetworkUtil.getBroadcastAddress());
	}
	
	/** 
	 * Network connection needed for this test!
	 */
	@Test public void testGetMyLanAddress_ShouldPass()
	{		
		assertNotNull(MMNetworkUtil.getMyLanAddress());
	}

	/** 
	 * Network connection needed for this test!
	 */
	@Test public void testGetMyLanAddressAsString_ShouldPass()
	{		
		assertNotNull(MMNetworkUtil.getMyLanAddressAsString());
	}
	
	/** 
	 * Network connection needed for this test!
	 */
	@Test public void testIsMyLanAddress_ShouldPass() throws InterruptedException
	{	
		MMMessageSender theMessageSender = new MMMessageSender();
		MMMessageReceiver theMessageReceiver = new MMMessageReceiver(MMNetworkUtil.UDP_BROADCAST_PORT, MMNetworkUtil.UDP_PORT);
		MMMessageListener theMessageListener = new MMMessageListener()
		{			
			@Override
			public void messageReceived(MMMessageEvent aMessageEvent)
			{
				if (!MMNetworkUtil.isMyLanAddress(aMessageEvent.getSenderAddress()))
					theHasErrorBoolean = true;
			}
		};	
		theMessageReceiver.addMessageListener(theMessageListener);
		theMessageReceiver.startReceiver();
		theMessageSender.sendUDPMessage(MMTestSupport.createANewValidUser(MMNetworkUtil.getMyLanAddressAsString()), "Hello World!");
		Thread.sleep(100);
		theMessageReceiver.stopReceiver();
		assertFalse(theHasErrorBoolean);
	}
	
}
