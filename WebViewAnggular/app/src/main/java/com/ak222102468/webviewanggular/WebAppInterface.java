package com.ak222102468.webviewanggular;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.List;

public class WebAppInterface {

    private Context _context;
    private Activity _activity;

    public WebAppInterface(Context context, Activity activity) {
        _context = context;
        _activity = activity;
    }

//    @JavascriptInterface
//    public void showNotification(String title, String message) {
//        String channelId = "webview_channel_id";
//        NotificationManager notificationManager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(_context, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setAutoCancel(true);
//
//        notificationManager.notify(1, builder.build());
//    }

    @JavascriptInterface
    public void showNotification(String title, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationManager notificationManager = _context.getSystemService(NotificationManager.class);
            if (!notificationManager.areNotificationsEnabled()) {
                // Arahkan ke pengaturan notifikasi aplikasi
                Intent intent = new Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, _context.getPackageName());
                _context.startActivity(intent);
                Toast.makeText(_context, "Aktifkan notifikasi dahulu!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        // Jika notifikasi diizinkan, tampilkan notifikasinya
        String channelId = "webview_channel_id";
        NotificationManager notificationManager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(_context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                _context,
                0,
                intent,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE :
                        PendingIntent.FLAG_UPDATE_CURRENT
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "WebView Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(_context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notificationManager.notify(1, builder.build());
    }


//    @JavascriptInterface
//    public void showCall() {
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:082252911465"));
//        _context.startActivity(intent);
//    }

    @JavascriptInterface
    public void showCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        _context.startActivity(intent);
    }

//    @JavascriptInterface
//    public void showWhatsApp() {
//        String url = "https://wa.me/082252911463";
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url));
//        _context.startActivity(intent);
//    }

    @JavascriptInterface
    public void showWhatsApp() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("whatsapp://send"));
            _context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(_context, "WhatsApp tidak ditemukan", Toast.LENGTH_SHORT).show();
            Log.e("WA_ERROR", "Error launching WhatsApp: " + e.getMessage());
        }
    }


    @JavascriptInterface
    public void showCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        _context.startActivity(intent);
    }

//    @JavascriptInterface
//    public void showCamera() {
//        Intent cameraIntent = new Intent(Intent.ACTION_MAIN, null);
//        cameraIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//        List<ResolveInfo> apps = _context.getPackageManager().queryIntentActivities(cameraIntent, 0);
//        for (ResolveInfo app : apps) {
//            String label = app.loadLabel(_context.getPackageManager()).toString().toLowerCase();
//            String packageName = app.activityInfo.packageName.toLowerCase();
//
//            if (label.contains("camera") || label.contains("kamera") || packageName.contains("camera")) {
//                Intent launchIntent = _context.getPackageManager().getLaunchIntentForPackage(app.activityInfo.packageName);
//                if (launchIntent != null) {
//                    _context.startActivity(launchIntent);
//                    return;
//                }
//            }
//        }
//
//        Toast.makeText(_context, "Kamera tidak ditemukan", Toast.LENGTH_SHORT).show();
//    }

}
