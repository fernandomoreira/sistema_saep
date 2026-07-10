package com.example.controle_almoxarifado.repository;

import com.example.controle_almoxarifado.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}