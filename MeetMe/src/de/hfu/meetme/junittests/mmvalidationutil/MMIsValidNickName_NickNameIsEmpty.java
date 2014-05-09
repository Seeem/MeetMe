/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidationutil;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class MMIsValidNickName_NickNameIsEmpty
{

	@Test
	public void test()
	{
		MMSupport.testNotValidNickName(MMSupport.getEmptyString());
	}

}
