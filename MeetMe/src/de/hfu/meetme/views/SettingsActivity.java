package de.hfu.meetme.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import de.hfu.meetme.R;
import de.hfu.meetme.Supporting;
import de.hfu.meetme.model.MMUser;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class SettingsActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public void finish()
	{
		MMUser user = Supporting.getUserFromSharedPreferences(this, false);
		if (user != null)
		{
			Intent intent = new Intent();
			intent.putExtra(MainActivity.IS_USER_CREATED, true);
			intent.putExtra(MainActivity.MMUSER_TAG, user);
			setResult(RESULT_OK, intent);
			super.finish();
		}

	}

}
