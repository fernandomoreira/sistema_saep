package dao;

import conexao.Conexao;

import java.sql.*;
import java.time.LocalDate;

public class RelatorioDAO {
    public void valorTotalPorCategoria() throws SQLException {
        String sql = "SELECT Categoria, SUM(Valor_Total_Item) AS Total FROM vw_estoque GROUP BY Categoria ORDER BY Categoria";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- VALOR TOTAL POR CATEGORIA ---");
            while (rs.next()) System.out.printf("Categoria: %s | Total: R$ %.2f%n", rs.getString("Categoria"), rs.getBigDecimal("Total"));
        }
    }

    public void listarSaidasDecrescente() throws SQLException {
        String sql = "SELECT p.Nome, m.Quantidade, m.Data_Movimentacao, p.Valor_Unitario, (m.Quantidade * p.Valor_Unitario) AS Total FROM Movimentacao m JOIN Produto p ON p.Id_Produto = m.fk_Produto_Id_Produto WHERE m.Tipo = 'S' ORDER BY m.Data_Movimentacao DESC";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- SAÍDAS POR DATA DECRESCENTE ---");
            while (rs.next()) System.out.printf("Data: %s | Produto: %s | Qtd: %d | Total: R$ %.2f%n", rs.getDate("Data_Movimentacao"), rs.getString("Nome"), rs.getInt("Quantidade"), rs.getBigDecimal("Total"));
        }
    }

    public void movimentacoesPeriodo(LocalDate inicio, LocalDate fim) throws SQLException {
        String sql = "SELECT p.Nome AS Produto, p.Unidade_Medida, " +
                "SUM(CASE WHEN m.Tipo='E' THEN m.Quantidade ELSE 0 END) AS Entradas, " +
                "SUM(CASE WHEN m.Tipo='S' THEN m.Quantidade ELSE 0 END) AS Saidas, " +
                "SUM(CASE WHEN m.Tipo='E' THEN m.Quantidade ELSE -m.Quantidade END) AS SaldoPeriodo, " +
                "SUM(CASE WHEN m.Tipo='E' THEN m.Quantidade * p.Valor_Unitario ELSE 0 END) AS ValorEntradas, " +
                "SUM(CASE WHEN m.Tipo='S' THEN m.Quantidade * p.Valor_Unitario ELSE 0 END) AS ValorSaidas " +
                "FROM Movimentacao m JOIN Produto p ON p.Id_Produto = m.fk_Produto_Id_Produto " +
                "WHERE m.Data_Movimentacao BETWEEN ? AND ? GROUP BY p.Nome, p.Unidade_Medida ORDER BY p.Nome";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(inicio));
            stmt.setDate(2, Date.valueOf(fim));
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\n--- MOVIMENTAÇÕES NO PERÍODO ---");
                while (rs.next()) System.out.printf("Produto: %s | Unidade: %s | Entradas: %d | Saídas: %d | Saldo: %d | Valor Entradas: R$ %.2f | Valor Saídas: R$ %.2f%n", rs.getString("Produto"), rs.getString("Unidade_Medida"), rs.getInt("Entradas"), rs.getInt("Saidas"), rs.getInt("SaldoPeriodo"), rs.getBigDecimal("ValorEntradas"), rs.getBigDecimal("ValorSaidas"));
            }
        }
    }

    public void maioresSaidasPeriodo(LocalDate inicio, LocalDate fim) throws SQLException {
        String sql = "SELECT p.Nome AS Produto, SUM(m.Quantidade) AS QtdSaida, SUM(m.Quantidade * p.Valor_Unitario) AS ValorSaidas FROM Movimentacao m JOIN Produto p ON p.Id_Produto = m.fk_Produto_Id_Produto WHERE m.Tipo = 'S' AND m.Data_Movimentacao BETWEEN ? AND ? GROUP BY p.Nome ORDER BY QtdSaida DESC";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(inicio));
            stmt.setDate(2, Date.valueOf(fim));
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\n--- PRODUTOS COM MAIOR VOLUME DE SAÍDA ---");
                while (rs.next()) System.out.printf("Produto: %s | Quantidade total de saída: %d | Valor saídas: R$ %.2f%n", rs.getString("Produto"), rs.getInt("QtdSaida"), rs.getBigDecimal("ValorSaidas"));
            }
        }
    }

    public void produtosLimites() throws SQLException {
        String sql = "SELECT Nome, Quantidade, Estoque_Minimo, Estoque_Maximo, (Quantidade / Estoque_Maximo) * 100 AS Percentual FROM Produto WHERE Quantidade <= Estoque_Minimo OR Quantidade >= Estoque_Maximo ORDER BY Nome";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- PRODUTOS EM LIMITE MÍNIMO OU MÁXIMO ---");
            while (rs.next()) System.out.printf("Produto: %s | Qtd: %d | Mín: %d | Máx: %d | Nível: %.2f%%%n", rs.getString("Nome"), rs.getInt("Quantidade"), rs.getInt("Estoque_Minimo"), rs.getInt("Estoque_Maximo"), rs.getDouble("Percentual"));
        }
    }
}
