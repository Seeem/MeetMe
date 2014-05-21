package de.hfu.meetme;

import java.util.Arrays;
import java.util.List;

import de.hfu.meetme.model.MMUser;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MMUserArrayAdapter extends ArrayAdapter<MMUser>
{

	private int mResource;
	private LayoutInflater mInflater;
	private int mFieldId;

	public MMUserArrayAdapter(Context aContext, int aResource, MMUser[] aObjects)
	{
		super(aContext, aResource, aObjects);
		init(aContext, aResource, 0, Arrays.asList(aObjects));
	}

	private void init(Context context, int resource, int textViewResourceId,
			List<MMUser> objects)
	{
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mResource = resource;
		mFieldId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		return createViewFromResource(position, convertView, parent, mResource);
	}

	private View createViewFromResource(int position, View convertView,
			ViewGroup parent, int resource)
	{
		View view;
		TextView text;
		if (convertView == null)
		{
			view = mInflater.inflate(resource, parent, false);
		} else
		{
			view = convertView;
		}
		try
		{
			if (mFieldId == 0)
			{
				// If no custom field is assigned, assume the whole resource is
				// a TextView
				text = (TextView) view;
			} else
			{

				// Otherwise, find the TextView field within the layout
				text = (TextView) view.findViewById(mFieldId);
			}
		} catch (ClassCastException e)
		{
			Log.e("ArrayAdapter",
					"You must supply a resource ID for a TextView");
			throw new IllegalStateException(
					"ArrayAdapter requires the resource ID to be a TextView", e);
		}
		MMUser item = getItem(position);
		if (item instanceof CharSequence)
		{
			text.setText((CharSequence) item);
		} else
		{
			text.setText(item.getUsername());
		}
		return view;
	}

}
