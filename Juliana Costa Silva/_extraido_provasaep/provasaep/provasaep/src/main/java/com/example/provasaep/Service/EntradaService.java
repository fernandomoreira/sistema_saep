package com.example.provasaep.Service;

import com.example.provasaep.model.Entrada;
import com.example.provasaep.model.Produto;
import com.example.provasaep.repository.EntradaRepository;
import com.example.provasaep.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final ProdutoRepository produtoRepository;

    public EntradaService(EntradaRepository entradaRepository,
                          ProdutoRepository produtoRepository) {

        this.entradaRepository = entradaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Entrada> listarTodos() {
        return entradaRepository.findAll();
    }

    public Entrada salvar(Entrada entrada) {

        Produto produto = entrada.getProduto();

        produto.setQuantidade(
                produto.getQuantidade() + entrada.getQuantidade_entrada()
        );

        produtoRepository.save(produto);

        return entradaRepository.save(entrada);
    }

    public void excluir(Integer id) {
        entradaRepository.deleteById(id);
    }

}