package com.example.provasaep.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "SAIDA")
public class Saida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_saida;

    private LocalDate data_final;

    private Integer quantidade_saida;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUTO_id_produto")
    private Produto produto;

    public Saida() {
    }

    public Integer getId_saida() {
        return id_saida;
    }

    public void setId_saida(Integer id_saida) {
        this.id_saida = id_saida;
    }

    public LocalDate getData_final() {
        return data_final;
    }

    public void setData_final(LocalDate data_final) {
        this.data_final = data_final;
    }

    public Integer getQuantidade_saida() {
        return quantidade_saida;
    }

    public void setQuantidade_saida(Integer quantidade_saida) {
        this.quantidade_saida = quantidade_saida;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}