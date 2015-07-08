package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by srikrishnan_suresh on 03-07-2015.
 */
public class RecorderFragment extends Fragment {
    private static final int CHECK_VALUE = 7;
    private ImageView previewImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recorder_layout, container, false);

        Button takePhoto = (Button)  rootView.findViewById(R.id.photo);
        previewImage = (ImageView) rootView.findViewById(R.id.preview_image);

        takePhoto.setOnClickListener(new TakePhotoButtonListener(getActivity(),this));
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this.getActivity().getApplicationContext(), resultCode+"", Toast.LENGTH_LONG).show();
        switch (requestCode) {
            case(CHECK_VALUE):
                if(resultCode == Activity.RESULT_OK){
                    String filePath = data.getStringExtra("imagePath");
                    previewImage.setVisibility(View.VISIBLE);
                    Toast.makeText(this.getActivity().getApplicationContext(), "Srikirshnan" + filePath, Toast.LENGTH_SHORT).show();
                    Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                    previewImage.setImageBitmap(myBitmap);
                }
        }
    }
}

