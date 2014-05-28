package de.hfu.meetme.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hfu.meetme.model.validation.MMUserValidation;
import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;

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
				String[] theKeys = SettingsFragment.getEditTextKeys();
				if (theKey.equals(theKeys[0]))
				{
					if (!MMUserValidation.isValidUsername(theChange.toString())
							.isValid())
					{
						isValid = false;
					}
				}
				if (theKey.equals(theKeys[1]))
				{
					if (!MMUserValidation
							.isValidFirstName(theChange.toString()).isValid())
					{
						isValid = false;
					}
				}
				if (theKey.equals(theKeys[2]))
				{
					if (!MMUserValidation.isValidLastName(theChange.toString())
							.isValid())
					{
						isValid = false;
					}
				}
				if (theKey.equals(theKeys[3]))
				{
					if (!isValidBirthday(theChange.toString()))
					{
						isValid = false;
					}
				}
				if (theKey.equals(theKeys[4]))
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

		try
		{
			theCal.setTime(theDateFormat.parse(aBirthday));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		return MMUserValidation.isValidBirthday(theCal).isValid();
	}

}
