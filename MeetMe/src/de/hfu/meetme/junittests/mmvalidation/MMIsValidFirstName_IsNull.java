/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidFirstName_IsNull
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidFirstName(null);
	}

}
