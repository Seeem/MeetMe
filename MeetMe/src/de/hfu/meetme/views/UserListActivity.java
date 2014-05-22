package de.hfu.meetme.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import de.hfu.meetme.R;

public class UserListActivity extends Activity
{

	private final static int REQUEST_CODE_SETTINGS_ACTIVITY = 1;
	public final static int REQUEST_CODE_USER_PROFILE_ACTIVITY = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_list, menu);
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
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if ((requestCode == UserListActivity.REQUEST_CODE_USER_PROFILE_ACTIVITY)
				&& (resultCode == RESULT_OK))
		{
		}
		if ((requestCode == UserListActivity.REQUEST_CODE_SETTINGS_ACTIVITY)
				&& (resultCode == RESULT_OK))
		{
			Toast.makeText(this, "Your profile has been saved",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void finish()
	{
		Intent intent = new Intent();
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
}