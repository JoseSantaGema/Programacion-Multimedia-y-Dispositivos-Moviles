package com.example.hilos;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button buttonStartPost, buttonStartRunOnUiThread;
    private int progressValue = 0;
    private boolean isRunning = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        buttonStartPost = findViewById(R.id.buttonStartPost);
        buttonStartRunOnUiThread = findViewById(R.id.buttonStartRunOnUiThread);

        // Botón que usa post() para actualizar la interfaz
        buttonStartPost.setOnClickListener(view -> {
            if (!isRunning) {
                startProgressWithPost();
            }
        });

        // Botón que usa runOnUiThread() para actualizar la interfaz
        buttonStartRunOnUiThread.setOnClickListener(view -> {
            if (!isRunning) {
                startProgressWithRunOnUiThread();
            }
        });
    }

    // Metodo 1: Usando post() con Handler
    private void startProgressWithPost() {
        isRunning = true;
        progressValue = 0;
        progressBar.setProgress(progressValue);

        new Thread(() -> {
            while (progressValue < 1000) {
                progressValue += 50;
                handler.post(() -> progressBar.setProgress(progressValue)); // Se usa post()
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isRunning = false;
        }).start();
    }

    //  Metodo 2: Usando runOnUiThread()
    private void startProgressWithRunOnUiThread() {
        isRunning = true;
        progressValue = 0;
        progressBar.setProgress(progressValue);

        new Thread(() -> {
            while (progressValue < 1000) {
                progressValue += 50;
                runOnUiThread(() -> progressBar.setProgress(progressValue)); // Se usa runOnUiThread()

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isRunning = false;
        }).start();
    }
}

