package com.example.materialdesigntextinputlayout;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias al TextInputLayout y TextInputEditText
        TextInputLayout emailLayout = findViewById(R.id.emailLayout);
        TextInputEditText emailEditText = findViewById(R.id.emailEditText);
        Button validateButton = findViewById(R.id.validateButton);

        // Listener para el botón de validación
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                if (!isValidEmail(email)) {
                    emailLayout.setError("Invalid email address");
                } else {
                    emailLayout.setError(null); // Quita el mensaje de error
                }
            }
        });
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
