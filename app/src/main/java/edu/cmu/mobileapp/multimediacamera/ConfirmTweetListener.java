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
    private long mediaPath;

    public ConfirmTweetListener(Activity activity, String tweetStatus, String filePath, long mediaPath) {
        this.tweetStatus = tweetStatus;
        this.filePath = filePath;
        this.activity = activity;
        this.mediaPath = mediaPath;
        this.task = new UpdateTwitterStatusTask(activity,mediaPath);
    }

    @Override
    public void onClick(View v) {
        task.execute(tweetStatus,filePath);
    }
}
