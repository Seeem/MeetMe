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
@SuiteClasses({ MMValidDateTest.class, MMValidDescriptionTest.class,
		MMValidFirstNameTest.class, MMValidLastNameTest.class,
		MMValidUsernameTest.class, MMValidUserTest.class, MMSendMessageTest.class,
		MMGenderTest.class, MMAddAndRemoveUsersTest.class})
public class AllTests
{

}
