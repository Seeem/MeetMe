package de.hfu.meetme;

import android.app.Fragment;
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
	
	public void onSaveButtonClicked(View view) {
		Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
	}
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_settings_button, container, false);
		
		Button saveButton = (Button) view.findViewById(R.id.settings_save_button);
	    saveButton.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
	        }
	    });
		
		return view;
}

}
