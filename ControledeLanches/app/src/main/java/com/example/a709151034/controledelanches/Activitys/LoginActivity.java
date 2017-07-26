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
import com.example.a709151034.controledelanches.Banco.UsuarioDAO;
import com.example.a709151034.controledelanches.Modelo.Usuario;
import com.example.a709151034.controledelanches.R;

public class LoginActivity extends AppCompatActivity {
    private EditText login, senha;
    private Button logar, btCadastro;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private Banco banco;
    private SQLiteDatabase conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        banco = new Banco(this);
        login = (EditText) findViewById(R.id.campoLogin);
        senha = (EditText) findViewById(R.id.campoSenha);
        logar = (Button) findViewById(R.id.acessar);
        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nome, Senha;
                Nome = login.getText().toString();
                Senha = senha.getText().toString();
                conn = banco.getWritableDatabase();
                usuarioDAO = new UsuarioDAO(conn);
                if(usuarioDAO.ValidaUsuario(Nome.trim(), Senha.trim())){
                    Toast.makeText(getApplicationContext(), "Usuário Logado com sucesso!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    login.setText("");
                    senha.setText("");
                    Toast.makeText(getApplicationContext(), "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btCadastro = (Button) findViewById(R.id.btCadastro);
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
