package com.example.gaye.gyk301;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class VoiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button recordButton;
    Button stopButton;
    Button playButton;

    private MediaRecorder recorder;
    private MediaPlayer player;
    private final String filepath = Environment.getExternalStorageDirectory().getPath() + "/record.3gp";
    private static final int REQUEST_AUDIO_PERMISSION_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ses Kaydı");

        recordButton = (Button) findViewById(R.id.btn_kayit);
        recordButton.setOnClickListener(this);
        stopButton = (Button) findViewById(R.id.btn_durdur);
        stopButton.setOnClickListener(this);
        playButton = (Button) findViewById(R.id.btn_cal);
        playButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == recordButton) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermissions()) {
                    startRecording();
                } else {
                    requestPermissions();
                }
            } else {
                startRecording();
            }
        } else if (v == stopButton) {
            stopRecording();
        } else if (v == playButton) {
            startPlaying();
        }

    }

    public void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder((MediaRecorder.AudioEncoder.AMR_NB));
        recorder.setOutputFile(filepath);
        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(getApplicationContext(), "Kayıt Yapılıyor", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (recorder != null) {
            Toast.makeText(getApplicationContext(), "Kayıt Durduruldu", Toast.LENGTH_LONG).show();
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
        }
    }

    private void startPlaying() {
        player = new MediaPlayer();
        player.setVolume(1.0f, 1.0f);
        try {
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            Toast.makeText(getApplicationContext(), "Kayıt Çalıyor", Toast.LENGTH_LONG).show();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                    player.release();
                    player = null;
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Beklenmeyen bir hata oluştu.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                        startRecording();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(VoiceActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
