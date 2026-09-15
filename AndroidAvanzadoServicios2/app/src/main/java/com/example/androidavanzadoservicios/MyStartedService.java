package com.example.androidavanzadoservicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class MyStartedService extends Service {
    private Timer timer = new Timer();
    private static final String TAG = "MyStartedService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Servicio creado");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado");

        // Inicia una tarea repetitiva cada 5 segundos
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "Ejecutando tarea en segundo plano...");
            }
        }, 0, 5000); // Inicia inmediatamente y repite cada 5 segundos
        return START_STICKY; // Reinicia el servicio si el sistema lo mata
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Servicio destruido");
        timer.cancel();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null; // No se usa en un Started Service
    }
}
