package com.mercadolibre.mutants.controllers;

import com.mercadolibre.mutants.entities.Mutant;
import com.mercadolibre.mutants.services.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/mutant")
public class MutantController {
    private MutantService mutantService;

    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("")
    public ResponseEntity<?> isMutant(@RequestBody Mutant mutant) {
        for (String s : mutant.getBases()) {
            System.out.println(s);
        }
        try {
            if (mutantService.isMutant(mutant)) {
                System.out.println("Es mutante");
                System.out.println(mutant.toString());
                return ResponseEntity.status(HttpStatus.OK).body("");
            } else {
                System.out.println("No es mutante");
                System.out.println(mutant.toString());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde.\"}");
        }
    }
}
