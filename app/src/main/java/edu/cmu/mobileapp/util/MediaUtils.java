package edu.cmu.mobileapp.util;

import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class MediaUtils {
    public static String[] getImageFiles(ContentResolver resolver) {
        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        //Stores all the images from the gallery in Cursor
        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        //Total number of images
        int count = cursor.getCount();

        //Create an array to store path to all the images
        String[] imageFiles = new String[count];

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            imageFiles[i]= cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        }
        return imageFiles;
    }

    public static String[] getVideoFiles(ContentResolver resolver) {
        final String[] columns = { MediaStore.Video.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Video.VideoColumns.DATE_TAKEN + " DESC";

        Cursor cursor = resolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        int count = cursor.getCount();

        String[] videoFiles = new String[count];

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            videoFiles[i]= cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        }
        return videoFiles;
    }
}
