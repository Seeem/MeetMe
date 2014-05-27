/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageType;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendUsers
{
	
	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender = new MMMessageSender();

	// Example as test:

	@Test public void testMain()
	{
		for (int i=0 ; i < 10 ; i++)
			messageSender.sendUDPBroadcastMessage(MMMessageType.CONNECT, MMTestSupport.createANewValidUser(String.valueOf(i)));
	}
	
}
