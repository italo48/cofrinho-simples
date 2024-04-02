package br.com.itcompany.core.entity.moeda;

public abstract class Moeda {
    protected Double valor;

    public abstract String info();

    public abstract Double converter();

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
