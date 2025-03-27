package com.ak222102468.webview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private Button _tampilkanButton;
    private EditText _urlEditText;
    private WebView _webView1;
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

        _tampilkanButton = (Button)findViewById(R.id.tampilkanButton);
        _urlEditText = (EditText)findViewById(R.id.urlEditText);
        _webView1 = (WebView)findViewById(R.id.webView1);

    }

    public void tampilkanButton_onclick(View view){
        String url = _urlEditText.getText().toString().trim();

//        if (url.isEmpty()) {
//            Toast.makeText(this, "Masukkan URL terlebih dahulu!", Toast.LENGTH_SHORT).show();
//            return;
//        }

//        if (!url.startsWith("http://") && !url.startsWith("https://")) {
//            showAlertDialog("Kesalahan", "URL harus diawali dengan 'https://'");
//            return;
//        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            Snackbar.make(view, "url harus dimulai dengan https://", Snackbar.LENGTH_LONG).show();
            return;
        }

        _webView1.setWebViewClient(new WebViewClient());
        _webView1.loadUrl(url);
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

}