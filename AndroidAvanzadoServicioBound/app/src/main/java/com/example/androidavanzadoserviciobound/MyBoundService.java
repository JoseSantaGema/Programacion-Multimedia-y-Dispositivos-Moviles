package com.example.androidavanzadoserviciobound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class MyBoundService extends Service {
    private final IBinder binder = new LocalBinder();
    private Timer timer = new Timer();
    private int counter = 0;
    private static final String TAG = "MyBoundService";

    // Clase interna para el IBinder
    public class LocalBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Servicio creado");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Servicio vinculado");
        startCounter(); // Iniciar tarea cuando la actividad se conecta
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Servicio desvinculado");
        stopCounter(); // Detener tarea cuando la actividad se desconecta
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCounter();
        Log.d(TAG, "Servicio destruido");
    }

    // Método para iniciar el contador
    private void startCounter() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                counter++;
                Log.d(TAG, "Contador: " + counter);
            }
        }, 0, 1000); // Cada segundo
    }

    // Método para detener el contador
    private void stopCounter() {
        timer.cancel();
    }

    // Método accesible desde la Activity para obtener el contador
    public int getCounter() {
        return counter;
    }
}

