package de.hfu.meetme.views;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import de.hfu.meetme.MMUserArrayAdapter;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMMessageManager;
import de.hfu.meetme.model.MMUser;

public class UserListFragment extends ListFragment
{

	// Instance-Members:
	
	/** */
	private MMMessageManager messageManager;
	
	/** */
	private class NetworkTask extends AsyncTask<Integer, Void, Void>
	{

		@Override protected Void doInBackground(Integer... aParams)
		{
			
			switch(aParams[0])
			{
				case 0:
				{
					getMessageManager().startListening();
					break;
				}
				case 1:
				{
					getMessageManager().stopListening();
					break;
				}
				case 2:
				{
					getMessageManager().refreshUsers();
					break;
				}			
			}
			
			return null;
		}

	}
	
	// Class-Members:
	
	/** */
	protected final static String MMUSER_KEY = "MMUserKey";

	// Internals:
	
	/** */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setMessageManager(new MMMessageManager(this));
		new NetworkTask().execute(0);
	}
	
	/** */
	@Override public void onDestroy()
	{
		super.onDestroy();
		new NetworkTask().execute(1);
	}
	
	/** */
	@Override public void onResume()
	{
		super.onResume();
		new NetworkTask().execute(0);
		updateView();
	}
	
	/** */
	@Override public void onPause()
	{
		super.onPause();
		new NetworkTask().execute(1);
	}
	
	/** */
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_user_list, container,false);

		//
		
		new NetworkTask().execute(2);
		
		// Some test code to test the ListView

//		try
//		{
//			Calendar cal = Calendar.getInstance();
//			SimpleDateFormat theDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
//			
//			cal.setTime(theDateFormat.parse("16.02.1993"));
//			MMUser.addUserIfNotAlreadyAdded(new MMUser("192.168.0.1", MMGender.MALE, "FrozenAngel", "Simeon","Sembach", cal));
//			
//			cal.setTime(theDateFormat.parse("25.06.1994"));
//			MMUser.addUserIfNotAlreadyAdded(new MMUser("192.168.0.2", MMGender.MALE, "DaGanstaYoda", "Dominik","Jung", cal));
//		} 
//		catch (ParseException e)
//		{
//			e.printStackTrace();
//		}
			
		return view;
	}

	/** */
	@Override public void onListItemClick(ListView listView, View view, int position, long id)
	{
		MMUser user = (MMUser) getListView().getItemAtPosition(position);
		intentUserProfileActivity(user);
	}

	/** */
	private void intentUserProfileActivity(MMUser user)
	{
		final Intent intent = new Intent(getActivity(),
				de.hfu.meetme.views.UserProfileActivity.class);
		intent.putExtra(MMUSER_KEY, user);
		startActivityForResult(intent,
				UserListActivity.REQUEST_CODE_USER_PROFILE_ACTIVITY);
	}

	/** */
	public void updateView()
	{
		this.getActivity().runOnUiThread(new Runnable() {
			
			@Override public void run()
			{
				setListAdapter(new MMUserArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, MMUser.getUsersAsArray()));
			}
			
		});
	}
	
	// Accessors:
	
	/**
	 * @return the messageManager
	 */
	public MMMessageManager getMessageManager()
	{
		return messageManager;
	}
	
	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(MMMessageManager messageManager)
	{
		this.messageManager = messageManager;
	}
	
}
