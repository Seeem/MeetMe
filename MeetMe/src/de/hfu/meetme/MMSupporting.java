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
import de.hfu.meetme.model.network.MMNetworkUtil;
import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * 
 * @author Dominik Jung
 * 
 *         Supporting class with methods which do not fit in any other class
 *         because they're used in multiple different classes.
 */
public final class MMSupporting
{
	/** */
	public final static String NOTIFICATION_ID = "notificationId";

	/** */
	public final static String MMUSER_KEY = "MMUserKey";

	/** */
	public static final int REQUEST_CODE_SETTINGS_ACTIVITY = 1;
	
	/** */
	public static final int REQUEST_CODE_USER_LIST_ACTIVITY = 2;
	
	/** */
	public final static int REQUEST_CODE_USER_PROFILE_ACTIVITY = 3;
	
	/** */
	public final static int REQUEST_CODE_MMCHAT_ACTIVITY = 4;
	
	/**
	 * Reads the user profile data from the SharedPreferences, set in the
	 * settings.
	 * 
	 * @param aContext
	 *            The context of any activity in the same package as the
	 *            SettingsActivity
	 * @param isValid
	 *            isValid set to true means that the userData set in the
	 *            SharedPreferences have been validated before. In this case,
	 *            the method skips the validation.
	 * 
	 * @return A MMUser object, if the input is correct, null otherwise.
	 */
	public static MMUser getUserFromSharedPreferences(Context aContext,
			boolean isValid)
	{

		final SharedPreferences theSettings = PreferenceManager
				.getDefaultSharedPreferences(aContext);

		// Get Data
		final String theId = MMNetworkUtil.getMyLanAddressAsString();
		final String theUsername = theSettings.getString("username", "Unknown");
		final String theFirstName = theSettings.getString("first_name", "John");
		final String theLastName = theSettings.getString("last_name", "Doe");
		final boolean isGenderMale = theSettings.getBoolean("gender", true);
		final String theDescription = theSettings.getString("description", "");

		Calendar theCal = Calendar.getInstance();
		SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat
				.getDateInstance();

		try
		{
			theCal.setTime(theDateFormat.parse(theSettings.getString("date_of_birth",
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
			if (!MMUserValidation.isValidUsername(theUsername).isValid())
			{
				isValid = false;
				Toast.makeText(
						aContext,
						MMUserValidation.isValidUsername(theUsername).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidFirstName(theFirstName).isValid())
			{
				isValid = false;
				Toast.makeText(
						aContext,
						MMUserValidation.isValidFirstName(theFirstName)
								.getMessage(), Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidLastName(theLastName).isValid())
			{
				isValid = false;
				Toast.makeText(
						aContext,
						MMUserValidation.isValidLastName(theLastName).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidBirthday(theCal).isValid())
			{
				isValid = false;
				Toast.makeText(aContext,
						MMUserValidation.isValidBirthday(theCal).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidGender(gender).isValid())
			{
				isValid = false;
				Toast.makeText(aContext,
						MMUserValidation.isValidGender(gender).getMessage(),
						Toast.LENGTH_SHORT).show();
			} else if (!MMUserValidation.isValidDescription(theDescription)
					.isValid())
			{
				isValid = false;
				Toast.makeText(
						aContext,
						MMUserValidation.isValidDescription(theDescription)
								.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}

		if (isValid)
			return new MMUser(theId, gender, theUsername, theFirstName, theLastName, theCal,
					theDescription);
		return null;
	}

}
