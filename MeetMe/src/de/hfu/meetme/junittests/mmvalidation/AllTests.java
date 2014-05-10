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
@SuiteClasses({ MMIsValidDate_IsNull.class,
		MMIsValidDescription_IsTooLong.class, MMIsValidFirstName_IsNull.class,
		MMIsValidFirstName_IsTooLong.class,
		MMIsValidFirstName_IsTooShort.class, MMIsValidLastName_IsNull.class,
		MMIsValidLastName_IsTooLong.class, MMIsValidLastName_IsTooShort.class,
		MMIsValidNickname_IsNull.class, MMIsValidNickname_IsTooLong.class,
		MMIsValidNickname_IsTooShort.class })
public class AllTests
{

}
