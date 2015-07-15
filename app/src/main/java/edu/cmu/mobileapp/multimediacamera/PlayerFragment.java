package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Date;
import java.util.List;

import edu.cmu.mobileapp.model.GalleryFile;
import edu.cmu.mobileapp.util.GalleryUtils;
import edu.cmu.mobileapp.util.TwitterUtils;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Created by srikrishnan_suresh on 03-07-2015.
 */
public class PlayerFragment extends Fragment {
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_layout, container, false);
        Activity activity = getActivity();

        List<GalleryFile> files = GalleryUtils.getGalleryFiles(activity);

        gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridView.setAdapter(new GridItemAdapter(getActivity(), files));

        gridView.setOnItemClickListener(new GalleryItemClickListener(activity, files));

        return rootView;
    }

}
