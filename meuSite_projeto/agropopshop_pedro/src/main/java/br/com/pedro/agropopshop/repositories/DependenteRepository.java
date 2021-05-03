package br.com.pedro.agropopshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedro.agropopshop.model.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {

}
