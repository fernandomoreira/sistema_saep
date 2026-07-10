package service;

import model.Categoria;
import model.Movimentacao;
import model.Produto;
import model.TipoMovimentacao;
import repository.BancoDados;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstoqueService {

    public void cadastrarProduto(String nome, String unidadeMedida, double valorUnitario, int quantidade, String nomeCategoria) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Nome do produto inválido.");
            return;
        }
        if (unidadeMedida == null || unidadeMedida.isBlank()) {
            System.out.println("Unidade de medida inválida.");
            return;
        }
        if (valorUnitario < 0) {
            System.out.println("Valor unitário inválido.");
            return;
        }
        if (quantidade < 0) {
            System.out.println("Quantidade inválida.");
            return;
        }
        if (nomeCategoria == null || nomeCategoria.isBlank()) {
            nomeCategoria = "Sem categoria";
        }

        boolean existe = BancoDados.produtos.stream()
                .anyMatch(p -> p.getNome().equalsIgnoreCase(nome.trim()));
        if (existe) {
            System.out.println("Produto já cadastrado: " + nome);
            return;
        }

        Categoria categoria = obterOuCriarCategoria(nomeCategoria.trim());
        Produto novoProduto = new Produto(nome.trim(), unidadeMedida.trim().toUpperCase(), valorUnitario, quantidade, categoria);
        BancoDados.produtos.add(novoProduto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private Categoria obterOuCriarCategoria(String nomeCategoria) {
        return BancoDados.categorias.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nomeCategoria))
                .findFirst()
                .orElseGet(() -> {
                    Categoria categoria = new Categoria(nomeCategoria);
                    BancoDados.categorias.add(categoria);
                    return categoria;
                });
    }

    public void listarProdutos() {
        if (BancoDados.produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE PRODUTOS ---");
        BancoDados.produtos.stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .forEach(p -> System.out.printf("Nome: %-12s | Categoria: %-12s | Unidade: %-3s | Valor: R$ %-7.2f | Quantidade: %-4d%n",
                        p.getNome(), p.getCategoria().getNome(), p.getUnidadeMedida(), p.getValorUnitario(), p.getQuantidade()));
    }

    public void listarValorTotalPorCategoria() {
        if (BancoDados.produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        Map<String, Double> totalPorCategoria = new HashMap<>();
        for (Produto produto : BancoDados.produtos) {
            String nomeCategoria = produto.getCategoria().getNome();
            double valorTotal = produto.getQuantidade() * produto.getValorUnitario();
            totalPorCategoria.merge(nomeCategoria, valorTotal, Double::sum);
        }

        System.out.println("\n--- VALOR TOTAL POR CATEGORIA ---");
        totalPorCategoria.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("Categoria: %-12s | Valor total em estoque: R$ %.2f%n",
                        entry.getKey(), entry.getValue()));
    }

    public void registrarMovimentacao(String nomeProduto, TipoMovimentacao tipo, int quantidade, LocalDate data) {
        if (nomeProduto == null || nomeProduto.isBlank()) {
            System.out.println("Nome do produto inválido.");
            return;
        }
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }
        if (data == null) {
            System.out.println("Data inválida.");
            return;
        }

        Produto produto = BancoDados.produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nomeProduto.trim()))
                .findFirst()
                .orElse(null);

        if (produto == null) {
            System.out.println("Produto não encontrado: " + nomeProduto);
            return;
        }

        if (tipo == TipoMovimentacao.SAIDA && produto.getQuantidade() < quantidade) {
            System.out.println("Não há estoque suficiente para a saída.");
            return;
        }

        Movimentacao movimentacao = new Movimentacao(produto, tipo, quantidade, data);
        BancoDados.movimentacoes.add(movimentacao);

        int novaQuantidade = tipo == TipoMovimentacao.ENTRADA
                ? produto.getQuantidade() + quantidade
                : produto.getQuantidade() - quantidade;
        produto.setQuantidade(novaQuantidade);

        System.out.println("Movimentação registrada com sucesso.");
    }

    public void listarSaidasPorDataDecrescente() {
        List<Movimentacao> saidas = BancoDados.movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.SAIDA)
                .sorted(Comparator.comparing(Movimentacao::getData).reversed())
                .collect(Collectors.toList());

        if (saidas.isEmpty()) {
            System.out.println("Nenhuma saída registrada.");
            return;
        }

        System.out.println("\n--- SAÍDAS POR DATA DECRESCENTE ---");
        saidas.forEach(m -> System.out.printf("Data: %s | Produto: %-12s | Quantidade: %-3d | Valor: R$ %.2f%n",
                m.getData(), m.getProduto().getNome(), m.getQuantidade(), m.getValorFinanceiro()));
    }

    public void listarMovimentacoesPorProduto(String nomeProduto) {
        if (nomeProduto == null || nomeProduto.isBlank()) {
            System.out.println("Nome do produto inválido.");
            return;
        }

        List<Movimentacao> movimentacoes = BancoDados.movimentacoes.stream()
                .filter(m -> m.getProduto() != null && m.getProduto().getNome().equalsIgnoreCase(nomeProduto.trim()))
                .sorted(Comparator.comparing(Movimentacao::getData))
                .collect(Collectors.toList());

        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação encontrada para o produto: " + nomeProduto);
            return;
        }

        System.out.println("\n--- MOVIMENTAÇÕES DO PRODUTO: " + nomeProduto + " ---");
        movimentacoes.forEach(m -> System.out.printf("Data: %s | Tipo: %-7s | Quantidade: %-3d | Valor: R$ %.2f%n",
                m.getData(), m.getTipo(), m.getQuantidade(), m.getValorFinanceiro()));
    }

    public void produtosMaiorSaidaNoPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            System.out.println("Período inválido.");
            return;
        }
        if (fim.isBefore(inicio)) {
            System.out.println("Data final deve ser igual ou posterior à data inicial.");
            return;
        }

        Map<String, Integer> saidaPorProduto = new HashMap<>();
        for (Movimentacao m : BancoDados.movimentacoes) {
            if (m.getTipo() == TipoMovimentacao.SAIDA) {
                LocalDate data = m.getData();
                if ((data.isEqual(inicio) || data.isAfter(inicio)) && (data.isEqual(fim) || data.isBefore(fim))) {
                    String nome = m.getProduto().getNome();
                    saidaPorProduto.merge(nome, m.getQuantidade(), Integer::sum);
                }
            }
        }

        if (saidaPorProduto.isEmpty()) {
            System.out.println("Nenhuma saída registrada no período informado.");
            return;
        }

        int maiorSaida = saidaPorProduto.values().stream().max(Integer::compareTo).orElse(0);
        List<Map.Entry<String, Integer>> maiores = saidaPorProduto.entrySet().stream()
                .filter(entry -> entry.getValue() == maiorSaida)
                .collect(Collectors.toList());

        System.out.println("\n--- PRODUTOS COM MAIOR SAÍDA NO PERÍODO ---");
        maiores.forEach(entry -> System.out.printf("Produto: %-12s | Total Saída: %-3d%n", entry.getKey(), entry.getValue()));
    }

    public void identificarProdutosLimites() {
        if (BancoDados.produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        List<Produto> minimos = BancoDados.produtos.stream()
                .filter(p -> p.getQuantidade() <= 0)
                .sorted(Comparator.comparing(Produto::getNome))
                .collect(Collectors.toList());

        List<Produto> maximos = BancoDados.produtos.stream()
                .filter(p -> p.getQuantidade() >= 100)
                .sorted(Comparator.comparing(Produto::getNome))
                .collect(Collectors.toList());

        System.out.println("\n--- PRODUTOS NO LIMITE MÍNIMO ---");
        if (minimos.isEmpty()) {
            System.out.println("Nenhum produto no limite mínimo.");
        } else {
            minimos.forEach(p -> System.out.printf("Produto: %-12s | Quantidade: %-3d%n", p.getNome(), p.getQuantidade()));
        }

        System.out.println("\n--- PRODUTOS NO LIMITE MÁXIMO ---");
        if (maximos.isEmpty()) {
            System.out.println("Nenhum produto no limite máximo.");
        } else {
            maximos.forEach(p -> System.out.printf("Produto: %-12s | Quantidade: %-3d%n", p.getNome(), p.getQuantidade()));
        }
    }
}
