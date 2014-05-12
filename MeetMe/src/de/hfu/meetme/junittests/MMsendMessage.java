/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.junit.Test;

import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.message.MMUserMessage;

/**
 * @author Simeon Sembach
 *
 */
public class MMsendMessage
{

	// TODO Implement a "real" sending test
	
	@Test
	public void testSaveAndReadAValidUser_ShouldPass()
	{
		boolean isExpected = true;
		
		try
		{
			sendUser(new MMUser(MMGender.MAN, "Sem", "Simeon", "Sembach", Calendar.getInstance(), "Hi I want to meet you!"));		
			MMUser theUser = receiveUser();    
		    System.out.println(theUser);	    
		} 
		catch (Exception e)
		{
//			e.printStackTrace();
			isExpected = !isExpected;
		}
		finally
		{
			new File("user.tmp").delete();
		}
		
		assertTrue(isExpected);
	}
	
	/** */
	private void sendUser(MMUser aUser) throws Exception
	{
		OutputStream theOutputStream = new FileOutputStream("user.tmp");		
		MMUserMessage theUserMessage = new MMUserMessage(aUser);		
		ObjectOutputStream oos = new ObjectOutputStream(theOutputStream);
		oos.writeObject(theUserMessage);
		oos.close();
	}
	
	/** */
	private MMUser receiveUser() throws Exception
	{
		InputStream theInputStream = new FileInputStream("user.tmp");
	    ObjectInputStream ois = new ObjectInputStream(theInputStream);
	    MMUserMessage theUserMessage = (MMUserMessage) ois.readObject();	
	    ois.close();
	    
	    return theUserMessage.getUser();
	}
	
}
