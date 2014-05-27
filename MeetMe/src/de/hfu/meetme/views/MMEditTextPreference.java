package de.hfu.meetme.views;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;

public class MMEditTextPreference extends EditTextPreference
{
    public MMEditTextPreference(Context aContext, AttributeSet anAttributeSet) {
        super(aContext, anAttributeSet);
        init();
    }

    public MMEditTextPreference(Context aContext) {
        super(aContext);
        init();
    }

    private void init() {

        setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference anArg0, Object anArg1)
			{
				setSummary(anArg1.toString());
				return true;
			}
        });
    }

}
