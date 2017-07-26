package com.example.a709151034.controledelanches.Modelo;

/**
 * Created by felzr on 23/06/2017.
 */

public class Produto {
    private int id;
    private String nome;
    private String tipo;
    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
