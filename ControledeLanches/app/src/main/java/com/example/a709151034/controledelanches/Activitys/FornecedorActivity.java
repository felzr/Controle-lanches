package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.a709151034.controledelanches.Adaptador.FornecedorAdaptador;
import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.FornecedorDAO;
import com.example.a709151034.controledelanches.Modelo.Fornecedor;
import com.example.a709151034.controledelanches.R;

import java.util.ArrayList;

public class FornecedorActivity extends AppCompatActivity {

    private ListView listView;
    private Banco database;
    private SQLiteDatabase conn;
    private FornecedorDAO fornecedorDAO;
    private ImageButton delete, update;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor);
        this.listView = (ListView) findViewById(R.id.listaFornecedor);

        try {
            database = new Banco(this);
            conn = database.getWritableDatabase();
            fornecedorDAO = new FornecedorDAO(conn);
            ArrayList<Fornecedor> lista = fornecedorDAO.buscar();
            FornecedorAdaptador fornecedorAdaptador = new FornecedorAdaptador(this,lista);
            this.listView.setAdapter(fornecedorAdaptador);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public void CadastroForncedor(View view) {
        Intent intent = new Intent(FornecedorActivity.this, CadastroFornecedorActivity.class);
        startActivity(intent);
        finish();
    }
    public void FecharForncedor(View view) {
        Intent intent = new Intent(FornecedorActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
