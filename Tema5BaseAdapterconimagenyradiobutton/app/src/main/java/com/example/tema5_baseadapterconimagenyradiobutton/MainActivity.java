package com.example.tema5_baseadapterconimagenyradiobutton;

import android.os.Bundle;
import android.widget.ListView;

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

        List<Elemento> listaElementos = new ArrayList<>();
        listaElementos.add(new Elemento(R.drawable.ic_launcher_foreground, "España", "Madrid"));
        listaElementos.add(new Elemento(R.drawable.ic_launcher_foreground, "Francia", "París"));
        listaElementos.add(new Elemento(R.drawable.ic_launcher_foreground, "Italia", "Roma"));

        AdaptadorBase adaptador = new AdaptadorBase(this, listaElementos);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adaptador);
    }
}