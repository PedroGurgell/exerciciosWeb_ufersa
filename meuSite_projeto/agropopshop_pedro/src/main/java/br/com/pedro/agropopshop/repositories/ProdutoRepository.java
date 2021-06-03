package br.com.pedro.agropopshop.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedro.agropopshop.model.Produto;


@Repository
@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    /*
    @Query("select p from Produto p where p.nome like %?1%")
    List<Produto> findProdutoByName(String nome);
	*/

    List<Produto> findByNomeContains(String nome);

}
