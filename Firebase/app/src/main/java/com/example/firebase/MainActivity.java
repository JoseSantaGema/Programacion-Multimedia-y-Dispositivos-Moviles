package com.example.firebase;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    Button btnGuardar;

    FirebaseFirestore db;  // La base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Inicializar firebase
        db = FirebaseFirestore.getInstance();

        // Listener del botón
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = txtNombre.getText().toString();

                if (nombre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Escribe algo", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Creamos un objeto para enviar a Firebase
                Map<String, Object> datos = new HashMap<>();
                datos.put("nombre", nombre);

                // Guardamos en la colección "usuarios"
                db.collection("usuarios")
                        .add(datos)
                        .addOnSuccessListener(documentReference ->
                                Toast.makeText(MainActivity.this,
                                        "Guardado correctamente!",
                                        Toast.LENGTH_SHORT).show()
                        )
                        .addOnFailureListener(e ->
                                Toast.makeText(MainActivity.this,
                                        "Error al guardar",
                                        Toast.LENGTH_SHORT).show()
                        );
            }
        });
    }
}
