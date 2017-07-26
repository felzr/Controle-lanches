package com.example.a709151034.controledelanches.Activitys;

/**
 * Created by felzr on 05/06/2017.
 */
/*
public class teste {
maisPedido = (TextView) findViewById(R.id.editTextMaisPedido);
        numeroPedidos = (TextView) findViewById(R.id.editTextNPedidos);
        totalVendas = (TextView) findViewById(R.id.editTextValorVendas);
        carrega();
        Calendar c = GregorianCalendar.getInstance();
        int anoAtual = c.get(Calendar.YEAR);
        int mesAtual = c.get(Calendar.MONTH);

        spinerMes = (Spinner) findViewById(R.id.sMesExtrato);
        ArrayAdapter<CharSequence> adapterMeses = ArrayAdapter.createFromResource(
                this, R.array.array_meses, android.R.layout.simple_spinner_item);
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerMes.setAdapter(adapterMeses);
        spinerMes.setSelection(Integer.parseInt(String.valueOf(adapterMeses.getItemId(mesAtual))));
        spinerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carrega();
                buscaVendas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinerAno = (Spinner) findViewById(R.id.sAnoExtrato);
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
                carrega();
                buscaVendas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Extrato = (ListView) findViewById(R.id.lvExtrato);
        Extrato.setAdapter(new VendasAdaptador(this, vendas()));



    }





    private void carrega() {
        double npedidos = PedidosMes();
        double tVendas = ValorvendasMes();
        numeroPedidos.setText(String.valueOf(npedidos));
        totalVendas.setText(DecimalUtils.format(tVendas));
    }

    private double ValorvendasMes() {
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


    private double PedidosMes() {
        double pedidosMensal = 0;
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
            pedidosMensal = pedidosDAO.PedidosData(dataPesquisa.getTime());
        } catch (SQLException e){e.printStackTrace();
        } catch (Exception e){e.printStackTrace();}
        return pedidosMensal;
    }
    private List<Pedidos> vendas(){
        List<Pedidos> vendas = null;
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
            vendas = pedidosDAO.vendasMes(dataPesquisa.getTime());
            if (vendas == null){
                vendas = new ArrayList<Pedidos>();
            }
        } catch (SQLException e){e.printStackTrace();
        } catch (Exception e){e.printStackTrace();}
        return vendas;
    }
    private void buscaVendas(){
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

            List<Pedidos> vendas = pedidosDAO.vendasMes(dataPesquisa.getTime());
            if (vendas == null || vendas.isEmpty()){
                vendas = new ArrayList<Pedidos>();
            }
            Extrato.setAdapter(new VendasAdaptador(this, vendas()));
        } catch (SQLException e){e.printStackTrace();}

    }
}
*/