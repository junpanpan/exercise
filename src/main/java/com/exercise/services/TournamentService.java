package com.exercise.services;

import com.exercise.database.model.Player;
import com.exercise.database.model.Tournament;
import com.exercise.database.repository.TournamentRepository;
import com.exercise.exceptions.ValidationException;
import com.exercise.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class TournamentService {
    private static final Logger LOG = LoggerFactory.getLogger(TournamentService.class);
    private final static List<String> ALLOWED_CURRENCY = List.of("EUR", "SEK", "USD");
    private final static String DEFAULT_CURR = "EUR";

    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> findAll() {
        LOG.info("to fetch all tournaments.");
        return tournamentRepository.findAll();
    }

    public Tournament findById(final Integer id) {
        LOG.info("to fetch tournament id: " + id);
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament " + id));
    }

    public Tournament add(final Tournament tournament) {

        validate(tournament);

        if (Util.isNullOrBlank(tournament.getCurrency()) ) {
            tournament.setCurrency(DEFAULT_CURR);
        }

        return save(tournament);
    }

    public Tournament update(final Tournament newTournament, final Integer id) {
        return tournamentRepository.findById(id)
                .map(tournament -> {
                    validateAndUpdate(newTournament, tournament);
                    return save(newTournament);
                })
                .orElseThrow(() -> new EntityNotFoundException("Tournament" + id));
    }

    public void deleteById(final Integer id) {
        findById(id);
        tournamentRepository.deleteById(id);
    }

    public List<Player> findAllPlayersById(final Integer id) {
        Tournament found = findById(id);
        return found.getPlayers();
    }

    private void validate(final Tournament newTournament) {

        LOG.info("Validating add new Tournament request..." + newTournament);

        if (!Util.isNullOrBlank(newTournament.getCurrency()) && !ALLOWED_CURRENCY.contains(newTournament.getCurrency())) {
            throw new ValidationException("invalid allowed currency. Allowed are " + ALLOWED_CURRENCY);
        }
    }

    private void validateAndUpdate(final Tournament updatedTournament,
                                   final Tournament currentTournament) {

        LOG.info("Validating update existing Tournament request" + updatedTournament);

        if (!Util.isNullOrBlank(updatedTournament.getCurrency())) {
            if (!ALLOWED_CURRENCY.contains(updatedTournament.getCurrency())) {
                throw new ValidationException("invalid allowed currency.");
            }
            currentTournament.setCurrency(updatedTournament.getCurrency());
        }

        currentTournament.setPrize(updatedTournament.getPrize());
    }

    private Tournament save(final Tournament tournament) {
        try {
            return tournamentRepository.save(tournament);
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            throw new ValidationException("name must be non null and unique.  prize must > 100");
        }
    }
}
