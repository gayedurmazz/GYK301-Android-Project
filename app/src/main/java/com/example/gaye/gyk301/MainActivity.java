package com.example.gaye.gyk301;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.gaye.gyk301.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button openPhotoAndVideo;
    Button openVoice;
    Button openMap;
    Button openWeb;
    Button dialCall;
    Button sendSms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        openPhotoAndVideo = (Button) findViewById(R.id.btn_media);
        openPhotoAndVideo.setOnClickListener(this);

        openVoice = (Button) findViewById(R.id.btn_voice);
        openVoice.setOnClickListener(this);

        openMap = (Button) findViewById(R.id.btn_map);
        openMap.setOnClickListener(this);

        openWeb = (Button) findViewById(R.id.btn_web);
        openWeb.setOnClickListener(this);

        dialCall = (Button) findViewById(R.id.btn_call);
        dialCall.setOnClickListener(this);

        sendSms = (Button) findViewById(R.id.btn_sms);
        sendSms.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if (view == openPhotoAndVideo){
            Intent intent = new Intent(this,CameraActivity.class);
            startActivity(intent);
        }else if (view == openVoice){
            Intent intent = new Intent(this,VoiceActivity.class);
            startActivity(intent);
        }else if (view == openMap){
            Intent intent = new Intent(this,MapActivity.class);
            startActivity(intent);
        }else if (view == openWeb){
            Intent intent = new Intent(this,WebActivity.class);
            startActivity(intent);
        }else if (view == dialCall){
            Intent intent = new Intent(this,CallActivity.class);
            startActivity(intent);
        }else if (view == sendSms){
            Intent intent = new Intent(this,SmsActivity.class);
            startActivity(intent);

        }

    }
}
