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
public class MMSendUserExample
{
	
	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender = new MMMessageSender();

	// Example as test:

	@Test public void testMain()
	{
		messageSender.sendUDPBroadcastMessage(MMMessageType.CONNECT, MMTestSupport.createANewValidUser());
	}
	
}
