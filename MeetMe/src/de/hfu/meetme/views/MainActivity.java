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
	private MMUser myself = null;
	
	/**A boolean which indicates if a {@link MMUser} object has been created yet or not. 
	 * If the MeetMe App gets started, and this boolean is set false, the App will bring
	 * the user to the SettingsActivity so he can create his user profile. After creating
	 * it, isUserCreated will be set to true and next time the {@link MMUser} object will
	 * be built from the data saved in the SharedPreferences */
	protected static boolean isUserCreated;
	
	/**A final String to identify the key used to save the userCreated boolean in the
	 * SharedPreferences */
	protected final static String IS_USER_CREATED= "isUserCreated";
	
	/**A final String which names the SharedPreferences */
	public final static String SHARED_PREFERENCES_NAME= "MeetMeAppPreferences";
	
	/**The tag used to put the {@link MMUser} object 'myself' as extra to an intent.  */
	public final static String MMUSER_TAG = "Myself";
	
	/** */
	private static final int REQUEST_CODE = 1;
	
	/**
	 * @return myself
	 */
	public MMUser getMyself()
	{
		return myself;
	}

	/**
	 * @param aMyself the myself to set
	 */
	public void setMyself(MMUser aMMuser)
	{
		myself = aMMuser;
	}

	/**
	 * @return isUserCreated
	 */
	public static boolean isUserCreated()
	{
		return isUserCreated;
	}

	/**
	 * @param isUserCreated the isUserCreated to set
	 */
	public static void setIsUserCreated(boolean isUserCreated)
	{
		MainActivity.isUserCreated = isUserCreated;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
		setIsUserCreated(settings.getBoolean(IS_USER_CREATED, false));

	
		if(!isUserCreated)
			intentSettingsActivity();
		else 
			myself = Supporting.getUserFromSharedPreferences(this);
			
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
		prefs.edit().putBoolean(IS_USER_CREATED, isUserCreated);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
		isUserCreated = prefs.getBoolean(IS_USER_CREATED, false);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if ((requestCode == REQUEST_CODE) && 
		                     (resultCode == RESULT_OK)) {
				
			Bundle bundle = intent.getExtras();
			setIsUserCreated(bundle.getBoolean(IS_USER_CREATED));
			setMyself((MMUser)bundle.getSerializable(MMUSER_TAG));
					
		       }
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
		final Intent intent = new Intent(this, de.hfu.meetme.views.SettingsActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}

	

}
