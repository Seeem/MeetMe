/**
 * 
 */
package de.hfu.meetme.tests;

import java.io.IOException;
import java.net.DatagramPacket;

import de.hfu.meetme.model.message.MMIWantToMeetYouMessage;
import de.hfu.meetme.model.message.MMIWantToMeetYouTooMessage;
import de.hfu.meetme.model.message.MMMessage;
import de.hfu.meetme.model.message.MMUserMessage;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 * 
 */
public class Test implements MMMessageListener
{

	MMMessageSender messageSender;
	
	@org.junit.Test
	public void test() throws IOException, InterruptedException
	{
		MMMessageReceiver theMessageReceiver = new MMMessageReceiver(this);
		
		theMessageReceiver.startSearching();
	
		messageSender = new MMMessageSender();	
		messageSender.sendUDPBroadcastMessage(MMNetworkUtil.NEW_IN_THE_NETWORK);
	
		Thread.sleep(1000);
		
		theMessageReceiver.stopSearching();
	}

	@Override public void UDPBroadcastMessageReceived(DatagramPacket aDatagramPacket)
	{
		String theDataAsString = new String(aDatagramPacket.getData(), 0, aDatagramPacket.getLength());		
		
		if (theDataAsString.equals(MMNetworkUtil.NEW_IN_THE_NETWORK))
		{
			try
			{
				messageSender.sendTCPMessage(aDatagramPacket.getAddress(), new MMIWantToMeetYouMessage("99"));
				
//				messageSender.sendUDPMessage(aDatagramPacket.getAddress(), MMNetworkUtil.ADD_ME);
			} 
			catch (Exception e) {}	
		}
	}

	@Override public void UDPSingleMessageReceived(DatagramPacket aDatagramPacket)
	{
		String theDataAsString = new String(aDatagramPacket.getData(), 0, aDatagramPacket.getLength());
		
		switch (theDataAsString)
		{
			case MMNetworkUtil.ADD_ME :
			{
				try
				{
//					messageSender.sendUDPMessage(aDatagramPacket.getAddress(), MMNetworkUtil.ADD_ME);
				} 
				catch (Exception e) {}	
				break;
			}
		}
	}


	@Override public void TCPMessageReceived(MMMessage aMessage)
	{
		if (aMessage instanceof MMUserMessage)
		{
//			MMUserMessage theUserMessage = (MMUserMessage) aMessage;
			
			System.out.println("User Message received");
		}
		else if (aMessage instanceof MMIWantToMeetYouMessage)
		{
//			MMIWantToMeetYouMessage theIWantToMeetYouMessage = (MMIWantToMeetYouMessage) aMessage;
			
			System.out.println("I want to meet you message received");
		}
		else if (aMessage instanceof MMIWantToMeetYouTooMessage)
		{
//			MMIWantToMeetYouTooMessage theIWantToMeetYouTooMessage = (MMIWantToMeetYouTooMessage) aMessage;
			
			System.out.println("I want to meet you too message received");
		}
		
		System.out.println(aMessage);
	}

}
