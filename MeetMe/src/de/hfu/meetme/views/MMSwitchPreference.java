package de.hfu.meetme.views;

import android.content.Context;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMSwitchPreference extends SwitchPreference
{
	public MMSwitchPreference(Context aContext, AttributeSet anAttributeSet)
	{
		super(aContext, anAttributeSet);
		init();
	}

	public MMSwitchPreference(Context aContext)
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
				return true;
			}
		});
	}

}
