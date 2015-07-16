package edu.cmu.mobileapp.multimediacamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

/**
 * Created by srikrishnan_suresh on 03-07-2015.
 */
public class RecorderFragment extends Fragment {
    private static final int IMAGE_CHECK_VALUE = 7;
    private static final int VIDEO_CHECK_VALUE = 9;
    private ImageView previewImage;
    private VideoView previewVideo;
    private String filePath;
    private Button discardPhoto;
    private Button savePhoto;
    private ImageButton tweetButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recorder_layout, container, false);

        Button takePhoto = (Button) rootView.findViewById(R.id.photo);
        Button takeVideo = (Button) rootView.findViewById(R.id.video);
        previewImage = (ImageView) rootView.findViewById(R.id.preview_image);
        previewVideo = (VideoView) rootView.findViewById(R.id.preview_video);
        savePhoto = (Button) rootView.findViewById(R.id.save_button);
        discardPhoto = (Button) rootView.findViewById(R.id.discard_image);
        tweetButton = (ImageButton) rootView.findViewById(R.id.tweet_button);

        takePhoto.setOnClickListener(new TakePhotoButtonListener(getActivity(),this));
        takeVideo.setOnClickListener(new TakeVideoButtonListener(getActivity(),this));
        tweetButton.setOnClickListener(new TweetButtonClickListener(getActivity()));
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        savePhoto.setVisibility(View.VISIBLE);
        discardPhoto.setVisibility(View.VISIBLE);
        tweetButton.setVisibility(View.VISIBLE);
        switch (requestCode) {
            case(IMAGE_CHECK_VALUE):
                if(resultCode == Activity.RESULT_OK){
                    filePath = data.getStringExtra("imagePath");
                    savePhoto.setOnClickListener(null);
                    savePhoto.setOnClickListener(new SaveMediaClickListener(getActivity(), filePath,MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE));
                    discardPhoto.setOnClickListener(null);
                    discardPhoto.setOnClickListener(new DiscardMediaClickListener(getActivity(), previewImage, filePath));
                    previewVideo.setVisibility(View.INVISIBLE);
                    previewImage.setVisibility(View.VISIBLE);
                    Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                    previewImage.setImageBitmap(myBitmap);
                }
                break;
            case(VIDEO_CHECK_VALUE):
                if(resultCode == Activity.RESULT_OK){
                    filePath = data.getStringExtra("videoPath");
                    savePhoto.setOnClickListener(null);
                    savePhoto.setOnClickListener(new SaveMediaClickListener(getActivity(), filePath, MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO));
                    discardPhoto.setOnClickListener(null);
                    discardPhoto.setOnClickListener(new DiscardMediaClickListener(getActivity(), previewVideo, filePath));
                    previewImage.setVisibility(View.INVISIBLE);
                    previewVideo.setVisibility(View.VISIBLE);
                    previewVideo.setVideoPath(filePath);
                    previewVideo.start();
                }
        }
    }
}