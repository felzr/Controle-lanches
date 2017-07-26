package com.example.a709151034.controledelanches.Banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a709151034.controledelanches.Modelo.Fornecedor;

import java.util.ArrayList;

/**
 * Created by 709151034 on 17/04/2017.
 */
public class FornecedorDAO extends GenericoDAO{
    private static final String TABLE = "fornecedor";

    private static final String FORNECEDORID = "fornecedor_id";
    private static final String NOME = "nome";
    private static final String CONTATO = "contato";
    private static final String EMAIL = "email";

    public FornecedorDAO(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public static String createTable() {
        return "CREATE TABLE fornecedor" +
                "(fornecedor_id integer primary key autoincrement," +
                " nome text," +
                " contato text," +
                " email text);";
    }

    public static String dropTable() {
        return "DROP TABLE fornecedor;";
    }

    public boolean delete(int cdForncedor) {
        return conn.delete(TABLE, FORNECEDORID + "=" + cdForncedor, null) > 0;
    }
    public long insert(Fornecedor fornecedor) {
        ContentValues values = new ContentValues();
        values.put(NOME, fornecedor.getNome());
        values.put(CONTATO, fornecedor.getContato());
        values.put(EMAIL, fornecedor.getEmail());
        System.out.println("Salvo com sucesso");
        return conn.insertOrThrow(TABLE, null, values);
    }

    public ArrayList<Fornecedor> buscar(){
        ArrayList<Fornecedor> lista = new ArrayList<>();

        String[] colunas = new String[]{"fornecedor_id", "nome", "contato", "email"};

        Cursor cursor = this.conn.query("fornecedor",colunas,null,null,null,null, " fornecedor_id  ASC");

        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId_(cursor.getInt(0));
                fornecedor.setNome(cursor.getString(1));
                fornecedor.setContato(cursor.getString(2));
                fornecedor.setEmail(cursor.getString(3));

                lista.add(fornecedor);

            }while(cursor.moveToNext());
        }

        return lista;
    }
    public Fornecedor buscaFornecedorId(int Id){
        Fornecedor fornecedor = new Fornecedor();
        String query =
                "SELECT "+
                        NOME +","+
                        CONTATO +","+
                        EMAIL +
                        " FROM " + TABLE +
                        " WHERE " + FORNECEDORID + "=?";

        Cursor cursor = conn.rawQuery(query, new String[] { String.valueOf(Id) } );
        if (cursor.moveToFirst()) {
            do {
                fornecedor.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                fornecedor.setContato(cursor.getString(cursor.getColumnIndex(CONTATO)));
                fornecedor.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return fornecedor;
    }
        public long update (Fornecedor fornecedor, int id){

            ContentValues valores = new ContentValues();
            valores.put(NOME, fornecedor.getNome());
            valores.put(CONTATO, fornecedor.getContato());
            valores.put(EMAIL, fornecedor.getEmail());

            return conn.update(TABLE, valores, FORNECEDORID + "= ?", new String[] {String.valueOf(id)});
        }
}
