package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.ProdutoDao;
import com.example.a709151034.controledelanches.Modelo.Produto;
import com.example.a709151034.controledelanches.R;

public class ResultadoProdutoActivity extends AppCompatActivity {
    private Banco database;
    private SQLiteDatabase conn;
    private ProdutoDao produtoDao;
    private int Id = 0;
    private Button btnSave, btnDelete, btnClose;
    private EditText nome, tipo, valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_produto);
        btnSave = (Button) findViewById(R.id.btA);
        btnDelete = (Button) findViewById(R.id.btD);
        btnClose = (Button) findViewById(R.id.btV);

        nome = (EditText) findViewById(R.id.eN);
        tipo = (EditText) findViewById(R.id.eTP);
        valor = (EditText) findViewById(R.id.eVP);

        database = new Banco(this);
        conn = database.getWritableDatabase();
        produtoDao = new ProdutoDao(conn);
        Intent intent = getIntent();
        Id = intent.getIntExtra("produto_id", 0);
        System.out.println(Id);
        Produto produto = new Produto();
        produto.setId(Id);
        produto = produtoDao.buscaProdutoId(Id);
        nome.setText(produto.getNome());
        tipo.setText(produto.getTipo());
        valor.setText(String.valueOf(produto.getValor()));

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produtoDao.delete(Id);
                Toast.makeText(getApplication(), "Produto Deletado", Toast.LENGTH_SHORT);
                Intent intent = new Intent(getApplicationContext(), ResultadoProdutoActivity.class);
                startActivity(intent);
                Intent intentMain = new Intent(getApplicationContext(), ProdutoActivity.class);
                startActivity(intentMain);
                finish();

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produto pro = new Produto();
                pro.setNome(nome.getText().toString());
                pro.setTipo(tipo.getText().toString());
                pro.setValor(Double.parseDouble(valor.getText().toString()));

                produtoDao.update(pro, Id);
                Intent intent = new Intent(getApplicationContext(), ResultadoProdutoActivity.class);
                startActivity(intent);
                Intent intentMain = new Intent(getApplicationContext(), ProdutoActivity.class);
                startActivity(intentMain);
                Toast.makeText(getApplication(), "Produto Alterado Alterado", Toast.LENGTH_SHORT);
                finish();
            }
        });


    }
}
