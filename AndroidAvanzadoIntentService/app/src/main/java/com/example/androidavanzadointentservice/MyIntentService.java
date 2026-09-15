package com.example.androidavanzadointentservice;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    // Constructor obligatorio
    public MyIntentService() {
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 1; i <= 5; i++) {
            Log.d(TAG, "Ejecutando tarea: " + i);
            try {
                Thread.sleep(5000); // Simula una tarea que dura 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "Tarea completada");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Servicio destruido");
    }
}
