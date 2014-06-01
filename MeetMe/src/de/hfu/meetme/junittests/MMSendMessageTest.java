/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.hfu.meetme.model.network.MMMessageEvent;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.model.network.receiver.MMMessageReceiver;

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
	private MMMessageReceiver messageReceiver = new MMMessageReceiver(MMNetworkUtil.UDP_BROADCAST_PORT, MMNetworkUtil.UDP_PORT);
	
	/** */
	private boolean hasUDPBroadcastMessageReceived = false;
	
	/** */
	private boolean hasUDPSingleMessageReceived = false;
	
	// Tests:
	
	@Test
	public void testSendAndReceiveAnUDPBroadcastMessage()
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
	public void testSendAndReceiveAnUDPSingleMessage()
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
	
	// Implementors:
	
	@Override public void messageReceived(MMMessageEvent aMessageEvent)
	{
		if (aMessageEvent.isUdpProtocol())
		{
			if (aMessageEvent.isBroadcastMessage())
			{
				hasUDPBroadcastMessageReceived = true;
			}
			else if (aMessageEvent.isSingleMessage())
			{
				hasUDPSingleMessageReceived = true;
			}
		}
	}
	
}
