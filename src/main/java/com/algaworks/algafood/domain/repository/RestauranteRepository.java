package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository 
        extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQuery,
                JpaSpecificationExecutor<Restaurante>{
    // List<Restaurante> listar ();
    // Restaurante buscar(Long id);
    // Restaurante adicionar(Restaurante restaurante);    
    // void remover(Restaurante restaurante);

    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);


    // List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

   
    
    
}
