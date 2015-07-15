package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import twitter4j.Status;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */
public class TweetClickListener implements AdapterView.OnItemClickListener {
    private Activity activity;
    private List<Status> statusList;
    public TweetClickListener(Activity activity, List<Status> statusList) {
        this.activity = activity;
        this.statusList = statusList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {

        Status status = statusList.get(position);
        String tweetURL = "https://twitter.com/" + status.getUser().getScreenName()+"/status/"+status.getId();

        //Intent viewMediaIntent = new Intent(activity.getApplicationContext(), MediaFullViewActivity.class);
        //viewMediaIntent.putExtra("filePath", files.get(position).getFilePath());
        //activity.startActivity(viewMediaIntent);
    }
}