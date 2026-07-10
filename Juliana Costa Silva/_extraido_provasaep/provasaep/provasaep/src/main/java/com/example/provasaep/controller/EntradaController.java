package com.example.provasaep.controller;

import com.example.provasaep.Service.EntradaService;
import com.example.provasaep.model.Entrada;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradaController {

    private final EntradaService entradaService;

    public EntradaController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping
    public List<Entrada> listarTodos() {
        return entradaService.listarTodos();
    }

    @PostMapping
    public Entrada salvar(@RequestBody Entrada entrada) {
        return entradaService.salvar(entrada);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        entradaService.excluir(id);
    }

}