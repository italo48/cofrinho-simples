package br.com.itcompany.core.entity.moeda.imp;

import br.com.itcompany.core.entity.moeda.Moeda;

public class Euro extends Moeda {

    public Euro(Double valor) {
        this.valor = valor;
    }
    @Override
    public String info() {
        return "EURO: " + this.valor;
    }

    @Override
    public Double converter() {
        return this.valor * 5.43;
    }
}
