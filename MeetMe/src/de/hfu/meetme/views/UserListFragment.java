package de.hfu.meetme.views;

import android.app.ListFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
import de.hfu.meetme.model.network.MMNetworkTaskType;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class UserListFragment extends ListFragment
{

	// Instance-Members:

	/** */
	private MMMessageManager messageManager;

	/** */
	private class NetworkTask extends AsyncTask<MMNetworkTaskType, Void, Void>
	{

		@Override
		protected Void doInBackground(MMNetworkTaskType... someParams)
		{
			if (someParams[0] == MMNetworkTaskType.START_LISTENING)
			{
				getMessageManager().startListening();
			} else if (someParams[0] == MMNetworkTaskType.STOP_LISTENING)
			{
				getMessageManager().stopListening();
			} else if (someParams[0] == MMNetworkTaskType.REFRESH_USERLIST)
			{
				getMessageManager().refreshUsers();
			}

			return null;
		}

	}

	// Class-Members:

	/** */
	protected final static String MMUSER_KEY = "MMUserKey";

	/** */
	protected final static String NOTIFICATION_ID = "notificationId";

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
	private void intentUserProfileActivity(final MMUser anUser)
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

	/** */
	public void addNotification(final MMUser anUser)
	{
		this.getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run()
			{
				generateNotification(anUser, "wants to meet you!");
			}

		});
	}

	// MM-API:

	/** */
	public void startListening()
	{
		new NetworkTask().execute(MMNetworkTaskType.START_LISTENING);
	}

	/** */
	public void stopListening()
	{
		new NetworkTask().execute(MMNetworkTaskType.STOP_LISTENING);
	}

	/** */
	public void refreshUserList()
	{
		new NetworkTask().execute(MMNetworkTaskType.REFRESH_USERLIST);
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

	/**
	 * 
	 * @param anUser
	 *            the user who sent the message. His username will be used as
	 *            notification title, his id as notification id to seperate
	 *            notifications from distinct users.
	 * @param message
	 *            the message to be shown as notification text, e.g.
	 *            "wants to meet you!"
	 */
	private void generateNotification(final MMUser anUser, final String message)
	{
		/*
		 * TODO: improve Notification 
		 * Add Vibration 
		 * Add Lights 
		 * Add a proper icon
		 * Add a proper intent
		 */
		String theUserId = anUser.getId();
		theUserId = theUserId.replace(".", "")
				.substring(theUserId.length() - 6);
		int theId = Integer.parseInt(theUserId);
		Intent theIntent = new Intent(getActivity(), UserListActivity.class);
		theIntent.putExtra(NOTIFICATION_ID, theId);

		PendingIntent thePendingIntent = PendingIntent.getActivity(
				getActivity(), 0, theIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		Notification theNotification = new Notification.Builder(getActivity())
				.setContentTitle(anUser.getUsername()).setContentText(message)
				.setWhen(System.currentTimeMillis())
				.setDefaults(Notification.DEFAULT_ALL)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(thePendingIntent).build();

		NotificationManager theManager = (NotificationManager) getActivity()
				.getSystemService(Context.NOTIFICATION_SERVICE);
		int theNotification_id = theId;
		theManager.notify(theNotification_id, theNotification);
	}

}
