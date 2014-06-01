package de.hfu.meetme.views;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.network.MMNetworkTask;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class UserProfileFragment extends Fragment
{

	@Override
	public void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater anInflater, ViewGroup aContainer,
			Bundle aSavedInstanceState)
	{

		// Inflate the layout for this fragment
		View theView = anInflater.inflate(R.layout.fragment_user_profile,
				aContainer, false);

		// Add an onClickListener
		Button theMeetMeButton = (Button) theView
				.findViewById(R.id.user_profile_fragment_meet_me_button);
		theMeetMeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View aView)
			{
				try
				{
					MMNetworkTask.sendMeetMeMessage(InetAddress.getByName(((UserProfileActivity) getActivity()).getUser().getId()));
				} 
				catch (UnknownHostException e) {}
			}

		});

		return theView;
	}

	@Override
	public void onActivityCreated(Bundle aSavedInstanceState)
	{
		super.onActivityCreated(aSavedInstanceState);
		fillTableWithUserData();
	}

	/**
	 * This method fills the table in the UserProfileActivity with the following
	 * user data: <br>
	 * username, first name, last name, birthday, gender, description
	 */
	private void fillTableWithUserData()
	{
		TextView theUserNameTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_user_name_value_tv);
		theUserNameTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getUsername());

		TextView theFirstNameTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_first_name_value_tv);
		theFirstNameTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getFirstName());

		TextView theLastNameTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_last_name_value_tv);
		theLastNameTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getLastName());

		TextView theBirthdayTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_birthday_value_tv);
		theBirthdayTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getBirthdayAsString());

		String theGenderText;
		MMGender theGender = ((UserProfileActivity) getActivity()).getUser()
				.getGender();
		if (theGender == MMGender.MALE)
			theGenderText = "Male";
		else
			theGenderText = "Female";

		TextView theGenderTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_gender_value_tv);
		theGenderTextView.setText(theGenderText);

		TextView theDescriptionTextView = (TextView) getActivity()
				.findViewById(R.id.user_profile_fragment_description_tv);
		theDescriptionTextView.setText("Description: \n"
				+ ((UserProfileActivity) getActivity()).getUser()
						.getDescription());
	}

}
