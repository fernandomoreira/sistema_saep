import java.sql.*;
import java.time.LocalDate;

public class EstoqueDAO {

    public void cadastrarProduto(String nome, String categoria, String unidade, int quantidade, double valorUnitario, int estoqueMinimo, int estoqueMaximo) {
        String sql = "INSERT INTO produto (nome, categoria, unidade_medida, quantidade, valor_unitario, estoque_minimo, estoque_maximo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nome);
            ps.setString(2, categoria);
            ps.setString(3, unidade);
            ps.setInt(4, quantidade);
            ps.setDouble(5, valorUnitario);
            ps.setInt(6, estoqueMinimo);
            ps.setInt(7, estoqueMaximo);

            ps.executeUpdate();
            System.out.println("Produto cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public void listarProdutos() {
        String sql = "SELECT id, nome, categoria, unidade_medida, quantidade, valor_unitario, estoque_minimo, estoque_maximo FROM produto ORDER BY nome";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                double total = rs.getInt("quantidade") * rs.getDouble("valor_unitario");

                System.out.println("\nID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome"));
                System.out.println("Categoria: " + rs.getString("categoria") + " | Medida: " + rs.getString("unidade_medida"));
                System.out.println("Qtd: " + rs.getInt("quantidade") + " | Valor Un.: R$" + rs.getDouble("valor_unitario") + " | Total: R$" + total);
                System.out.println("Limites -> Min: " + rs.getInt("estoque_minimo") + " | Max: " + rs.getInt("estoque_maximo"));
            }
            if (!encontrou) System.out.println("Nenhum produto cadastrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    public void listarValorTotalPorCategoria() {
        String sql = "SELECT categoria, SUM(quantidade * valor_unitario) AS total_categoria FROM produto GROUP BY categoria ORDER BY categoria";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.println("Categoria: " + rs.getString("categoria") + " | Valor total: R$ " + rs.getDouble("total_categoria"));
            }
            if (!encontrou) System.out.println("Nenhuma categoria encontrada.");
        } catch (SQLException e) {
            System.out.println("Erro ao listar por categoria: " + e.getMessage());
        }
    }

    public void registrarEntrada(int produtoId, int quantidadeEntrada) {
        String sqlBusca = "SELECT quantidade, valor_unitario FROM produto WHERE id = ?";
        String sqlUpdate = "UPDATE produto SET quantidade = quantidade + ? WHERE id = ?";
        String sqlMov = "INSERT INTO movimentacao (produto_id, tipo, quantidade, data_mov, valor_unitario, valor_total) VALUES (?, 'ENTRADA', ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);
            
            try (PreparedStatement psBusca = con.prepareStatement(sqlBusca);
                 PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
                 PreparedStatement psMov = con.prepareStatement(sqlMov)) {
                
                psBusca.setInt(1, produtoId);
                double valorUnitario;
                
                try (ResultSet rs = psBusca.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Produto não encontrado.");
                        con.rollback();
                        return;
                    }
                    valorUnitario = rs.getDouble("valor_unitario");
                }

                psUpdate.setInt(1, quantidadeEntrada);
                psUpdate.setInt(2, produtoId);
                psUpdate.executeUpdate();

                psMov.setInt(1, produtoId);
                psMov.setInt(2, quantidadeEntrada);
                psMov.setDate(3, Date.valueOf(LocalDate.now()));
                psMov.setDouble(4, valorUnitario);
                psMov.setDouble(5, quantidadeEntrada * valorUnitario);
                psMov.executeUpdate();

                con.commit();
                System.out.println("Entrada registrada com sucesso.");
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar entrada: " + e.getMessage());
        }
    }

    public void registrarSaida(int produtoId, int quantidadeSaida) {
        String sqlBusca = "SELECT quantidade, valor_unitario FROM produto WHERE id = ?";
        String sqlUpdate = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ?";
        String sqlMov = "INSERT INTO movimentacao (produto_id, tipo, quantidade, data_mov, valor_unitario, valor_total) VALUES (?, 'SAIDA', ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement psBusca = con.prepareStatement(sqlBusca);
                 PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
                 PreparedStatement psMov = con.prepareStatement(sqlMov)) {

                psBusca.setInt(1, produtoId);
                int quantidadeAtual;
                double valorUnitario;

                try (ResultSet rs = psBusca.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Produto não encontrado.");
                        con.rollback();
                        return;
                    }
                    quantidadeAtual = rs.getInt("quantidade");
                    valorUnitario = rs.getDouble("valor_unitario");
                }

                if (quantidadeAtual < quantidadeSaida) {
                    System.out.println("Quantidade insuficiente em estoque.");
                    con.rollback();
                    return;
                }

                psUpdate.setInt(1, quantidadeSaida);
                psUpdate.setInt(2, produtoId);
                psUpdate.executeUpdate();

                psMov.setInt(1, produtoId);
                psMov.setInt(2, quantidadeSaida);
                psMov.setDate(3, Date.valueOf(LocalDate.now()));
                psMov.setDouble(4, valorUnitario);
                psMov.setDouble(5, quantidadeSaida * valorUnitario);
                psMov.executeUpdate();

                con.commit();
                System.out.println("Saída registrada com sucesso.");
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar saída: " + e.getMessage());
        }
    }

    public void listarSaidasPorData() {
        String sql = "SELECT p.nome, m.quantidade, m.valor_total, m.data_mov FROM movimentacao m JOIN produto p ON p.id = m.produto_id WHERE m.tipo = 'SAIDA' ORDER BY m.data_mov DESC, m.id DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.println("Data: " + rs.getDate("data_mov") + " | Produto: " + rs.getString("nome") + " | Qtd: " + rs.getInt("quantidade") + " | Total: R$" + rs.getDouble("valor_total"));
            }
            if (!encontrou) System.out.println("Nenhuma saída encontrada.");
        } catch (SQLException e) {
            System.out.println("Erro ao listar saídas: " + e.getMessage());
        }
    }

    public void listarMovimentacoesPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        String sql = "SELECT p.nome, p.unidade_medida, " +
                     "COALESCE(SUM(CASE WHEN m.tipo = 'ENTRADA' THEN m.quantidade ELSE 0 END), 0) AS total_entradas, " +
                     "COALESCE(SUM(CASE WHEN m.tipo = 'SAIDA' THEN m.quantidade ELSE 0 END), 0) AS total_saidas, " +
                     "COALESCE(SUM(CASE WHEN m.tipo = 'ENTRADA' THEN m.quantidade ELSE 0 END), 0) - COALESCE(SUM(CASE WHEN m.tipo = 'SAIDA' THEN m.quantidade ELSE 0 END), 0) AS saldo_periodo, " +
                     "COALESCE(SUM(CASE WHEN m.tipo = 'ENTRADA' THEN m.valor_total ELSE 0 END), 0) AS valor_total_entradas, " +
                     "COALESCE(SUM(CASE WHEN m.tipo = 'SAIDA' THEN m.valor_total ELSE 0 END), 0) AS valor_total_saidas " +
                     "FROM movimentacao m JOIN produto p ON p.id = m.produto_id WHERE m.data_mov BETWEEN ? AND ? GROUP BY p.id, p.nome, p.unidade_medida ORDER BY p.nome";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDate(1, Date.valueOf(dataInicial));
            ps.setDate(2, Date.valueOf(dataFinal));

            try (ResultSet rs = ps.executeQuery()) {
                boolean encontrou = false;
                while (rs.next()) {
                    encontrou = true;
                    System.out.println("\nProduto: " + rs.getString("nome") + " (" + rs.getString("unidade_medida") + ")");
                    System.out.println("Entradas: " + rs.getInt("total_entradas") + " | Saídas: " + rs.getInt("total_saidas") + " | Saldo: " + rs.getInt("saldo_periodo"));
                    System.out.println("Financ. Entradas: R$" + rs.getDouble("valor_total_entradas") + " | Financ. Saídas: R$" + rs.getDouble("valor_total_saidas"));
                }
                if (!encontrou) System.out.println("Nenhuma movimentação no período.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar movimentações: " + e.getMessage());
        }
    }

    public void listarProdutosMaiorSaidaPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        String sql = "SELECT p.nome, COALESCE(SUM(m.quantidade), 0) AS total_saida, COALESCE(SUM(m.valor_total), 0) AS valor_total_financeiro " +
                     "FROM movimentacao m JOIN produto p ON p.id = m.produto_id WHERE m.tipo = 'SAIDA' AND m.data_mov BETWEEN ? AND ? GROUP BY p.id, p.nome ORDER BY total_saida DESC, p.nome ASC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDate(1, Date.valueOf(dataInicial));
            ps.setDate(2, Date.valueOf(dataFinal));

            try (ResultSet rs = ps.executeQuery()) {
                boolean encontrou = false;
                while (rs.next()) {
                    encontrou = true;
                    System.out.println("Produto: " + rs.getString("nome") + " | Total Saída: " + rs.getInt("total_saida") + " | Faturamento: R$" + rs.getDouble("valor_total_financeiro"));
                }
                if (!encontrou) System.out.println("Nenhuma saída no período.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos com maior saída: " + e.getMessage());
        }
    }

    public void identificarLimitesEstoque() {
        String sql = "SELECT nome, quantidade, estoque_minimo, estoque_maximo FROM produto WHERE quantidade <= estoque_minimo OR quantidade >= estoque_maximo ORDER BY nome";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                int quantidade = rs.getInt("quantidade");
                int max = rs.getInt("estoque_maximo");
                int min = rs.getInt("estoque_minimo");
                
                double percentual = max > 0 ? (quantidade * 100.0) / max : 0;
                String status = quantidade <= min ? "MÍNIMO ATINGIDO" : "MÁXIMO ATINGIDO";

                System.out.println("Produto: " + rs.getString("nome") + " | Qtd: " + quantidade + " | Status: " + status + " | Nível: " + String.format("%.2f", percentual) + "%");
            }
            if (!encontrou) System.out.println("Nenhum produto atingiu os limites.");
        } catch (SQLException e) {
            System.out.println("Erro ao identificar limites: " + e.getMessage());
        }
    }
}