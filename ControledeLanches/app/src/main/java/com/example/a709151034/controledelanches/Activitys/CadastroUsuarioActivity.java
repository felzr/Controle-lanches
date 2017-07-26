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
import com.example.a709151034.controledelanches.Banco.UsuarioDAO;
import com.example.a709151034.controledelanches.Modelo.Usuario;
import com.example.a709151034.controledelanches.R;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private EditText nome, login, senha;
    private Button Salvar;
    private UsuarioDAO usuarioDAO;
    private Banco banco;
    private SQLiteDatabase conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        banco = new Banco(this);
        nome = (EditText) findViewById(R.id.inputNome);
        login = (EditText) findViewById(R.id.inputLogin);
        senha = (EditText) findViewById(R.id.inputSenha);
        Salvar = (Button) findViewById(R.id.Cadastrar);
        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());
                conn = banco.getWritableDatabase();
                usuarioDAO = new UsuarioDAO(conn);
                usuarioDAO.addUsuario(usuario);
                Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                Intent intentMain = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentMain);
            }
        });
    }
}
