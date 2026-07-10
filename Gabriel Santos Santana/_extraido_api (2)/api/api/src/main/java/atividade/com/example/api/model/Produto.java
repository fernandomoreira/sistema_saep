package atividade.com.example.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String unidadeMedida;
    private Integer quantidade;
    private BigDecimal valorUnitario;

    @ManyToOne
    private Categoria categoria;
}