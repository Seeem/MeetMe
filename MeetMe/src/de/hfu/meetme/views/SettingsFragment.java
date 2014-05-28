package de.hfu.meetme.views;

import de.hfu.meetme.R;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class SettingsFragment extends PreferenceFragment
{
	
	/**
	 * the keys of the preferences in the SettingsFragment
	 */
	private final static String[] EDIT_TEXT_KEYS = { "username", "first_name", "last_name",
			"date_of_birth", "description" };

	
	/**
	 * @return the keys
	 */
	public static String[] getEditTextKeys()
	{
		return EDIT_TEXT_KEYS;
	}

	@Override
	public void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		changeSummaries();
	}

	/**
	 * changes the summaries of the MMEditTextPreferences to their current value
	 */
	public void changeSummaries()
	{
		// Reading: for each String theKey in keys
		for (String theKey : EDIT_TEXT_KEYS)
		{
			Preference thePreference = findPreference(theKey);

			if (thePreference != null)
			{
				if (thePreference instanceof MMEditTextPreference)
				{
					MMEditTextPreference theMMEditTextPreference = ((MMEditTextPreference) thePreference);
					String theText = theMMEditTextPreference.getText();
					if (theText != null)
					{
						theMMEditTextPreference.setSummary(theText);
					}
				}
			}
		}
	}
}
