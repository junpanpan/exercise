package com.exercise.services;

import com.exercise.database.model.Player;
import com.exercise.database.repository.PlayerRepository;
import com.exercise.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@Service
public class PlayerService {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TournamentService tournamentService;

    public Player addNew(final Integer tourId, final Player newPlayer) {
        LOG.info("to add new player: " + newPlayer + " to tourId: " + tourId);

        newPlayer.setTournament(tournamentService.findById(tourId));

        try {
            return playerRepository.save(newPlayer);
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            throw new ValidationException("player's name must be non null and unique in the same tournament.");
        }
    }

    public void deleteById(final Integer id) {
        try {
            playerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(ex.getMessage());
        }
    }

    private Player findById(final Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player " + id));
    }
}
