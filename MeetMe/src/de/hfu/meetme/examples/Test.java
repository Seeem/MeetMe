/**
 * 
 */
package de.hfu.meetme.examples;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Simeon Sembach
 *
 */
public class Test
{

	@org.junit.Test
	public void test() throws UnknownHostException
	{
		System.out.println(InetAddress.getLocalHost().getHostAddress());
	}

}
