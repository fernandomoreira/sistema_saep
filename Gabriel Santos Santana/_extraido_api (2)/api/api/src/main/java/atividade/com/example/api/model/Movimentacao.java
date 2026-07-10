package atividade.com.example.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // ENTRADA ou SAIDA
    private Integer quantidade;
    private LocalDate dataMovimentacao;
    private BigDecimal valorUnitario;

    @ManyToOne
    private Produto produto;
}