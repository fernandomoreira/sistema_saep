package com.example.provasaep.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ENTRADA")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_entrada;

    private LocalDate data_inicial;

    private Integer quantidade_entrada;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUTO_id_produto")
    private Produto produto;

    public Entrada() {
    }

    public Integer getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(Integer id_entrada) {
        this.id_entrada = id_entrada;
    }

    public LocalDate getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(LocalDate data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Integer getQuantidade_entrada() {
        return quantidade_entrada;
    }

    public void setQuantidade_entrada(Integer quantidade_entrada) {
        this.quantidade_entrada = quantidade_entrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}