package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by srikrishnan_suresh on 15-07-2015.
 */
public class BrowserActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser_layout);

        WebView browserView = (WebView) findViewById(R.id.browserView);
        Intent i = getIntent();
        String URL = i.getStringExtra("tweetURL");
        browserView.loadUrl(URL);
    }
}
