package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.FornecedorDAO;
import com.example.a709151034.controledelanches.Modelo.Fornecedor;
import com.example.a709151034.controledelanches.R;

public class AtualizarFornecedorActivity extends AppCompatActivity {
    private EditText inputForncedor;
    private EditText inputTelefone;
    private EditText inputEmail;
    private TextView id;
    private Button at, sair;
    private Banco database;
    private SQLiteDatabase conn;
    private FornecedorDAO fornecedorDAO;
    private  int idF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_fornecedor);
        inputForncedor = (EditText) findViewById(R.id.txtProduto);
        inputEmail = (EditText) findViewById(R.id.txtValor);
        inputTelefone = (EditText) findViewById(R.id.txtTel);
        id = (TextView) findViewById(R.id.txtId);

        database = new Banco(this);
        conn = database.getWritableDatabase();
        fornecedorDAO = new FornecedorDAO(conn);
        Fornecedor fornecedor = new Fornecedor();

        Intent intent = getIntent();
        idF = intent.getIntExtra("id", 0);

        fornecedorDAO.buscaFornecedorId(idF);
        fornecedor = new Fornecedor();
        inputForncedor.setText(fornecedor.getNome());
        inputTelefone.setText(fornecedor.getContato());
        inputEmail.setText(fornecedor.getEmail());
        String idZ = Integer.toString(idF);
        id.setText(idZ.toString());



        at = (Button) findViewById(R.id.btCadProduto);
        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fornecedor z = new Fornecedor();
                z.setNome(inputForncedor.getText().toString());
                z.setContato(inputTelefone.getText().toString());
                z.setEmail(inputEmail.getText().toString());
                fornecedorDAO.update(z,idF);

                Intent intent = new Intent(getApplicationContext(), AtualizarFornecedorActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Fornecedor atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intentMain = new Intent(getApplicationContext(), FornecedorActivity.class);
                startActivity(intentMain);

            }
        });
}
}
