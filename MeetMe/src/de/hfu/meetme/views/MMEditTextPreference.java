package de.hfu.meetme.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import de.hfu.meetme.model.validation.MMUserValidation;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMEditTextPreference extends EditTextPreference
{
	public MMEditTextPreference(Context aContext, AttributeSet anAttributeSet)
	{
		super(aContext, anAttributeSet);
		init();
	}

	public MMEditTextPreference(Context aContext)
	{
		super(aContext);
		init();
	}

	private void init()
	{

		setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference aPreference,
					Object theChange)
			{
				boolean isValid = true;
				String theKey = getKey();
				String[] theKeys = MMSettingsFragment.getEditTextKeys();
				if (theKey.equals(theKeys[0]))
				{
					if (!MMUserValidation.isValidUsername(theChange.toString())
							.isValid())
					{
						isValid = false;
					}
				}
				else if (theKey.equals(theKeys[1]))
				{
					if (!MMUserValidation
							.isValidFirstName(theChange.toString()).isValid())
					{
						isValid = false;
					}
				}
				else if (theKey.equals(theKeys[2]))
				{
					if (!MMUserValidation.isValidLastName(theChange.toString())
							.isValid())
					{
						isValid = false;
					}
				}
				else if (theKey.equals(theKeys[3]))
				{
					if (!isValidBirthday(theChange.toString()))
					{
						isValid = false;
					}
				}
				else if (theKey.equals(theKeys[4]))
				{
					if (!MMUserValidation.isValidDescription(
							theChange.toString()).isValid())
					{
						isValid = false;
					}
				}

				if (isValid)
					setSummary(theChange.toString());
				return isValid;
			}
		});
	}

	private boolean isValidBirthday(String aBirthday)
	{
		Calendar theCal = Calendar.getInstance();
		SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat
				.getDateInstance();
		theDateFormat.setLenient(false);
		
		try
		{
			theCal.setTime(theDateFormat.parse(aBirthday));
		} catch (ParseException e)
		{
			return false;
		}

		return MMUserValidation.isValidBirthday(theCal).isValid();
	}

}
