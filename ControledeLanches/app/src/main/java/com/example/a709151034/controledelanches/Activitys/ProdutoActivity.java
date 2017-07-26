package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.a709151034.controledelanches.Adaptador.PedidosAdaptador;
import com.example.a709151034.controledelanches.Adaptador.ProdutoAdaptor;
import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.PedidosDAO;
import com.example.a709151034.controledelanches.Banco.ProdutoDao;
import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.Modelo.Produto;
import com.example.a709151034.controledelanches.R;

import java.util.ArrayList;

import static com.example.a709151034.controledelanches.R.id.lista;
import static com.example.a709151034.controledelanches.R.id.listaproduto;

public class ProdutoActivity extends AppCompatActivity {
    private ListView listView;
    private Banco database;
    private SQLiteDatabase conn;
    private ProdutoDao produtoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        listView = (ListView) findViewById(listaproduto);
        try {
            database = new Banco(this);
            conn = database.getWritableDatabase();
            produtoDao = new ProdutoDao(conn);
            ArrayList<Produto> lista = produtoDao.buscar();
            ProdutoAdaptor produtoAdaptador = new ProdutoAdaptor(this,lista);
            this.listView.setAdapter(produtoAdaptador);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void CadastroProduto(View view) {
        Intent intent = new Intent(ProdutoActivity.this, CadastroProdutoActivity.class);
        startActivity(intent);
        finish();
    }
    public void FecharProduto(View view) {
        Intent intent = new Intent(ProdutoActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
