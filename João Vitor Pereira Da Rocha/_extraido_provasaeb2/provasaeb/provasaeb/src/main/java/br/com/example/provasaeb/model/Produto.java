package br.com.example.provasaeb.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;


@Entity
@Table(name = "Produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produto;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A unidade de medida é obrigatória")
    private String unidademedida; // mapeado como unidade_medida no banco devido ao padrão camelCase do JPA

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantidade;

    @NotNull(message = "O valor unitário é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor unitário deve ser maior que zero")
    private Double valor_unitario;

    @NotNull(message = "A categoria é obrigatória")
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    // Getters e Setters
    public Long getId_produto() { return id_produto; }
    public void setId_produto(Long id_produto) { this.id_produto = id_produto; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getUnidade_medida() { return unidademedida; }
    public void setUnidade_medida(String unidade_medida) { this.unidademedida = java.util.Objects.toString(unidade_medida, "Unidade"); }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getValor_unitario() { return valor_unitario; }
    public void setValor_unitario(Double valor_unitario) { this.valor_unitario = valor_unitario; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}