package br.com.example.provasaeb.controller;

import br.com.example.provasaeb.model.Categoria;
import br.com.example.provasaeb.model.Entrada;
import br.com.example.provasaeb.model.Produto;

import br.com.example.provasaeb.repository.ProdutoRepository;
import br.com.example.provasaeb.repository.SaidaRepository;
import br.com.example.provasaeb.repository.EntradaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api") // Mapeamento base para os endpoints
public class AlmoxarifadoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto) {
        Produto novo = produtoRepository.save(produto);
        return ResponseEntity.ok(novo);
    }

    @GetMapping("/saidas")
    public List<br.com.example.provasaeb.repository.Saida> listarSaidasDecrescente() {
        return saidaRepository.findAllByOrderByDataFinalDesc();
    }

    @PostMapping("/entradas")
    @Transactional
    public ResponseEntity<Entrada> registrarEntrada(@Valid @RequestBody Entrada entrada) {
        // Busca o produto mandado na entrada para atualizar o estoque
        Produto produto = produtoRepository.findById(entrada.getProduto().getId_produto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Soma a quantidade que chegou ao estoque atual do produto
        produto.setQuantidade(produto.getQuantidade() + entrada.getQuantidade());
        produtoRepository.save(produto);

        // Configura a data atual na entrada e salva
        entrada.setProduto(produto);
        entrada.setData_inicial(LocalDateTime.now());
        Entrada novaEntrada = entradaRepository.save(entrada);

        return ResponseEntity.ok(novaEntrada);
    }
}