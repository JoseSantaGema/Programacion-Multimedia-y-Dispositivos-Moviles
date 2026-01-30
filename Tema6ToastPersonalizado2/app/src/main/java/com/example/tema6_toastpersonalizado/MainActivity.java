package com.example.tema6_toastpersonalizado;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnToast);
        btn.setOnClickListener(v -> {
            Toast t = Toast.makeText(this, "Hola! Soy un Toast", Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        });
    }
}
