package br.com.itcompany;

import br.com.itcompany.core.Cofrinho;
import br.com.itcompany.core.IMoedaDAO;
import br.com.itcompany.infra.MoedaDAO;
import br.com.itcompany.ui.TerminalUI;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        List<String> menuItems = Arrays.asList("listar moedas", "adicionar moeda", "remover moeda", "mostrar total em reais");
        IMoedaDAO dao = new MoedaDAO();
        Cofrinho cofrinhoApp = new Cofrinho(dao);
        String appName = "cofrinho";
        TerminalUI ui = new TerminalUI(appName, menuItems, cofrinhoApp);

        ui.init();
    }
}
