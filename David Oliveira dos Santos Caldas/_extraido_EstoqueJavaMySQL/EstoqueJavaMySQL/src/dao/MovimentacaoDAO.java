package dao;

import conexao.Conexao;

import java.sql.*;
import java.time.LocalDate;

public class MovimentacaoDAO {
    public void registrarEntrada(int idProduto, int quantidade, LocalDate data, int idUsuario) throws SQLException {
        registrarMovimentacao('E', idProduto, quantidade, data, idUsuario);
    }

    public void registrarSaida(int idProduto, int quantidade, LocalDate data, int idUsuario) throws SQLException {
        registrarMovimentacao('S', idProduto, quantidade, data, idUsuario);
    }

    private void registrarMovimentacao(char tipo, int idProduto, int quantidade, LocalDate data, int idUsuario) throws SQLException {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        if (data == null) throw new IllegalArgumentException("Data obrigatória.");

        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);
            try {
                int estoqueAtual = buscarEstoque(conn, idProduto);
                int novoEstoque = tipo == 'E' ? estoqueAtual + quantidade : estoqueAtual - quantidade;
                if (novoEstoque < 0) throw new IllegalArgumentException("Estoque insuficiente para saída.");
                if (novoEstoque > 100) throw new IllegalArgumentException("A movimentação ultrapassa o estoque máximo de 100 unidades.");

                try (PreparedStatement upd = conn.prepareStatement("UPDATE Produto SET Quantidade = ? WHERE Id_Produto = ?")) {
                    upd.setInt(1, novoEstoque);
                    upd.setInt(2, idProduto);
                    upd.executeUpdate();
                }

                try (PreparedStatement ins = conn.prepareStatement("INSERT INTO Movimentacao (Tipo, Quantidade, Data_Movimentacao, fk_Produto_Id_Produto, fk_Usuario_Id_Usuario) VALUES (?, ?, ?, ?, ?)")) {
                    ins.setString(1, String.valueOf(tipo));
                    ins.setInt(2, quantidade);
                    ins.setDate(3, Date.valueOf(data));
                    ins.setInt(4, idProduto);
                    ins.setInt(5, idUsuario);
                    ins.executeUpdate();
                }
                conn.commit();
                System.out.println("Movimentação registrada e saldo atualizado.");
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    private int buscarEstoque(Connection conn, int idProduto) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT Quantidade FROM Produto WHERE Id_Produto = ? FOR UPDATE")) {
            stmt.setInt(1, idProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) throw new IllegalArgumentException("Produto inexistente.");
                return rs.getInt("Quantidade");
            }
        }
    }
}
