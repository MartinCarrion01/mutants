package com.mercadolibre.mutants.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mutants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Mutant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Este string contiene el toString de el ADN enviado en la peticion a la api de mutantes
    //Lo usamos para guardar el adn en la base de datos, pero no para el algoritmo de detección
    @Lob
    @Column(name = "dna")
    private String dnaPersist;

    //Este atributo es usado para trabajar la detección del mutante en el adn, no se persiste
    @Transient
    private List<String> dna;

    @Column(name = "isMutant")
    private Boolean isMutant;
}
