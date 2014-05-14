package de.hfu.meetme.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hfu.meetme.R;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SettingsSaveButtonFragment extends Fragment
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_settings_button, container, false);
		
		//Add an onClickListener 
		Button saveButton = (Button) view.findViewById(R.id.settings_save_button);
	    saveButton.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
	            
	            MainActivity.myself = getUser();
	            Intent intent = new Intent(getActivity(), de.hfu.meetme.views.MainActivity.class);
	            startActivity(intent);
	        }
	    });
		
		return view;
	}
	
	/**Reads the user profile data from the SharedPreferences, set in the settings.
	 * Currently expects the input to be correct*/
	private MMUser getUser() {
		
		final SharedPreferences settings = getActivity().getPreferences(0);
		
		//Get Data
        final String id = "Test";
        final String username = settings.getString("username", "Unknown");
        final String firstName = settings.getString("first_name", "John");
        final String lastName = settings.getString("last_name", "Doe");
        final boolean isGenderMale = settings.getBoolean("gender", true);
        final String description = settings.getString("description", "");
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        try
		{
			cal.setTime(theDateFormat.parse(settings.getString("date_of_birth", "01.01.2001")));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
        
        MMGender gender;
        
        if(isGenderMale)
        	gender = MMGender.MALE;
        else
        	gender = MMGender.FEMALE;
		
		return new MMUser(id, gender, username, firstName, lastName, cal, description);
	}
	
	

}
