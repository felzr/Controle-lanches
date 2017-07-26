package com.example.a709151034.controledelanches.Adaptador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Activitys.AtualizarFornecedorActivity;
import com.example.a709151034.controledelanches.Activitys.CadastroEstoqueActivity;
import com.example.a709151034.controledelanches.Activitys.EstoqueActivity;
import com.example.a709151034.controledelanches.Activitys.FlagActivity;
import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.EstoqueDao;
import com.example.a709151034.controledelanches.Modelo.Estoque;
import com.example.a709151034.controledelanches.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by felzr on 06/06/2017.
 */

public class EstoqueAdaptador extends ArrayAdapter<Estoque> {
    private Banco database;
    private SQLiteDatabase conn;
    private EstoqueDao estoqueDao;
    private Context context;
    private List<Estoque> lista;

    public EstoqueAdaptador(Context context, List<Estoque> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Estoque estoque = this.lista.get(position);
        database = new Banco(context);
        conn = database.getWritableDatabase();
        estoqueDao = new EstoqueDao(conn);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.campos_estoque_activity, null);
        final View layout = convertView;
        final TextView nome = (TextView) convertView.findViewById(R.id.textViewNomeProduto);
        nome.setText(estoque.getProduto().toString());

        final TextView quantidade = (TextView) convertView.findViewById(R.id.textViewQta);
        String total = Integer.toString(estoque.getQuantidade());
        quantidade.setText(total);
        final int y = estoque.getQuantidade();
        final int x = estoque.getId();
        // TextView id = (TextView) convertView.findViewById(R.id.textViewIdProduto) ;
        //String idTexto = Integer.toString(estoque.getId());
        //id.setText(idTexto);

        ImageButton del = (ImageButton) convertView.findViewById(R.id.btdelProduto);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                estoqueDao.delete(x);
                Toast.makeText(getContext(), "Produto Deletado", Toast.LENGTH_SHORT);
                layout.setVisibility(View.GONE);
            }
        });
        ImageButton edit = (ImageButton) convertView.findViewById(R.id.btUpdateProduto);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String produto = nome.getText().toString();
                String id = Integer.toString(x);
                String qtd = Integer.toString(y);
                Intent flag = new Intent(context, FlagActivity.class);
                flag.putExtra("id", id);
                flag.putExtra("qtd",qtd);
                flag.putExtra("produto",produto);
                context.startActivity(flag);

            }
        });


        return convertView;
    }
}
