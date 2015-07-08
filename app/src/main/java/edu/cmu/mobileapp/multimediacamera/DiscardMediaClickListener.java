package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by srikrishnan_suresh on 08-07-2015.
 */
public class DiscardMediaClickListener implements View.OnClickListener{
    private Activity activity;
    private ImageView previewImage;
    private String filePath;

    public DiscardMediaClickListener(Activity activity, ImageView previewImage, String filePath) {
        this.activity = activity;
        this.previewImage = previewImage;
        this.filePath = filePath;
    }
    @Override
    public void onClick(View v) {
        File createdFile = new File(filePath);
        if(createdFile.exists()) {
            previewImage.setImageDrawable(null);
            if(createdFile.delete()) {
                Toast.makeText(activity.getApplicationContext(), "Picture deleted", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(activity.getApplicationContext(), "Unable to delete", Toast.LENGTH_SHORT).show();
    }
}
