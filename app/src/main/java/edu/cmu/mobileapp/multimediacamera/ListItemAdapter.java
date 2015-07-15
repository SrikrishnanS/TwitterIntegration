package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.cmu.mobileapp.util.DateUtils;
import twitter4j.Status;

/**
 * Created by srikrishnan_suresh on 14-07-2015.
 */
public class ListItemAdapter extends BaseAdapter {
    private Activity activity;
    private List<Status> statusList;

    private static LayoutInflater inflater = null;

    public ListItemAdapter(Activity activity, List<Status> statusList) {
        this.activity = activity;
        this.statusList = statusList;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return statusList.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null)
            view = inflater.inflate(R.layout.list_item_layout, null);

        TextView fullNameText = (TextView)view.findViewById(R.id.full_name);
        TextView screenNameText = (TextView)view.findViewById(R.id.screen_name);
        TextView dateText = (TextView)view.findViewById(R.id.date);
        TextView statusText = (TextView)view.findViewById(R.id.status);

        Status status = statusList.get(position);
        fullNameText.setText(status.getUser().getName());
        screenNameText.setText("@"+status.getUser().getScreenName());
        dateText.setText(DateUtils.getShortDateString(status.getCreatedAt()));
        statusText.setText(status.getText());

        return view;
    }
}
