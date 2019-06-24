package com.example.gaye.gyk301;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallActivity extends AppCompatActivity {

    EditText enterPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Arama Yap");

        enterPhoneNumber = (EditText) findViewById(R.id.et_number);
        findViewById(R.id.btn_arama).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receivedPhoneNumber = enterPhoneNumber.getText().toString();
                callPhoneNumber(receivedPhoneNumber);
            }
        });

    }

    public void callPhoneNumber(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
