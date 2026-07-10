package br.com.example.provasaeb.repository;

import br.com.example.provasaeb.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}