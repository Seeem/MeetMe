package de.hfu.meetme.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.networktask.MMNetworkTask;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMSettingsActivity extends Activity
{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem anItem)
	{
		int theId = anItem.getItemId();
		if (theId == android.R.id.home)
		{
			finish();
			return true;
		}
		return super.onOptionsItemSelected(anItem);
	}

	@Override
	public void finish()
	{
		MMUser user = MMSupporting.getUserFromSharedPreferences(this, false);
		if (user != null)
		{
			final Intent theIntent = new Intent();
			theIntent.putExtra(MMMainActivity.IS_USER_CREATED, true);
			setResult(RESULT_OK, theIntent);
			MMUser.setMyself(user);
			MMNetworkTask.sendUpdate();	
			super.finish();
			Toast.makeText(this, "Your settings have been saved", Toast.LENGTH_SHORT).show();
		}
		
	}

}
