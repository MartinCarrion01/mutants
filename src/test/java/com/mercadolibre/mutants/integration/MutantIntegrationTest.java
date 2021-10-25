package com.mercadolibre.mutants.integration;

import com.mercadolibre.mutants.MutantsApplication;
import com.mercadolibre.mutants.services.MutantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MutantsApplication.class)
@AutoConfigureMockMvc
public class MutantIntegrationTest {

    @Autowired
    private MutantService mutantService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testMutantsSuccessful() throws Exception {
        mockMvc.perform(post("/api/v1/mutant")
                        .content("{\"dna\": [\"TAAGAAAGG\",\"TAACGAGGG\",\"CCTATCCCT\",\"CAGAGCTGT\",\"GATTCTCGA\",\"CTGAAGCTG\",\"ACTTAACAT\",\"CCTTGCACG\",\"TGGGGGCAG\"]}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void testMutantsFailure() throws Exception {
        mockMvc.perform(post("/api/v1/mutant")
                        .content("{\"dna\": [\"AGCT\", \"TTAT\", \"GGCC\", \"ATTA\"]}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
    
}
