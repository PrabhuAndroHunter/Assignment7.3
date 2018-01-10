package com.assignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.toString();

    private ImageView mImageView;
    private Button mLoadButtonBt ;

    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialise layout
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        // init views
        initViews();

        mLoadButtonBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent();
                intent.setType("image/*");     /* get Only images */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }

    // This method will init views
    private void initViews(){
        Log.d(TAG, "initViews: ");
        mImageView = (ImageView) findViewById(R.id.imageView);
        mLoadButtonBt = (Button) findViewById(R.id.button_load);
    }

    // After select image from gallery load that image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri); // convert into bitmap
                mImageView.setImageBitmap(bitmap);          /* get selected images and set into ImageView */
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
