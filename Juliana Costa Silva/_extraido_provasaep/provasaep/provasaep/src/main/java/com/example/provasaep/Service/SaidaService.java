package com.example.provasaep.Service;

import com.example.provasaep.model.Produto;
import com.example.provasaep.model.Saida;
import com.example.provasaep.repository.ProdutoRepository;
import com.example.provasaep.repository.SaidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidaService {

    private final SaidaRepository saidaRepository;
    private final ProdutoRepository produtoRepository;

    public SaidaService(SaidaRepository saidaRepository,
                        ProdutoRepository produtoRepository) {

        this.saidaRepository = saidaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Saida> listarTodos() {
        return saidaRepository.findAll();
    }

    public Saida salvar(Saida saida) {

        Produto produto = saida.getProduto();

        if (produto.getQuantidade() < saida.getQuantidade_saida()) {
            throw new RuntimeException("Estoque insuficiente.");
        }

        produto.setQuantidade(
                produto.getQuantidade() - saida.getQuantidade_saida()
        );

        produtoRepository.save(produto);

        return saidaRepository.save(saida);
    }

    public void excluir(Integer id) {
        saidaRepository.deleteById(id);
    }

}