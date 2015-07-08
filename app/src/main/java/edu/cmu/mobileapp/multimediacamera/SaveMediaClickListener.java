package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

import edu.cmu.mobileapp.util.DateUtils;

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
        Log.i("logger", "srikriss: " + android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL + ": " + Build.VERSION.RELEASE + ": " + DateUtils.getCurrentFullDate(new Date().getTime()));
        Toast.makeText(activity.getApplicationContext(), "Picture saved", Toast.LENGTH_SHORT).show();
    }
}
