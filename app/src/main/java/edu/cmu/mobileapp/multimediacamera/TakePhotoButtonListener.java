package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.support.v4.app.Fragment;
/**
 * Created by srikrishnan_suresh on 06-07-2015.
 */
public class TakePhotoButtonListener implements View.OnClickListener{

    private Activity activity;
    private Fragment fragment;

    private static final int IMAGE_CHECK_VALUE = 7;
    public TakePhotoButtonListener(Activity activity, Fragment fragment) {
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public void onClick(View v) {
        Intent takeVideoIntent = new Intent(activity.getApplicationContext(), CameraActivity.class);
        fragment.startActivityForResult(takeVideoIntent, IMAGE_CHECK_VALUE);
    }
}
