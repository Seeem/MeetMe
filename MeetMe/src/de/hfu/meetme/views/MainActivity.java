package de.hfu.meetme.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;

public class MainActivity extends Activity {

	protected static MMUser myself = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (myself == null) {
			intentSettingsActivity();
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
		Intent intent = new Intent(this, de.hfu.meetme.views.SettingsActivity.class);
		startActivity(intent);
	}

}
