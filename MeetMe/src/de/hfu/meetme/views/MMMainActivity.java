 package de.hfu.meetme.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import de.hfu.meetme.R;
import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.service.MMNetworkService;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMMainActivity extends Activity
{

	/**
	 * A final String to identify the key used to save the userCreated boolean
	 * in the SharedPreferences
	 */
	protected final static String IS_USER_CREATED = "isUserCreated";

	/** A final String which names the SharedPreferences */
	public final static String SHARED_PREFERENCES_NAME = "MeetMeAppPreferences";

	/**
	 * The tag used to put the {@link MMUser} object 'myself' as extra to an
	 * intent.
	 */
	public final static String MMUSER_TAG = "Myself";

	/**
	 * @return A boolean which indicates if a {@link MMUser} object has been
	 *         created yet or not. If the MeetMe App gets started, and this
	 *         boolean is set to false, the App will bring the user to the
	 *         SettingsActivity so he can create his user profile. After
	 *         creating it, isUserCreated will be set to true and next time the
	 *         {@link MMUser} object will be built from the data saved in the
	 *         SharedPreferences.
	 */
	//TODO: Move to MMUser
	public boolean isUserCreated()
	{
		SharedPreferences theSettings = getSharedPreferences(
				SHARED_PREFERENCES_NAME, MODE_PRIVATE);
		return theSettings.getBoolean(IS_USER_CREATED, false);
	}

	/**
	 * @param isUserCreated
	 *            the isUserCreated to set
	 */
	//TODO: Move to MMUser
	public void setIsUserCreated(boolean isUserCreated)
	{
		SharedPreferences thePrefs = getSharedPreferences(SHARED_PREFERENCES_NAME,
				MODE_PRIVATE);
		thePrefs.edit().putBoolean(IS_USER_CREATED, isUserCreated).commit();
	}

	@Override
	protected void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);
		setContentView(R.layout.activity_main);

		if (!isUserCreated())
		{
			intentSettingsActivity();
		} else
		{
			MMUser.setMyself(MMSupporting.getUserFromSharedPreferences(this, false));
			MMUser.getMyself().print();
		}
		
		startService(new Intent(this, MMNetworkService.class));
		Intent intent = new Intent(this, MMChatActivity.class); //TODO: Remove
		startActivity(intent);
	}

	/** */
	@Override protected void onResume()
	{
		super.onResume();			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu aMenu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, aMenu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem anItem)
	{
		int theId = anItem.getItemId();
		if (theId == R.id.action_settings)
		{
			intentSettingsActivity();
			return true;
		}
		return super.onOptionsItemSelected(anItem);
	}

	@Override
	protected void onActivityResult(int aRequestCode, int aResultCode,
			Intent anIntent)
	{
		if ((aRequestCode == MMSupporting.REQUEST_CODE_SETTINGS_ACTIVITY)
				&& (aResultCode == RESULT_OK))
		{

			Bundle theBundle = anIntent.getExtras();
			setIsUserCreated(theBundle.getBoolean(IS_USER_CREATED));

			Toast.makeText(this, "Your profile has been saved",
					Toast.LENGTH_SHORT).show();

		}
		if ((aRequestCode == MMSupporting.REQUEST_CODE_USER_LIST_ACTIVITY)
				&& (aResultCode == RESULT_OK))
		{
		}
	}

	/** Sends an intent to the UserListActivity */
	public void intentUserListActivity(View aView)
	{	
		final Intent theIntent = new Intent(this,de.hfu.meetme.views.MMUserListActivity.class);
		startActivityForResult(theIntent, MMSupporting.REQUEST_CODE_USER_LIST_ACTIVITY);
	}

	/** Sends an intent to the SettingsActivity */
	private void intentSettingsActivity()
	{
		final Intent theIntent = new Intent(this,
				de.hfu.meetme.views.MMSettingsActivity.class);
		startActivityForResult(theIntent, MMSupporting.REQUEST_CODE_SETTINGS_ACTIVITY);
	}

}
