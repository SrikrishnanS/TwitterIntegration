package edu.cmu.mobileapp.multimediacamera;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by srikrishnan_suresh on 03-07-2015.
 */
public class RecorderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recorder_layout, container, false);

        Button takePhoto = (Button)  rootView.findViewById(R.id.photo);
        takePhoto.setOnClickListener(new TakePhotoButtonListener(getActivity()));
        return rootView;
    }
}

