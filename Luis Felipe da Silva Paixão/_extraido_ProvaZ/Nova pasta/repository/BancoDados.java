package repository;

import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Movimentacao;
import model.Produto;

public class BancoDados {
    public static final List<Categoria> categorias = new ArrayList<>();
    public static final List<Produto> produtos = new ArrayList<>();
    public static final List<Movimentacao> movimentacoes = new ArrayList<>();
}
