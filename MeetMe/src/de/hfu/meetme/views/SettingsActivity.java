package de.hfu.meetme.views;

import de.hfu.meetme.R;
import de.hfu.meetme.Supporting;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 
 * @author Dominik Jung
 *
 */
public class SettingsActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public void finish() {
		Intent intent = new Intent();
			
		//Some Code returning the USer and boolean
		intent.putExtra(MainActivity.IS_USER_CREATED, true);
		intent.putExtra(MainActivity.MMUSER_TAG, Supporting.getUserFromSharedPreferences(this));
		
		setResult(RESULT_OK, intent);
		super.finish();
	}
	
}
