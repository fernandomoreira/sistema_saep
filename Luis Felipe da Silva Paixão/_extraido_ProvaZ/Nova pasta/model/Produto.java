package model;

public class Produto {
    private String nome;
    private String unidadeMedida;
    private double valorUnitario;
    private int quantidade;
    private Categoria categoria;

    public Produto(String nome, String unidadeMedida, double valorUnitario, int quantidade, Categoria categoria) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public String getNome() { return nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public double getValorUnitario() { return valorUnitario; }
    public int getQuantidade() { return quantidade; }
    public Categoria getCategoria() { return categoria; }
    
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    // Calcula a porcentagem baseada no limite máximo de 100
    public double getNivelEstoque() {
        return (this.quantidade / 100.0) * 100.0;
    }
}
