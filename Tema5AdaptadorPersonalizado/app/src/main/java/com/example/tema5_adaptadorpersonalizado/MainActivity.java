package com.example.tema5_adaptadorpersonalizado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView lista = findViewById(R.id.listView);

        LayoutInflater inflater = LayoutInflater.from(this);
        View header = inflater.inflate(R.layout.cabecera, lista, false);
        lista.addHeaderView(header, null, false); // false = cabecera no clicable


        List<Datos> datos = new ArrayList<>();
        datos.add(new Datos("España", "Madrid"));
        datos.add(new Datos("Francia", "París"));
        datos.add(new Datos("Italia", "Roma"));

        AdaptadorDatos adaptador = new AdaptadorDatos(this, datos);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener((parent, view, position, id) -> {
            // Si la cabecera no es clicable, position ya corresponde a ítems reales
            Datos d = datos.get(position - lista.getHeaderViewsCount());
            Toast.makeText(this, d.getTitulo() + " - " + d.getSubtitulo(), Toast.LENGTH_SHORT).show();
        });

    }
}