/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidNickname_IsTooLong
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidNickname(MMTestsSupport.getTooLongNickname());
	}

}
