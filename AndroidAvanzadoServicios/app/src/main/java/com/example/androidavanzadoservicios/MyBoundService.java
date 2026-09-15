package com.example.androidavanzadoservicios;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyBoundService extends Service {
    private final IBinder binder = new LocalBinder();
    private final Random random = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public int getRandomNumber() {
        return random.nextInt(100); // Devuelve un número aleatorio entre 0 y 99
    }
}

