package de.hfu.meetme.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import de.hfu.meetme.R;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMChatFragment extends Fragment
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

			}

		});

		return theView;
	}

	@Override
	public void onActivityCreated(Bundle aSavedInstanceState)
	{
		super.onActivityCreated(aSavedInstanceState);
	}
}
