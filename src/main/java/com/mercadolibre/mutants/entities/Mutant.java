package com.mercadolibre.mutants.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Mutant {
    private Long id;
    private String[] bases;
    private int mutantTokens = 0;  //Este atributo representa la cantidad de cadenas mutantes que tiene la secuencia de ADN

    public void Augment() {
        this.mutantTokens++;
    }
}
