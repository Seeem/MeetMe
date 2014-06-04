package de.hfu.meetme.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import de.hfu.meetme.MMUserArrayAdapter;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerEvent;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerListener;
import de.hfu.meetme.model.network.networktask.MMNetworkTask;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class UserListFragment extends ListFragment implements
		MMMessageManagerListener
{

	// Class-Members:

	/** */
	protected final static String MMUSER_KEY = "MMUserKey";

	// Internals:

	/** */
	@Override
	public void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);

		if (getActivity().getIntent() != null)
		{
			MMNetworkTask.refreshUserlist();
		}
	}

	/** */
	@Override
	public void onResume()
	{
		super.onResume();
		MMNetworkTask.addMessageManagerListener(this);
		updateUserListView();
	}

	/** */
	@Override
	public void onPause()
	{
		super.onPause();
		MMNetworkTask.removeMessageManagerListener(this);
	}

	/** */
	@Override
	public View onCreateView(LayoutInflater anInflater, ViewGroup aContainer,
			Bundle aSavedInstanceState)
	{
		// Inflate the layout for this fragment
		View theView = anInflater.inflate(R.layout.fragment_user_list,
				aContainer, false);

		return theView;
	}

	/** */
	@Override
	public void onListItemClick(ListView aListView, View aView, int aPosition, long anId)
	{
		intentUserProfileActivity((MMUser) getListView().getItemAtPosition(aPosition));
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

	// Internals:

	/** */
	private void updateUserListView()
	{
		this.getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run()
			{
				if (MMUser.getUsersAsArray().length > 0)
				{
					makeNoUsersFoundTextFragmentVisible(false);
				} else
				{
					makeNoUsersFoundTextFragmentVisible(true);
				}
				setListAdapter(new MMUserArrayAdapter(getActivity(),
						android.R.layout.simple_list_item_1, MMUser
								.getUsersAsArray()));
			}

		});
	}

	private void makeNoUsersFoundTextFragmentVisible(boolean makeVisible)
	{
		FragmentManager theFragmentManager = getFragmentManager();
		FragmentTransaction theTransaction = getFragmentManager()
				.beginTransaction()
				.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out);

		if (makeVisible)
		{
			theTransaction.show(
					theFragmentManager
							.findFragmentById(R.id.mm_user_list_text_fragment))
					.commit();
		} else
		{
			theTransaction.hide(
					theFragmentManager
							.findFragmentById(R.id.mm_user_list_text_fragment))
					.commit();
		}
	}

	// Implementors:

	/** */
	@Override
	public void managerEventPerformed(MMMessageManagerEvent aMessageManagerEvent)
	{
		if (aMessageManagerEvent == null)
			throw new NullPointerException("message manager event is null.");

		if (aMessageManagerEvent.isUserAdded())
		{
			updateUserListView();
		} 
		else if (aMessageManagerEvent.isUserRemoved())
		{
			updateUserListView();
		} 
		else if (aMessageManagerEvent.isUserUpdate())
		{
			updateUserListView();
		}
	}
}
