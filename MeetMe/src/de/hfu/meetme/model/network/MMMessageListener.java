/**
 * 
 */
package de.hfu.meetme.model.network;

import java.net.DatagramPacket;

import de.hfu.meetme.model.message.MMMessage;

/**
 * @author Simeon Sembach
 *
 */
public interface MMMessageListener
{

	public void UDPBroadcastMessageReceived(DatagramPacket aDatagramPacket);
	
	public void UDPSingleMessageReceived(DatagramPacket aDatagramPacket);
	
	public void TCPMessageReceived(MMMessage aMessage);
	
}
