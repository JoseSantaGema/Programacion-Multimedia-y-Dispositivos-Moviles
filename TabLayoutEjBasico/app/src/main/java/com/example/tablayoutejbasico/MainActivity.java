package com.example.tablayoutejbasico;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.addTab(tabs.newTab().setText("Pestaña1"));
        tabs.addTab(tabs.newTab().setText("Pestaña2"));
        tabs.addTab(tabs.newTab().setText("Pestaña3"));

        // Añadir el listener a los tabs
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Acción cuando se selecciona una pestaña
                int position = tab.getPosition();
                Toast.makeText(MainActivity.this, "Pestaña " + (position + 1) +
                        " seleccionada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Acción cuando una pestaña se deselecciona
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Acción cuando una pestaña ya seleccionada se vuelve a seleccionar
            }

        }
    }
}
