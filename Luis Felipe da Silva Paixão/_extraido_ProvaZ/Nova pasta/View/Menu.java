//Aluno: Luis Felipe
//Turma:96229

package View;

import model.Categoria;
import model.Produto;
import model.TipoMovimentacao;
import repository.BancoDados;
import service.EstoqueService;
import util.Datautil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private EstoqueService service = new EstoqueService();

    public void iniciar() {
        carregarDadosIniciais();

        int opcao = 0;
        while (opcao != 10) {
            imprimirMenu();
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Digite um número de 1 a 10.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarNovoProduto();
                    break;
                case 2:
                    service.listarProdutos();
                    break;
                case 3:
                    service.listarValorTotalPorCategoria();
                    break;
                case 4:
                    registrarMov(TipoMovimentacao.ENTRADA);
                    break;
                case 5:
                    registrarMov(TipoMovimentacao.SAIDA);
                    break;
                case 6:
                    service.listarSaidasPorDataDecrescente();
                    break;
                case 7:
                    consultarMovimentacoesPorProduto();
                    break;
                case 8:
                    exibirProdutosMaiorSaidaPeriodo();
                    break;
                case 9:
                    service.identificarProdutosLimites();
                    break;
                case 10:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Digite um número de 1 a 10.");
            }
        }
    }

    private void carregarDadosIniciais() {
        Categoria gerais = new Categoria("Gerais");
        BancoDados.categorias.add(gerais);

        BancoDados.produtos.add(new Produto("Caderno", "UN", 15.50, 10, gerais));
        BancoDados.produtos.add(new Produto("Caneta", "CX", 50.00, 105, gerais));
        BancoDados.produtos.add(new Produto("Borracha", "UN", 2.00, 0, gerais));
    }

    private void imprimirMenu() {
        System.out.println();
        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                   CONTROLE DE ARMAZENAMENTO               |");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("1. Cadastrar novo produto");
        System.out.println("2. Listar todos os produtos");
        System.out.println("3. Listar valor total por categoria");
        System.out.println("4. Registrar entrada");
        System.out.println("5. Registrar saída");
        System.out.println("6. Exibir saídas por data decrescente");
        System.out.println("7. Exibir movimentações por produto");
        System.out.println("8. Exibir produtos com maior saída no período");
        System.out.println("9. Identificar produtos nos limites mínimo e máximo");
        System.out.println("10. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarNovoProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Unidade de medida: ");
        String unidade = scanner.nextLine();

        System.out.print("Valor unitário: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Valor inválido.");
            scanner.nextLine();
            return;
        }
        double valorUnitario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Quantidade: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Quantidade inválida.");
            scanner.nextLine();
            return;
        }
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        service.cadastrarProduto(nome, unidade, valorUnitario, quantidade, categoria);
    }

    private void registrarMov(TipoMovimentacao tipo) {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Quantidade: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Quantidade inválida.");
            scanner.nextLine();
            return;
        }
        int qtd = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data (DD/MM/AAAA): ");
        try {
            LocalDate data = Datautil.formatarData(scanner.nextLine());
            service.registrarMovimentacao(nome, tipo, qtd, data);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato DD/MM/AAAA.");
        }
    }

    private void consultarMovimentacoesPorProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        service.listarMovimentacoesPorProduto(nome);
    }

    private void exibirProdutosMaiorSaidaPeriodo() {
        try {
            System.out.print("Data inicial (DD/MM/AAAA): ");
            LocalDate inicio = Datautil.formatarData(scanner.nextLine());
            System.out.print("Data final (DD/MM/AAAA): ");
            LocalDate fim = Datautil.formatarData(scanner.nextLine());
            service.produtosMaiorSaidaNoPeriodo(inicio, fim);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato DD/MM/AAAA.");
        }
    }
}
