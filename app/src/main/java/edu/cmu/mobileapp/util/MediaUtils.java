package edu.cmu.mobileapp.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.mobileapp.model.MediaFile;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class MediaUtils {
    public static List<MediaFile> getMediaFiles(ContentResolver resolver) {
        List<MediaFile> mediaList = new ArrayList<MediaFile>(getImageFiles(resolver));
        //mediaList.addAll(getVideoFiles(resolver));
        return mediaList;
    }

    public static List<MediaFile> getImageFiles(ContentResolver resolver) {
        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_TAKEN };
        final String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";

        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);

        int count = cursor.getCount();


        List<MediaFile> imageFiles = new ArrayList<MediaFile>();

        String filePath, timeTaken;
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            timeTaken = DateUtils.getDate(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)));
            imageFiles.add(new MediaFile(filePath, timeTaken));
        }

        return imageFiles;
    }

   /* public static List<String> getVideoFiles(ContentResolver resolver) {
        final String[] columns = { MediaStore.Video.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Video.VideoColumns.DATE_TAKEN + " DESC";

        Cursor cursor = resolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        int count = cursor.getCount();

        List<String> videoFiles = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            videoFiles.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
        }
        return videoFiles;
    }*/
}
