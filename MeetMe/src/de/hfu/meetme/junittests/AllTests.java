/**
 * 
 */
package de.hfu.meetme.junittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Simeon Sembach
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ MMAddAndRemoveUsersTest.class, MMGenderTest.class,
		MMSendMessageTest.class, MMValidDateTest.class,
		MMValidDescriptionTest.class, MMValidFirstNameTest.class,
		MMValidLastNameTest.class, MMValidUsernameTest.class,
		MMValidUserTest.class })
public class AllTests
{

}
