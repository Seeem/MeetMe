package de.hfu.meetme.views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMUserProfileActivity extends Activity
{
	/**
	 * The user clicked in the {@link MMUserListActivity}
	 */
	private MMUser user;

	// Accessors:
	
	/**
	 * @return the user
	 */
	protected MMUser getUser()
	{
		return user;
	}

	/**
	 * @param anUser the user to set
	 */
	protected void setUser(MMUser anUser)
	{
		user = anUser;
	}	
	
	@Override
	protected void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		final ActionBar theActionBar = getActionBar();
		theActionBar.setDisplayHomeAsUpEnabled(true);
		
		Bundle theExtras = getIntent().getExtras();
		setUser((MMUser) theExtras.getSerializable(MMSupporting.MMUSER_KEY));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu aMenu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, aMenu);
		return true;
	}

	/** */
	@Override public boolean onOptionsItemSelected(MenuItem anItem)
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
	public void finish()
	{
		Intent theIntent = new Intent(this, MMUserListActivity.class);
		setResult(RESULT_OK, theIntent);
		super.finish();
	}

	/**
	 * Sends an intent to the SettingsActivity
	 */
	private void intentSettingsActivity()
	{
		final Intent theIntent = new Intent(this,
				de.hfu.meetme.views.MMSettingsActivity.class);
		startActivityForResult(theIntent, MMSupporting.REQUEST_CODE_SETTINGS_ACTIVITY);
	}

	/** */
	@Override protected void onActivityResult(int aRequestCode, int aResultCode,
			Intent anIntent)
	{
		if ((aRequestCode == MMSupporting.REQUEST_CODE_SETTINGS_ACTIVITY)
				&& (aResultCode == RESULT_OK))
		{
			Toast.makeText(this, "Your profile has been saved",
					Toast.LENGTH_SHORT).show();
		}
	}

}
