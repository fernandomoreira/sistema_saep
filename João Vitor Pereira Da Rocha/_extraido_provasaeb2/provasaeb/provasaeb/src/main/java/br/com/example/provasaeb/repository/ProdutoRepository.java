package br.com.example.provasaeb.repository;

import br.com.example.provasaeb.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Regra: valor total por categoria
    @Query(value = "SELECT c.nome as categoria, SUM(p.quantidade * p.valor_unitario) as valorTotal " +
                   "FROM Produto p JOIN Categoria c ON p.id_categoria = c.id_categoria GROUP BY c.nome", nativeQuery = true)
    List<Map<String, Object>> findValorTotalPorCategoria();

    // Regra: Movimentações no período (7 campos obrigatórios usando subqueries para evitar multiplicação de joins)
    @Query(value = "SELECT p.nome AS nomeProduto, p.unidade_medida AS unidadeMedida, " +
                   "COALESCE(ent.total_qtd, 0) AS totalEntradas, COALESCE(sai.total_qtd, 0) AS totalSaidas, " +
                   "(COALESCE(ent.total_qtd, 0) - COALESCE(sai.total_qtd, 0)) AS saldoPeriodo, " +
                   "COALESCE(ent.total_val, 0) AS valorTotalEntradas, " +
                   "COALESCE(sai.total_qtd * p.valor_unitario, 0) AS valorTotalSaidas " +
                   "FROM Produto p " +
                   "LEFT JOIN (SELECT id_produto, SUM(quantidade) AS total_qtd, SUM(quantidade * valor_unitario) AS total_val FROM Entrada WHERE data_inicial BETWEEN :inicio AND :fim GROUP BY id_produto) ent ON p.id_produto = ent.id_produto " +
                   "LEFT JOIN (SELECT id_produto, SUM(quantidade) AS total_qtd FROM Saida WHERE data_final BETWEEN :inicio AND :fim GROUP BY id_produto) sai ON p.id_produto = sai.id_produto", nativeQuery = true)
    List<MovimentacaoProjection> findMovimentacoesNoPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    // Regra: Maiores saídas no período (3 campos obrigatórios)
    @Query(value = "SELECT p.nome AS nomeProduto, SUM(s.quantidade) AS quantidadeTotalSaida, SUM(s.quantidade * p.valor_unitario) AS valorTotalSaidas " +
                   "FROM Saida s JOIN Produto p ON s.id_produto = p.id_produto " +
                   "WHERE s.data_final BETWEEN :inicio AND :fim GROUP BY p.id_produto, p.nome ORDER BY quantidadeTotalSaida DESC", nativeQuery = true)
    List<MaiorSaidaProjection> findMaioresSaidasNoPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    // Regra: Identificar produtos nos limites mínimos (0) e máximos (100)
    @Query(value = "SELECT * FROM Produto WHERE quantidade = 0 OR quantidade >= 100", nativeQuery = true)
    List<Produto> findProdutosNosLimites();
}