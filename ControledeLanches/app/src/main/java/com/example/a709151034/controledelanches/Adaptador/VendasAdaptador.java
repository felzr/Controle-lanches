package com.example.a709151034.controledelanches.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.R;

import java.util.List;

/**
 * Created by felzr on 06/05/2017.
 */

public class VendasAdaptador {
    /*private Context context;
    private List<Pedidos> pedido;

    public VendasAdaptador(Context context, List<Pedidos> pedidos){
        this.context = context;
        this.pedido = pedidos;
    }


    @Override
    public int getCount() {
        return pedido.size();
    }

    @Override
    public Object getItem(int position) {
        return pedido.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pedido.get(position).getPedido_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Pedidos pedidos = pedido.get(position);
        View layout;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.layout_vendas, null);
        } else{
            layout = convertView;
        }

        return layout;
    }*/
}
