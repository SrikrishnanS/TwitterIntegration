package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class MediaFullViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_media_layout);

        Intent i = getIntent();
        long mediaType = i.getLongExtra("mediaType", 0);
        String filePath = i.getStringExtra("filePath");
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        VideoView videoView = (VideoView) findViewById(R.id.video_player_view);

        if(mediaType == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE) {
            videoView.setVisibility(View.INVISIBLE);
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            imageView.setImageBitmap(bitmap);
        }
        else if(mediaType == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO) {
            imageView.setVisibility(View.INVISIBLE);
            videoView.setVideoPath(filePath);
            MediaController controller = new MediaController(this);
            videoView.setMediaController(controller);
            videoView.start();
        }
    }
}
