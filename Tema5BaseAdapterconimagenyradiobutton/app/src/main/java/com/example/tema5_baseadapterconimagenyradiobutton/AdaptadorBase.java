package com.example.tema5_baseadapterconimagenyradiobutton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorBase extends BaseAdapter {

    private Context context;
    private List<Elemento> elementos;

    public AdaptadorBase(Context context, List<Elemento> elementos) {
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public int getCount() { return elementos.size(); }

    @Override
    public Object getItem(int position) { return elementos.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_elemento, parent, false);

        Elemento e = elementos.get(position);

        ImageView img = convertView.findViewById(R.id.imgBandera);
        TextView titulo = convertView.findViewById(R.id.txtTitulo);
        TextView contenido = convertView.findViewById(R.id.txtContenido);

        img.setImageResource(e.imagen);
        titulo.setText(e.titulo);
        contenido.setText(e.contenido);

        return convertView;
    }
}

