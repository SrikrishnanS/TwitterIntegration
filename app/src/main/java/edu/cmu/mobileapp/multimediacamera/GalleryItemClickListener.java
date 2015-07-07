package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

import edu.cmu.mobileapp.model.GalleryFile;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class GalleryItemClickListener implements OnItemClickListener {

    private Activity activity;
    private List<GalleryFile> files;

    public GalleryItemClickListener(Activity activity, List<GalleryFile> files) {
        this.activity = activity;
        this.files = files;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {

        Intent viewMediaIntent = new Intent(activity.getApplicationContext(), MediaFullViewActivity.class);
        viewMediaIntent.putExtra("filePath", files.get(position).getFilePath());
        viewMediaIntent.putExtra("mediaType", files.get(position).getType());
        activity.startActivity(viewMediaIntent);
    }
}
