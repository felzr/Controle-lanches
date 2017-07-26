package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.a709151034.controledelanches.Adaptador.EstoqueAdaptador;
import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.EstoqueDao;
import com.example.a709151034.controledelanches.Modelo.Estoque;
import com.example.a709151034.controledelanches.R;

import java.util.ArrayList;

public class EstoqueActivity extends AppCompatActivity {
    private ListView listView;
    private Banco database;
    private SQLiteDatabase conn;
    private EstoqueDao estoqueDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);
        this.listView = (ListView) findViewById(R.id.listaEstoque);

        try {
            database = new Banco(this);
            conn = database.getWritableDatabase();
            estoqueDao = new EstoqueDao(conn);
            ArrayList<Estoque> lista = estoqueDao.buscar();
            EstoqueAdaptador estoqueAdaptador = new EstoqueAdaptador(this, lista);
            this.listView.setAdapter(estoqueAdaptador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CadastroEstoque(View view) {
        Intent intent = new Intent(EstoqueActivity.this, CadastroEstoqueActivity.class);
        startActivity(intent);
        finish();
    }

    public void Back(View view) {
        Intent intent = new Intent(EstoqueActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
