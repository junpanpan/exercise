package com.exercise.controller;

import com.exercise.controllers.TournamentController;
import com.exercise.database.model.Player;
import com.exercise.database.model.Tournament;
import com.exercise.services.TournamentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TournamentController.class)
class TournamentControllerIT extends AbstractTest {

    private static final Tournament t1 = new Tournament("t1", 200);
    private static final Tournament t2 = new Tournament("t2", 500);

    @MockBean
    private TournamentService tournamentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindAll() throws Exception {
        when(tournamentService.findAll()).thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/tournaments"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(toJson(Arrays.asList(t1, t2))));
    }

    @Test
    void testNotFind() throws Exception {
        when(tournamentService.findById(2)).thenThrow(new EntityNotFoundException(" 2"));

        mockMvc.perform(get("/tournaments/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testAdd() throws Exception {
        when(tournamentService.add(any())).thenReturn(t2);

        mockMvc.perform(post("/tournaments")
                .content(toJson(t2)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(toJson(t2)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/tournaments/2"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("Success"));
    }

    @Test
    void testGetAllPlayers() throws Exception {

        Player player_1 = new Player("p1");
        player_1.setTournament(t1);
        Player player_2 = new Player("p2");
        player_2.setTournament(t1);

        when(tournamentService.findAllPlayersById(2)).thenReturn(Arrays.asList(player_1, player_2));

        mockMvc.perform(get("/tournaments/2/players").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(toJson(Arrays.asList(player_1, player_2))));
    }
}
