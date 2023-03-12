package com.exercise.controller;

import com.exercise.controllers.PlayerController;
import com.exercise.database.model.Player;
import com.exercise.database.model.Tournament;
import com.exercise.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
class PlayerControllerIT extends AbstractTest {

    private static final Player player = new Player("p1");
    private static final Tournament t1 = new Tournament("t1", 200);

    @MockBean
    private PlayerService playerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAdd() throws Exception {
        player.setTournament(t1);
        when(playerService.addNew(eq(2), any())).thenReturn(player);

        mockMvc.perform(post("/tournament/2/player")
                .content(toJson(player)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(toJson(player)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/player/2"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("Success"));
    }
}
