package com.example.tema5_gridview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView txtSeleccion;
    private String[] paises = {
            "España", "Francia", "Alemania", "Italia", "Portugal",
            "Suecia", "Noruega", "Dinamarca", "Bélgica", "Austria"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridViewPaises);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, paises);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "Seleccionado: " + paises[position], Toast.LENGTH_SHORT).show());

    }
}