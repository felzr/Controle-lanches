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
import com.example.a709151034.controledelanches.Banco.FornecedorDAO;
import com.example.a709151034.controledelanches.Modelo.Fornecedor;
import com.example.a709151034.controledelanches.R;

public class CadastroFornecedorActivity extends AppCompatActivity {
    private Banco banco;
    private SQLiteDatabase conn;
    private FornecedorDAO forncedorDAO;
    private EditText inputForncedor;
    private EditText inputTelefone;
    private EditText inputEmail;
    private Button bt, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_fornecedor);

        inputForncedor = (EditText) findViewById(R.id.txtProduto);
        inputTelefone = (EditText) findViewById(R.id.txtTel);
        inputEmail = (EditText) findViewById(R.id.txtValor);
        banco = new Banco(this);
        bt = (Button) findViewById(R.id.btCadProduto);
        Intent intent = getIntent();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputForncedor.equals("") && inputTelefone.equals("") && inputEmail.equals("")){
                    Toast.makeText(CadastroFornecedorActivity.this, "Preencha todos os campos" , Toast.LENGTH_LONG).show();
                }else{
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setNome(inputForncedor.getText().toString());
                    fornecedor.setContato(inputTelefone.getText().toString());
                    fornecedor.setEmail(inputEmail.getText().toString());

                    conn = banco.getWritableDatabase();
                    forncedorDAO = new FornecedorDAO(conn);
                    forncedorDAO.insert(fornecedor);

                    Intent intent = new Intent(getApplicationContext(), CadastroFornecedorActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Fornecedor cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intentMain = new Intent(getApplicationContext(), FornecedorActivity.class);
                    startActivity(intentMain);

                }
            }
        });
        bt2 = (Button) findViewById(R.id.btVoltar);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroFornecedorActivity.this, FornecedorActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
