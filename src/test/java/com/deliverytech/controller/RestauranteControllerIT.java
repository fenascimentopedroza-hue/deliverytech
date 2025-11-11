package com.deliverytech.controller;

import com.deliverytech.DeliveryTechApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DeliveryTechApplication.class)
@AutoConfigureMockMvc
public class RestauranteControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarRestaurantes_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/restaurantes"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pizza Express")));
    }
}
