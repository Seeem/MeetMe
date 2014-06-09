package de.hfu.meetme.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.R;
import de.hfu.meetme.model.network.networktask.MMNetworkTask;
import de.hfu.meetme.service.MMNetworkService;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMUserListActivity extends Activity
{

	// Internals:

	@Override
	protected void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);
		setContentView(R.layout.activity_user_list);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu aMenu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_list, aMenu);
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
		else if (theId == R.id.action_bar_refresh)
		{
			MMNetworkTask.refreshUserlist();
			return true;
		}
		else if (theId == R.id.action_stop_service)
		{
			stopService(new Intent(this, MMNetworkService.class));
			return true;
		}
		else if (theId == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(anItem);
	}

	@Override
	public void onActivityResult(int aRequestCode, int aResultCode,
			Intent anIntent)
	{
		if ((aRequestCode == MMSupporting.REQUEST_CODE_SETTINGS_ACTIVITY)
				&& (aResultCode == RESULT_OK))
		{
			Toast.makeText(this, "Your profile has been saved",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void finish()
	{
		Intent theIntent = new Intent();
		setResult(RESULT_OK, theIntent);
		super.finish();
	}

	// MM-API:

	/** Sends an intent to the SettingsActivity */
	private void intentSettingsActivity()
	{
		final Intent theIntent = new Intent(this,
				de.hfu.meetme.views.MMSettingsActivity.class);
		startActivityForResult(theIntent, MMSupporting.REQUEST_CODE_SETTINGS_ACTIVITY);
	}

}
