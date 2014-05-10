/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidNickname_IsNull
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidNickname(null);
	}

}
