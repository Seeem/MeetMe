package de.hfu.meetme.views;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import de.hfu.meetme.R;
import de.hfu.meetme.model.MMUser;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerEvent;
import de.hfu.meetme.model.network.messagemanager.MMMessageManagerListener;
import de.hfu.meetme.model.network.networktask.MMNetworkTask;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMChatFragment extends Fragment implements
		MMMessageManagerListener
{

	private MMUser user;
	private TextView chatLog;

	// Accessors:

	/**
	 * @return the chatLog
	 */
	public TextView getChatLog()
	{
		return chatLog;
	}

	/**
	 * @param aChatLog
	 *            the chatLog to set
	 */
	public void setChatLog(TextView aChatLog)
	{
		chatLog = aChatLog;
	}

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
	public void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		MMNetworkTask.addMessageManagerListener(this);
		updateChatView();
	}

	@Override
	public void onPause()
	{
		super.onPause();
		MMNetworkTask.removeMessageManagerListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater anInflater, ViewGroup aContainer,
			Bundle aSavedInstanceState)
	{

		// Inflate the layout for this fragment
		final View theView = anInflater.inflate(R.layout.fragment_mmchat,
				aContainer, false);

		setChatLog((TextView) theView
				.findViewById(R.id.mmchat_fragment_chat_text_view));

		final EditText theChatEditText = (EditText) theView
				.findViewById(R.id.mmchat_fragment_chat_edit_text);

		// Add an onClickListener
		ImageButton theSendButton = (ImageButton) theView
				.findViewById(R.id.mmchat_fragment_chat_send_button);
		theSendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View aView)
			{
				String theMessage = theChatEditText.getText().toString();

				if (!theMessage.equals(""))
				{
					theChatEditText.setText("");
					MMNetworkTask.sendChatMessage(getUser().getIpAddress(),
							theMessage);
					getUser().appendChatMessage(theMessage, MMUser.getMyself());
					updateChatView();
				}
			}

		});

		// Make TextView scrollable TODO
		((TextView) theView.findViewById(R.id.mmchat_fragment_chat_text_view))
				.setMovementMethod(new ScrollingMovementMethod());

		return theView;
	}

	@Override
	public void onActivityCreated(Bundle aSavedInstanceState)
	{
		super.onActivityCreated(aSavedInstanceState);
		setUser(MMUser.getUserById(((MMChatActivity) getActivity()).getUser().getId()));
		getChatLog().setText(getUser().getChatLogAsString());
	}

	/** */
	private void updateChatView()
	{
		this.getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run()
			{
				getChatLog().setText(getUser().getChatLogAsString());
			}

		});
	}

	@Override
	public void managerEventPerformed(MMMessageManagerEvent aMessageManagerEvent)
	{
		if (aMessageManagerEvent.isUserMessage()
				&& aMessageManagerEvent.getUser() == getUser())
		{
			updateChatView();	
		}
	}
}
