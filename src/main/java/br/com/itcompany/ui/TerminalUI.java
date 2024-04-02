package br.com.itcompany.ui;

import br.com.itcompany.core.Cofrinho;
import br.com.itcompany.core.entity.moeda.Moeda;
import br.com.itcompany.core.entity.moeda.imp.Dolar;
import br.com.itcompany.core.entity.moeda.imp.Euro;
import br.com.itcompany.core.entity.moeda.imp.Real;

import java.util.*;
import java.util.stream.Collectors;

public class TerminalUI {
    private final String nomeApp;
    private final List<String> opcoesMenu;
    private final Cofrinho cofrinhoApp;

    public TerminalUI(String nomeApp, List<String> opcoesMenu, Cofrinho cofrinhoApp) {
        this.cofrinhoApp = cofrinhoApp;
        this.nomeApp = nomeApp.toUpperCase(Locale.ROOT);
        this.opcoesMenu = opcoesMenu.stream().map(op -> op.toUpperCase(Locale.ROOT)).collect(Collectors.toList());
    }

    public void init() {
        int opcaoSelecionada;
        int moedaEscolhida;
        List<String> nomesMoedas = Arrays.asList("REAL", "DOLAR", "EURO");
        HashMap<String, Class<?>> campos = new HashMap<>();
        campos.put("Valor", Double.class);
        List<String> valoresFormulario;

        do {
            banner(nomeApp);
            menu(opcoesMenu);
            opcaoSelecionada = opcao();

            switch (opcaoSelecionada) {
                case 1:
                    banner(opcoesMenu.get(0));
                    if (cofrinhoApp.listarMoedas().isEmpty()) {
                        msg("Não há moedas!");
                    } else {
                        imprimirLista(cofrinhoApp.listarMoedas().stream().map(Moeda::info).collect(Collectors.toList()));
                    }
                    break;
                case 2:
                    banner(opcoesMenu.get(1));
                    menu(nomesMoedas);
                    moedaEscolhida = opcao();

                    switch (moedaEscolhida) {
                        case 0:
                            break;
                        case 1:
                            valoresFormulario = formulario("Cadastro - Real", campos);
                            if (!valoresFormulario.isEmpty() && cofrinhoApp.adicionarMoeda(new Real(Double.parseDouble(valoresFormulario.get(0)))) != null) {
                                msg("Moeda cadastrada com sucesso!");
                            }
                            break;
                        case 2:
                            valoresFormulario = formulario("Cadastro - Dolar", campos);
                            if (!valoresFormulario.isEmpty() && cofrinhoApp.adicionarMoeda(new Dolar(Double.parseDouble(valoresFormulario.get(0)))) != null) {
                                msg("Moeda cadastrada com sucesso!");

                            }
                            break;
                        case 3:
                            valoresFormulario = formulario("Cadastro - Euro", campos);
                            if (!valoresFormulario.isEmpty() && cofrinhoApp.adicionarMoeda(new Euro(Double.parseDouble(valoresFormulario.get(0)))) != null) {
                                msg("Moeda cadastrada com sucesso!");
                            }
                            break;
                        default:
                            msg("Opção inválida");
                            break;
                    }
                    break;
                case 3:
                    banner(opcoesMenu.get(2));
                    if (cofrinhoApp.listarMoedas().isEmpty()) {
                        msg("Não há moedas para remover");
                    } else {
//                        List<String> moedasDisponiveis = cofrinhoApp.listarMoedas().stream().map(m -> m.info().split(":")[0]).collect(Collectors.toList());
                        menu(nomesMoedas);
                        moedaEscolhida = opcao();

                        switch (moedaEscolhida) {
                            case 0:
                                break;
                            case 1:
                                if (cofrinhoApp.listarMoedas().stream().anyMatch(m -> m.info().toUpperCase(Locale.ROOT).contains("REAL"))) {
                                    valoresFormulario = formulario("Remover - Real", campos);
                                    if (!valoresFormulario.isEmpty() && cofrinhoApp.removerMoeda(new Real(Double.parseDouble(valoresFormulario.get(0)))) != null) {
                                        msg("Moeda removida com sucesso");
                                    }
                                } else {
                                    msg("Não há Real no cofre");
                                }
                                break;
                            case 2:
                                if (cofrinhoApp.listarMoedas().stream().anyMatch(m -> m.info().toUpperCase(Locale.ROOT).contains("DOLAR"))) {
                                    valoresFormulario = formulario("Remover - Dolar", campos);
                                    if (!valoresFormulario.isEmpty() && cofrinhoApp.removerMoeda(new Dolar(Double.parseDouble(valoresFormulario.get(0)))) != null) {
                                        msg("Moeda removida com sucesso");
                                    }
                                } else {
                                    msg("Não há Dolar no cofre");
                                }
                                break;
                            case 3:
                                if (cofrinhoApp.listarMoedas().stream().anyMatch(m -> m.info().toUpperCase(Locale.ROOT).contains("EURO"))) {
                                    valoresFormulario = formulario("Remover - Euro", campos);
                                    if (!valoresFormulario.isEmpty() && cofrinhoApp.removerMoeda(new Euro(Double.parseDouble(valoresFormulario.get(0)))) != null) {
                                        msg("Moeda removida com sucesso");
                                    }
                                } else {
                                    msg("Não há Euro no cofre");
                                }
                                break;
                            default:
                                msg("Opção inválida");
                                break;
                        }
                    }
                    break;
                case 4:
                    banner(opcoesMenu.get(3));
                    if (cofrinhoApp.listarMoedas().isEmpty()) {
                        msg("Não há moedas para converter");
                    } else {
                        banner("Valor final em R$: " + cofrinhoApp.totalConvertido());
                    }
                    break;
                case 0:
                    banner("bye");
                    break;
                default:
                    msg("Opção inválida");
            }
        } while (opcaoSelecionada != 0);
    }

