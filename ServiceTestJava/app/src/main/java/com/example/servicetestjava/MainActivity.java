package com.example.servicetestjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.example.servicetestjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding = null;

    private MyService.DownloadBinder binder;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (MyService.DownloadBinder) iBinder;
            binder.startDownload();
            binder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bindServiceBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MyService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE); // 绑定Service
        });


        binding.unbindServiceBtn.setOnClickListener(view -> {
            unbindService(serviceConnection); // 解绑Service
        });

    }
}