package com.yylykym.servicebasepracticejava;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class DownloadService extends Service {

    private DownloadTask downloadTask;

    private String downloadURL;

    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;

    private DownloadListener downloadListener = new DownloadListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onProgress(int progress) {
            // 更新通知进度
            notificationBuilder.setProgress(100, progress, false);
            notificationManager.notify(1, notificationBuilder.build());
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            notificationManager.notify(2, notificationBuilder.build());
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            notificationManager.notify(3, notificationBuilder.build());
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadURL = url;
                downloadTask = new DownloadTask(downloadListener);
                downloadTask.execute(downloadURL);
                startForeground(1,  notificationBuilder.build());
            }
        }
    }

}