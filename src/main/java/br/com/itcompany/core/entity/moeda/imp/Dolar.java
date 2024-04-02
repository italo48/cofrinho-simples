package br.com.itcompany.core.entity.moeda.imp;

import br.com.itcompany.core.entity.moeda.Moeda;

public class Dolar extends Moeda {
    public Dolar(Double valor) {
        this.valor = valor;
    }
    @Override
    public String info() {
        return "DOLAR: " + this.valor;
    }

    @Override
    public Double converter() {
        return this.valor * 5.04;
    }
}
