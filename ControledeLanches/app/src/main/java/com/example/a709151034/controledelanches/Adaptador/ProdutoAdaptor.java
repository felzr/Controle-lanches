package com.example.a709151034.controledelanches.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a709151034.controledelanches.Activitys.ResultadoPedidoActivity;
import com.example.a709151034.controledelanches.Activitys.ResultadoProdutoActivity;
import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.Modelo.Produto;
import com.example.a709151034.controledelanches.R;

import java.util.List;

/**
 * Created by felzr on 23/06/2017.
 */

public class ProdutoAdaptor extends ArrayAdapter<Produto> {
    private Context context;
    private List<Produto> lista;
    public ProdutoAdaptor(Context context, List<Produto> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Produto posicao = this.lista.get(position);
        String itemId= Integer.toString(posicao.getId());

        convertView = LayoutInflater.from(this.context).inflate(R.layout.campos_produto_activity, null);
        final TextView textViewId = (TextView) convertView.findViewById(R.id.textViewIdProduto);
        textViewId.setText(itemId);

        TextView textViewNome = (TextView) convertView.findViewById(R.id.textViewCampoProduto);
        textViewNome.setText(posicao.getNome());

        textViewNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroPedido = textViewId.getText().toString();
                Intent objIndent = new Intent(context,ResultadoProdutoActivity.class);
                objIndent.putExtra("produto_id", Integer.parseInt(numeroPedido));
                context.startActivity(objIndent);
            }
        });
        textViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroPedido = textViewId.getText().toString();
                Intent objIndent = new Intent(context,ResultadoProdutoActivity.class);
                objIndent.putExtra("produto_id", Integer.parseInt(numeroPedido));
                context.startActivity(objIndent);
            }
        });




        return convertView;

    }
}
