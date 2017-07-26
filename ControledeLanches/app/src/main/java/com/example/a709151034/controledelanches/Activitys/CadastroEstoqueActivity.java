package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.EstoqueDao;
import com.example.a709151034.controledelanches.Modelo.Estoque;
import com.example.a709151034.controledelanches.R;

public class CadastroEstoqueActivity extends AppCompatActivity {
    private EditText produto, quantidade;
    private Button cadastroProduto;
    private ImageButton voltar;
    private Banco banco;
    private SQLiteDatabase conn;
    private EstoqueDao estoqueDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estoque);
        banco = new Banco(this);
        produto = (EditText) findViewById(R.id.editTextNomeProduto);
        quantidade = (EditText) findViewById(R.id.editTextQuantidadeProduto);
        cadastroProduto = (Button) findViewById(R.id.buttonCadastrarProduto);
        cadastroProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quant = quantidade.getText().toString();
                Estoque estoque = new Estoque();
                estoque.setProduto(produto.getText().toString());
                estoque.setQuantidade(Integer.parseInt(quant));
                try{

                    conn = banco.getWritableDatabase();
                    estoqueDao = new EstoqueDao(conn);
                    estoqueDao.insert(estoque);
                    Intent intent = new Intent(CadastroEstoqueActivity.this, EstoqueActivity.class);
                    startActivity(intent);
                    Toast.makeText(CadastroEstoqueActivity.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                }catch (SQLException e){
                    e.printStackTrace();
                    Toast.makeText(CadastroEstoqueActivity.this, "Erro ao Cadastrar", Toast.LENGTH_LONG).show();
                }
            }
        });
        voltar =(ImageButton) findViewById(R.id.imageButtonSairE);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
