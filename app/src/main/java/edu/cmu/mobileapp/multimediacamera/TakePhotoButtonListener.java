package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Created by srikrishnan_suresh on 06-07-2015.
 */
public class TakePhotoButtonListener implements View.OnClickListener{

    private Activity activity;
    public TakePhotoButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent takePhotoIntent = new Intent(activity.getApplicationContext(), CameraActivity.class);
        activity.startActivity(takePhotoIntent);
    }
}
