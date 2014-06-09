package de.hfu.meetme.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.model.network.messagemanager.MMMessageManager;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerEvent;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerListener;

/**
 * @author Simeon Sembach
 *
 */
public class MMOnlineUserSimulation implements MMMessageManagerListener
{

	@Test
	public void testSimulation()
	{
		System.out.println("My Lan Address: " + MMNetworkUtil.getMyLanAddressAsString());
		System.out.println();
		MMUser.initializeUsers();
		MMUser.setMyself(new MMUser(MMNetworkUtil.getMyLanAddressAsString(), MMGender.FEMALE, "SimulatedUser", "Geile", "Sau", MMTestSupport.getValidBirthday()));		
		MMMessageManager theMessageManager = new MMMessageManager();
		theMessageManager.addMessageManagerListener(this);
		theMessageManager.startListening();
		theMessageManager.refreshUsers();
		pseudoWait();	
		theMessageManager.removeMessageManagerListener(this);
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

	@Override public void managerEventPerformed(MMMessageManagerEvent aMessageManagerEvent)
	{
		System.out.println("-----");
		if (aMessageManagerEvent.isUserAdded())
		{
			System.out.println("User was added:");
			aMessageManagerEvent.getUser().print();
		}
		else if (aMessageManagerEvent.isUserRemoved())
		{
			System.out.println("User was removed: ");
			aMessageManagerEvent.getUser().print();
		}
		else if (aMessageManagerEvent.isUserWantsAMeeting())
		{
			System.out.println("User wants a meeting: ");
			aMessageManagerEvent.getUser().print();
		}
		else if (aMessageManagerEvent.isUserUpdate())
		{
			System.out.println("User updated:");
			aMessageManagerEvent.getUser().print();
		}
		else if (aMessageManagerEvent.isUserMessage())
		{
			System.out.println("User message:");
			System.out.println(aMessageManagerEvent.getMessage());
		}
		System.out.println("-----");
	}

}