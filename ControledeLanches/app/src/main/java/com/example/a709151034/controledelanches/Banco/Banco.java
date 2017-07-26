package com.example.a709151034.controledelanches.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 709151034 on 17/04/2017.
 */

public class Banco extends SQLiteOpenHelper {

    private static final String DB_NAME = "Controle";
    private static final int DB_VERSION = 1;

    public Banco(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PedidosDAO.createTable());
        db.execSQL(FornecedorDAO.createTable());
        db.execSQL(UsuarioDAO.createTable());
        db.execSQL(EstoqueDao.createTable());
        db.execSQL(ProdutoDao.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PedidosDAO.dropTable());
        db.execSQL(FornecedorDAO.dropTable());
        db.execSQL(UsuarioDAO.dropTable());
        db.execSQL(EstoqueDao.dropTable());
        db.execSQL(ProdutoDao.dropTable());
    }
}