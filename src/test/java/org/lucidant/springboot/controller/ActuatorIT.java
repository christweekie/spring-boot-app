package org.lucidant.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ActuatorIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenHealthEndpoint_whenCall_thenReturns200() throws Exception {
        mockMvc.perform(get("/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"))
            .andExpect(jsonPath("$.components").isNotEmpty())
            .andExpect(jsonPath("$.components.ping.status").value("UP"))
        ;
    }

    @Test
    void givenInfoEndpoint_whenCall_thenReturns200() throws Exception {
        mockMvc.perform(get("/info"))
            .andExpect(status().isOk())
        ;
    }
}
