/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.network.message.MMMessageType;
import de.hfu.meetme.model.network.sender.MMMessageSender;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendMeetMesExample
{
	
	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender = new MMMessageSender();

	// Example as test:

	@Test public void testMain()
	{
		for (int i=0 ; i < 1 ; i++)
		{
			messageSender.sendUDPMessage(MMTestSupport.createANewValidUser("192.168.119.54").getIpAddress(), MMMessageType.MEETME, MMTestSupport.createANewValidUser());
		}		
	}
	
}
