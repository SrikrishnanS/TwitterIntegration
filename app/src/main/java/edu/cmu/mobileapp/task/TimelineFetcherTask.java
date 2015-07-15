package edu.cmu.mobileapp.task;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import android.app.Activity;
import android.widget.ListView;

import edu.cmu.mobileapp.multimediacamera.ListItemAdapter;
import edu.cmu.mobileapp.multimediacamera.R;
import edu.cmu.mobileapp.multimediacamera.TweetClickListener;
import edu.cmu.mobileapp.util.DateUtils;
import edu.cmu.mobileapp.util.TwitterUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */

public class TimelineFetcherTask extends AsyncTask<String, Void, List<twitter4j.Status>> {

    private Activity activity;
    private ListView listView;
    public TimelineFetcherTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<twitter4j.Status> statusList) {
        super.onPostExecute(statusList);
        listView = (ListView) activity.findViewById(R.id.listView);
        listView.setAdapter(new ListItemAdapter(activity, statusList));
        listView.setOnItemClickListener(new TweetClickListener(activity, statusList));
    }

    @Override
    protected List<twitter4j.Status> doInBackground(String... params) {
        List<twitter4j.Status> statusList = null;
        Twitter twitter = null;
        String twitterHandler = params[0];
        try {
            twitter = TwitterUtils.getInstance().getTwitter();
            statusList = twitter.getUserTimeline(twitterHandler);
        }
        catch(TwitterException exception) {

        }
        return statusList;
    }
}

