/**
 * 
 */
package de.hfu.meetme.examples;

import org.junit.Test;

import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * @author Simeon Sembach
 *
 */
public class MMMyLanIpAddressExample
{

	// The Example:
	
	@Test public void testExample()
	{	
		String theLanAdress = MMNetworkUtil.getMyLanAddressAsString();
		if(theLanAdress != null)
			System.out.println("My LAN addresse: " + theLanAdress);
		else
			System.out.println("Not possible to get the LAN addresse");
	}
		
}
