package br.com.example.provasaeb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Entrada")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_entrada;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private Integer quantidade;
    private Double valor_unitario;
    private LocalDateTime data_inicial;

    // Getters e Setters
    public Long getId_entrada() { return id_entrada; }
    public void setId_entrada(Long id_entrada) { this.id_entrada = id_entrada; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getValor_unitario() { return valor_unitario; }
    public void setValor_unitario(Double valor_unitario) { this.valor_unitario = valor_unitario; }
    public LocalDateTime getData_inicial() { return data_inicial; }
    public void setData_inicial(LocalDateTime data_inicial) { this.data_inicial = data_inicial; }
}