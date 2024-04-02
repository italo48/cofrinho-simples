package br.com.itcompany.core.entity.moeda.imp;

import br.com.itcompany.core.entity.moeda.Moeda;

public class Real extends Moeda {
    public Real(Double valor) {
        this.valor = valor;
    }

    @Override
    public String info() {
        return "REAL: " + this.valor;
    }

    @Override
    public Double converter() {
        return this.valor;
    }
}
