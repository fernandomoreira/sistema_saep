package com.example.controle_almoxarifado.service;

import com.example.controle_almoxarifado.dto.MovimentacaoDTO;
import com.example.controle_almoxarifado.model.*;
import com.example.controle_almoxarifado.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlmoxarifadoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private VwEstoqueRepository vwEstoqueRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<VwEstoque> obterRelatorioEstoque() {
        return vwEstoqueRepository.findAll();
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Movimentacao registrarMovimentacao(MovimentacaoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if ("Entrada".equalsIgnoreCase(dto.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() + dto.getQuantidade());
        } else if ("Saida".equalsIgnoreCase(dto.getTipo())) {
            if (produto.getQuantidade() < dto.getQuantidade()) {
                throw new RuntimeException("Saldo insuficiente em estoque!");
            }
            produto.setQuantidade(produto.getQuantidade() - dto.getQuantidade());
        } else {
            throw new RuntimeException("Tipo de movimentação inválido!");
        }

        produtoRepository.save(produto);

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setUsuario(usuario);
        mov.setQuantidade(dto.getQuantidade());
        mov.setTipo(dto.getTipo());
        mov.setData(LocalDate.now());

        return movimentacaoRepository.save(mov);
    }
}