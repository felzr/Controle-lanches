package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.PedidosDAO;
import com.example.a709151034.controledelanches.Banco.ProdutoDao;
import com.example.a709151034.controledelanches.Modelo.Produto;
import com.example.a709151034.controledelanches.R;

public class CadastroProdutoActivity extends AppCompatActivity {
    private Banco banco;
    private SQLiteDatabase conn;
    private ProdutoDao produtoDao;
    private Spinner spinner;
    private EditText desc, valor;
    private Button button, button2;
    private Double va;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        banco = new Banco(this);


        desc = (EditText) findViewById(R.id.txtProduto);
        valor = (EditText)  findViewById(R.id.txtValor);

        spinner = (Spinner) findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button = (Button) findViewById(R.id.btCadProduto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerS = spinner.getSelectedItem().toString();
                System.out.println(spinnerS);
                va = Double.parseDouble(valor.getText().toString());
                Produto p = new Produto();
                p.setNome(desc.getText().toString());
                p.setValor(va);
                p.setTipo(spinnerS);
                conn = banco.getWritableDatabase();
                produtoDao = new ProdutoDao(conn);
                produtoDao.insert(p);
                Intent intent = new Intent(getApplicationContext(), CadastroProdutoActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intentMain = new Intent(getApplicationContext(), ProdutoActivity.class);
                startActivity(intentMain);
                finish();

            }
        });
        button2 = (Button) findViewById(R.id.btVoltar);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
