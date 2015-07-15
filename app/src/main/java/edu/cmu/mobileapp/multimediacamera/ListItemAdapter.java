package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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


        return view;
    }
}
