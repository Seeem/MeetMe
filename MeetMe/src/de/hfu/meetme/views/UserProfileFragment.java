package de.hfu.meetme.views;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import de.hfu.meetme.R;
import de.hfu.meetme.junittests.support.MMTestSupport;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.message.MMUserMessage;
import de.hfu.meetme.model.network.MMMessageEvent;
import de.hfu.meetme.model.network.MMMessageListener;
import de.hfu.meetme.model.network.MMMessageReceiver;
import de.hfu.meetme.model.network.MMMessageSender;
import de.hfu.meetme.model.network.MMNetworkUtil;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class UserProfileFragment extends Fragment
{

	private class NetworkTask extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... aParams)
		{
			// TEST_START:
			
			MMMessageSender theMessageSender = new MMMessageSender();
			MMMessageReceiver theReceiver = new MMMessageReceiver();
				
			theReceiver.addMessageListener(new MMMessageListener()
			{				
				@Override
				public void messageReceived(MMMessageEvent aMessageEvent)
				{
					System.out.println(aMessageEvent.getMessageAsString());
				}
			});
			
			theReceiver.startReceiver();				
		
			theMessageSender.sendUDPMessage(MMNetworkUtil.getLocalhostAddress(), "hello World");
			theMessageSender.sendUDPBroadcastMessage("Broadcast");
			theMessageSender.sendTCPMessage(MMNetworkUtil.getLocalhostAddress(), new MMUserMessage(MMTestSupport.createANewValidUser()));

			theReceiver.stopReceiver();
			
			// TEST_END
			
			return null;
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_user_profile, container,
				false);

		// Add an onClickListener
		Button meetMeButton = (Button) view
				.findViewById(R.id.user_profile_fragment_meet_me_button);
		meetMeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO: Some send "Please Meet Me"-Message!
				// Toast.makeText(getActivity(), "Message sent.",
				// Toast.LENGTH_SHORT).show();
				new NetworkTask().execute();

			}

		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		fillTableWithUserData();
	}

	/**
	 * This method fills the table in the UserProfileActivity with the following
	 * user data: <br>
	 * username, first name, last name, birthday, gender, description
	 */
	private void fillTableWithUserData()
	{
		TextView userNameTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_user_name_value_tv);
		userNameTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getUsername());

		TextView firstNameTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_first_name_value_tv);
		firstNameTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getFirstName());

		TextView lastNameTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_last_name_value_tv);
		lastNameTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getLastName());

		TextView birthdayTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_birthday_value_tv);
		birthdayTextView.setText(((UserProfileActivity) getActivity())
				.getUser().getBirthdayAsString());

		String genderText;
		MMGender gender = ((UserProfileActivity) getActivity()).getUser()
				.getGender();
		if (gender == MMGender.MALE)
			genderText = "Male";
		else
			genderText = "Female";

		TextView genderTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_gender_value_tv);
		genderTextView.setText(genderText);

		TextView descriptionTextView = (TextView) getActivity().findViewById(
				R.id.user_profile_fragment_description_tv);
		descriptionTextView.setText("Description: \n"
				+ ((UserProfileActivity) getActivity()).getUser()
						.getDescription());
	}

}
