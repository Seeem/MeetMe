/**
 * 
 */
package de.hfu.meetme.junittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public class MMgenderTest
{

	@Test
	public void testGenderIsMan_ShouldPass()
	{
		boolean isExpected = true;
		
		MMUser theUser = MMTestSupport.createANewValidMan();
		
		assertEquals(isExpected, theUser.isMan());
	}
	
	@Test
	public void testGenderIsNoMan_ShouldPass()
	{
		boolean isExpected = false;
		
		MMUser theUser = MMTestSupport.createANewValidWoman();
		
		assertEquals(isExpected, theUser.isMan());
	}
	
	@Test
	public void testGenderIsWoman_ShouldPass()
	{
		boolean isExpected = true;
		
		MMUser theUser = MMTestSupport.createANewValidWoman();
		
		assertEquals(isExpected, theUser.isWoman());
	}
	
	@Test
	public void testGenderIsNoWoman_ShouldPass()
	{
		boolean isExpected = false;
		
		MMUser theUser = MMTestSupport.createANewValidMan();
		
		assertEquals(isExpected, theUser.isWoman());
	}
	
}
