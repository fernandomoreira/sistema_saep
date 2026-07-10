package servico.service;

import Modelo.model.Produto;
import Repositorio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // 1. Classe que cuidará das Regras de Negócio
public class ProdutoService {

    @Autowired // 2. Injeção de Dependência:Conectar com o repositorio
    private ProdutoRepository produtoRepository;

    // REGRA DE NEGÓCIO: Cadastrar produto com as validações Exigidas
    public Produto cadastrarProduto(Produto produto) {
        // Validação de Categoria
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória!");
        }
        
        // Validação de Quantidade (não pode ser negativa)
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a zero!");
        }
        
        // Validação de Valor Unitário (deve ser maior que zero)
        if (produto.getValorUnitario() == null || produto.getValorUnitario() <= 0) {
            throw new IllegalArgumentException("O valor unitário deve ser maior que zero!");
        }

        // Se passar por todas as validações, o repositório salva no MySQL
        return produtoRepository.save(produto);
    }

    // REGRA DE NEGÓCIO: Listar todos os produtos cadastrados
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
}