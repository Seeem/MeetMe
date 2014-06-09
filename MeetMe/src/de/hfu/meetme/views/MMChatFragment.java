package de.hfu.meetme.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import de.hfu.meetme.R;

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
		
		// Add an onClickListener
		ImageButton theSendButton = (ImageButton) theView
				.findViewById(R.id.mmchat_fragment_chat_send_button);
		theSendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View aView)
			{
				// TODO: the sending operations

				// Test:
				TextView theChatTextView = (TextView) theView
						.findViewById(R.id.mmchat_fragment_chat_text_view);
				theChatTextView.setText("very very very very very very " +
						"very very very very very very very very very " +
						"very very very very very very very very very very " +
						"very very very very very very very very very " +
						"very very very very very very very very very " +
						"very very very very very very very very very " +
						"very very very very very very very very very" +
						"very very very very very very very very very long text");
				//Test
			}

		});

		return theView;
	}

	@Override
	public void onActivityCreated(Bundle aSavedInstanceState)
	{
		super.onActivityCreated(aSavedInstanceState);
	}
}
