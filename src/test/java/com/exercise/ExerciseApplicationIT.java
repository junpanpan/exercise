package com.exercise;

import com.exercise.database.model.Player;
import com.exercise.database.model.Tournament;
import com.exercise.database.repository.TournamentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExerciseApplicationIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Test
    void addNewTournament() throws Exception {
        final String tourName = "t4";
        Tournament tournament = new Tournament(tourName, 300);
        mockMvc.perform(post("/tournaments")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tournament)))
                .andExpect(status().isCreated());

        Tournament entity = tournamentRepository.findByName(tourName);
        assertEquals(300, entity.getPrize());
    }

    @Test
    void addNewPlayer() throws Exception {
        final String tourName = "t1";
        Tournament tournament = new Tournament(tourName, 200);
        mockMvc.perform(post("/tournaments")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tournament)))
                .andExpect(status().isCreated());

        Tournament entity = tournamentRepository.findByName(tourName);

        final int tourId = entity.getId();

        Player player = new Player("p");
        mockMvc.perform(post("/tournament/" + tourId + "/player")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(player)))
                .andExpect(status().isCreated());
    }

    @Test
    void testHttpClient_Tournament() throws Exception {
        mockMvc.perform(get("/tournaments"))
                .andExpect(status().isOk());
    }

    @Test
    void testHttpClient() {
        webTestClient.get()
                    .uri("/tournaments/100")
                    .exchange()
                    .expectStatus().isNotFound();
    }
}