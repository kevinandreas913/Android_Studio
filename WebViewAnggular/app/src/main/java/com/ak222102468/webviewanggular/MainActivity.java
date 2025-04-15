package com.ak222102468.webviewanggular;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private WebView _webViewAnggular;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        _webViewAnggular = findViewById(R.id.webViewAnggular);

        // Aktifkan JavaScript
        WebSettings webSettings = _webViewAnggular.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Buka dalam aplikasi
        _webViewAnggular.setWebViewClient(new WebViewClient());
        _webViewAnggular.loadUrl("https://stmikpontianak.cloud/011100862/angular011100862");

        WebAppInterface WebAppInterface = new WebAppInterface(this, this);
        _webViewAnggular.addJavascriptInterface(WebAppInterface, "Android");

    }

    // back
    @Override
    public void onBackPressed() {
        if (_webViewAnggular.canGoBack()) {
            _webViewAnggular.goBack();
        } else {
            super.onBackPressed();
        }
    }
}