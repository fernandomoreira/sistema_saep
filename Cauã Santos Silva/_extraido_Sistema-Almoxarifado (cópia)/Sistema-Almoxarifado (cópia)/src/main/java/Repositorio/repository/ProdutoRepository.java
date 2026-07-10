package Repositorio.repository;


import Modelo.model.Produto; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Cuida do acesso ao banco de dados
public interface ProdutoRepository  extends JpaRepository<Produto, Long>{

}
