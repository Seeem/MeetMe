/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidNickName_IsTooLong
{

	@Test
	public void test()
	{
		MMTestsSupport.testNotValidNickName(MMTestsSupport.getTooLongNickName());
	}

}
