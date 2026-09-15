package com.example.tema5_listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        listView = findViewById(R.id.listViewPaises);
        txtSeleccion = findViewById(R.id.txtSeleccion);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, paises);

        listView.setAdapter(adaptador);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String seleccionado = paises[position];
            txtSeleccion.setText("Has seleccionado: " + seleccionado);
        });
    }
}
