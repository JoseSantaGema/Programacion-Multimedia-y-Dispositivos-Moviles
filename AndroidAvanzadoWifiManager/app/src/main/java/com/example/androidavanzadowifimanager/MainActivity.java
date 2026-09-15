package com.example.androidavanzadowifimanager;

import android.Manifest;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private MyWifiReceiver myWifiReceiver = new MyWifiReceiver();
    private TextView name, intensidad, ip;
    private boolean isReceiverRegistered = false; // Para saber si el receiver está registrado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los TextViews
        name = findViewById(R.id.name);
        intensidad = findViewById(R.id.intensidad);
        ip = findViewById(R.id.ip);
        Button boton = findViewById(R.id.boton);

        // Verificar si el permiso ya está concedido
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permiso
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Si el permiso ya está concedido, registrar el receiver
            registerWifiReceiver();
        }

        // Configurar el botón para actualizar la información Wi-Fi
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWifiReceiver.updateWifiInfo(name, intensidad, ip);
            }
        });
    }

    private void registerWifiReceiver() {
        if (!isReceiverRegistered) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            registerReceiver(myWifiReceiver, filter);
            isReceiverRegistered = true; // Marcar que el receiver está registrado
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isReceiverRegistered) {
            unregisterReceiver(myWifiReceiver);
            isReceiverRegistered = false;
        }
    }
    // Manejar la respuesta del usuario sobre permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                registerWifiReceiver(); // Registrar el receiver si el permiso fue concedido
            } else {
                name.setText("⚠️ Permiso de ubicación necesario para obtener el SSID.");
            }
        }
    }
}
