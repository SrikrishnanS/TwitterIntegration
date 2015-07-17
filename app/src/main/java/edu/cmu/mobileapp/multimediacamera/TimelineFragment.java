package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import edu.cmu.mobileapp.task.TimelineFetcherTask;
import edu.cmu.mobileapp.util.TwitterUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by srikrishnan_suresh on 12-07-2015.
 */
public class TimelineFragment extends Fragment {
    private ListView listView;
    private TimelineFetcherTask timelineFetcherTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timeline_layout, container, false);
        final Activity activity = getActivity();

        final SwipeRefreshLayout pullToRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.pullToRefresh);
        listView = (ListView) rootView.findViewById(R.id.listView);

        final String twitterHandler = activity.getString(R.string.handler_name);

        new TimelineFetcherTask(activity).execute(twitterHandler);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new TimelineFetcherTask(activity).execute(twitterHandler);
                pullToRefresh.setRefreshing(false);
            }
        });
        return rootView;
    }

}
