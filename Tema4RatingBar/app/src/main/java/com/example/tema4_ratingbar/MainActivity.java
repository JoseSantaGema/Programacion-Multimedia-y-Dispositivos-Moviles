package com.example.tema4_ratingbar;

import android.os.Bundle;
import android.widget.RatingBar;
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
        setContentView(R.layout.linear);
        RatingBar rb = findViewById(R.id.rating);
        rb.setOnRatingBarChangeListener((bar, rating, fromUser) ->
                Toast.makeText(this, "Valoración: " + rating, Toast.LENGTH_SHORT).show());
    }
}