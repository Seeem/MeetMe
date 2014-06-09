/**
 * 
 */
package de.hfu.meetme.examples;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

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
		try
		{
			messageSender.sendUDPMessage(InetAddress.getByName("192.168.119.53"), "Bla bla bla.");
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
