package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class GridItemAdapter extends BaseAdapter{
    private Activity activity;
    private List<String> fileList;

    private static LayoutInflater inflater = null;

    public GridItemAdapter(Activity activity, List<String> fileList) {
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

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        Bitmap bmp = BitmapFactory.decodeFile(fileList.get(position),options);
        image.setImageBitmap(bmp);
        return view;
    }
}