package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import java.io.File;

import edu.cmu.mobileapp.task.UpdateTwitterStatusTask;
import twitter4j.Twitter;

/**
 * Created by srikrishnan_suresh on 16-07-2015.
 */
public class ConfirmTweetListener implements View.OnClickListener {


    private final String filePath;
    private final String tweetStatus;
    private AsyncTask<String,String,String> task;
    private Activity activity;

    public ConfirmTweetListener(Activity activity, String tweetStatus, String filePath) {
        this.tweetStatus = tweetStatus;
        this.filePath = filePath;
        this.activity = activity;
        this.task = new UpdateTwitterStatusTask(activity);
    }

    @Override
    public void onClick(View v) {
        task.execute(tweetStatus,filePath);
    }
}
