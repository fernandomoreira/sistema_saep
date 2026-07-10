package com.example.controle_almoxarifado.repository;

import com.example.controle_almoxarifado.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}