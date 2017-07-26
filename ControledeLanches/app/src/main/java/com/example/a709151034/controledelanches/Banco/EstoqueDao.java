package com.example.a709151034.controledelanches.Banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a709151034.controledelanches.Modelo.Estoque;
import com.example.a709151034.controledelanches.Modelo.Fornecedor;
import com.example.a709151034.controledelanches.Modelo.Pedidos;

import java.util.ArrayList;

/**
 * Created by felzr on 06/06/2017.
 */

public class EstoqueDao extends  GenericoDAO {

    private static final String TABLE = "estoque";
    private static final String ID = "id";
    private static final String PRODUTO = "produto";
    private static final String QUANTIDADE = "quantidade";

    public EstoqueDao(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public static String createTable() {
        return "CREATE TABLE estoque" +
                "(id integer primary key autoincrement," +
                " produto text," +
                " quantidade integer," +
                " forncedor_id int references fornecedor(fornecedor_id));";
    }

    public static String dropTable() {
        return "DROP TABLE estoque;";
    }

    public  static String Trigger(){ return "";}

    public boolean delete(int p) {
        return conn.delete(TABLE, ID + "=" + p, null) > 0;
    }

    public long insert(Estoque estoque) {
        ContentValues values = new ContentValues();
        values.put(PRODUTO, estoque.getProduto());
        values.put(QUANTIDADE, estoque.getQuantidade());

        System.out.println("Salvo com sucesso");
        return conn.insertOrThrow(TABLE, null, values);
    }
    public ArrayList<Estoque> buscar(){
        ArrayList<Estoque> lista = new ArrayList<>();

        String[] colunas = new String[]{"id", "produto", "quantidade"};

        Cursor cursor = this.conn.query("estoque",colunas,null,null,null,null, " id  ASC");

        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
               Estoque estoque = new Estoque();
                estoque.setId(cursor.getInt(0));
                estoque.setProduto(cursor.getString(1));
                estoque.setQuantidade(cursor.getInt(2));

                lista.add(estoque);

            }while(cursor.moveToNext());
        }

        return lista;
    }
    public String[] todasAsBebidas()
    {
        Cursor cursor = this.conn.query(TABLE, new String[] {PRODUTO}, null, null, null, null, null);

        if(cursor.getCount() >0)
        {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext())
            {
                str[i] = cursor.getString(cursor.getColumnIndex(PRODUTO));
                i++;
            }
            return str;
        }
        else
        {
            return new String[] {};
        }
    }

    public Estoque buscaEstoqueId(int Id){
        Estoque estoque = new Estoque();
        String query =
                "SELECT "+
                        PRODUTO +","+
                        QUANTIDADE +
                        " FROM " + TABLE +
                        " WHERE " + ID + "=?";

        Cursor cursor = conn.rawQuery(query, new String[] { String.valueOf(Id) } );
        if (cursor.moveToFirst()) {
            do {
                estoque.setProduto(cursor.getString(cursor.getColumnIndex(PRODUTO)));
                estoque.setQuantidade(cursor.getInt(cursor.getColumnIndex(QUANTIDADE)));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return estoque;
    }
    public long update (Estoque estoque, int id){

        ContentValues valores = new ContentValues();
        valores.put(PRODUTO, estoque.getProduto());
        valores.put(QUANTIDADE, estoque.getQuantidade());

        return conn.update(TABLE, valores, ID + "= ?", new String[] {String.valueOf(id)});
    }
    public void updateQuantidade (int estoque, int id){

        ContentValues valores = new ContentValues();
        valores.put(QUANTIDADE, estoque);
        conn.execSQL("update estoque set quantidade =" + estoque + " where id ="+id);
        conn.close();
    }
    public void updateQuantidadeNome(String nome){
        conn.execSQL("CREATE TRIGGER after insert  ON pedido \n" +
                "  BEGIN\n" +
                "    UPDATE estoque SET quantidade = quantidade-1 WHERE produto = "+nome+";" +
                "  END;");
    }
   /* public void updateQuantidadeNome (String Nome){

        conn.execSQL("update estoque set quantidade = quantidade -1 where produto ="+Nome);
        conn.close();
    }*/
}
