/**
 * 
 */
package de.hfu.meetme.junittests.support;

import java.util.Calendar;

import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;

/**
 * @author Simeon Sembach
 *
 */
public final class MMTestSupport
{
	
	private static Calendar getValidBirthday()
	{
		Calendar theBirthday = Calendar.getInstance();
		theBirthday.set(Calendar.YEAR, theBirthday.get(Calendar.YEAR)-12);
		return theBirthday;
	}
	
	public static MMUser createANewValidUser()
	{	
		return new MMUser("", MMGender.MALE, "Sem", "Simeon", "Sembach", getValidBirthday(), "Hi I want to meet you!");
	}
	
	public static MMUser createANewValidUser(String aId)
	{
		return new MMUser(aId, MMGender.MALE, "Sem", "Simeon", "Sembach", getValidBirthday(), "Hi I want to meet you!");
	}
	
	public static MMUser createANewNotValidUser()
	{
		return new MMUser("", MMGender.MALE, "", "", "", null, "");
	}
	
	public static MMUser createANewValidMan()
	{
		return new MMUser("", MMGender.MALE, "Sem", "Simeon", "Sembach", getValidBirthday(), "Hi I want to meet you!");
	}
	
	public static MMUser createANewValidWoman()
	{
		return new MMUser("", MMGender.FEMALE, "Quotenfrau", "Leona", "Musterfrau", getValidBirthday(), "Hi I want to meet you!");
	}
	
}
