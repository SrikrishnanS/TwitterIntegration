package edu.cmu.mobileapp.task;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import android.app.Activity;
import edu.cmu.mobileapp.multimediacamera.R;
import edu.cmu.mobileapp.util.TwitterUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */

public class TimelineFetcherTask extends AsyncTask<String, Void, List<twitter4j.Status>> {

    private Activity activity;
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
        twitter4j.Status status = statusList.get(0);
        Log.i("Text>>>",status.getText());
        Log.i("Screen Name>>>",status.getUser().getScreenName());
        Log.i("Name>>>",status.getUser().getName());

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

