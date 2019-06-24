package com.example.gaye.gyk301;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Web Sayfasına Git");

        webView = (WebView) findViewById(R.id.web_view);
        openWebPage("https://www.youtube.com/watch?v=ReOMuhbdoNs");
    }

    public void openWebPage(String url){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        final ProgressDialog progress = ProgressDialog.show(this, "Sürpriz", "Sayfa Yükleniyor...", true);
        progress.show();
         //webViewClient dinliyor.
         webView.setWebViewClient(new WebViewClient(){
             @Override
             public void onPageFinished(WebView view, String url) {
                 super.onPageFinished(view, url);

                 Toast.makeText(getApplicationContext(),"Sayfa Yüklendi",Toast.LENGTH_SHORT).show();
                 progress.dismiss();
             }

             @Override
             public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                 super.onReceivedError(view, request, error);
                 Toast.makeText(getApplicationContext(),"Bir Hata Oluştu",Toast.LENGTH_SHORT).show();
                 progress.dismiss();
             }
         });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
