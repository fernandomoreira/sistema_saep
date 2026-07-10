package com.example.controle_almoxarifado.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vw_estoque")
public class VwEstoque {

    @Id
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;

    private Integer quantidade;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "valor_total_item")
    private BigDecimal valorTotalItem;

    @Column(name = "usuario_responsavel")
    private String usuarioResponsavel;

    public VwEstoque() {}

    public Long getIdProduto() { return idProduto; }
    public String getNomeProduto() { return nomeProduto; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public BigDecimal getValorTotalItem() { return valorTotalItem; }
    public String getUsuarioResponsavel() { return usuarioResponsavel; }
}