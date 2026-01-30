package com.example.tema9_multimedia;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private TextView txtState;
    private Button btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtState = findViewById(R.id.txtState);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);

        // Create the MediaPlayer with an audio file placed in res/raw/sample_audio.mp3
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);

        // When the audio finishes, we update the UI
        mediaPlayer.setOnCompletionListener(mp -> txtState.setText("Estado: terminado"));

        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer == null) {
                // Recreate if it was released
                mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
                mediaPlayer.setOnCompletionListener(mp -> txtState.setText("Estado: terminado"));
            }

            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                txtState.setText("Estado: reproduciendo");
            }
        });

        btnPause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                txtState.setText("Estado: pausado");
            }
        });

        btnStop.setOnClickListener(v -> stopAndReset());
    }

    private void stopAndReset() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            // After stop(), you must prepare again. Easiest approach: release and recreate.
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
            mediaPlayer.setOnCompletionListener(mp -> txtState.setText("Estado: terminado"));
            txtState.setText("Estado: parado");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Always release resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
