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

		@Override
		protected Void doInBackground(Integer... someParams)
		{

			switch (someParams[0])
			{
				case 0:
				{
					System.out.println("Listener started");
					getMessageManager().startListening();
					break;
				}
				case 1:
				{
					System.out.println("Listener stopped");
					getMessageManager().stopListening();
					break;
				}
				case 2:
				{
					System.out.println("Refreshing");
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
	public void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);
		setMessageManager(new MMMessageManager(this));
		new NetworkTask().execute(0);
	}

	/** */
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		new NetworkTask().execute(1);
	}

	/** */
	@Override
	public void onResume()
	{
		super.onResume();
		new NetworkTask().execute(0);
		updateView();
	}

	/** */
	@Override
	public void onPause()
	{
		super.onPause();
		new NetworkTask().execute(1);
	}

	/** */
	@Override
	public View onCreateView(LayoutInflater anInflater, ViewGroup aContainer,
			Bundle aSavedInstanceState)
	{
		// Inflate the layout for this fragment
		View theView = anInflater.inflate(R.layout.fragment_user_list, aContainer,
				false);

		new NetworkTask().execute(2);
		return theView;
	}

	/** */
	@Override
	public void onListItemClick(ListView aListView, View aView, int aPosition,
			long anId)
	{
		MMUser theUser = (MMUser) getListView().getItemAtPosition(aPosition);
		intentUserProfileActivity(theUser);
	}

	/** */
	private void intentUserProfileActivity(MMUser anUser)
	{
		final Intent theIntent = new Intent(getActivity(),
				de.hfu.meetme.views.UserProfileActivity.class);
		theIntent.putExtra(MMUSER_KEY, anUser);
		startActivityForResult(theIntent,
				UserListActivity.REQUEST_CODE_USER_PROFILE_ACTIVITY);
	}

	/** */
	public void updateView()
	{
		this.getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run()
			{
				setListAdapter(new MMUserArrayAdapter(getActivity(),
						android.R.layout.simple_list_item_1, MMUser
								.getUsersAsArray()));
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
	 * @param aMessageManager
	 *            the messageManager to set
	 */
	public void setMessageManager(MMMessageManager aMessageManager)
	{
		this.messageManager = aMessageManager;
	}

}
