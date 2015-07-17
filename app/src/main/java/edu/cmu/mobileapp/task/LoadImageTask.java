package edu.cmu.mobileapp.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import edu.cmu.mobileapp.multimediacamera.BrowserActivity;
import edu.cmu.mobileapp.multimediacamera.R;
import edu.cmu.mobileapp.util.TwitterUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by srikrishnan_suresh on 17-07-2015.
 */
public class LoadImageTask extends AsyncTask<String, String, String> {
    private Activity activity;
    private ProgressDialog progress;

    public LoadImageTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(activity);
        progress.setMessage("Loading Tweets...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        final Bitmap bitmap;
        final ImageView imageView = (ImageView) activity.findViewById(R.id.browserView);
        String URL = params[0];
        try {
            url = new URL(URL);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }

            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String string) {
        progress.hide();
    }
}
