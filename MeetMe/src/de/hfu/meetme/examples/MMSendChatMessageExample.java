/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.network.sender.MMMessageSender;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendChatMessageExample
{
	
	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender = new MMMessageSender();

	// Example as test:

	@Test public void testMain()
	{	
		messageSender.sendUDPMessage(MMTestSupport.createANewValidUser("192.168.119.53"), "Bla bla bla.");
	}
	
}
