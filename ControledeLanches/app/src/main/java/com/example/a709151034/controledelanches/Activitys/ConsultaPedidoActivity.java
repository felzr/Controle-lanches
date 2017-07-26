package com.example.a709151034.controledelanches.Activitys;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a709151034.controledelanches.Adaptador.PedidosAdaptador;
import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.PedidosDAO;
import com.example.a709151034.controledelanches.Modelo.Fornecedor;
import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.a709151034.controledelanches.R.id.lista;
import static com.example.a709151034.controledelanches.R.id.textView;

public class ConsultaPedidoActivity extends AppCompatActivity {

    private ListView listView;
        private Banco database;
        private SQLiteDatabase conn;
        private PedidosDAO pedidosDAO;
        private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_pedido);
       listView = (ListView) findViewById(lista);
        try {
            database = new Banco(this);
            conn = database.getWritableDatabase();
            pedidosDAO = new PedidosDAO(conn);
            ArrayList<Pedidos> lista = pedidosDAO.buscar();
            PedidosAdaptador pedidoAdapter = new PedidosAdaptador(this,lista);
            this.listView.setAdapter(pedidoAdapter);
        }catch (Exception ex){
            ex.printStackTrace();
        }

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textView = (TextView) findViewById(R.id.textViewID);

               String numeroPedido = textView.getText().toString();
                Intent objIndent = new Intent(getApplicationContext(),ResultadoPedidoActivity.class);
                objIndent.putExtra("pedido_id", Integer.parseInt(numeroPedido));
                startActivity(objIndent);
            }
        });*/



    }
}
