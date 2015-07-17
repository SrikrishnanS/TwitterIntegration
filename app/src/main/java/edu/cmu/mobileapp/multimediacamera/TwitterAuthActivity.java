package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

import edu.cmu.mobileapp.util.AppConstants;
import edu.cmu.mobileapp.util.DateUtils;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by srikrishnan_suresh on 16-07-2015.
 */
public class TwitterAuthActivity extends Activity {
    Twitter twitter;
    RequestToken requestToken = null;
    AccessToken accessToken;
    String oauth_url,oauth_verifier,profile_url;
    Dialog auth_dialog;
    WebView web;
    SharedPreferences pref;
    Bitmap bitmap;
    String filePath;
    String tweetStatus;

    private ImageView previewImage;
    private EditText tweetMessage;
    private ImageButton confirmTweetButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_auth_layout);
        pref=getApplicationContext().getSharedPreferences("Pref", 0);
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(pref.getString("CONSUMER_KEY", AppConstants.TWITTER_CONSUMER_KEY), pref.getString("CONSUMER_SECRET", AppConstants.TWITTER_CONSUMER_SECRET));

        initComponents();

        if(!isTwitterLoggedInAlready())
            getConfirmation();
        else
            showComponents();

    }
    private void initComponents() {
        previewImage = (ImageView) findViewById(R.id.tweet_preview_image);
        tweetMessage = (EditText) findViewById(R.id.tweet_message);
        confirmTweetButton = (ImageButton) findViewById(R.id.confirm_tweet_button);

        tweetStatus = "@MobileApp4 srikriss: " + android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL + ": " + Build.VERSION.RELEASE + ": " + DateUtils.getCurrentFullDate(new Date().getTime());
        tweetMessage.setText(tweetStatus);
        Intent intent = getIntent();
        filePath = intent.getStringExtra("filePath");
        bitmap = BitmapFactory.decodeFile(filePath);
        previewImage.setImageBitmap(bitmap);
    }

    private void showComponents(){
        previewImage.setVisibility(View.VISIBLE);
        tweetMessage.setVisibility(View.VISIBLE);
        confirmTweetButton.setVisibility(View.VISIBLE);
        confirmTweetButton.setOnClickListener(null);
        confirmTweetButton.setOnClickListener(new ConfirmTweetListener(this, tweetStatus, filePath));
    }

    private void getConfirmation() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TwitterAuthActivity.this);
        alertDialogBuilder.setTitle("Request Twitter Access");
        alertDialogBuilder.setMessage("The App would like to access your Twitter Account");


        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                new TokenGetTask().execute();
            }
        });
        alertDialogBuilder.setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                finish();
            }

        });

        AlertDialog confirmDialog = alertDialogBuilder.create();
        confirmDialog.show();
    }
    private boolean isTwitterLoggedInAlready() {
        return pref.getBoolean(AppConstants.PREF_KEY_TWITTER_LOGIN, false);
    }


    private class TokenGetTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            try {
                requestToken = twitter.getOAuthRequestToken();
                oauth_url = requestToken.getAuthorizationURL();
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return oauth_url;
        }
        @Override
        protected void onPostExecute(String oauth_url) {
            if(oauth_url != null){
                auth_dialog = new Dialog(TwitterAuthActivity.this);
                auth_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                auth_dialog.setContentView(R.layout.twitter_auth_dialog);
                web = (WebView)auth_dialog.findViewById(R.id.twitter_auth_view);
                web.getSettings().setJavaScriptEnabled(true);
                web.loadUrl(oauth_url);
                web.setWebViewClient(new WebViewClient() {
                    boolean authComplete = false;
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon){
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (url.contains("oauth_verifier") && authComplete == false){
                            authComplete = true;
                            Log.e("Url",url);
                            Uri uri = Uri.parse(url);
                            oauth_verifier = uri.getQueryParameter("oauth_verifier");

                            auth_dialog.dismiss();
                            new AccessTokenTask().execute();
                        }else if(url.contains("denied")){
                            auth_dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "You need to login to Tweet", Toast.LENGTH_SHORT).show();
                            finishActivity(2);

                        }
                    }
                });
                auth_dialog.show();
                auth_dialog.setCancelable(true);
            }else{
                Toast.makeText(getApplicationContext(), "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class AccessTokenTask extends AsyncTask<String, String, Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(String... args) {

            try {
                accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("ACCESS_TOKEN", accessToken.getToken());
                edit.putString("ACCESS_TOKEN_SECRET", accessToken.getTokenSecret());
                User user = twitter.showUser(accessToken.getUserId());
                profile_url = user.getOriginalProfileImageURL();
                edit.putString("NAME", user.getName());
                edit.putString("IMAGE_URL", user.getOriginalProfileImageURL());
                edit.putBoolean(AppConstants.PREF_KEY_TWITTER_LOGIN, true);
                edit.commit();
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return true;
        }
        @Override
        protected void onPostExecute(Boolean response) {
            if(response){
                showComponents();
            }
        }

    }
}
