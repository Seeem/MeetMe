/**
 * 
 */
package de.hfu.meetme.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMMessageManager;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMOnlineUserSimulation
{

	@Test
	public void testSimulation()
	{
		MMUser.initializeUsers();
		MMUser.setMyself(new MMUser(MMNetworkUtil.getMyLanAddressAsString(), MMGender.FEMALE, "SimulatedUser", "Geile", "Sau", MMTestSupport.getValidBirthday()));		
		MMMessageManager theMessageManager = new MMMessageManager();
		theMessageManager.startListening();
		theMessageManager.refreshUsers();
		pseudoWait();	
		theMessageManager.stopListening();	
	}
	
	private void pseudoWait()
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			input.readLine();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}