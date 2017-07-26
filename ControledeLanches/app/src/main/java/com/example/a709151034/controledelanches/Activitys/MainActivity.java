package com.example.a709151034.controledelanches.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a709151034.controledelanches.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void novopedido(View view) {
        Intent intent = new Intent(MainActivity.this, CadastroPedidoActivity.class);
        startActivity(intent);

    }

    public void consultapedido(View view) {
        Intent intent = new Intent(MainActivity.this, ConsultaPedidoActivity.class);
        startActivity(intent);

    }

    public void Fornecedor(View view) {
        Intent intent = new Intent(MainActivity.this, FornecedorActivity.class);
        startActivity(intent);

    }
    public void Relatorio(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);

    }
}
