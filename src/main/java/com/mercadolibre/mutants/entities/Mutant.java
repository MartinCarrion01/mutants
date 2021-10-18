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
    private boolean isMutant;
}
