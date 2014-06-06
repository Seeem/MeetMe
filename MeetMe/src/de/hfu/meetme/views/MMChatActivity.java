package de.hfu.meetme.views;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.R;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMChatActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mmchat);
		
		Bundle theExtras = getIntent().getExtras();
		if (theExtras != null)
		{
			int theNotificationId = theExtras
					.getInt(MMSupporting.NOTIFICATION_ID);
			NotificationManager theManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			theManager.cancel(theNotificationId);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mmchat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
