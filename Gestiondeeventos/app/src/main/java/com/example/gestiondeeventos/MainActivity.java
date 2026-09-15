package com.example.gestiondeeventos;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> events;
    private ArrayAdapter<String> adapter;
    private final String CHANNEL_ID = "event_notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Solicitar permiso para enviar notificaciones para Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }


        ListView listView = findViewById(R.id.listView);

        Button addEventButton = findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddEventDialog();
            }
        });

        //Inicializamos variables
        events = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String event = events.get(position);
                showCustomToast(event);
            }
        });

    }

    private void showAddEventDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir Evento");

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_event, null);
        builder.setView(dialogView);

        TextView eventNameInput = dialogView.findViewById(R.id.eventNameInput);

        builder.setPositiveButton("Siguiente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String eventName = eventNameInput.getText().toString();

                if (!eventName.isEmpty()) {
                    pickDateTime(eventName);
                } else {
                    Toast.makeText(null, "El nombre del evento no puede estar vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        builder.show();
    }

    private void pickDateTime(String eventName) {
        Calendar calendar = Calendar.getInstance();
        //Esta es una expresión lambda que implementa el método onDateSet de la interfaz DatePickerDialog.OnDateSetListener
        new android.app.DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            //Dentro de la lambda, se establece la fecha seleccionada en el calendario.
            calendar.set(year, month, dayOfMonth);
            //Esta es otra expresión lambda que implementa el método onTimeSet de la interfaz TimePickerDialog.OnTimeSetListener.
            new android.app.TimePickerDialog(this, (timeView, hourOfDay, minute) -> {
                //Dentro de esta lambda, se establece la hora seleccionada en el calendario.
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);


                String eventDetails = eventName + " el dia " +calendar.get(Calendar.DAY_OF_MONTH)+ " del " +
                        (calendar.get(Calendar.MONTH)+ 1) + " a las " + calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
                events.add(eventDetails);
                adapter.notifyDataSetChanged();

                scheduleNotification(eventName, calendar);
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void scheduleNotification(String eventName, Calendar calendar) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Icono pequeño
                .setContentTitle("Recordatorio de Evento")
                .setContentText("Recuerda que tienes que hacer esto:"+eventName+ "El dia " +calendar.get(Calendar.DAY_OF_MONTH)+ " del " + calendar.get(Calendar.MONTH)+ 1)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // Añadir el intent
                .setAutoCancel(true); // Cierra la notificación al tocarla

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = (int) System.currentTimeMillis();
        notificationManager.notify(notificationId, builder.build());

        Toast.makeText(this, "Notificación programada", Toast.LENGTH_SHORT).show();
    }


    private void showCustomToast(String eventDetails) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, findViewById(R.id.toastLayout));

        TextView text = layout.findViewById(R.id.toastText);
        text.setText(eventDetails);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
