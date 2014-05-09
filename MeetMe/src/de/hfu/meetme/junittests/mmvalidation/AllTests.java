/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Simeon Sembach
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ MMIsValidFirstName_IsNull.class,
		MMIsValidFirstName_IsTooLong.class,
		MMIsValidFirstName_IsTooShort.class, MMIsValidLastName_IsNull.class,
		MMIsValidLastName_IsTooLong.class, MMIsValidLastName_IsTooShort.class,
		MMIsValidNickName_IsNull.class, MMIsValidNickName_IsTooLong.class,
		MMIsValidNickName_IsTooShort.class })
public class AllTests
{

}
