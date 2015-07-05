package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class FullViewImage extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image_layout);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

        Intent i = getIntent();
        String filePath = i.getStringExtra("filePath");
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        imageView.setImageBitmap(bitmap);
    }
}
