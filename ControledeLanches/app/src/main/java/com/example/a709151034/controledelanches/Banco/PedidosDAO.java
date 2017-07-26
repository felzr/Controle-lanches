package com.example.a709151034.controledelanches.Banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 709151034 on 17/04/2017.
 */
public class PedidosDAO extends GenericoDAO {

    private static final String TABLE = "pedido";

    private static final String PEDIDO_ID = "pedido_id";
    private static final String CLIENTE = "cliente";
    private static final String LANCHE = "lanche";
    private static final String BEBIDA = "bebida";
    private static final String EXTRAS = "extras";
    private static final String VALOR = "valor";
    private static final String FORMA = "forma";
    private static final String DATA = "data";


    public PedidosDAO(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public static String createTable() {
        return "CREATE TABLE pedido" +
                "(pedido_id integer primary key autoincrement," +
                " cliente text," +
                " lanche text," +
                " bebida text," +
                " extras text," +
                " valor double," +
                " data date default current_timestamp," +
                " forma text);";
    }


    public static String dropTable() {
        return "DROP TABLE pedido;";
    }

    public boolean delete(int p){
        return conn.delete(TABLE, PEDIDO_ID + "=" + p, null) > 0;
    }


    public long insert(Pedidos pedido) {
        ContentValues values = new ContentValues();
        values.put(CLIENTE, pedido.getCliente());
        values.put(LANCHE, pedido.getLanche());
        values.put(BEBIDA, pedido.getBebida());
        values.put(EXTRAS, pedido.getExtras());
        values.put(VALOR, pedido.getValor());
        values.put(FORMA, pedido.getForma());

        System.out.println("Salvo com sucesso");
        return conn.insertOrThrow(TABLE, null, values);

    }

    public ArrayList<Pedidos> buscar(){
        ArrayList<Pedidos> lista = new ArrayList<>();

        String[] colunas = new String[]{"pedido_id", "cliente", "lanche", "bebida"};

        Cursor cursor = this.conn.query("pedido",colunas,null,null,null,null, "pedido_id ASC");

        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {

                Pedidos pedidos = new Pedidos();
                pedidos.setPedido_id(cursor.getInt(0));
                pedidos.setCliente(cursor.getString(1));
                pedidos.setLanche(cursor.getString(2));
                pedidos.setBebida(cursor.getString(3));

                lista.add(pedidos);

            }while(cursor.moveToNext());
        }

        return lista;
    }
    public Pedidos buscaPedidoId(int Id){
    Pedidos pedidos = new Pedidos();
        String query =
                "SELECT "+
                CLIENTE +","+
                LANCHE +","+
                BEBIDA +","+
                EXTRAS +","+
                VALOR +","+
                FORMA +
                " FROM " + TABLE +
                " WHERE " + PEDIDO_ID + "=?";

        Cursor cursor = conn.rawQuery(query, new String[] { String.valueOf(Id) } );
        if (cursor.moveToFirst()) {
            do {
                pedidos.setCliente(cursor.getString(cursor.getColumnIndex(CLIENTE)));
                pedidos.setLanche(cursor.getString(cursor.getColumnIndex(LANCHE)));
                pedidos.setBebida(cursor.getString(cursor.getColumnIndex(BEBIDA)));
                pedidos.setExtras(cursor.getString(cursor.getColumnIndex(EXTRAS)));
                pedidos.setValor(cursor.getDouble(cursor.getColumnIndex(VALOR)));
                pedidos.setForma(cursor.getString(cursor.getColumnIndex(FORMA)));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return pedidos;
    }
    public long update (Pedidos pedidos ,int id){

        ContentValues valores = new ContentValues();
        valores.put(CLIENTE, pedidos.getCliente());
        valores.put(LANCHE, pedidos.getLanche());
        valores.put(BEBIDA, pedidos.getBebida());
        valores.put(EXTRAS, pedidos.getExtras());
        valores.put(VALOR, pedidos.getValor());
        valores.put(FORMA, pedidos.getForma());

        return conn.update(TABLE, valores, PEDIDO_ID + "= ?", new String[] {String.valueOf(id)});
    }


    public int PedidosData(Date time, Date timeFinal) {

        StringBuffer query = new StringBuffer();
        query.append("select count(" + PEDIDO_ID +")   ");
        query.append("from " + TABLE  +             " ");
        query.append("where " + DATA + "between "+time+ " and " +timeFinal);

        String[] param = {DateUtils.format(time,"yyyy-MM-dd")};
        String[] paramFinal = {DateUtils.format(timeFinal,"yyyy-MM-dd")};

        Cursor c = conn.rawQuery("select count(pedido_id) as total from pedido ", null);

        int pedidosMes = 0;
        if(c.moveToFirst()){
            pedidosMes = c.getColumnIndex("total");
            System.out.println("Total:"+c.getColumnIndex("total"));
        }
        c.close();
        return pedidosMes;
    }

    public double VendasData(Date time) {
        StringBuffer query = new StringBuffer();
        query.append("select sum(" + VALOR +")     ");
        query.append("from " + TABLE +              " ");
        query.append("where " + DATA + "= ? ");

        String[] param = {DateUtils.format(time,"yyyy-MM-dd")};

        Cursor c = conn.rawQuery(query.toString(), param);
        double rendaMensal = 0;
        if(c.moveToFirst()){
            rendaMensal = c.getInt(0);

        }
        c.close();
        return rendaMensal;
    }
}
