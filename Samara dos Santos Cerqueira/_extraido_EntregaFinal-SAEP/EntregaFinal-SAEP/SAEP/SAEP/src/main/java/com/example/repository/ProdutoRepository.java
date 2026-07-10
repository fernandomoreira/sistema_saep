package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("""
            SELECT
                p.categoria,
                SUM(p.quantidade * p.valorUnitario)
            FROM Produto p
            GROUP BY p.categoria
            ORDER BY p.categoria
            """)
    List<Object[]> valorTotalPorCategoria();

    @Query("""
            SELECT p
            FROM Produto p
            ORDER BY p.nome
            """)
    List<Produto> listarProdutos();

    @Query("""
            SELECT
                p.nome,
                p.quantidade,
                CASE
                    WHEN p.quantidade <= 0 THEN 'MINIMO'
                    WHEN p.quantidade >= 100 THEN 'MAXIMO'
                END,
                (p.quantidade * 100.0 / 100)
            FROM Produto p
            WHERE p.quantidade <= 0
               OR p.quantidade >= 100
            ORDER BY p.nome
            """)
    List<Object[]> estoqueLimite();

}