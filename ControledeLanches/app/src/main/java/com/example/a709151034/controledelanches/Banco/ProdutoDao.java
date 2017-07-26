package com.example.a709151034.controledelanches.Banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a709151034.controledelanches.Modelo.Fornecedor;
import com.example.a709151034.controledelanches.Modelo.Produto;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by felzr on 23/06/2017.
 */

public class ProdutoDao extends GenericoDAO {
    private static final String TABLE = "produto";

    private static final String PRODUTOID = "idproduto";
    private static final String NOME = "nome";
    private static final String VALOR = "valor";
    private static final String TIPO = "tipo";

    public ProdutoDao(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public static String createTable() {
        return "CREATE TABLE produto" +
                "(idproduto integer primary key autoincrement," +
                " nome text," +
                " valor double," +
                " tipo text);";
    }

    public static String dropTable() {
        return "DROP TABLE produto;";
    }

    public long insert(Produto produto) {
        ContentValues values = new ContentValues();
        values.put(NOME, produto.getNome());
        values.put(VALOR, produto.getValor());
        values.put(TIPO, produto.getTipo());
        System.out.println("Salvo com sucesso");
        return conn.insertOrThrow(TABLE, null, values);
    }
    public boolean delete(int cdProduto) {
        return conn.delete(TABLE, PRODUTOID + "=" + cdProduto, null) > 0;
    }
    public ArrayList<Produto> buscar(){
        ArrayList<Produto> lista = new ArrayList<>();

        String[] colunas = new String[]{"idproduto", "nome", "valor", "tipo"};

        Cursor cursor = this.conn.query("produto",colunas,null,null,null,null, " idproduto  ASC");

        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Produto produto = new Produto();
                produto.setId(cursor.getInt(0));
                produto.setNome(cursor.getString(1));
                produto.setValor(cursor.getDouble(2));
                produto.setTipo(cursor.getString(3));

                lista.add(produto);

            }while(cursor.moveToNext());
        }

        return lista;
    }
    public Produto buscaProdutoId(int Id){
        System.out.println(Id);
        Produto produto = new Produto();
        String query =
                "SELECT "+
                        NOME +","+
                        VALOR +","+
                        TIPO +
                        " FROM " + TABLE +
                        " WHERE " + PRODUTOID + "=?";

        Cursor cursor = conn.rawQuery(query, new String[] { String.valueOf(Id) } );
        if (cursor.moveToFirst()) {
            do {
                produto.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                produto.setValor(cursor.getDouble(cursor.getColumnIndex(VALOR)));
                produto.setTipo(cursor.getString(cursor.getColumnIndex(TIPO)));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return produto;
    }
    public long update (Produto produto, int id){

        ContentValues valores = new ContentValues();
        valores.put(NOME, produto.getNome());
        valores.put(VALOR, produto.getValor());
        valores.put(TIPO, produto.getTipo());

        return conn.update(TABLE, valores, PRODUTOID + "= ?", new String[] {String.valueOf(id)});
    }
}
