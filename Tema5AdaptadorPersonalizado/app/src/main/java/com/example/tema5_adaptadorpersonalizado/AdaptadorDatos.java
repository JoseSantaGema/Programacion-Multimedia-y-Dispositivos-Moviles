package com.example.tema5_adaptadorpersonalizado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptadorDatos extends ArrayAdapter<Datos> {

    public AdaptadorDatos(Context context, List<Datos> datos) {
        super(context, 0, datos);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datos dato = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.elemento, parent, false);
        }
        TextView titulo = convertView.findViewById(R.id.txtTitulo);
        TextView subtitulo = convertView.findViewById(R.id.txtSubtitulo);
        titulo.setText(dato.getTitulo());
        subtitulo.setText(dato.getSubtitulo());
        return convertView;
    }
}

