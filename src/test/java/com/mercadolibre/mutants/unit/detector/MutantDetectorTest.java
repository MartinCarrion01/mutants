package com.mercadolibre.mutants.unit.detector;

import com.mercadolibre.mutants.detector.MutantDetector;
import com.mercadolibre.mutants.entities.Mutant;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MutantDetectorTest {

    private MutantDetector mutantDetector = new MutantDetector();
    private Mutant testMutant = new Mutant();

    //Se testea adn dado en la consigna
    @Test
    @Order(1)
    void testConsigna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        testMutant.setBases(dna);
        assertTrue(mutantDetector.dnaAnalyze(testMutant));
    }

    //Ejemplo de dna no mutante
    @Test
    @Order(2)
    void testNoMutante() {
        String[] dna = {"AGCT", "TTAT", "GGCC", "ATTA"};
        testMutant.setBases(dna);
        assertFalse(mutantDetector.dnaAnalyze(testMutant));
    }

    //Ejemplo de cadenas de mutante en diagonales inversas
    @Test
    @Order(3)
    void testDiagonalInversa() {
        String[] dna = {"AGCTTG", "ACGTAG", "CCTAAA", "ATAGCC", "CAAAGG", "GTAGGT"};
        testMutant.setBases(dna);
        assertTrue(mutantDetector.dnaAnalyze(testMutant));
    }

    //Ejemplo extra
    @Test
    @Order(4)
    void testExtra1() {
        String[] dna = {"AAAATG", "ACGTAG", "CCTAAA", "ATAGCC", "CAAAGG", "GTAGGT"};
        testMutant.setBases(dna);
        assertTrue(mutantDetector.dnaAnalyze(testMutant));
    }

    //Ejemplo extra
    @Test
    @Order(5)
    void testExtra2() {
        String[] dna = {"TAAGAAAGG", "TAACGAGGG", "CCTATCCCT", "CAGAGCTGT", "GATTCTCGA", "CTGAAGCTG", "ACTTAACAT", "CCTTGCACG", "TGGGGGCAG"};
        testMutant.setBases(dna);
        assertTrue(mutantDetector.dnaAnalyze(testMutant));
    }
}
