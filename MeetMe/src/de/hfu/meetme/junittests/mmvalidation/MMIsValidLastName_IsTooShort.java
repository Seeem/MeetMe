/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidLastName_IsTooShort
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidLastName(MMTestsSupport.getTooShortLastName());
	}

}
