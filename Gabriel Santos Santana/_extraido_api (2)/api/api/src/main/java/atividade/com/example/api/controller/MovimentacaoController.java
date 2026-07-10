package atividade.com.example.api.controller;

import atividade.com.example.api.model.*;
import atividade.com.example.api.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    private final MovimentacaoRepository repo;
    private final ProdutoRepository produtoRepo;

    public MovimentacaoController(MovimentacaoRepository r, ProdutoRepository p) {
        this.repo = r;
        this.produtoRepo = p;
    }

    @PostMapping("/entrada/{id}")
    public Movimentacao entrada(@PathVariable Long id, @RequestBody Movimentacao m) {

        Produto p = produtoRepo.findById(id).orElseThrow();

        p.setQuantidade(p.getQuantidade() + m.getQuantidade());

        m.setTipo("ENTRADA");
        m.setProduto(p);
        m.setDataMovimentacao(LocalDate.now());
        m.setValorUnitario(p.getValorUnitario());

        produtoRepo.save(p);
        return repo.save(m);
    }

    @PostMapping("/saida/{id}")
    public Movimentacao saida(@PathVariable Long id, @RequestBody Movimentacao m) {

        Produto p = produtoRepo.findById(id).orElseThrow();

        if (p.getQuantidade() < m.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente");
        }

        p.setQuantidade(p.getQuantidade() - m.getQuantidade());

        m.setTipo("SAIDA");
        m.setProduto(p);
        m.setDataMovimentacao(LocalDate.now());
        m.setValorUnitario(p.getValorUnitario());

        produtoRepo.save(p);
        return repo.save(m);
    }

    @GetMapping("/saida")
    public List<Movimentacao> listarSaida() {
        return repo.findByTipoOrderByDataMovimentacaoDesc("SAIDA");
    }
}