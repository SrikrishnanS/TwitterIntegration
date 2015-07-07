package edu.cmu.mobileapp.multimediacamera;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by srikrishnan_suresh on 06-07-2015.
 */
public class CameraActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}
