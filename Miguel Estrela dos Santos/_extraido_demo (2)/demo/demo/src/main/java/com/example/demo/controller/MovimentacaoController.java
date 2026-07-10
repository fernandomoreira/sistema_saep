package com.example.demo.controller;

import com.example.demo.model.Movimentacao;
import com.example.demo.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService service;

    @GetMapping
    public List<Movimentacao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Movimentacao buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Movimentacao salvar(@RequestBody Movimentacao movimentacao) {
        return service.salvar(movimentacao);
    }

    @PutMapping("/{id}")
    public Movimentacao atualizar(@PathVariable Long id, @RequestBody Movimentacao movimentacao) {
        movimentacao.setIdMovimentacao(id);
        return service.salvar(movimentacao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}