package edu.cmu.mobileapp.multimediacamera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.app.Activity;

import edu.cmu.mobileapp.util.AppConstants;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */
public class TweetButtonClickListener implements View.OnClickListener{

    private Activity activity;
    private String filePath;
    private SharedPreferences preference;
    private long mediaType;

    public TweetButtonClickListener(Activity activity, String filePath, long mediaType) {
        this.activity = activity;
        this.filePath = filePath;
        this.mediaType = mediaType;
    }

    @Override
    public void onClick(View v) {
        preference=activity.getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor edit = preference.edit();
        edit.putString("CONSUMER_KEY", AppConstants.TWITTER_CONSUMER_KEY);
        edit.putString("CONSUMER_SECRET", AppConstants.TWITTER_CONSUMER_SECRET);
        edit.commit();

        Intent twitterIntent = new Intent(activity.getApplicationContext(), TwitterAuthActivity.class);
        twitterIntent.putExtra("filePath",filePath);
        twitterIntent.putExtra("mediaType",mediaType);
        activity.startActivity(twitterIntent);

    }
}

