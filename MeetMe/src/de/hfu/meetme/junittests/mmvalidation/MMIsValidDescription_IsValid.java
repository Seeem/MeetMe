/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidDescription_IsValid
{

	@Test
	public void test()
	{
		MMTestsSupport.testValidDescription(MMTestsSupport.getValidDescription());
	}

}
