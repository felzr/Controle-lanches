package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.EstoqueDao;
import com.example.a709151034.controledelanches.Banco.PedidosDAO;
import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.R;

public class CadastroPedidoActivity extends AppCompatActivity {

    private Banco banco;
    private SQLiteDatabase conn;
    private PedidosDAO pedidosDAO;
    private EstoqueDao estoqueDao;


    private static final String[] LANCHES = new String[] {"Trad.", "Pren.", "Americ."};
    private static final String[] FORMA = new String[] {"Dinheiro", "Cartão Crédito", "Cartão Débito", "Sodexo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        banco = new Banco(this);

        final EditText Nome = (EditText) findViewById(R.id.campoNome);

        final AutoCompleteTextView Lanches = (AutoCompleteTextView) findViewById(R.id.campoLanche);
        ArrayAdapter<String> adaptadorLanches = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LANCHES);
        Lanches.setAdapter(adaptadorLanches);
        Lanches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lanches.showDropDown();
            }
        });

        final EditText Extras = (EditText) findViewById(R.id.campoExtras);
        //Busca bebidas cadastradas no banco de dados.
        conn = banco.getWritableDatabase();
        estoqueDao= new EstoqueDao(conn);
        String BEBIDAS[] = estoqueDao.todasAsBebidas();

        final AutoCompleteTextView Bebida = (AutoCompleteTextView) findViewById(R.id.campoBebida);
        ArrayAdapter<String> adaptadorBebidas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, BEBIDAS);
        Bebida.setAdapter(adaptadorBebidas);
        Bebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bebida.showDropDown();
            }
        });

        final EditText Valor = (EditText) findViewById(R.id.campoValor);

        final AutoCompleteTextView Forma = (AutoCompleteTextView) findViewById(R.id.campoForma);
        ArrayAdapter<String> adaptadorForma = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FORMA);
        Forma.setAdapter(adaptadorForma);
        Forma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Forma.showDropDown();
            }
        });

        Button Salvar = (Button) findViewById(R.id.btCadProduto);


        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Nome.equals("") && Lanches.equals("") && Valor.equals("") && Forma.equals("")) {
                    Toast.makeText(CadastroPedidoActivity.this, "Digite uma Pedido", Toast.LENGTH_SHORT).show();
                /*}
                    else if(Bebida != null){
                    String qtd = Bebida.getText().toString();
                    estoqueDao.updateQuantidadeNome(qtd);



               */  }else {
                    try {
                        Pedidos pedidos = new Pedidos();
                        pedidos.setCliente(Nome.getText().toString());
                        pedidos.setLanche(Lanches.getText().toString());
                        pedidos.setExtras(Extras.getText().toString());
                        pedidos.setBebida(Bebida.getText().toString());
                        pedidos.setValor(Double.parseDouble(Valor.getText().toString()));
                        pedidos.setForma(Forma.getText().toString());

                        conn = banco.getWritableDatabase();
                        pedidosDAO = new PedidosDAO(conn);
                        pedidosDAO.insert(pedidos);

                        Intent intent = new Intent(getApplicationContext(), CadastroPedidoActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Pedido cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentMain);
                        finish();
                    } catch (SQLException ex) {
                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar Pedido!", Toast.LENGTH_LONG).show();
                        ex.printStackTrace();

                    }
                }
            }
        });

    }
    }

