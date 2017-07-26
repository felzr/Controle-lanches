package com.example.a709151034.controledelanches.Activitys;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.a709151034.controledelanches.Banco.Banco;
import com.example.a709151034.controledelanches.Banco.PedidosDAO;
import com.example.a709151034.controledelanches.R;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by felzr on 28/05/2017.
 */
public class VendasActivity extends AppCompatActivity {

    private TextView maisPedido;
    private TextView numeroPedidos;
    private TextView totalVendas;
    private Spinner spinerMes;
    private Spinner spinerAno;

    private Banco database;
    private SQLiteDatabase conn;
    private PedidosDAO pedidosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        maisPedido = (TextView) findViewById(R.id.txtmaisP);
        numeroPedidos = (TextView) findViewById(R.id.txtnumeroP);
        totalVendas = (TextView) findViewById(R.id.txtvendasT);

        Campos();

        Calendar c = GregorianCalendar.getInstance();
        int anoAtual = c.get(Calendar.YEAR);
        int mesAtual = c.get(Calendar.MONTH);
        //Carregas meses para o spinner
        spinerMes = (Spinner) findViewById(R.id.spinnerMes);
        ArrayAdapter<CharSequence> adapterMeses = ArrayAdapter.createFromResource(
                this, R.array.array_meses, android.R.layout.simple_spinner_item);
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerMes.setAdapter(adapterMeses);
        spinerMes.setSelection(Integer.parseInt(String.valueOf(adapterMeses.getItemId(mesAtual))));
        spinerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Campos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // carrega anos para o spinner
        spinerAno = (Spinner) findViewById(R.id.spinnerAno);
        String[] anos = new String[5];
        for (int i = 0; i < 5; i++) {
            anos[i] = String.valueOf(anoAtual - i);
        }
        ArrayAdapter<String> adapterAnos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, anos);
        adapterAnos.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinerAno.setAdapter(adapterAnos);
        spinerAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Campos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Campos() {
        int numeroP = PedidosMes();
        double totalV = VendasMes();
        String maisP;
        String campo1, campo2, campo3;
        campo1 = Double.toString(numeroP);
        campo2 = Double.toString(totalV);
        numeroPedidos.setText(campo1);
        totalVendas.setText(campo2);
    }

    private double VendasMes() {
        double Vendas = 0;
        try {
            int mesPesqusia = spinerMes.getSelectedItemPosition();
            int anoPesquisa = Integer.parseInt(spinerAno.getSelectedItem().toString());
            Calendar dataPesquisa = GregorianCalendar.getInstance();
            dataPesquisa.set(Calendar.DAY_OF_MONTH, 1);
            dataPesquisa.set(Calendar.MONTH, mesPesqusia);
            dataPesquisa.set(Calendar.YEAR, anoPesquisa);

            database = new Banco(this);
            conn = database.getWritableDatabase();
            pedidosDAO = new PedidosDAO(conn);
            Vendas = pedidosDAO.VendasData(dataPesquisa.getTime());
        } catch (SQLException e){e.printStackTrace();
        } catch (Exception e){e.printStackTrace();}
        return Vendas;
    }

    private int PedidosMes() {
        int pedidosMensal = 0;
        try {
            int mesPesqusia = spinerMes.getSelectedItemPosition();
            int anoPesquisa = Integer.parseInt(spinerAno.getSelectedItem().toString());
            Calendar dataPesquisa = GregorianCalendar.getInstance();
            dataPesquisa.set(Calendar.DAY_OF_MONTH, 1);
            dataPesquisa.set(Calendar.MONTH, mesPesqusia);
            dataPesquisa.set(Calendar.YEAR, anoPesquisa);

            Calendar dataPesquisaFinal = GregorianCalendar.getInstance();
            dataPesquisaFinal.set(Calendar.DAY_OF_MONTH, 30);
            dataPesquisaFinal.set(Calendar.MONTH, mesPesqusia);
            dataPesquisaFinal.set(Calendar.YEAR, anoPesquisa);

            database = new Banco(this);
            conn = database.getWritableDatabase();
            pedidosDAO = new PedidosDAO(conn);
            pedidosMensal = pedidosDAO.PedidosData(dataPesquisa.getTime(), dataPesquisaFinal.getTime());
        } catch (SQLException e){e.printStackTrace();
        } catch (Exception e){e.printStackTrace();}
        return pedidosMensal;
    }
}
