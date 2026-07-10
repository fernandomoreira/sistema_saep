package br.com.example.provasaeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaidaRepository extends JpaRepository<Saida, Long> {
    // Regra: Listar todas as saídas em ordem decrescente por data
    List<Saida> findAllByOrderByDataFinalDesc();
}