package com.example.a709151034.controledelanches.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.PedidosDAO;
import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.R;

public class ResultadoPedidoActivity extends Activity {
    private Context context;
    private Banco database;
    private SQLiteDatabase conn;
    private PedidosDAO pedidosDAO;
    private EditText editTextNome;
    private EditText editTextLanche;
    private EditText editTextBebida;
    private EditText editTextExtras;
    private EditText editTextValor;
    private EditText editTextForma;
    private int Id = 0;
    private Button btnSave, btnDelete, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_pedido);



        btnSave = (Button) findViewById(R.id.btA);
        btnDelete = (Button) findViewById(R.id.btD);
        btnClose = (Button) findViewById(R.id.btnVoltar);


        editTextNome = (EditText) findViewById(R.id.eN);
        editTextLanche = (EditText) findViewById(R.id.eTP);
        editTextBebida = (EditText) findViewById(R.id.eVP);
        editTextExtras = (EditText) findViewById(R.id.editTextExtras);
        editTextValor = (EditText) findViewById(R.id.editTextValor);
        editTextForma = (EditText) findViewById(R.id.editTextForma);

        database = new Banco(this);
        conn = database.getWritableDatabase();
        pedidosDAO = new PedidosDAO(conn);
            Intent intent = getIntent();
        Pedidos pedidos = new Pedidos();
        Id = intent.getIntExtra("pedido_id", 0);

        pedidos.setPedidoLista(Id);
        pedidos = pedidosDAO.buscaPedidoId(Id);
        editTextNome.setText((pedidos.getCliente()));
        editTextLanche.setText(pedidos.getLanche());
        editTextBebida.setText(pedidos.getBebida());
        editTextExtras.setText(pedidos.getExtras());
        editTextValor.setText(String.valueOf(pedidos.getValor()));
        editTextForma.setText(pedidos.getForma());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
        }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedidosDAO.delete(Id);
                Toast.makeText(getApplication(), "Pedido Deletado", Toast.LENGTH_SHORT);
                Intent intent = new Intent(getApplicationContext(), ResultadoPedidoActivity.class);
                startActivity(intent);
                Intent intentMain = new Intent(getApplicationContext(), ConsultaPedidoActivity.class);
                startActivity(intentMain);
                finish();

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pedidos pedidos = new Pedidos();
                pedidos.setCliente(editTextNome.getText().toString());
                pedidos.setLanche(editTextLanche.getText().toString());
                pedidos.setBebida(editTextBebida.getText().toString());
                pedidos.setExtras(editTextExtras.getText().toString());
                pedidos.setValor(Double.parseDouble(editTextValor.getText().toString()));
                pedidos.setForma(editTextForma.getText().toString());
                pedidosDAO.update(pedidos, Id);
                System.out.println(pedidos.getCliente()+ "" +pedidos.getLanche());
                Intent intent = new Intent(getApplicationContext(), ResultadoPedidoActivity.class);
                startActivity(intent);
                Intent intentMain = new Intent(getApplicationContext(), ConsultaPedidoActivity.class);
                startActivity(intentMain);
                Toast.makeText(getApplication(), "Pedido Alterado", Toast.LENGTH_SHORT);
                finish();
            }
        });

    }

}

