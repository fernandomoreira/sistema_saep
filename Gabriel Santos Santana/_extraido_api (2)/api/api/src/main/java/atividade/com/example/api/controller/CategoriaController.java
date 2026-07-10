package atividade.com.example.api.controller;

import atividade.com.example.api.model.Categoria;
import atividade.com.example.api.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository repo;

    public CategoriaController(CategoriaRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Categoria salvar(@RequestBody Categoria c) {
        return repo.save(c);
    }

    @GetMapping
    public List<Categoria> listar() {
        return repo.findAll();
    }
}