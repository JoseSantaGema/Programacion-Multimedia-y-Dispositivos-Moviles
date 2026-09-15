package com.example.materialdesignfloatingactionbutton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Referencias a los elementos del layout
        TextView textView = findViewById(R.id.textView);
        FloatingActionButton fab = findViewById(R.id.fab);
        // Configuración del click listener del FAB
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al presionar el botón
                textView.setText("Floating Action Button Clicked!");
                Toast.makeText(MainActivity.this, "FAB Pressed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
