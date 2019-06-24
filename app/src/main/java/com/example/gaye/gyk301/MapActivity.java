package com.example.gaye.gyk301;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Harita");

        findViewById(R.id.btn_open_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri getLocation = Uri.parse("geo:41.500380,25.301840");
                showMap(getLocation);
            }
        });
    }
    public void showMap(Uri getLocation){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(getLocation);
        // Cihazda harita açabilecek uygulama var mı kontrolü yapılır.
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
