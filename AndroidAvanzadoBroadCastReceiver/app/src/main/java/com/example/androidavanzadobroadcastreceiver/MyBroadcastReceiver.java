package com.example.androidavanzadobroadcastreceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //proporciona acceso a los servicios de conectividad de redes
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //detecta el estado de la red que esta activa
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            //Verificamos si existe red y esta activa
            if (activeNetwork != null && activeNetwork.isConnected()) {
                Log.d(TAG, "📡 ¡Conectado a Internet!");
            } else {
                Log.d(TAG, "❌ Desconectado de Internet.");
            }
        }
    }
}

