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
public class MMNetworkService extends Service implements
		MMMessageManagerListener
{

	// Class members:

	public static boolean SERVICE_STATE_ON;

	// Instance Members:

	private final IBinder binder = new MMNetworkServiceBinder();

	// Internals:

	@Override
	public void onCreate()
	{
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		MMNetworkTask.addMessageManagerListener(this);
		MMNetworkTask.startListening();
		Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
		SERVICE_STATE_ON = true;
		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		MMNetworkTask.removeMessageManagerListener(this);
		MMNetworkTask.stopListening();
		Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
		SERVICE_STATE_ON = false;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return binder;
	}

	public class MMNetworkServiceBinder extends Binder
	{
		// Interface Methods
	}

	@Override
	public void managerEventPerformed(MMMessageManagerEvent aMessageManagerEvent)
	{
		if (aMessageManagerEvent.isUserWantsAMeeting())
		{
			generateNotification(aMessageManagerEvent.getUser(),
					" wants to meet you!");
		} else if (aMessageManagerEvent.isUserMessage())
		{
			MMUser theUser = MMUser.getUserById(aMessageManagerEvent.getUser()
					.getId());
			theUser.appendChatMessage(aMessageManagerEvent.getMessage(),
					theUser);
			if (!MMChatActivity.CHAT_ACTIVITY_ACTIVE)
			{
				generateNotification(theUser, aMessageManagerEvent.getMessage(),
						theUser.getUsername() + " has sent you a message\n" +
						aMessageManagerEvent.getMessage());
			}
		}
	}

	/**
	 * Creates a notification.
	 * 
	 * @param anUser
	 *            the user who sent the message. His username will be used as
	 *            notification title, his id as notification id to seperate
	 *            notifications from distinct users.
	 * @param aMessage
	 *            the message to be shown as notification text, e.g.
	 *            "wants to meet you!"
	 * @param aTickerText
	 *            the text to be shown in the notification ticker.
	 */
	private void generateNotification(final MMUser anUser,
			final String aMessage, final String aTickerText)
	{
		if (anUser == null)
			throw new NullPointerException("user is null.");

		int theId = anUser.getNotificationId();

		Intent theIntent = new Intent(this, MMChatActivity.class);
		theIntent.putExtra(MMSupporting.NOTIFICATION_ID, theId);
		theIntent.putExtra(MMSupporting.MMUSER_KEY, anUser);

		Notification theNotification = new Notification.Builder(this)
				.setContentTitle(anUser.getUsername())
				.setContentText(aMessage)
				.setWhen(System.currentTimeMillis())
				.setDefaults(Notification.DEFAULT_ALL)
				.setSmallIcon(android.R.drawable.ic_dialog_email)
				.setTicker(aTickerText)
				.setLights(0xFFFFFFFF, 2000, 3000)
				.setContentIntent(
						PendingIntent.getActivity(this, 0, theIntent,
								PendingIntent.FLAG_UPDATE_CURRENT)).build();

		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
				.notify(theId, theNotification);
	}

	/**
	 * @param anUser
	 *            the user who sent the message. His username will be used as
	 *            notification title, his id as notification id to seperate
	 *            notifications from distinct users.
	 * @param aMessage
	 *            the message to be shown as notification text, e.g.
	 *            "wants to meet you!" 
	 */
	private void generateNotification(final MMUser anUser, final String aMessage)
	{
		generateNotification(anUser, aMessage, anUser.getUsername() + aMessage);

	}
}
