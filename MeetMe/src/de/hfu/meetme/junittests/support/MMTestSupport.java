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
	
	public static MMUser createANewValidUser()
	{
		return new MMUser(MMGender.MAN, "Sem", "Simeon", "Sembach", Calendar.getInstance(), "Hi I want to meet you!");
	}
	
	public static MMUser createANewValidUser(String aId)
	{
		return new MMUser(aId, MMGender.MAN, "Sem", "Simeon", "Sembach", Calendar.getInstance(), "Hi I want to meet you!");
	}
	
	public static MMUser createANewNotValidUser()
	{
		return new MMUser(MMGender.MAN, "", "", "", null, "");
	}
	
	public static MMUser createANewValidMan()
	{
		return new MMUser(MMGender.MAN, "Sem", "Simeon", "Sembach", Calendar.getInstance(), "Hi I want to meet you!");
	}
	
	public static MMUser createANewValidWoman()
	{
		return new MMUser(MMGender.WOMAN, "Quotenfrau", "Leona", "Musterfrau", Calendar.getInstance(), "Hi I want to meet you!");
	}
	
}
