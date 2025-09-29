package com.example.tema2_ciclodevidaactividades;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    // OnCreate es cuando se crea la activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asignamos un layout a la activity
        setContentView(R.layout.activity_main);

        // Escribimos en el Logcat - Filtra por el tag "Ejemplo"
        Log.i("Ejemplo", "Estoy en onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ejemplo", "Estoy en onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ejemplo", "Estoy en onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ejemplo", "Estoy en onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ejemplo", "Estoy en onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ejemplo", "Estoy en onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ejemplo", "Estoy en onDestroy");
    }
}
