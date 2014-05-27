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
		MMNetworkTest.class, MMSendMessageTest.class, MMValidDateTest.class,
		MMValidDescriptionTest.class, MMValidFirstNameTest.class,
		MMValidLastNameTest.class, MMValidUsernameTest.class,
		MMUserTest.class, MMMessageManagerTest.class })
public class AllTests
{

}
