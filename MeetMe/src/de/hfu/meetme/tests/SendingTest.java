/**
 * 
 */
package de.hfu.meetme.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;

import org.junit.Test;

import de.hfu.anybeam.networkCore.Client;
import de.hfu.anybeam.networkCore.DataReceiver;
import de.hfu.anybeam.networkCore.DataReceiverAdapter;
import de.hfu.anybeam.networkCore.DeviceType;
import de.hfu.anybeam.networkCore.EncryptionType;
import de.hfu.anybeam.networkCore.NetworkEnvironmentSettings;
import de.hfu.anybeam.networkCore.TransmissionEvent;
import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.message.MMUserMessage;

/**
 * @author Simeon Sembach
 * 
 */
public class SendingTest implements DataReceiverAdapter
{

	@Test
	public void test() throws Exception
	{
		Client theTargetClient = new Client(
				InetAddress.getLocalHost(),
				"Target Client", 
				1338, 
				"noid", 
				System.getProperty("os.name"),
				DeviceType.TYPE_UNKNOWN);

		EncryptionType theEncryptionType = EncryptionType.NONE;
		
		NetworkEnvironmentSettings theSetting = new NetworkEnvironmentSettings(
				"Test Device",
				DeviceType.TYPE_UNKNOWN,
				theEncryptionType, 
				1338, 
				1337,
				new byte[0]);
		
		new DataReceiver(theSetting, this);
		
		sendUser(theSetting, theTargetClient, MMTestSupport.createANewValidUser(""));
	}

	/** */
	private void sendUser(NetworkEnvironmentSettings aSetting, Client aClient, MMUser aUser) throws Exception
	{
		ByteArrayOutputStream theOutputStream 	 = new ByteArrayOutputStream();
		MMUserMessage theUserMessage 			 = new MMUserMessage(aUser);		
		ObjectOutputStream theObjectOutputStream = new ObjectOutputStream(theOutputStream);
		
		theObjectOutputStream.writeObject(theUserMessage);
		theObjectOutputStream.close();
		
		aClient.sendData(new ByteArrayInputStream(theOutputStream.toByteArray()), aSetting);
	}
	
	/** */
	@Override public OutputStream downloadStarted(TransmissionEvent aE, String aClientId)
	{
		try
		{
			System.out.println("Download started");
			return new ByteArrayOutputStream();
		} 
		catch (Exception e) 
		{
			return System.err;
		}
	}
	
	@Override public void transmissionStarted(TransmissionEvent aE)
	{
		System.out.println("Transmission started");
	}

	@Override public void transmissionProgressChanged(TransmissionEvent aE)
	{
		System.out.println("Transmission progrss changed");	
	}

	@Override public void transmissionDone(TransmissionEvent aE)
	{
		System.out.println("Transmission done");
	}

	@Override public void transmissionFailed(TransmissionEvent aE)
	{
		System.out.println("Transmission failed");
	}

	@Override public void closeOutputStream(TransmissionEvent aE, OutputStream aOut)
	{
		System.out.println("close Output stream");
	}

}
