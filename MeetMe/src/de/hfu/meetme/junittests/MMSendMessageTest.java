/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.message.MMUserMessage;
import de.hfu.meetme.model.network.MMMessageEvent;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageProtocoll;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageType;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendMessageTest implements MMMessageListener
{

	// Instance-Members:
	
	/** */
	private boolean hasUDPBroadcastMessageReceived = false;
	
	/** */
	private boolean hasUDPSingleMessageReceived = false;
	
	/** */
	private boolean hasTCPMessageReceived = false;
	
	// Tests:
	
	@Test
	public void testSendAndReceiveAnUDPBroadcastMessage() throws IOException, InterruptedException
	{
		MMMessageReceiver.addMessageListener(this);
		MMMessageReceiver.startReceiver();
		
		MMMessageSender.startSender();	
		MMMessageSender.sendUDPBroadcastMessage(MMNetworkUtil.UDP_MESSAGE_PING);
		
		Thread.sleep(100);
		
		MMMessageSender.stopSender();
		MMMessageReceiver.stopReceiver();
		MMMessageReceiver.removeMessageListener(this);
		
		assertTrue(hasUDPBroadcastMessageReceived);
	}

	@Test
	public void testSendAndReceiveAnUDPSingleMessage() throws IOException, InterruptedException
	{
		MMMessageReceiver.addMessageListener(this);
		MMMessageReceiver.startReceiver();
		
		MMMessageSender.startSender();	
		MMMessageSender.sendUDPMessage(MMNetworkUtil.getLocalhostAddress(), MMNetworkUtil.UDP_MESSAGE_PING);
		
		Thread.sleep(100);
		
		MMMessageSender.stopSender();
		MMMessageReceiver.stopReceiver();
		MMMessageReceiver.removeMessageListener(this);
		
		assertTrue(hasUDPSingleMessageReceived);
	}
	
	@Test
	public void testSendAndReceiveAnTCPMessage() throws IOException, InterruptedException
	{
		MMMessageReceiver.addMessageListener(this);
		MMMessageReceiver.startReceiver();
		
		MMMessageSender.startSender();	
		MMMessageSender.sendTCPMessage(MMNetworkUtil.getLocalhostAddress(), new MMUserMessage(MMTestSupport.createANewValidUser()));
	
		Thread.sleep(100);
		
		MMMessageSender.stopSender();
		MMMessageReceiver.stopReceiver();
		MMMessageReceiver.removeMessageListener(this);
		
		assertTrue(hasTCPMessageReceived);
	}
	
	// Implementors:
	
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{
		if (aMessageEvent.getMessageProtocoll() == MMMessageProtocoll.UDP)
		{
			if (aMessageEvent.getMessageType() == MMMessageType.BROADCAST)
			{
				hasUDPBroadcastMessageReceived = true;
			}
			else if (aMessageEvent.getMessageType() == MMMessageType.SINGLE)
			{
				hasUDPSingleMessageReceived = true;
			}
		}
		else if (aMessageEvent.getMessageProtocoll() == MMMessageProtocoll.TCP)
		{
			hasTCPMessageReceived = true;	
		}
	}
	
}
