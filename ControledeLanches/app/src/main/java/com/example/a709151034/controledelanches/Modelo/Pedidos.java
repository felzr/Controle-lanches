package com.example.a709151034.controledelanches.Modelo;

import java.util.Date;

/**
 * Created by 709151034 on 17/04/2017.
 */

public class Pedidos {
    private String cliente;
    private String lanche;
    private String bebida;
    private String extras;
    private double valor;
    private String forma;
    private int pedido_id;
    private int pedidoLista;
    private Date dataA;
    private String dataB;


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getLanche() {
        return lanche;
    }

    public void setLanche(String lanche) {
        this.lanche = lanche;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {this.forma = forma;}

    public int getPedido_id() {return pedido_id;}

    public void setPedido_id(int pedido_id) {this.pedido_id = pedido_id;}

    public int getPedidoLista() {
        return pedidoLista;
    }

    public void setPedidoLista(int pedidoLista) {
        this.pedidoLista = pedidoLista;
    }


    public Date getDataA() {
        return dataA;
    }

    public void setDataA(Date dataA) {
        this.dataA = dataA;
    }


    public String getDataB() {
        return dataB;
    }

    public void setDataB(String dataB) {
        this.dataB = dataB;
    }
}

