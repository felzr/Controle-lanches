package com.example.a709151034.controledelanches.Activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.PedidosDAO;
import com.example.a709151034.controledelanches.Modelo.Pedidos;
import com.example.a709151034.controledelanches.R;
import com.example.a709151034.controledelanches.util.DateUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class MenuActivity extends AppCompatActivity {
    private Banco banco;
    private SQLiteDatabase conn;
    private PedidosDAO pedidosDAO;
    private ImageButton voltar, exportar;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE =
            {Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        verifyStoragePermissions(this);
        banco = new Banco(this);
        conn = banco.getReadableDatabase();

        voltar = (ImageButton) findViewById(R.id.imageButton);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        exportar = (ImageButton) findViewById(R.id.btExportar);
        exportar.setOnClickListener(new View.OnClickListener() {
            Cursor c = null;
            @Override
            public void onClick(View v) {
                try {
                final String fileName = "Pedidos.xls";

                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS), fileName);

                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setLocale(new Locale("pt", "PT"));
                c = conn.rawQuery("select * from pedido", null);
                WritableWorkbook workbook;
                    workbook = Workbook.createWorkbook(file, wbSettings);

                    //Excel sheet name. 0 represents first sheet
                    WritableSheet sheet = workbook.createSheet("Pedidos", 0);


                    try {
                        sheet.addCell(new Label(0, 0, "Pedidos")); // column and row
                        sheet.addCell(new Label(1, 0, "Cliente"));
                        sheet.addCell(new Label(2, 0, "Lanche"));
                        sheet.addCell(new Label(3, 0, "Bebida"));
                        sheet.addCell(new Label(4, 0, "Extras"));
                        sheet.addCell(new Label(5, 0, "Valor"));
                        sheet.addCell(new Label(6, 0, "Data"));
                        sheet.addCell(new Label(7, 0, "Forma"));
                        if(c.getCount() >0){
                            c.moveToFirst();
                            do {
                                int i = c.getPosition() + 1;
                                Pedidos pedidos = new Pedidos();
                                pedidos.setPedido_id(c.getInt(0));
                                pedidos.setCliente(c.getString(1));
                                pedidos.setLanche(c.getString(2));
                                pedidos.setBebida(c.getString(3));
                                pedidos.setExtras(c.getString(4));
                                pedidos.setValor(c.getDouble(5));
                                pedidos.setDataA(DateUtils.parse(c.getString(6), "yyyy-MM-dd"));
                                pedidos.setForma(c.getString(7));
                                pedidos.setDataB(pedidos.getDataA().toString());
                                String id = Integer.toString(pedidos.getPedido_id());
                                String valor = Double.toString(pedidos.getValor());

                                sheet.addCell(new Label(0, i, id));
                                sheet.addCell(new Label(1, i, pedidos.getCliente()));
                                sheet.addCell(new Label(2, i, pedidos.getLanche()));
                                sheet.addCell(new Label(3, i, pedidos.getBebida()));
                                sheet.addCell(new Label(4, i, pedidos.getExtras()));
                                sheet.addCell(new Label(5, i, valor));
                                sheet.addCell(new Label(6, i,pedidos.getDataB()));
                                sheet.addCell(new Label(7, i, pedidos.getForma()));

                            }while (c.moveToNext());{
                                c.close();
                            }

                        }
                        Toast.makeText(MenuActivity.this, "Gravada com sucesso", Toast.LENGTH_LONG).show();

                    } catch (RowsExceededException e) {
                        e.printStackTrace();
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }

                    workbook.write();

                    try {
                        workbook.close();
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void Vendas(View view) {
        Intent intent = new Intent(MenuActivity.this, VendasActivity.class);
        startActivity(intent);

    }
    public void Estoque(View view) {
        Intent intent = new Intent(MenuActivity.this, EstoqueActivity.class);
        startActivity(intent);

    }
    public void Produto(View view) {
        Intent intent = new Intent(MenuActivity.this, ProdutoActivity.class);
        startActivity(intent);

    }
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE); } }


}



