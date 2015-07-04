package edu.cmu.mobileapp.util;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.mobileapp.model.GalleryFile;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class GalleryUtils {
    public static List<GalleryFile> getGalleryFiles(Activity activity) {
        List<GalleryFile> galleryList = new ArrayList<GalleryFile>();
        String[] columns = { MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE,
        };
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
        final String orderBy = MediaStore.Files.FileColumns.DATE_MODIFIED;
        Uri queryUri = MediaStore.Files.getContentUri("external");

        Cursor cursor = activity.managedQuery(queryUri,
                columns,
                selection,
                null,
                MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC"
        );

        int count = cursor.getCount();

        String filePath, timeTaken;
        long type;

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
            timeTaken = DateUtils.getDate(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED))*1000);
            type = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE));
            galleryList.add(new GalleryFile(filePath, timeTaken, type));
        }
        return galleryList;
    }
}
