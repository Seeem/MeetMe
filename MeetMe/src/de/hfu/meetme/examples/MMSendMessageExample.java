/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.model.network.message.MMMessageEvent;
import de.hfu.meetme.model.network.message.MMMessageListener;
import de.hfu.meetme.model.network.message.MMMessageType;
import de.hfu.meetme.model.network.receiver.MMMessageReceiver;
import de.hfu.meetme.model.network.sender.MMMessageSender;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendMessageExample implements MMMessageListener
{
	
	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender = new MMMessageSender();

	/** */
	private MMMessageReceiver messageReceiver = new MMMessageReceiver(MMNetworkUtil.UDP_BROADCAST_PORT, MMNetworkUtil.UDP_PORT);
		
	// Example as test:

	@Test public void testMain()
	{
		try
		{
			messageReceiver.addMessageListener(this);
			messageReceiver.startReceiver();
			Thread.sleep(100);
			messageSender.sendUDPBroadcastMessage("Hallo Welt!");
			Thread.sleep(100);
			messageSender.sendUDPBroadcastMessage(MMMessageType.CONNECT, MMTestSupport.createANewValidUser());
			Thread.sleep(100);
			messageSender.sendUDPMessage(MMTestSupport.createANewValidUser("127.0.0.1"), "Hallo Welt!");
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
		if (aMessageEvent.isUdpProtocol())
		{
			if (aMessageEvent.isBroadcastMessage())
			{
				System.out.print("UDP broadcast message received: ");
			}
			else if (aMessageEvent.isSingleMessage())
			{
				System.out.print("UDP single message received: ");
			}
		}
		
		System.out.print(aMessageEvent.getTimestampAsString() + ": ");
		System.out.println(aMessageEvent.getMessage());
	}
	
}
