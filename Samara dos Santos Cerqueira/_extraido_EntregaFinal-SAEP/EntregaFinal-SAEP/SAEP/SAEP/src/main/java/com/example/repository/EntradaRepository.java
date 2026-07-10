package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Entrada;



public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

    @Query("""
            SELECT e
            FROM Entrada e
            WHERE e.dataEntrada BETWEEN :inicio AND :fim
            ORDER BY e.dataEntrada DESC
            """)
    List<Entrada> buscarPorPeriodo(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

}

