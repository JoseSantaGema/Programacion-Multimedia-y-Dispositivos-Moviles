package com.example.tema4_togglebuttonimagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton; // para OnCheckedChangeListener

public class MainActivity extends AppCompatActivity {

    private TextView txtEstado;
    private ToggleButton toggleBtn;
    private ImageButton imgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEstado = findViewById(R.id.txtEstado);
        toggleBtn = findViewById(R.id.toggleBtn);
        imgBtn = findViewById(R.id.imgBtn);

        // --- TOGGLEBUTTON ---
        // Cuando se pulsa, queda "cambiado" (on/off). Usamos OnCheckedChangeListener clásico.
        toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtEstado.setText("Estado: Pulsado");
                } else {
                    txtEstado.setText("Estado: No Pulsado");
                }
            }
        });

        // --- IMAGEBUTTON ---
        // Mismo manejo que un Button, pero con imagen. Listener clásico.
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Has pulsado el ImageButton", Toast.LENGTH_SHORT).show();

                // cambia el icono al pulsar (si tienes dos drawables)
                imgBtn.setImageResource(R.drawable.ic_pause);
            }
        });
    }
}
