package com.exercise.controller;

import com.exercise.controllers.PlayerController;
import com.exercise.database.model.Player;
import com.exercise.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    private final Player player = new Player("p1");

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    void testAdd() {
        when(playerService.addNew(2, player)).thenReturn(player);

        ResponseEntity<Player> actual = playerController.add(player, 2);

        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
        assertEquals(player, actual.getBody());
    }

    @Test
    void testDelete() {

        ResponseEntity<String> actual = playerController.delete(4);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        assertEquals("Success", actual.getBody());
    }

    @Test
    void testDelete_Exception() {

        doThrow(new EntityNotFoundException("3")).when(playerService).deleteById(3);
        assertThrows(EntityNotFoundException.class, () -> playerController.delete(3));
    }
}
