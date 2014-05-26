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
import de.hfu.meetme.model.network.MMMessageProtocol;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMMessageTargetType;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendMessageTest implements MMMessageListener
{

	// Instance-Members:
	
	/** */
	private MMMessageSender messageSender = new MMMessageSender();

	/** */
	private MMMessageReceiver messageReceiver = new MMMessageReceiver();
	
	/** */
	private boolean hasUDPBroadcastMessageReceived = false;
	
	/** */
	private boolean hasUDPSingleMessageReceived = false;
	
	/** */
	private boolean hasTCPMessageReceived = false;
	
	// Tests:
	
	@Test
	public void testSendAndReceiveAnUDPBroadcastMessage() throws IOException
	{
		try
		{
			messageReceiver.addMessageListener(this);
			messageReceiver.startReceiver();		
			messageSender.sendUDPBroadcastMessage(MMNetworkUtil.UDP_MESSAGE_PING);		
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
	
		assertTrue(hasUDPBroadcastMessageReceived);
	}

	@Test
	public void testSendAndReceiveAnUDPSingleMessage() throws IOException
	{
		try
		{
			messageReceiver.addMessageListener(this);
			messageReceiver.startReceiver();	
			messageSender.sendUDPMessage(MMNetworkUtil.getLocalhostAddress(), MMNetworkUtil.UDP_MESSAGE_PING);
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

		assertTrue(hasUDPSingleMessageReceived);
	}
	
	@Test
	public void testSendAndReceiveAnTCPMessage() throws IOException
	{
		try
		{
			messageReceiver.addMessageListener(this);
			messageReceiver.startReceiver();				
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
		
		assertTrue(hasTCPMessageReceived);
	}
	
	// Implementors:
	
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{
		if (aMessageEvent.getMessageProtocol() == MMMessageProtocol.UDP)
		{
			if (aMessageEvent.getMessageTargetType() == MMMessageTargetType.BROADCAST)
			{
				hasUDPBroadcastMessageReceived = true;
			}
			else if (aMessageEvent.getMessageTargetType() == MMMessageTargetType.SINGLE)
			{
				hasUDPSingleMessageReceived = true;
			}
		}
		else if (aMessageEvent.getMessageProtocol() == MMMessageProtocol.TCP)
		{
			hasTCPMessageReceived = true;	
		}
	}
	
}
