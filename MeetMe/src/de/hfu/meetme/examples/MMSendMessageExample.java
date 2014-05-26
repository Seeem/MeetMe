/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.message.MMUserMessage;
import de.hfu.meetme.model.network.MMMessageEvent;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageProtocol;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageTargetType;
import de.hfu.meetme.model.network.MMMessageType;
import de.hfu.meetme.model.network.MMNetworkUtil;

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
	private MMMessageReceiver messageReceiver = new MMMessageReceiver();
		
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
			messageSender.sendUDPMessage(MMNetworkUtil.getLocalhostAddress(), "Hallo Welt!");
			Thread.sleep(100);
			messageSender.sendTCPMessage(MMNetworkUtil.getLocalhostAddress(), new MMUserMessage(MMTestSupport.createANewValidUser()));		
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
		if (aMessageEvent.getMessageProtocol() == MMMessageProtocol.UDP)
		{
			if (aMessageEvent.getMessageTargetType() == MMMessageTargetType.BROADCAST)
			{
				System.out.print("UDP broadcast message received: ");
			}
			else if (aMessageEvent.getMessageTargetType() == MMMessageTargetType.SINGLE)
			{
				System.out.print("UDP single message received: ");
			}
		}
		else if (aMessageEvent.getMessageProtocol() == MMMessageProtocol.TCP)
		{
			System.out.print("TCP message received: ");	
		}
		
		System.out.print(aMessageEvent.getTimestampAsString() + ": ");
		System.out.println(aMessageEvent.getMessageAsString());
	}
	
}
