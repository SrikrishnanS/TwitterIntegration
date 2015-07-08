package edu.cmu.mobileapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by srikrishnan_suresh on 04-07-2015.
 */
public class DateUtils {
    public static String getDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }

    public static String getDateTime(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_HHmmss");
        return formatter.format(date);
    }

    public static String getCurrentFullDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        return formatter.format(date);
    }
}
