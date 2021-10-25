package com.mercadolibre.mutants.stats;

import com.mercadolibre.mutants.entities.Mutant;

import java.util.List;

public class StatsExperto {
    public static Stats statsService(List<Mutant> mutantList) {
        Stats stats = new Stats();
        double countHuman = mutantList.size();
        double countMutant = 0.0;
        double mutantRatio = 0.0;
        for (Mutant m : mutantList) {
            if (m.getIsMutant()) {
                countMutant++;
            }
        }
        mutantRatio = countMutant / countHuman;
        stats.setCount_human_dna(countHuman);
        stats.setCount_mutant_dna(countMutant);
        stats.setMutantRatio(mutantRatio);
        return stats;
    }
}
