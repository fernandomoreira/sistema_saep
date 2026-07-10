package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Produto;
import com.example.model.Saida;
import com.example.repository.ProdutoRepository;
import com.example.repository.SaidaRepository;

@Service
public class SaidaService {

    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

   

    public Saida registrarSaida(Saida saida) {

        Produto produto = produtoRepository.findById(
                saida.getProduto().getId())
                .orElseThrow(() ->
                new RuntimeException("Produto não encontrado."));

        if (saida.getQuantidade() <= 0) {

            throw new RuntimeException(
                    "Quantidade inválida.");

        }

        if (produto.getQuantidade()
                < saida.getQuantidade()) {

            throw new RuntimeException(
                    "Estoque insuficiente.");

        }

        produto.setQuantidade(

                produto.getQuantidade()
                - saida.getQuantidade()

        );

        produtoRepository.save(produto);

        return saidaRepository.save(saida);

    }



    public List<Saida> listarSaidas() {

        return saidaRepository.listarSaidas();

    }

   

    public List<Object[]> movimentacaoSaidas(
            LocalDate inicio,
            LocalDate fim) {

        return saidaRepository.movimentacaoSaidas(
                inicio,
                fim);

    }

    

    public List<Object[]> produtosMaisVendidos(
            LocalDate inicio,
            LocalDate fim) {

        return saidaRepository.produtosMaisVendidos(
                inicio,
                fim);

    }

}