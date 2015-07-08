package edu.cmu.mobileapp.multimediacamera;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by srikrishnan_suresh on 08-07-2015.
 */
public class VideoActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.video_container, Camera2VideoFragment.newInstance())
                    .commit();
        }
    }
}
