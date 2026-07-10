package com.example.provasaep.controller;

import com.example.provasaep.Service.SaidaService;
import com.example.provasaep.model.Saida;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saidas")
public class SaidaController {

    private final SaidaService saidaService;

    public SaidaController(SaidaService saidaService) {
        this.saidaService = saidaService;
    }

    @GetMapping
    public List<Saida> listarTodos() {
        return saidaService.listarTodos();
    }

    @PostMapping
    public Saida salvar(@RequestBody Saida saida) {
        return saidaService.salvar(saida);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        saidaService.excluir(id);
    }

}