package com.example.practica5_materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        EditText editText = view.findViewById(R.id.editText);

        view.findViewById(R.id.submitButton).setOnClickListener(v -> {
            String input = editText.getText().toString();
            if (input.isEmpty()) {
                textInputLayout.setError("El campo no puede estar vacío");
            } else {
                textInputLayout.setError(null);
                Toast.makeText(getContext(), "Enviado: " + input, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