    private void banner(String texto) {
        int tamBanner = texto.length() + 4;

        for (int i = 0; i < tamBanner; i++) {
            System.out.print("=");
            if (i == tamBanner - 1) {
                System.out.println();
            }
        }
        System.out.println("| " + texto.toUpperCase(Locale.ROOT) + " |");
        for (int i = 0; i < tamBanner; i++) {
            System.out.print("=");
            if (i == tamBanner - 1) {
                System.out.println();
            }
        }
    }

    private void menu(List<String> items) {
        System.out.println();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + " - " + items.get(i));
        }
        System.out.println("0 - SAIR");
        System.out.println();
    }

    private int opcao() {
        int opcaoEscolhida = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.print(">>>: ");
        try {
            opcaoEscolhida = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            msg("Por favor digite um número inteiro que corresponda a uma das opções acima.");
        }
        return opcaoEscolhida;
    }

    private void msg(String texto){
        System.out.println();
        System.out.println(texto);
        System.out.println();
    }

    private List<String> formulario(String titulo, HashMap<String, Class<?>> campos) {
        Scanner input = new Scanner(System.in);
        List<String> valores = new ArrayList<>();

        banner(titulo);
        System.out.println();
        campos.forEach((campo, tipo) -> {
            System.out.print(campo.toUpperCase(Locale.ROOT) + ": ");
            if (tipo.equals(Integer.class)) {
                try {
                    valores.add(String.valueOf(Integer.parseInt(input.nextLine())));
                } catch (NumberFormatException e) {
                    msg("'" + campo + "' Não é um valor numérico");
                }
            } else if (tipo.equals(Double.class)) {
                try {
                    valores.add(String.valueOf(Double.parseDouble(input.nextLine())));
                } catch (NumberFormatException e) {
                    msg("'" + campo + "' Não é um valor numérico");
                }
            } else if (tipo.equals(Boolean.class)) {
                try {
                    valores.add(String.valueOf(Boolean.parseBoolean(input.nextLine())));
                } catch (Exception e) {
                    msg("'" + campo + "' Não é um valor booleano (verdadeiro ou falso)");
                }
            } else if (tipo.equals(String.class)) {
                valores.add(input.nextLine());
            }
        });
        System.out.println();

        return valores;
    }

    private void imprimirLista(Iterable<?> items) {
        System.out.println();
        items.forEach(item -> System.out.println("- " + item));
        System.out.println();
    }
}
