package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.detector.MutantDetector;
import com.mercadolibre.mutants.entities.Mutant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantService {
    public boolean isMutant(Mutant mutant) throws Exception {
        try {
            MutantDetector md = new MutantDetector();
            boolean isMutant = md.dnaAnalyze(mutant);
            mutant.setMutant(isMutant);
            return mutant.isMutant();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
