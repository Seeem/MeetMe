package de.hfu.meetme.service;

import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerEvent;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerListener;
import de.hfu.meetme.model.network.networktask.MMNetworkTask;
import de.hfu.meetme.views.MMUserListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMNetworkService extends Service implements MMMessageManagerListener
{
	//Class Members:
	/** */
	
	
	//Instance Members:
	
	private final IBinder binder = new MMNetworkServiceBinder();
	// public MMNetworkService()
	// {
	// }

	//Internals
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
	
	@Override
	public int onStartCommand (Intent intent, int flags, int startId) {
		MMNetworkTask.addMessageManagerListener(this);
		MMNetworkTask.startListening();
		Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		MMNetworkTask.removeMessageManagerListener(this);
		MMNetworkTask.stopListening();
		Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return binder;
	}

	public class MMNetworkServiceBinder extends Binder
	{
		//Interface Methods
	}

	@Override
	public void managerEventPerformed(MMMessageManagerEvent aMessageManagerEvent)
	{
		if (aMessageManagerEvent.isUserWantsAMeeting())
		{
			System.out.println("User wants a meeting: "+aMessageManagerEvent.getUser());
			generateNotification(aMessageManagerEvent.getUser(), " wants to meet you!");
		}		
	}
	
	
	
	/**
	 * 
	 * @param anUser
	 *            the user who sent the message. His username will be used as
	 *            notification title, his id as notification id to seperate
	 *            notifications from distinct users.
	 * @param message
	 *            the message to be shown as notification text, e.g.
	 *            "wants to meet you!"
	 */
	private void generateNotification(final MMUser anUser, final String message)
	{
		
		if (anUser == null)
			throw new NullPointerException("user is null.");
		/*
		 * TODO: improve Notification 
		 * Add a proper icon (could be better)
		 * Add a proper intent
		 */
		String theUserId = anUser.getId();
		theUserId = theUserId.replace(".", "").substring(theUserId.length() - 6);
		int theId = Integer.parseInt(theUserId);
		Intent theIntent = new Intent(this, MMUserListActivity.class);
		theIntent.putExtra(MMSupporting.NOTIFICATION_ID, theId);

		PendingIntent thePendingIntent = PendingIntent.getActivity(
				this, 0, theIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		Notification theNotification = new Notification.Builder(this)
				.setContentTitle(anUser.getUsername()).setContentText(message)
				.setWhen(System.currentTimeMillis())
				.setDefaults(Notification.DEFAULT_ALL)
				.setSmallIcon(android.R.drawable.ic_dialog_email)
				.setTicker(anUser.getUsername() + " wants to meet you!")
				.setLights(0xFFFFFFFF, 1500, 3000)
				.setContentIntent(thePendingIntent)
				.build();

		NotificationManager theManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);
		int theNotification_id = theId;
		theManager.notify(theNotification_id, theNotification);
	}
}
