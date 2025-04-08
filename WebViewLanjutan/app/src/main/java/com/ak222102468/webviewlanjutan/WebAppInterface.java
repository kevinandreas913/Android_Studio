package com.ak222102468.webviewlanjutan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import android.net.Uri;

public class WebAppInterface {
    private Activity _activity;
    private Context _context;

    public WebAppInterface(Context context, Activity activity)
    {
        _context = context;
        _activity = activity;
    }

    @JavascriptInterface
    public void showToast(String message)
    {
        Toast.makeText(_context, message, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showSms()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
        _context.startActivity(intent);
    }


    @JavascriptInterface
    public void showWhatsApp(String nomorHp, String pesan)
    {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String uri = "whatsapp://send?phone=" + nomorHp + "&text=" + Uri.encode(pesan);
            intent.setData(Uri.parse(uri));
            _context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(_context, "WhatsApp tidak terinstal atau terjadi error", Toast.LENGTH_SHORT).show();
        }
    }

}
