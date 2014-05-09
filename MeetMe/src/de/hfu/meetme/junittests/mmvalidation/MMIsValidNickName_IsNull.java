/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidNickName_IsNull
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidNickName(null);
	}

}
