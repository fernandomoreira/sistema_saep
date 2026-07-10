package com.example.controle_almoxarifado.repository;

import com.example.controle_almoxarifado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}