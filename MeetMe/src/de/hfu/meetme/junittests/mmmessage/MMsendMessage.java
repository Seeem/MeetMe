/**
 * 
 */
package de.hfu.meetme.junittests.mmmessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.junit.Test;

import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.message.MMUserMessage;

/**
 * @author Simeon Sembach
 *
 */
public class MMsendMessage
{

	@Test
	public void testSendUser_shouldPass()
	{
		try
		{
			OutputStream theOutputStream = new FileOutputStream("user.tmp");
			
			MMUser theUser = new MMUser(
					"Sem",
					"Simeon",
					"Sembach",
					Calendar.getInstance(),
					"Hi I want to meet you!");
			
			MMUserMessage theMessage = new MMUserMessage(theUser);
				
			ObjectOutputStream oos = new ObjectOutputStream(theOutputStream);
			oos.writeObject(theMessage);
			oos.flush();
			oos.close();
			
			InputStream theInputStream = new FileInputStream("user.tmp");
					
		    ObjectInputStream ois = new ObjectInputStream(theInputStream);
		    theMessage = (MMUserMessage) ois.readObject();
		   		
		    ois.close();
		    
		    System.out.println(theMessage.getUser());
		    
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
