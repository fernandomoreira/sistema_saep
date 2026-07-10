package com.example.controle_almoxarifado.repository;

import com.example.controle_almoxarifado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}