package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

import edu.cmu.mobileapp.util.DateUtils;

/**
 * Created by srikrishnan_suresh on 08-07-2015.
 */
public class SaveMediaClickListener implements View.OnClickListener{
    private Activity activity;
    private int mediaType;
    private String filePath;

    public SaveMediaClickListener(Activity activity, String filePath, int mediaType) {
        this.activity = activity;
        this.filePath = filePath;
        this.mediaType = mediaType;
    }

    @Override
    public void onClick(View v) {
        Log.i("logger", "srikriss: " + android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL + ": " + Build.VERSION.RELEASE + ": " + DateUtils.getCurrentFullDate(new Date().getTime()));

        ContentValues values = new ContentValues();
        Long time = System.currentTimeMillis()/1000;
        values.put(MediaStore.Files.FileColumns.DATE_ADDED, time);
        values.put(MediaStore.Files.FileColumns.DATE_MODIFIED, time);
        values.put(MediaStore.Files.FileColumns.DATA, filePath);
        values.put(MediaStore.Files.FileColumns.MEDIA_TYPE, mediaType);
        activity.getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
        Toast.makeText(activity.getApplicationContext(), "File saved", Toast.LENGTH_SHORT).show();

        Button saveButton = (Button)activity.findViewById(R.id.save_button);
        Button discardButton = (Button)activity.findViewById(R.id.discard_image);
        saveButton.setVisibility(View.INVISIBLE);
        discardButton.setVisibility(View.INVISIBLE);
    }
}
