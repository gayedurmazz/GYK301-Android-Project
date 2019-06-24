package com.example.gaye.gyk301;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class CameraActivity extends AppCompatActivity {

    Button btnCamera;
    Button btnVideo;

    private static final int VIDEO_ACTION_CODE = 101;
    private static final int IMAGE_ACTION_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fotoğraf ve Video");

        btnCamera = (Button)findViewById(R.id.btn_camera);
        btnVideo = (Button)findViewById(R.id.btn_video);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeVideo();
            }
        });

    }
    public void takePhoto(){
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhotoIntent, IMAGE_ACTION_CODE);
    }

    public void takeVideo(){
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(takeVideoIntent, VIDEO_ACTION_CODE);
    }

    // Uygulamadan geri dönüş değeri alındığı zaman otomatik olarak çalışır.
    // Yukarıda startActivityForResult kullanıldığı için bu fonksiyona girilir.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return;

        switch(requestCode){
            case VIDEO_ACTION_CODE:
                VideoView videoView = ((VideoView) findViewById(R.id.vv_video));
                videoView.setVideoURI(data.getData());
                videoView.setMediaController(new MediaController(this));
                // Sonraki satırlar videoyu başlatmak için.
                videoView.requestFocus();
                videoView.start();
                break;

            case IMAGE_ACTION_CODE:
                Bundle extras = data.getExtras();
                ((ImageView) findViewById(R.id.iv_camera)).setImageBitmap((Bitmap) extras.get("data"));
                break;

            default:
                break;


        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}