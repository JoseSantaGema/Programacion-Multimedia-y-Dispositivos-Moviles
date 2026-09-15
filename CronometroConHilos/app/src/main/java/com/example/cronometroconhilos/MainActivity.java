package com.example.cronometroconhilos;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTime;
    private Button buttonStart, buttonPause, buttonReset;

    private long startTime = 0L;   // Tiempo de inicio en milisegundos
    private long pausedTime = 0L;  // Tiempo acumulado antes de pausar
    private boolean isRunning = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        buttonStart = findViewById(R.id.buttonStart);
        buttonPause = findViewById(R.id.buttonPause);
        buttonReset = findViewById(R.id.buttonReset);

        // Botón para iniciar o continuar el cronómetro
        buttonStart.setOnClickListener(view -> {
            if (!isRunning) {
                isRunning = true;
                startTime = System.currentTimeMillis() - pausedTime; // Continuar desde la pausa
                handler.postDelayed(runnable, 10);
            }
        });

        // Botón para pausar el cronómetro
        buttonPause.setOnClickListener(view -> {
            if (isRunning) {
                isRunning = false;
                pausedTime = System.currentTimeMillis() - startTime; // Guardar tiempo transcurrido
            }
        });

        // Botón para reiniciar el cronómetro
        buttonReset.setOnClickListener(view -> {
            isRunning = false;
            startTime = 0L;
            pausedTime = 0L;
            textViewTime.setText("00:00:00");
        });
    }

    // Runnable que actualiza el tiempo cada 10ms
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long elapsedTime = System.currentTimeMillis() - startTime;

                int minutes = (int) (elapsedTime / 60000);
                int seconds = (int) ((elapsedTime / 1000) % 60);
                int centiseconds = (int) ((elapsedTime % 1000) / 10); // Centésimas de segundo

                String timeFormatted = String.format("%02d:%02d:%02d", minutes, seconds, centiseconds);
                textViewTime.setText(timeFormatted);
                handler.postDelayed(this, 10);// llamada recursiva.

            }
        }
    };
}
