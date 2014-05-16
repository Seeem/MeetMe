/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.message.MMUserMessage;

/**
 * @author Simeon Sembach
 *
 */
public class MMSendMessageTest
{

	/** */
	private ByteArrayOutputStream sendingStream;
	
	// TODO Implement a "real" sending test
	
	// Tests:
	
	@Test
	public void testSendOnALocalByteStream_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			sendUserOnLocalByteStream(MMTestSupport.createANewValidUser());		
			receiveUserOnLocalByteStream();
		} 
		catch (Exception e)
		{
			isExpected = !isExpected;
		}
		
		assertTrue(isExpected);
	}
	
	// Internals (Instance):
	
	/** */
	private void sendUserOnLocalByteStream(MMUser aUser) throws Exception
	{
		setSendingStream(new ByteArrayOutputStream());
		MMUserMessage theUserMessage = new MMUserMessage(aUser);		
		ObjectOutputStream oos = new ObjectOutputStream(getSendingStream());
		oos.writeObject(theUserMessage);
		oos.close();
	}
	
	/** */
	private MMUser receiveUserOnLocalByteStream() throws Exception
	{
		InputStream theInputStream = new ByteArrayInputStream(getSendingStream().toByteArray());
	    ObjectInputStream ois = new ObjectInputStream(theInputStream);
	    MMUserMessage theUserMessage = (MMUserMessage) ois.readObject();	
	    ois.close();
	    
	    return theUserMessage.getUser();
	}

	// Accessors:
	
	/**
	 * @return the sendingStream
	 */
	public ByteArrayOutputStream getSendingStream()
	{
		return sendingStream;
	}

	/**
	 * @param sendingStream the sendingStream to set
	 */
	public void setSendingStream(ByteArrayOutputStream sendingStream)
	{
		this.sendingStream = sendingStream;
	}
	
}
