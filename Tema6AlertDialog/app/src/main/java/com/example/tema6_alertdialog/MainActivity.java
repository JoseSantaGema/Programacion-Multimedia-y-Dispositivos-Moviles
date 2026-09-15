package com.example.tema6_alertdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnAlert);
        btn.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("Confirmación")
                    .setMessage("¿Qué quieres hacer?")
                    .setPositiveButton("Aceptar", (d, which) ->
                            Toast.makeText(this, "Has aceptado", Toast.LENGTH_SHORT).show()
                    )
                    .setNegativeButton("Cancelar", (d, which) ->
                            Toast.makeText(this, "Has cancelado", Toast.LENGTH_SHORT).show()
                    )
                    .setNeutralButton("Ignorar", (d, which) ->
                            Toast.makeText(this, "Has ignorado", Toast.LENGTH_SHORT).show()
                    )
                    .show();
        });

        // Date Picker

        Button btndate = findViewById(R.id.btnDate);
        TextView txt = findViewById(R.id.txtDate);

        btndate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dlg = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) ->
                            txt.setText("Fecha: " + dayOfMonth + "/" + (month + 1) + "/" + year),
                    y, m, d
            );
            dlg.show();
        });

        //time picker

        Button btn_time = findViewById(R.id.btnTime);
        TextView txt_time = findViewById(R.id.txtTime);

        btn_time.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int h = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);

            TimePickerDialog dlg = new TimePickerDialog(
                    this,
                    (view, hourOfDay, minute) ->
                            txt_time.setText(String.format("Hora: %02d:%02d", hourOfDay, minute)),
                    h, min, true
            );
            dlg.show();
        });

    }
}
