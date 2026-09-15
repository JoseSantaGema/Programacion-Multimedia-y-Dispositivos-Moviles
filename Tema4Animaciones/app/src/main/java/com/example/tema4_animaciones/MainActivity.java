package com.example.tema4_animaciones;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Animation animacionActual;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.textoAnimado);
        Button btnTranslate = findViewById(R.id.btnTranslate);
        Button btnRotate = findViewById(R.id.btnRotate);
        Button btnDetener = findViewById(R.id.btnDetener);

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detenerAnimacionActual();
                animacionActual = AnimationUtils.loadAnimation(MainActivity.this, R.anim.mianimacion);
                texto.startAnimation(animacionActual);
            }
        });

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detenerAnimacionActual();
                animacionActual = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotar);
                texto.startAnimation(animacionActual);
            }
        });

        btnDetener.setOnClickListener(v -> {
            detenerAnimacionActual();
        });

        /* Todos los On Click hechos con Lambda

        btnTranslate.setOnClickListener(v -> {
            detenerAnimacionActual();
            animacionActual = AnimationUtils.loadAnimation(this, R.anim.mianimacion);
            texto.startAnimation(animacionActual);
        });

        btnRotate.setOnClickListener(v -> {
            detenerAnimacionActual();
            animacionActual = AnimationUtils.loadAnimation(this, R.anim.rotar);
            texto.startAnimation(animacionActual);
        });

        *****************************************/
    }

    private void detenerAnimacionActual() {
        if (animacionActual != null) {
            texto.clearAnimation();
            animacionActual.cancel();
            animacionActual = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        detenerAnimacionActual();
    }
}

