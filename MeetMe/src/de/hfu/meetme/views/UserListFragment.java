package de.hfu.meetme.views;

import android.app.ListFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import de.hfu.meetme.MMUserArrayAdapter;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.MMMessageManager;
import de.hfu.meetme.model.network.MMNetworkTask;
import de.hfu.meetme.model.network.MMNetworkTaskType;

public class UserListFragment extends ListFragment
{

	// Instance-Members:

	/** */
	private MMMessageManager messageManager;

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
	}

	/** */
	@Override
	public void onResume()
	{
		super.onResume();
		startListening();
		refreshUserList();
	}

	/** */
	@Override
	public void onPause()
	{
		super.onPause();
		stopListening();
	}

	/** */
	@Override
	public View onCreateView(LayoutInflater anInflater, ViewGroup aContainer,
			Bundle aSavedInstanceState)
	{
		// Inflate the layout for this fragment
		View theView = anInflater.inflate(R.layout.fragment_user_list,
				aContainer, false);

		refreshUserList();
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

	// MM-API:
	
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

	/** */
	public void addNotification(final MMUser anUser)
	{
		this.getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run()
			{
				// TODO: Notification

				PendingIntent thePendingIntent = PendingIntent.getActivity(
						getActivity(), 0, new Intent(),
						PendingIntent.FLAG_UPDATE_CURRENT);

				Notification theNotification = new Notification.Builder(
						getActivity())
						.setContentTitle("Message")
						.setContentText(
								anUser.getUsername() + " wants to meet you!")
						.setContentIntent(thePendingIntent)
						.build();
				
				NotificationManager mgr =(NotificationManager)
						getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
						int theNotification_id = (int) System.currentTimeMillis();
						mgr.notify(theNotification_id, theNotification);

			}

		});
	}

	/** */
	public void startListening()
	{
		MMNetworkTask.startNetworkTask(getMessageManager(), MMNetworkTaskType.START_LISTENING);
	}

	/** */
	public void stopListening()
	{
		MMNetworkTask.startNetworkTask(getMessageManager(), MMNetworkTaskType.STOP_LISTENING);
	}

	/** */
	public void refreshUserList()
	{
		MMNetworkTask.startNetworkTask(getMessageManager(), MMNetworkTaskType.REFRESH_USERLIST);
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
