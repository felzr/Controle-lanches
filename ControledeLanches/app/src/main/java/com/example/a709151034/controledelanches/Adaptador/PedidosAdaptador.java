package com.example.a709151034.controledelanches.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a709151034.controledelanches.Activitys.ConsultaPedidoActivity;
import com.example.a709151034.controledelanches.Activitys.ResultadoPedidoActivity;
import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.R;

import java.util.List;

import static com.example.a709151034.controledelanches.R.id.textView;

/**
 * Created by felzr on 18/04/2017.
 */

public class PedidosAdaptador extends ArrayAdapter<Pedidos> {
    private ListView listView;
    private Context context;
    private List<Pedidos> lista;
    private List<Integer> ids;

    public PedidosAdaptador(Context context, List<Pedidos> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Pedidos posicao = this.lista.get(position);
        String itemId= Integer.toString(posicao.getPedido_id());

        posicao.setPedidoLista(posicao.getPedido_id());
        convertView = LayoutInflater.from(this.context).inflate(R.layout.campos_pedido_activity, null);
        final TextView textViewId = (TextView) convertView.findViewById(R.id.textViewID);
        textViewId.setText(itemId);

        TextView textViewCliente = (TextView) convertView.findViewById(R.id.textViewNome);
        textViewCliente.setText(posicao.getCliente());

        TextView textViewLanche = (TextView) convertView.findViewById(R.id.textViewLanche);
        textViewLanche.setText(posicao.getLanche());

        TextView textViewBebida = (TextView) convertView.findViewById(R.id.textViewBebida);
        textViewBebida.setText(posicao.getBebida());

        textViewCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroPedido = textViewId.getText().toString();
                Intent objIndent = new Intent(context,ResultadoPedidoActivity.class);
                objIndent.putExtra("pedido_id", Integer.parseInt(numeroPedido));
                context.startActivity(objIndent);
            }
        });
        textViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroPedido = textViewId.getText().toString();
                Intent objIndent = new Intent(context,ResultadoPedidoActivity.class);
                objIndent.putExtra("pedido_id", Integer.parseInt(numeroPedido));
                context.startActivity(objIndent);
            }
        });
        textViewBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroPedido = textViewId.getText().toString();
                Intent objIndent = new Intent(context,ResultadoPedidoActivity.class);
                objIndent.putExtra("pedido_id", Integer.parseInt(numeroPedido));
                context.startActivity(objIndent);
            }
        });
        textViewLanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroPedido = textViewId.getText().toString();
                Intent objIndent = new Intent(context,ResultadoPedidoActivity.class);
                objIndent.putExtra("pedido_id", Integer.parseInt(numeroPedido));
                context.startActivity(objIndent);
            }
        });



        return convertView;

    }
}
