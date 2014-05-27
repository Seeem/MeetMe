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
	 * the number of preferences in the SettingsFragment
	 */
	final int numberOfKeys = 6;

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

	public void changeSummaries()
	{
		String[] theKeys = { "username", "first_name", "last_name",
				"date_of_birth", "gender", "description" };
		// Reading: for each String theKey in theKeys
		for (String theKey : theKeys)
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
