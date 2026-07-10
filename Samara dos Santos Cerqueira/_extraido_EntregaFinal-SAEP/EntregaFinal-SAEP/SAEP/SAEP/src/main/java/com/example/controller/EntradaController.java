package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.model.Entrada;
import com.example.service.EntradaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/entradas")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrada registrar(@Valid @RequestBody Entrada entrada){

        return entradaService.registrarEntrada(entrada);

    }

    @GetMapping("/periodo")
    public List<Entrada> listarPeriodo(

            @RequestParam LocalDate inicio,

            @RequestParam LocalDate fim){

        return entradaService.buscarPorPeriodo(inicio, fim);

    }

}