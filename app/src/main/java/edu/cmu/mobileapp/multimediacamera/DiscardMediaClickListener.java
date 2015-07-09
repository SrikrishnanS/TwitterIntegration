package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by srikrishnan_suresh on 08-07-2015.
 */
public class DiscardMediaClickListener implements View.OnClickListener{
    private Activity activity;
    private ImageView previewImage;
    private VideoView previewVideo;
    private String filePath;

    public DiscardMediaClickListener(Activity activity, VideoView previewVideo, String filePath) {
        this.activity = activity;
        this.previewVideo = previewVideo;
        this.filePath = filePath;
    }

    public DiscardMediaClickListener(Activity activity, ImageView previewImage, String filePath) {
        this.activity = activity;
        this.previewImage = previewImage;
        this.filePath = filePath;
    }
    @Override
    public void onClick(View v) {
        File createdFile = new File(filePath);
        if(createdFile.exists()) {
            if(previewImage != null)
                previewImage.setImageDrawable(null);
            if(previewVideo != null)
                previewVideo.setVisibility(View.INVISIBLE);
            if(createdFile.delete()) {
                Toast.makeText(activity.getApplicationContext(), "File deleted", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(activity.getApplicationContext(), "Unable to delete", Toast.LENGTH_SHORT).show();
    }
}
