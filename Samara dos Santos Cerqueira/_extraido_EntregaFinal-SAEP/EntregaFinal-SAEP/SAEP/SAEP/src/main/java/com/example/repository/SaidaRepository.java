package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Saida;

public interface SaidaRepository
        extends JpaRepository<Saida, Integer> {

    @Query("""
            SELECT s
            FROM Saida s
            ORDER BY s.dataSaida DESC
            """)
    List<Saida> listarSaidas();

    @Query("""
            SELECT
                s.produto.nome,
                s.produto.unidadeMedida,
                SUM(s.quantidade),
                SUM(s.quantidade * s.produto.valorUnitario)
            FROM Saida s
            WHERE s.dataSaida
            BETWEEN :inicio AND :fim
            GROUP BY
                s.produto.nome,
                s.produto.unidadeMedida
            ORDER BY s.produto.nome
            """)
    List<Object[]> movimentacaoSaidas(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

    @Query("""
            SELECT
                s.produto.nome,
                SUM(s.quantidade),
                SUM(s.quantidade * s.produto.valorUnitario)
            FROM Saida s
            WHERE s.dataSaida
            BETWEEN :inicio AND :fim
            GROUP BY s.produto.nome
            ORDER BY SUM(s.quantidade) DESC
            """)
    List<Object[]> produtosMaisVendidos(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

}