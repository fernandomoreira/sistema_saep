import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import dao.RelatorioDAO;
import model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();
    private static final MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private static final RelatorioDAO relatorioDAO = new RelatorioDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInt("Escolha: ");
            try {
                switch (opcao) {
                    case 1 -> produtoDAO.listarTodos();
                    case 2 -> cadastrarProduto();
                    case 3 -> registrarEntrada();
                    case 4 -> registrarSaida();
                    case 5 -> relatorioDAO.valorTotalPorCategoria();
                    case 6 -> relatorioDAO.listarSaidasDecrescente();
                    case 7 -> movimentacoesPeriodo();
                    case 8 -> maioresSaidasPeriodo();
                    case 9 -> relatorioDAO.produtosLimites();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE ESTOQUE =====");
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Cadastrar novo produto");
        System.out.println("3 - Registrar entrada");
        System.out.println("4 - Registrar saída");
        System.out.println("5 - Listar valor total por categoria");
        System.out.println("6 - Listar saídas por data decrescente");
        System.out.println("7 - Listar entradas e saídas por período");
        System.out.println("8 - Produtos com maior volume de saída por período");
        System.out.println("9 - Produtos em limite mínimo ou máximo");
        System.out.println("0 - Sair");
    }

    private static void cadastrarProduto() throws Exception {
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Unidade de medida: ");
        String unidade = sc.nextLine();
        int quantidade = lerInt("Quantidade inicial: ");
        BigDecimal valor = lerBigDecimal("Valor unitário: ");
        int idCategoria = lerInt("ID da categoria: ");
        produtoDAO.cadastrar(new Produto(nome, unidade, quantidade, valor, idCategoria));
        System.out.println("Produto cadastrado com sucesso.");
    }

    private static void registrarEntrada() throws Exception {
        int idProduto = lerInt("ID do produto: ");
        int quantidade = lerInt("Quantidade de entrada: ");
        LocalDate data = lerData("Data inicial/entrada (AAAA-MM-DD): ");
        int idUsuario = lerInt("ID do usuário: ");
        movimentacaoDAO.registrarEntrada(idProduto, quantidade, data, idUsuario);
    }

    private static void registrarSaida() throws Exception {
        int idProduto = lerInt("ID do produto: ");
        int quantidade = lerInt("Quantidade de saída: ");
        LocalDate data = lerData("Data final/saída (AAAA-MM-DD): ");
        int idUsuario = lerInt("ID do usuário: ");
        movimentacaoDAO.registrarSaida(idProduto, quantidade, data, idUsuario);
    }

    private static void movimentacoesPeriodo() throws Exception {
        LocalDate inicio = lerData("Data inicial (AAAA-MM-DD): ");
        LocalDate fim = lerData("Data final (AAAA-MM-DD): ");
        relatorioDAO.movimentacoesPeriodo(inicio, fim);
    }

    private static void maioresSaidasPeriodo() throws Exception {
        LocalDate inicio = lerData("Data inicial (AAAA-MM-DD): ");
        LocalDate fim = lerData("Data final (AAAA-MM-DD): ");
        relatorioDAO.maioresSaidasPeriodo(inicio, fim);
    }

    private static int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static BigDecimal lerBigDecimal(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return new BigDecimal(sc.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido.");
            }
        }
    }

    private static LocalDate lerData(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalDate.parse(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Digite a data no formato AAAA-MM-DD.");
            }
        }
    }
}
