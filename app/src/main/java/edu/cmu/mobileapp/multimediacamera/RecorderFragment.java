package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by srikrishnan_suresh on 03-07-2015.
 */
public class RecorderFragment extends Fragment {
    private static final int CHECK_VALUE = 7;
    private ImageView previewImage;
    private String filePath;
    private Button discardPhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recorder_layout, container, false);

        Button takePhoto = (Button) rootView.findViewById(R.id.photo);
        previewImage = (ImageView) rootView.findViewById(R.id.preview_image);
        Button savePhoto = (Button) rootView.findViewById(R.id.save_button);
        discardPhoto = (Button) rootView.findViewById(R.id.discard_image);

        savePhoto.setOnClickListener(new SaveMediaClickListener(getActivity()));
        takePhoto.setOnClickListener(new TakePhotoButtonListener(getActivity(),this));
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(CHECK_VALUE):
                if(resultCode == Activity.RESULT_OK){
                    filePath = data.getStringExtra("imagePath");
                    discardPhoto.setOnClickListener(new DiscardMediaClickListener(getActivity(), previewImage, filePath));
                    previewImage.setVisibility(View.VISIBLE);
                    Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                    previewImage.setImageBitmap(myBitmap);
                }
        }
    }
}