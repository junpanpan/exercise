package com.exercise.controller;

import com.exercise.controllers.TournamentController;
import com.exercise.database.model.Tournament;
import com.exercise.services.TournamentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TournamentControllerTest {

    private static final Tournament t1 = new Tournament("t1", 200);
    private static final Tournament t2 = new Tournament("t2", 500);

    @Mock
    private TournamentService tournamentService;

    @InjectMocks
    private TournamentController controller;

    @Test
    void testFindAll() {
        when(tournamentService.findAll()).thenReturn(Arrays.asList(t1, t2));

        ResponseEntity<List<Tournament>> actual = controller.findAll();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(Arrays.asList(t1, t2), actual.getBody());
    }

    @Test
    void testFindById() {
        when(tournamentService.findById(2)).thenReturn(t1);

        ResponseEntity<Tournament> actual = controller.findById(2);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(t1, actual.getBody());
    }

    @Test
    void testAdd() {
        when(tournamentService.add(t1)).thenReturn(t1);

        ResponseEntity<Tournament> actual = controller.add(t1);

        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
        assertEquals(t1, actual.getBody());
    }

    @Test
    void testDelete() {
        ResponseEntity<String> actual = controller.delete(3);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        assertEquals("Success", actual.getBody());
    }
}
