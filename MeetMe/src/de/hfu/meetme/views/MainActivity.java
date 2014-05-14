package de.hfu.meetme.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import de.hfu.meetme.R;
import de.hfu.meetme.Supporting;
import de.hfu.meetme.model.MMUser;

/**
 * 
 * @author Dominik Jung
 *
 */
public class MainActivity extends Activity {

	/**The user profile */
	protected static MMUser myself = null;
	
	/**A boolean which indicates if a {@link MMUser} object has been created yet or not. 
	 * If the MeetMe App gets started, and this boolean is set false, the App will bring
	 * the user to the SettingsActivity so he can create his user profile. After creating
	 * it, isUserCreated will be set to true and next time the {@link MMUser} object will
	 * be built from the data saved in the SharedPreferences */
	protected static boolean isUserCreated;
	
	/**A final String to identify the key used to save the userCreated boolean in the
	 * SharedPreferences */
	protected final static String IS_USER_CREATED= "isUserCreated";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (myself == null) {
			if(!isUserCreated)
				intentSettingsActivity();
			else 
				myself = Supporting.getUser(this);
			
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		prefs.edit().putBoolean(IS_USER_CREATED, isUserCreated);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		isUserCreated = prefs.getBoolean(IS_USER_CREATED, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			intentSettingsActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Intents the SettingsActivity */
	private void intentSettingsActivity() {
		Intent intent = new Intent(this, de.hfu.meetme.views.SettingsActivity.class);
		startActivity(intent);
	}

}
