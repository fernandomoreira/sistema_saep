package atividade.com.example.api.controller;

import atividade.com.example.api.model.*;
import atividade.com.example.api.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepo;
    private final CategoriaRepository categoriaRepo;

    public ProdutoController(ProdutoRepository p, CategoriaRepository c) {
        this.produtoRepo = p;
        this.categoriaRepo = c;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto p) {
        return produtoRepo.save(p);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoRepo.findAll();
    }
}