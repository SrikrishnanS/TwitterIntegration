package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by srikrishnan_suresh on 08-07-2015.
 */
public class SaveMediaClickListener implements View.OnClickListener{
    private Activity activity;
    public SaveMediaClickListener(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(activity.getApplicationContext(), "Picture saved", Toast.LENGTH_SHORT).show();
    }
}
