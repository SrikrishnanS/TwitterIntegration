package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.cmu.mobileapp.model.GalleryFile;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class GridItemAdapter extends BaseAdapter{
    private Activity activity;
    private List<GalleryFile> fileList;

    private static LayoutInflater inflater = null;

    public GridItemAdapter(Activity activity, List<GalleryFile> fileList) {
        this.activity = activity;
        this.fileList = fileList;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return fileList.size();

    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null)
            view = inflater.inflate(R.layout.grid_item_layout, null);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView textView = (TextView) view.findViewById(R.id.date);


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        String filePath = fileList.get(position).getFilePath();
        String dateTaken = fileList.get(position).getDateTaken();
        Long type = fileList.get(position).getType();
        String fileType = "";
        Bitmap bitmap = null;
        if(type == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE) {
            bitmap = BitmapFactory.decodeFile(filePath, options);
            fileType = "Photo";
        }
        else if(type == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO) {
            bitmap = ThumbnailUtils.createVideoThumbnail(filePath, 0);
            fileType = "Video";
        }
        image.setImageBitmap(bitmap);
        textView.setText(fileType + " on " + dateTaken);
        return view;
    }
}
