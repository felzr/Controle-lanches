package com.example.a709151034.controledelanches.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.EstoqueDao;
import com.example.a709151034.controledelanches.R;

import java.sql.SQLOutput;

/**
 * Created by felzr on 18/06/2017.
 */

public class FlagActivity extends Activity implements View.OnClickListener {
    private EditText qtd;
    private TextView produto;
    private TextView idQtd;
    private Button at;
    private int id, q;
    private Banco banco;
    private SQLiteDatabase conn;
    private EstoqueDao estoqueDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Atualizar Estoque");
        setContentView(R.layout.activity_flag_estoque);
        banco = new Banco(this);
        conn = banco.getWritableDatabase();
        estoqueDao = new EstoqueDao(conn);

        Intent intent = getIntent();
        String idIntent = intent.getStringExtra("id");
        String qtdIntent = intent.getStringExtra("qtd");
        String produtoIntent = intent.getStringExtra("produto");

        qtd = (EditText) findViewById(R.id.editTextQtdEstoque);
        produto = (TextView) findViewById(R.id.textView35);
        idQtd = (TextView) findViewById(R.id.textViewQtdId);
        at = (Button) findViewById(R.id.buttonQtdUpdt);

        qtd.setText(qtdIntent);
        idQtd.setText(idIntent);
        produto.setText(produtoIntent);

        id = Integer.parseInt(idIntent);

        System.out.println(id);


        at.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String quant = qtd.getText().toString();
        q = Integer.parseInt(quant);
        System.out.println(q);
        estoqueDao.updateQuantidade(q, id);
        Toast.makeText(FlagActivity.this,"Quantidade em estoque atualizada", Toast.LENGTH_SHORT).show();
        finish();
        Intent i = new Intent(FlagActivity.this, EstoqueActivity.class);
        startActivity(i);

    }
}

