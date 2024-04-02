package br.com.itcompany.core;

import br.com.itcompany.core.entity.moeda.Moeda;

import java.util.List;

public interface IMoedaDAO {
    List<Moeda> list();
    Moeda insert(Moeda moeda);
    Moeda update(Moeda moeda);
    Moeda remove(Moeda moeda);
    Moeda find(String info);
}
