/**
 * 
 */
package de.hfu.meetme.junittests.mmvalidation.mmuser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Simeon Sembach
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ MMisValidDate.class, MMisValidDescription.class,
		MMisValidFirstName.class, MMisValidLastName.class,
		MMisValidNickname.class, MMisValidUser.class })
public class All_MMUser_Tests
{

}
