package com.mercadolibre.mutants.repositories;

import com.mercadolibre.mutants.entities.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {
}
