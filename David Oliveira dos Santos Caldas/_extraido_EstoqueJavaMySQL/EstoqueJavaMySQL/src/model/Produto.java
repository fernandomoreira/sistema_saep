package model;

import java.math.BigDecimal;

public class Produto {
    private int id;
    private String nome;
    private String unidadeMedida;
    private int quantidade;
    private BigDecimal valorUnitario;
    private int categoriaId;

    public Produto(String nome, String unidadeMedida, int quantidade, BigDecimal valorUnitario, int categoriaId) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.categoriaId = categoriaId;
    }

    public Produto(int id, String nome, String unidadeMedida, int quantidade, BigDecimal valorUnitario, int categoriaId) {
        this(nome, unidadeMedida, quantidade, valorUnitario, categoriaId);
        this.id = id;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public int getQuantidade() { return quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public int getCategoriaId() { return categoriaId; }
}
