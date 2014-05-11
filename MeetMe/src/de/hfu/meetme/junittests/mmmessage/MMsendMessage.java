/**
 * 
 */
package de.hfu.meetme.junittests.mmmessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Calendar;

import org.junit.Test;

import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.message.MMMessage;
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
			PipedOutputStream pos = new PipedOutputStream();
			PipedInputStream pis = new PipedInputStream();			
			pos.connect(pis);
			
			MMUser theUser = new MMUser(
					"Sem",
					"Simeon",
					"Sembach",
					Calendar.getInstance(),
					"Hi I want to meet you!");
			
			MMMessage theMessage = new MMUserMessage(theUser);
				
			ObjectOutputStream oos = new ObjectOutputStream(pos);
			oos.writeObject(theMessage);
						
		    ObjectInputStream ois = new ObjectInputStream(pis);
		    theMessage = (MMMessage) ois.readObject();
		   		
		    System.out.println(theMessage.getMessage());
		    
		    oos.close();
		    ois.close();
		    
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
