package br.com.pedro.agropopshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedro.agropopshop.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

		
}
