package de.hfu.meetme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * 
 * @author Dominik Jung
 * 
 *         Supporting class with methods which do not fit in any other class
 *         because they're used in multiple different classes.
 */
public class Supporting
{

	/**
	 * Reads the user profile data from the SharedPreferences, set in the
	 * settings.
	 * 
	 * @param context
	 *            The context of any activity in the same package as the
	 *            SettingsActivity
	 * @param isValid
	 *            isValid set to true means that the userData set in the
	 *            SharedPreferences have been validated before. In this case,
	 *            the method skips the validation.
	 * 
	 * @return A MMUser object, if the input is correct, null otherwise.
	 */
	public static MMUser getUserFromSharedPreferences(Context context,
			boolean isValid)
	{

		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);

		// Get Data
		final String id = "Test"; // TODO: Here should be the unique ID
		final String username = settings.getString("username", "Unknown");
		final String firstName = settings.getString("first_name", "John");
		final String lastName = settings.getString("last_name", "Doe");
		final boolean isGenderMale = settings.getBoolean("gender", true);
		final String description = settings.getString("description", "");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat
				.getDateInstance();

		try
		{
			cal.setTime(theDateFormat.parse(settings.getString("date_of_birth",
					"01.01.2001")));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		MMGender gender;
		if (isGenderMale)
			gender = MMGender.MALE;
		else
			gender = MMGender.FEMALE;

		// Validation - only if not validated before
		if (!isValid)
		{
			isValid = true;
			if (!MMUserValidation.isValidUsername(username).isValid())
			{
				isValid = false;
				Toast.makeText(
						context,
						MMUserValidation.isValidUsername(username).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidFirstName(firstName).isValid())
			{
				isValid = false;
				Toast.makeText(
						context,
						MMUserValidation.isValidFirstName(firstName)
								.getMessage(), Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidLastName(lastName).isValid())
			{
				isValid = false;
				Toast.makeText(
						context,
						MMUserValidation.isValidLastName(lastName).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidBirthday(cal).isValid())
			{
				isValid = false;
				Toast.makeText(context,
						MMUserValidation.isValidBirthday(cal).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidGender(gender).isValid())
			{
				isValid = false;
				Toast.makeText(context,
						MMUserValidation.isValidGender(gender).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidDescription(description)
					.isValid())
			{
				isValid = false;
				Toast.makeText(
						context,
						MMUserValidation.isValidDescription(description)
								.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}

		if (isValid)
			return new MMUser(id, gender, username, firstName, lastName, cal,
					description);
		return null;
	}
}
