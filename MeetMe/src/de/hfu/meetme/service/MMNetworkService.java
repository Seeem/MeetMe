package de.hfu.meetme.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import de.hfu.meetme.MMSupporting;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerEvent;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerListener;
import de.hfu.meetme.model.network.networktask.MMNetworkTask;
import de.hfu.meetme.views.MMChatActivity;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMNetworkService extends Service implements MMMessageManagerListener
{

	//Instance Members:
	
	private final IBinder binder = new MMNetworkServiceBinder();

	//Internals:
	
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
		 */
		int theId = anUser.getNotificationId();
			
		Intent theIntent = new Intent(this, MMChatActivity.class);
		theIntent.putExtra(MMSupporting.NOTIFICATION_ID, theId);

		Notification theNotification = new Notification.Builder(this)
				.setContentTitle(anUser.getUsername()).setContentText(message)
				.setWhen(System.currentTimeMillis())
				.setDefaults(Notification.DEFAULT_ALL)
				.setSmallIcon(android.R.drawable.ic_dialog_email)
				.setTicker(anUser.getUsername() + " wants to meet you!")
				.setLights(0xFFFFFFFF, 2000, 3000)
				.setContentIntent(PendingIntent.getActivity(this, 0, theIntent, PendingIntent.FLAG_UPDATE_CURRENT))
				.build();

		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
			.notify(theId, theNotification);
	}
}
