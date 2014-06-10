package de.hfu.meetme.views;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMChatActivity extends Activity
{

	public static boolean CHAT_ACTIVITY_ACTIVE;

	/** */
	private MMUser user;

	// Accessors

	/**
	 * @return the user
	 */
	public MMUser getUser()
	{
		return user;
	}

	/**
	 * @param aUser
	 *            the user to set
	 */
	public void setUser(MMUser aUser)
	{
		user = aUser;
	}

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
			setUser((MMUser) theExtras.getSerializable(MMSupporting.MMUSER_KEY));
		}
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle(getUser().getUsername());
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
			final Intent theIntent = new Intent(this,
					de.hfu.meetme.views.MMSettingsActivity.class);
			startActivityForResult(theIntent,
					MMSupporting.REQUEST_CODE_SETTINGS_ACTIVITY);
			return true;
		}
		if (id == android.R.id.home)
		{
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		CHAT_ACTIVITY_ACTIVE = true;
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		CHAT_ACTIVITY_ACTIVE = false;
	}

	@Override
	public void finish()
	{
		super.finish();
	}

}
