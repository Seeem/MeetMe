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
			public boolean onPreferenceChange(Preference aPreference, Object theChange)
			{
				boolean isValid = true;
				String theKey = getKey();
				switch (theKey) {
					case "username":
						if (!MMUserValidation.isValidUsername(theChange.toString()).isValid()){
							isValid = false;
						}
						break;
					case "first_name":
						if (!MMUserValidation.isValidFirstName(theChange.toString()).isValid()){
							isValid = false;
						}
						break;
					case "last_name":
						if (!MMUserValidation.isValidLastName(theChange.toString()).isValid()){
							isValid = false;
						}
						break;
					case "date_of_birth":
						if (!isValidBirthday(theChange.toString())){
							isValid = false;
						}
						break;
					case "description":
						if (!MMUserValidation.isValidDescription(theChange.toString()).isValid()){
							isValid = false;
						}
						break;
					
				}
				if (isValid)
					setSummary(theChange.toString());
				return isValid;
			}
        });
    }
    
    private boolean isValidBirthday(String aBirthday) {
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
