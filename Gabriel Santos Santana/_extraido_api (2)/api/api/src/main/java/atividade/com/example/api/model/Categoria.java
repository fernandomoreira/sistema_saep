package atividade.com.example.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}