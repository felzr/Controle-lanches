package com.example.a709151034.controledelanches.Banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.a709151034.controledelanches.Modelo.Usuario;

/**
 * Created by 709151034 on 17/04/2017.
 */
public class UsuarioDAO extends GenericoDAO {
    private static final String TABLE = "usuario";

    private static final String USUARIOID = "usuario_id";
    private static final String NOME = "nome";
    private static final String USUARIO = "login";
    private static final String SENHA = "senha";

    public UsuarioDAO(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public static String createTable() {
        return "CREATE TABLE usuario" +
                "(usuario_id integer primary key autoincrement," +
                " nome text," +
                " login text," +
                " senha text);";
    }

    public static String dropTable() {
        return "DROP TABLE usuario;";
    }


    public boolean delete(long cdUsuario) {
        return conn.delete(TABLE, USUARIO + "=" + cdUsuario, null) > 0;
    }
    public long addUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(NOME, usuario.getNome());
        values.put(USUARIO, usuario.getLogin());
        values.put(SENHA, usuario.getSenha());
        System.out.println("Salvo com sucesso");
        return conn.insertOrThrow(TABLE, null, values);
    }
    public boolean ValidaUsuario(String login) {

        String[] columns = {USUARIO};
        String selection = USUARIO + " = ?";
        String[] selectionArgs = {login};
        Cursor cursor = conn.query(TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        conn.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean ValidaUsuario(String login, String senha) {

        String[] columns = {USUARIOID};
        String selection = USUARIO + " = ?" + " AND " + SENHA + " = ?";
        String[] selectionArgs = {login, senha};

        Cursor cursor = conn.query(TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        conn.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

}
