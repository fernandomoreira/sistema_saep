package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Saida;
import com.example.service.SaidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/saidas")
public class SaidaController {

    @Autowired
    private SaidaService saidaService;

    @PostMapping
    public Saida registrar(@Valid @RequestBody Saida saida) {

        return saidaService.registrarSaida(saida);

    }

    @GetMapping
    public List<Saida> listar() {

        return saidaService.listarSaidas();

    }

    @GetMapping("/periodo")
    public List<Object[]> periodo(

            @RequestParam LocalDate inicio,

            @RequestParam LocalDate fim) {

        return saidaService.movimentacaoSaidas(inicio, fim);

    }

    @GetMapping("/mais-vendidos")
    public List<Object[]> maisVendidos(

            @RequestParam LocalDate inicio,

            @RequestParam LocalDate fim) {

        return saidaService.produtosMaisVendidos(inicio, fim);

    }

}