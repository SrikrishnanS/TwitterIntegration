package edu.cmu.mobileapp.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;
import android.app.Activity;
import android.widget.ListView;

import edu.cmu.mobileapp.multimediacamera.ListItemAdapter;
import edu.cmu.mobileapp.multimediacamera.R;
import edu.cmu.mobileapp.multimediacamera.TweetItemClickListener;
import edu.cmu.mobileapp.util.TwitterUtils;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */

public class TimelineFetcherTask extends AsyncTask<String, Void, List<twitter4j.Status>> {

    private Activity activity;
    private ListView listView;
    private ProgressDialog progress;
    public TimelineFetcherTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(activity);
        progress.setMessage("Loading Tweets...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }

    @Override
    protected void onPostExecute(List<twitter4j.Status> statusList) {
        super.onPostExecute(statusList);
        progress.hide();
        listView = (ListView) activity.findViewById(R.id.listView);
        listView.setAdapter(new ListItemAdapter(activity, statusList));
        listView.setOnItemClickListener(new TweetItemClickListener(activity, statusList));
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

