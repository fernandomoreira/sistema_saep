package dao;

import conexao.Conexao;
import model.Produto;

import java.math.BigDecimal;
import java.sql.*;

public class ProdutoDAO {
    public void cadastrar(Produto produto) throws SQLException {
        validarProduto(produto);
        if (!categoriaExiste(produto.getCategoriaId())) {
            throw new IllegalArgumentException("Categoria inexistente.");
        }

        String sql = "INSERT INTO Produto (Nome, Unidade_Medida, Quantidade, Valor_Unitario, Estoque_Minimo, Estoque_Maximo, fk_Categoria_Id_Categoria) VALUES (?, ?, ?, ?, 0, 100, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getUnidadeMedida());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setBigDecimal(4, produto.getValorUnitario());
            stmt.setInt(5, produto.getCategoriaId());
            stmt.executeUpdate();
        }
    }

    public void listarTodos() throws SQLException {
        String sql = "SELECT p.Id_Produto, p.Nome, p.Unidade_Medida, p.Quantidade, p.Valor_Unitario, c.Nome AS Categoria FROM Produto p JOIN Categoria c ON c.Id_Categoria = p.fk_Categoria_Id_Categoria ORDER BY p.Nome";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n--- PRODUTOS CADASTRADOS ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Produto: %s | Unidade: %s | Qtd: %d | Valor: R$ %.2f | Categoria: %s%n",
                        rs.getInt("Id_Produto"), rs.getString("Nome"), rs.getString("Unidade_Medida"),
                        rs.getInt("Quantidade"), rs.getBigDecimal("Valor_Unitario"), rs.getString("Categoria"));
            }
        }
    }

    public boolean categoriaExiste(int idCategoria) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Categoria WHERE Id_Categoria = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) throw new IllegalArgumentException("Nome obrigatório.");
        if (produto.getUnidadeMedida() == null || produto.getUnidadeMedida().isBlank()) throw new IllegalArgumentException("Unidade de medida obrigatória.");
        if (produto.getQuantidade() < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        if (produto.getValorUnitario() == null || produto.getValorUnitario().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor unitário deve ser maior que zero.");
        if (produto.getCategoriaId() <= 0) throw new IllegalArgumentException("Categoria inválida.");
    }
}
