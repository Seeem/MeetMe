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
@SuiteClasses({ MMisValidDateTest.class, MMisValidDescriptionTest.class,
		MMisValidFirstNameTest.class, MMisValidLastNameTest.class,
		MMisValidUsernameTest.class, MMisValidUserTest.class, MMsendMessage.class,
		MMgenderTest.class})
public class AllTests
{

}
