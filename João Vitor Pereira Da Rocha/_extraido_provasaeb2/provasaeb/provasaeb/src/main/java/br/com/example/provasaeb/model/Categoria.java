package br.com.example.provasaeb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;
    private String nome;

    // Getters e Setters
    public Long getId_categoria() { return id_categoria; }
    public void setId_categoria(Long id_categoria) { this.id_categoria = id_categoria; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}