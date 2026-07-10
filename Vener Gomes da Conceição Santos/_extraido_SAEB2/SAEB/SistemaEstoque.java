//ALUNO : VENER GOMES
//TURMA G96229

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SistemaEstoque {

    private final Scanner sc = new Scanner(System.in);
    private final EstoqueDAO dao = new EstoqueDAO();

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n===== CONTROLE DE ALMOXARIFADO =====");
            System.out.println("1 - Cadastrar novo produto");
            System.out.println("2 - Listar todos os produtos");
            System.out.println("3 - Listar valor total por categoria");
            System.out.println("4 - Registrar entrada");
            System.out.println("5 - Registrar saída");
            System.out.println("6 - Listar saídas por data decrescente");
            System.out.println("7 - Listar movimentações por período");
            System.out.println("8 - Listar produtos com maior saída no período");
            System.out.println("9 - Identificar produtos nos limites mínimo e máximo");
            System.out.println("0 - Sair");

            opcao = lerInteiro("Escolha uma opção: ", 0, 9);

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    dao.listarProdutos();
                    break;
                case 3:
                    dao.listarValorTotalPorCategoria();
                    break;
                case 4:
                    registrarEntrada();
                    break;
                case 5:
                    registrarSaida();
                    break;
                case 6:
                    dao.listarSaidasPorData();
                    break;
                case 7:
                    listarMovimentacoesPeriodo();
                    break;
                case 8:
                    listarProdutosMaiorSaidaPeriodo();
                    break;
                case 9:
                    dao.identificarLimitesEstoque();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarProduto() {
        String nome = lerTexto("Nome do produto: ");
        String categoria = lerTexto("Categoria: ");
        String unidade = lerTexto("Unidade de medida: ");
        int quantidade = lerInteiro("Quantidade inicial: ", 0);
        double valorUnitario = lerDouble("Valor unitário: ", 0.01);
        int estoqueMinimo = lerInteiro("Estoque mínimo: ", 0);
        int estoqueMaximo = lerInteiro("Estoque máximo: ", estoqueMinimo);

        dao.cadastrarProduto(nome, categoria, unidade, quantidade, valorUnitario, estoqueMinimo, estoqueMaximo);
    }

    private void registrarEntrada() {
        int produtoId = lerInteiro("ID do produto: ", 1);
        int quantidadeEntrada = lerInteiro("Quantidade de entrada: ", 1);
        dao.registrarEntrada(produtoId, quantidadeEntrada);
    }

    private void registrarSaida() {
        int produtoId = lerInteiro("ID do produto: ", 1);
        int quantidadeSaida = lerInteiro("Quantidade de saída: ", 1);
        dao.registrarSaida(produtoId, quantidadeSaida);
    }

    private void listarMovimentacoesPeriodo() {
        LocalDate de = lerData("Data inicial (yyyy-MM-dd): ");
        LocalDate ate = lerData("Data final (yyyy-MM-dd): ");
        dao.listarMovimentacoesPeriodo(de, ate);
    }

    private void listarProdutosMaiorSaidaPeriodo() {
        LocalDate de = lerData("Data inicial (yyyy-MM-dd): ");
        LocalDate ate = lerData("Data final (yyyy-MM-dd): ");
        dao.listarProdutosMaiorSaidaPeriodo(de, ate);
    }

    private String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("Esse campo não pode ficar vazio.");
        }
    }

    private int lerInteiro(String mensagem, int minimo) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= minimo) return valor;
                System.out.println("Digite um valor inteiro maior ou igual a " + minimo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private int lerInteiro(String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= minimo && valor <= maximo) return valor;
                System.out.println("Digite uma opção entre " + minimo + " e " + maximo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private double lerDouble(String mensagem, double minimo) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                if (valor >= minimo) return valor;
                System.out.println("Digite um valor maior ou igual a " + minimo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número decimal válido.");
            }
        }
    }

    private LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                return LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato yyyy-MM-dd.");
            }
        }
    }
}