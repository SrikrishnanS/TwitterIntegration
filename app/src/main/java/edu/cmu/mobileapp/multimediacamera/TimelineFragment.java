package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by srikrishnan_suresh on 12-07-2015.
 */
public class TimelineFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timeline_layout, container, false);
        Activity activity = getActivity();



        return rootView;
    }
}
