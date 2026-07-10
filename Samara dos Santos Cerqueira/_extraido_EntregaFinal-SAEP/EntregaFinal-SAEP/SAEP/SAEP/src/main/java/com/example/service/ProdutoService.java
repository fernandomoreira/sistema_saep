package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Produto;
import com.example.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;



    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Integer id) {

        return produtoRepository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Produto não encontrado."));

    }

    public Produto salvar(Produto produto) {

        return produtoRepository.save(produto);

    }

    public Produto atualizar(Integer id, Produto produto) {

        buscarPorId(id);

        produto.setId(id);

        return produtoRepository.save(produto);

    }

    public void excluir(Integer id) {

        buscarPorId(id);

        produtoRepository.deleteById(id);

    }

    public List<Object[]> valorTotalPorCategoria() {

        return produtoRepository.valorTotalPorCategoria();

    }

    public List<Produto> listarProdutos() {

        return produtoRepository.listarProdutos();

    }

    public List<Object[]> estoqueLimite() {

        return produtoRepository.estoqueLimite();

    }

}