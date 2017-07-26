package com.example.a709151034.controledelanches.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Activitys.AtualizarFornecedorActivity;
import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.FornecedorDAO;
import com.example.a709151034.controledelanches.Modelo.Fornecedor;
import com.example.a709151034.controledelanches.R;

import java.util.List;

/**
 * Created by felzr on 26/04/2017.
 */

public class FornecedorAdaptador extends ArrayAdapter<Fornecedor> {
    private Banco database;
    private SQLiteDatabase conn;
    private FornecedorDAO fornecedorDAO;
    private Context context;
    private List<Fornecedor> lista;


    public FornecedorAdaptador(Context context, List<Fornecedor> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Fornecedor p = this.lista.get(position);
        database = new Banco(context);
        conn = database.getWritableDatabase();
        fornecedorDAO = new FornecedorDAO(conn);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.campos_fornecedor_activity, null);
        final View layout = convertView;
        TextView textViewId = (TextView) convertView.findViewById(R.id.txtId);
        final   int xxx = p.getId_();
        final String h = Integer.toString(xxx);
        textViewId.setText(h);
        TextView textViewCliente = (TextView) convertView.findViewById(R.id.editTextProduto);
        textViewCliente.setText(p.getNome());


        ImageButton btdel = (ImageButton) convertView.findViewById(R.id.btdelProduto);
        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = p.getId_();
                fornecedorDAO.delete(x);
                Toast.makeText(getContext(), "Fornecedor Deletado", Toast.LENGTH_SHORT);
                layout.setVisibility(View.GONE);

            }
        });

        ImageButton btUpdate = (ImageButton) convertView.findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AtualizarFornecedorActivity.class);
                intent.putExtra("id", p.getId_());
                context.startActivity(intent);
            }
        });


        ImageButton btLigar = (ImageButton) convertView.findViewById(R.id.btLigar);
        btLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String numero = p.getContato().toString();
                    Uri uri = Uri.parse("tel:"+numero);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    context.startActivity(intent);
            }
        });



        return convertView;

    }
}