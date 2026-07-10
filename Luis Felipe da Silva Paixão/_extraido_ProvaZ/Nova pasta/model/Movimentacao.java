package model;

import java.time.LocalDate;

public class Movimentacao {
    private Produto produto;
    private TipoMovimentacao tipo;
    private int quantidade;
    private LocalDate data;
    private double valorFinanceiro;

    public Movimentacao(Produto produto, TipoMovimentacao tipo, int quantidade, LocalDate data) {
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
        this.valorFinanceiro = quantidade * produto.getValorUnitario();
    }

    public Produto getProduto() { return produto; }
    public TipoMovimentacao getTipo() { return tipo; }
    public int getQuantidade() { return quantidade; }
    public LocalDate getData() { return data; }
    public double getValorFinanceiro() { return valorFinanceiro; }
}