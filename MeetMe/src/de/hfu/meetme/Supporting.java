package de.hfu.meetme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.views.MainActivity;

/**
 * 
 * @author Dominik Jung
 *
 * Supporting class with methods which do not fit in any other class because they're used in multiple
 * different classes.
 */
public class Supporting {
	
	/**
	 * Reads the user profile data from the SharedPreferences, set in the
	 * settings. Currently expects the input to be correct
	 */
	public static MMUser getUserFromSharedPreferences(Context context) {

		final SharedPreferences settings = 
				context.getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Activity.MODE_PRIVATE);

		// Get Data
		final String id = "Test"; //TODO: Here should be the unique ID
		final String username = settings.getString("username", "Unknown");
		final String firstName = settings.getString("first_name", "John");
		final String lastName = settings.getString("last_name", "Doe");
		final boolean isGenderMale = settings.getBoolean("gender", true);
		final String description = settings.getString("description", "");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		
		try
		{
			cal.setTime(theDateFormat.parse(settings.getString("date_of_birth", "01.01.2001")));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		MMGender gender;
		if (isGenderMale)
			gender = MMGender.MALE;
		else
			gender = MMGender.FEMALE;

		return new MMUser(id, gender, username, firstName, lastName, cal, description);
	}
}
