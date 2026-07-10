package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Entrada;
import com.example.model.Produto;
import com.example.repository.EntradaRepository;
import com.example.repository.ProdutoRepository;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public Entrada registrarEntrada(Entrada entrada) {

        Produto produto = produtoRepository.findById(
                entrada.getProduto().getId())
                .orElseThrow(() ->
                new RuntimeException("Produto não encontrado."));

        produto.setQuantidade(

                produto.getQuantidade()
                + entrada.getQuantidade()

        );

        produtoRepository.save(produto);

        return entradaRepository.save(entrada);

    }


    public List<Entrada> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorPeriodo'");
    }

}