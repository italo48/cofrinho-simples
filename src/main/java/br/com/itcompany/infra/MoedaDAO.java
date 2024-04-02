package br.com.itcompany.infra;

import br.com.itcompany.core.IMoedaDAO;
import br.com.itcompany.core.entity.moeda.Moeda;

import java.util.ArrayList;
import java.util.List;

public class MoedaDAO implements IMoedaDAO {
    private final List<Moeda> moedaList;

    public MoedaDAO() {
        this.moedaList = new ArrayList<>();
    }

    public List<Moeda> list() {
        return moedaList;
    }

    public Moeda insert(Moeda moeda) {
        moedaList.add(moeda);
        return moeda;
    }

    public Moeda update(Moeda moeda) {
        for (Moeda m : moedaList) {
            if (m.info().equals(moeda.info())) {
                m.setValor(moeda.getValor());
            }
        }
        return moeda;
    }

    public Moeda remove(Moeda moeda) {
        return moedaList.remove(find(moeda.info())) ? moeda : null;
    }

    public Moeda find(String info) {
        return moedaList.stream().filter(m -> m.info().startsWith(info.split(":")[0])).findFirst().orElse(null);
    }
}
