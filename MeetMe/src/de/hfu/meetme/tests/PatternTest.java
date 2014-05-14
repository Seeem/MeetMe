/**
 * 
 */
package de.hfu.meetme.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Simeon Sembach
 *
 */
public class PatternTest
{

	@Test
	public void test()
	{
		Pattern theUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{2,12}");
	
		Matcher theFirstNameMatcher = theUsernamePattern.matcher("");
		
		System.out.println(theFirstNameMatcher.matches());
	}
	
}
