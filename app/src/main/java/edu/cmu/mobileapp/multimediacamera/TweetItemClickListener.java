package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import twitter4j.MediaEntity;
import twitter4j.Status;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */
public class TweetItemClickListener implements AdapterView.OnItemClickListener {
    private Activity activity;
    private List<Status> statusList;
    public TweetItemClickListener(Activity activity, List<Status> statusList) {
        this.activity = activity;
        this.statusList = statusList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {

        Status status = statusList.get(position);
        MediaEntity[] entities = status.getMediaEntities();
        if(entities.length >0 ) {
            String tweetURL = entities[0].getMediaURL();

            Intent viewWebpage = new Intent(activity.getApplicationContext(), BrowserActivity.class);
            viewWebpage.putExtra("tweetURL", tweetURL);
            activity.startActivity(viewWebpage);
        }
    }
}
