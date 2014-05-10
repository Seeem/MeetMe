/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidDescription_IsTooLong
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidDescription(MMTestsSupport.getTooLongDescription());
	}

}
