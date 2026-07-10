package atividade.com.example.api.repository;

import atividade.com.example.api.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByTipoOrderByDataMovimentacaoDesc(String tipo);
}