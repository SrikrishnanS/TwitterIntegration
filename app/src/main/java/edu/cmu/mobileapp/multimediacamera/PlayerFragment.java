package edu.cmu.mobileapp.multimediacamera;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cmu.mobileapp.util.MediaUtils;

/**
 * Created by srikrishnan_suresh on 03-07-2015.
 */
public class PlayerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_layout, container, false);
        String[] imageFiles = MediaUtils.getImageFiles(getActivity().getContentResolver());
        String[] videoFiles = MediaUtils.getVideoFiles(getActivity().getContentResolver());

        return rootView;
    }

}
