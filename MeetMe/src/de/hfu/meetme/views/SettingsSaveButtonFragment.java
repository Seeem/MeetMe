
package de.hfu.meetme.views;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import de.hfu.meetme.R;
import de.hfu.meetme.Supporting;

/**
 * 
 * @author Dominik Jung
 *
 */
public class SettingsSaveButtonFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_settings_button, container, false);

		// Add an onClickListener
		Button saveButton = (Button) view.findViewById(R.id.settings_save_button);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Your user profile has been saved!", Toast.LENGTH_SHORT).show();
				MainActivity.myself = Supporting.getUser(getActivity());
				MainActivity.isUserCreated = true;
				Intent intent = new Intent(getActivity(), de.hfu.meetme.views.MainActivity.class);
				startActivity(intent);
			}

		});

		return view;
	}
}
