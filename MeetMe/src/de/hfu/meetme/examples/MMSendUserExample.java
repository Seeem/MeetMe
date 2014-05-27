/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.network.MMMessageEvent;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageType;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendUserExample implements MMMessageListener
{
	
	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender = new MMMessageSender();

	/** */
	private MMMessageReceiver messageReceiver = new MMMessageReceiver();
		
	// Example as test:

	@Test public void testMain()
	{
		try
		{
			messageReceiver.addMessageListener(this);
			messageReceiver.startReceiver();
			Thread.sleep(100);
			messageSender.sendUDPBroadcastMessage(MMMessageType.CONNECT, MMTestSupport.createANewValidUser());
			Thread.sleep(100);
		} 
		catch (Exception anException)
		{
			anException.printStackTrace();
		}
		finally
		{
			messageReceiver.stopReceiver();
			messageReceiver.removeMessageListener(this);
		}
	}

	// Implementors:
	
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{
		
	}
	
}
