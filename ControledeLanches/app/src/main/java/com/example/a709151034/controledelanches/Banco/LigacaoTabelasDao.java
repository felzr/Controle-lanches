package com.example.a709151034.controledelanches.Banco;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by felzr on 23/06/2017.
 */

public class LigacaoTabelasDao extends GenericoDAO {

    public LigacaoTabelasDao(SQLiteDatabase conn) {
        this.conn = conn;
    }
    public static String createTableItem() {
        return "CREATE TABLE item_pedido" +
                "(idproduto int," +
                " idpedido int," +
                " quantidade double);";
    }

    public static String dropTableItem() {
        return "DROP TABLE item_pedido;";
    }
    public static String createTableTipo() {
        return "CREATE TABLE tipo_produto" +
                "(id_tipo_produto integer primary key autoincrement," +
                " tipo text);";
    }

    public static String dropTableTipo() {
        return "DROP TABLE tipo_produto;";
    }
}
