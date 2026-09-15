package com.example.androidavanzadowifimanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.TextView;

public class MyWifiReceiver extends BroadcastReceiver {
    private static final String TAG = "MyWifiReceiver";
    String ssid="" ;
    int rssi=0 ;
    int ip=0;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction()) ||
                WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiManager.isWifiEnabled() /*&& wifiInfo.getNetworkId() != -1*/) {
                ssid = wifiInfo.getSSID();
                rssi = wifiInfo.getRssi();
                ip = wifiInfo.getIpAddress();
                Log.d(TAG, "📶 Conectado a Wi-Fi: " + ssid);
                Log.d(TAG, "📡 Intensidad de señal: " + rssi + " dBm");
                Log.d(TAG, "            if (activeNetwork != null && activeNetwork.isConnected()) {\n" +
                        "                Log.d(TAG, \"\uD83D\uDCE1 ¡Conectado a Internet!\");\n" +
                        "            } else {\n" +
                        "                Log.d(TAG, \"❌ Desconectado de Internet.\");\n" +
                        "            } Dirección IP: " + formatIPAddress(ip));
            } else {
                Log.d(TAG, "❌ Wi-Fi Desconectado.");
            }
        }
    }
    public void updateWifiInfo(TextView name, TextView intensidad,TextView ip){
        name.setText("📶 Conectado a Wi-Fi: " + ssid);
        intensidad.setText("📡 Intensidad de señal: " + rssi + " dBm");
        ip.setText("🌐 Dirección IP: " + formatIPAddress(this.ip));
    }
    private String formatIPAddress(int ip) {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
    }
}

