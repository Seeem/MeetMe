package de.hfu.meetme.views;

import de.hfu.meetme.R;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class SettingsFragment extends PreferenceFragment
{
	@Override
	public void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
	}

}
