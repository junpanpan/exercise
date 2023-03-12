package com.exercise.service;

import com.exercise.database.model.Player;
import com.exercise.database.model.Tournament;
import com.exercise.database.repository.PlayerRepository;
import com.exercise.exceptions.ValidationException;
import com.exercise.services.PlayerService;
import com.exercise.services.TournamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private TournamentService tournamentService;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService service;

    @BeforeEach
    void init() {

    }

    @Test
    void testAdd() {

        Tournament tour = new Tournament();
        tour.setId(2);
        when(tournamentService.findById(2)).thenReturn(tour);

        Player player = new Player("p");
        when(playerRepository.save(player)).thenReturn(player);

        Player actual = service.addNew(2, player);
        assertEquals(player, actual);
        assertEquals(2, actual.getTournament().getId());
    }

    @Test
    //todo
    void testDelete() {

    }
}
