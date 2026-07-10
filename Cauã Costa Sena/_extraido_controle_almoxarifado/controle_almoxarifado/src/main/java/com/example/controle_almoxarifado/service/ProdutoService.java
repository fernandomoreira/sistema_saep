package com.example.controle_almoxarifado.service;

import com.example.controle_almoxarifado.model.Produto;
import com.example.controle_almoxarifado.repository.CategoriaRepository;
import com.example.controle_almoxarifado.repository.ProdutoRepository;
import com.example.controle_almoxarifado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired 
    private ProdutoRepository produtoRepository;
    
    @Autowired 
    private CategoriaRepository categoriaRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto cadastrar(Produto produto) {
        if (produto.getValorUnitario() == null || produto.getValorUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Erro: O valor unitário deve ser maior que zero.");
        }
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Erro: A quantidade não pode ser negativa.");
        }
        if (produto.getCategoria() == null || produto.getCategoria().getIdCategoria() == null || 
            !categoriaRepository.existsById(produto.getCategoria().getIdCategoria())) {
            throw new IllegalArgumentException("Erro: Categoria inválida.");
        }
        if (produto.getUsuario() == null || produto.getUsuario().getIdUsuario() == null || 
            !usuarioRepository.existsById(produto.getUsuario().getIdUsuario())) {
            throw new IllegalArgumentException("Erro: Usuário inválido.");
        }

        return produtoRepository.save(produto);
    }
}