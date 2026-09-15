package com.example.materialdesignsnackbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Referencia al botón en el layout
        Button showSnackbarButton = findViewById(R.id.showSnackbarButton);
        // Configurar el click listener del botón
        showSnackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un Snackbar con una acción
                Snackbar.make(v, "This is a Snackbar!", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Acción al presionar "UNDO"
                                Snackbar.make(v, "Action undone", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
