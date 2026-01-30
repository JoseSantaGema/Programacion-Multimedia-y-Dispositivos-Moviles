package com.example.tema4_checkbox;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.checkbox);
        CheckBox cbWifi = findViewById(R.id.cbWifi);
        CheckBox cbBt   = findViewById(R.id.cbBluetooth);
        CompoundButton.OnCheckedChangeListener l = (btn, checked) ->
                Toast.makeText(this, btn.getText()+": "+(checked?"ON":"OFF"), Toast.LENGTH_SHORT).show();
        cbWifi.setOnCheckedChangeListener(l);
        cbBt.setOnCheckedChangeListener(l);
    }
}