package de.hfu.meetme.views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class UserProfileActivity extends Activity
{
	/**
	 * The user clicked in the {@link UserListActivity}
	 */
	private MMUser user;

	/**
	 * @return the user
	 */
	public MMUser getUser()
	{
		return user;
	}

	/**
	 * @param aUser
	 *            the user to set
	 */
	public void setUser(MMUser aUser)
	{
		user = aUser;
	}

	private static final int REQUEST_CODE_SETTINGS_ACTIVITY = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		final ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		

		Bundle extras = getIntent().getExtras();
		setUser((MMUser) extras.getSerializable(UserListFragment.MMUSER_KEY));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			intentSettingsActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void finish()
	{
		Intent intent = new Intent(this, UserListActivity.class);
		setResult(RESULT_OK, intent);
		super.finish();
	}

	/** Sends an intent to the SettingsActivity */
	private void intentSettingsActivity()
	{
		final Intent intent = new Intent(this,
				de.hfu.meetme.views.SettingsActivity.class);
		startActivityForResult(intent, REQUEST_CODE_SETTINGS_ACTIVITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent)
	{
		if ((requestCode == REQUEST_CODE_SETTINGS_ACTIVITY)
				&& (resultCode == RESULT_OK))
		{
			Toast.makeText(this, "Your profile has been saved",
					Toast.LENGTH_SHORT).show();
		}
	}
}
