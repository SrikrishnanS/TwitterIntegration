package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by srikrishnan_suresh on 08-07-2015.
 */
public class TakeVideoButtonListener implements View.OnClickListener{

    private Activity activity;
    private Fragment fragment;

    private static final int VIDEO_CHECK_VALUE = 9;
    public TakeVideoButtonListener(Activity activity, Fragment fragment) {
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public void onClick(View v) {
        Intent takeVideoIntent = new Intent(activity.getApplicationContext(), VideoActivity.class);
        fragment.startActivityForResult(takeVideoIntent, VIDEO_CHECK_VALUE);
    }
}
