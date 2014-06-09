package de.hfu.meetme.views;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
import de.hfu.meetme.model.network.networktask.MMNetworkTask;

/**
 * 
 * @author Dominik Jung
 * 
 */
public class MMChatFragment extends Fragment
{
	

	@Override
	public void onCreate(Bundle aSavedInstanceState)
	{
		super.onCreate(aSavedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater anInflater, ViewGroup aContainer,
			Bundle aSavedInstanceState)
	{

		// Inflate the layout for this fragment
		final View theView = anInflater.inflate(R.layout.fragment_mmchat, aContainer,
				false);
		
		final EditText theChatEditText = 
				(EditText) theView.findViewById(R.id.mmchat_fragment_chat_edit_text);
		
		// Add an onClickListener
		ImageButton theSendButton = (ImageButton) theView
				.findViewById(R.id.mmchat_fragment_chat_send_button);
		theSendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View aView)
			{
				// TODO: the sending operations
				try
				{
					MMNetworkTask.sendChatMessage(InetAddress.getByName(
							((MMChatActivity)getActivity()).getUser().getId()),
							theChatEditText.getText().toString());
				} catch (UnknownHostException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		//Make TextView scrollable
		((TextView) theView.findViewById(R.id.mmchat_fragment_chat_text_view))
			.setMovementMethod(new ScrollingMovementMethod());
		

		return theView;
	}

	@Override
	public void onActivityCreated(Bundle aSavedInstanceState)
	{
		super.onActivityCreated(aSavedInstanceState);
	}
}
