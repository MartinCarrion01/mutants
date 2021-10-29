package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.detector.MutantDetector;
import com.mercadolibre.mutants.entities.Mutant;
import com.mercadolibre.mutants.repositories.MutantRepository;
import com.mercadolibre.mutants.stats.Stats;
import com.mercadolibre.mutants.stats.StatsExperto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MutantService {
    private MutantRepository mutantRepository;

    public MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    public boolean isMutant(Mutant mutant) throws Exception {
        try {
            MutantDetector md = new MutantDetector();
            boolean isMutant = md.dnaAnalyze(mutant);
            mutant.setIsMutant(isMutant);
            mutant.setDnaPersist(mutant.getDna().toString());
            mutant = mutantRepository.save(mutant);
            return mutant.getIsMutant();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Stats statsService() throws Exception {
        try {
            List<Mutant> mutantList = mutantRepository.findAll();
            Stats stats = StatsExperto.statsService(mutantList);
            return stats;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
