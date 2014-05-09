/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidFirstName_IsTooShort
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidFirstName(MMTestsSupport.getTooShortFirstName());
	}

}
