package de.hfu.meetme.views;

import de.hfu.meetme.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMUserListButtonFragment extends Fragment
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
		View theView = anInflater.inflate(R.layout.fragment_user_list_button,
				aContainer, false);

		// Add an onClickListener
		Button theRefreshButton = (Button) theView
				.findViewById(R.id.user_list_refresh_button);
		theRefreshButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View aView)
			{
				((UserListFragment) getFragmentManager().findFragmentById(R.id.user_list_fragment)).refreshUserList();
			}

		});

		return theView;
	}
}
