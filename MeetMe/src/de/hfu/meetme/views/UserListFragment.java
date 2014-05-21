package de.hfu.meetme.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hfu.meetme.MMUserArrayAdapter;
import de.hfu.meetme.R;
import de.hfu.meetme.Supporting;
import de.hfu.meetme.model.MMGender;
import de.hfu.meetme.model.MMUser;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class UserListFragment extends ListFragment
{

	protected final static String MMUSER_KEY = "MMUserKey";

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
		View view = inflater.inflate(R.layout.fragment_user_list, container,
				false);

		// Some test code to test the ListView

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat
				.getDateInstance();

		MMUser[] users = new MMUser[3];
		users[0] = Supporting
				.getUserFromSharedPreferences(getActivity(), false);

		try
		{
			cal.setTime(theDateFormat.parse("16.02.1993"));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		users[1] = new MMUser("ID2", MMGender.MALE, "FrozenAngel", "Simeon",
				"Sembach", cal);
		try
		{
			cal.setTime(theDateFormat.parse("01.01.2001"));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		users[2] = new MMUser("ID3", MMGender.FEMALE, "MissTest", "Test",
				"User", cal);
		MMUserArrayAdapter usersAdapter = new MMUserArrayAdapter(getActivity(),
				android.R.layout.simple_list_item_1, users);

		setListAdapter(usersAdapter);
		return view;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id)
	{
		MMUser user = (MMUser) getListView().getItemAtPosition(position);
		intentUserProfileActivity(user);
	}

	private void intentUserProfileActivity(MMUser user)
	{
		final Intent intent = new Intent(getActivity(),
				de.hfu.meetme.views.UserProfileActivity.class);
		intent.putExtra(MMUSER_KEY, user);
		startActivityForResult(intent,
				UserListActivity.REQUEST_CODE_USER_PROFILE_ACTIVITY);
	}
}
