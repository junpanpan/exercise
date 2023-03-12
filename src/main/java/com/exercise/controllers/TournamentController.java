package com.exercise.controllers;

import com.exercise.database.model.Player;
import com.exercise.database.model.Tournament;
import com.exercise.services.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Operation(summary = "find all tournaments")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Tournament>> findAll() {
        List<Tournament> all = tournamentService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @Operation(summary = "find tournament by its id")
    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tournament> findById(@PathVariable(name = "id") int id) {
        Tournament found = tournamentService.findById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @Operation(summary = "add a new tournament")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tournament> add(@RequestBody Tournament tournament) {
        Tournament added = tournamentService.add(tournament);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @Operation(summary = "update tournament by its id")
    @PutMapping(path="/{id}")
    public ResponseEntity<Tournament> update(@RequestBody Tournament newTournament, @PathVariable(name = "id") int id) {
        Tournament updated = tournamentService.update(newTournament, id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @Operation(summary = "delete tournament by its id")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id){
        tournamentService.deleteById(id);
        return new ResponseEntity<>("Success", HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "find all player for certain tournament.")
    @GetMapping(path = "/{id}/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Player>> findAll(@PathVariable int id) {
        List<Player> allPlayers = tournamentService.findAllPlayersById(id);
        return new ResponseEntity<>(allPlayers, HttpStatus.OK);
    }
}
