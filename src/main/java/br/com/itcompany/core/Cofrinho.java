package br.com.itcompany.core;

import br.com.itcompany.core.entity.moeda.Moeda;

import java.util.List;

public class Cofrinho {
    private final IMoedaDAO listaMoedas;

    public Cofrinho(IMoedaDAO moedas) {
        this.listaMoedas = moedas;
    }

    public Moeda adicionarMoeda(Moeda moeda) {
        Moeda moedaEncontrada = listaMoedas.find(moeda.info());
        if (moedaEncontrada != null) {
            moedaEncontrada.setValor(moedaEncontrada.getValor() + moeda.getValor());
            return listaMoedas.update(moedaEncontrada);
        }
        return listaMoedas.insert(moeda);
    }

    public Moeda removerMoeda(Moeda moeda) {
        Moeda moedaEncontrada = listaMoedas.find(moeda.info());
        if (moedaEncontrada != null) {
            double valorRemovido = moedaEncontrada.getValor() - moeda.getValor();
            if (valorRemovido > 0) {
                moedaEncontrada.setValor(valorRemovido);
                return listaMoedas.update(moedaEncontrada);
            }
            return listaMoedas.remove(moeda);
        }
        return null;
    }

    public List<Moeda> listarMoedas() {
        return listaMoedas.list();
    }

    public Double totalConvertido() {
        return !listaMoedas.list().isEmpty() ? listaMoedas.list().stream().mapToDouble(Moeda::converter).sum() : 0.0;
    }
}
