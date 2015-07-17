package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import edu.cmu.mobileapp.task.LoadImageTask;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */
public class BrowserActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser_layout);

        Intent i = getIntent();
        String URL = i.getStringExtra("tweetURL");

        new LoadImageTask(this).execute(URL);
    }
}
