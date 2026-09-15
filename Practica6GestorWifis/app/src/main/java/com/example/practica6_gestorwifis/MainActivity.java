package com.example.practica6_gestorwifis;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION = 1;
    private WifiManager wifiManager;
    private ListView listView;
    private TextView wifiInfoTextView;
    private List<ScanResult> results;
    private ArrayAdapter<String> adapter;
    private String selectedSSID;
    private int selectedSignalStrength;
    private String selectedIPAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        listView = findViewById(R.id.wifiListView);
        wifiInfoTextView = findViewById(R.id.wifiInfoTextView);
        Button scanButton = findViewById(R.id.scanButton);
        Button connectButton = findViewById(R.id.connectButton);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION);
        } else {
            scanWifi();
        }

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanWifi();
            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConnectDialog();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScanResult selectedResult = results.get(position);
                selectedSSID = selectedResult.SSID;
                selectedSignalStrength = selectedResult.level;
                selectedIPAddress = selectedResult.BSSID; // This is the MAC address, not IP address
                wifiInfoTextView.setText("📡 Name: " + selectedSSID + "\n📶 Signal Strength: " + selectedSignalStrength + "\n🌐 Bssid: " + selectedIPAddress);
            }
        });

        registerReceiver(new WifiReceiver(), new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private void scanWifi() {
        wifiManager.startScan();
        results = wifiManager.getScanResults();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (ScanResult scanResult : results) {
            adapter.add("\uD83D\uDEDC "+scanResult.SSID + " / " + scanResult.level);
        }
        listView.setAdapter(adapter);
    }

    private void showConnectDialog() {
        ConnectDialogFragment dialog = new ConnectDialogFragment();
        dialog.show(getSupportFragmentManager(), "ConnectDialogFragment");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            scanWifi();
        }
    }

    public String getSelectedSSID() {
        return selectedSSID;
    }
    private class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
            if (success) {
                scanWifi();
            }
        }
    }
}