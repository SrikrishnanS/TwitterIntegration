package edu.cmu.mobileapp.multimediacamera;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MultimediaCameraActivity extends ActionBarActivity {

    private ActionBar.Tab playerTab, recorderTab;
    private Fragment playerFragment = new PlayerFragment();
    private Fragment recorderFragment = new RecorderFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia_camera);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        playerTab = actionBar.newTab();
        recorderTab = actionBar.newTab();

        playerTab.setText(R.string.player);
        recorderTab.setText(R.string.recorder);

        playerTab.setTabListener(new TabClickListener(playerFragment));
        recorderTab.setTabListener(new TabClickListener(recorderFragment));

        actionBar.addTab(playerTab);
        actionBar.addTab(recorderTab);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multimedia_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
