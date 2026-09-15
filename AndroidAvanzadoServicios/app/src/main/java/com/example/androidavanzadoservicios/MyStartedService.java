package com.example.androidavanzadoservicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class MyStartedService extends Service {
    private Timer timer = new Timer();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("StartedService", "Servicio creado");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("StartedService", "Servicio iniciado");

        // Tarea en segundo plano cada 5 segundos

        return START_STICKY; // Reinicia el servicio si el sistema lo mata
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("StartedService", "Servicio destruido");
        timer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

