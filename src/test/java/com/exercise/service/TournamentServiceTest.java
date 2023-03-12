package com.exercise.service;

import com.exercise.database.model.Tournament;
import com.exercise.database.repository.TournamentRepository;
import com.exercise.exceptions.ValidationException;
import com.exercise.services.TournamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    private TournamentRepository repository;

    @InjectMocks
    private TournamentService service;

    @BeforeEach
    void init() {

    }

    @Test
    void testFindAll() {
        service.findAll();
        verify(repository).findAll();
    }

    @Test
    void testFindById() {
        Tournament tour = new Tournament();
        when(repository.findById(1)).thenReturn(Optional.of(tour));

        Tournament actual = service.findById(1);

        assertEquals(tour, actual);
    }

    @Test
    void testAdd() {
        Tournament tournament = new Tournament("t", 100);

        when(repository.save(tournament)).thenReturn(tournament);

        Tournament actual = service.add(tournament);

        assertEquals("t", actual.getName());
        assertEquals(100, actual.getPrize());
        assertEquals("EUR", actual.getCurrency());
    }

    @Test
    void testAdd_InvalidCurrency() {
        Tournament tournament = new Tournament("t", 100, "dd");
        assertThrows(ValidationException.class, () -> service.add(tournament));
    }

    @Test
    //todo
    void testUpdate() {

    }

    @Test
    //todo
    void testDelete() {

    }

    @Test
    //todo
    void testFindAllPlayers() {

    }
}
