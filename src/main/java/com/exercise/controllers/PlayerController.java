package com.exercise.controllers;

import com.exercise.database.model.Player;
import com.exercise.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "add a new player to certain tournament.")
    @PostMapping(path="/tournament/{tourId}/player", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> add(@RequestBody Player player, @PathVariable(name = "tourId") int tourId) {
        Player added = playerService.addNew(tourId, player);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @Operation(summary = "delete player by its id")
    @DeleteMapping(path="/player/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        playerService.deleteById(id);
        return new ResponseEntity<>("Success", HttpStatus.NO_CONTENT);
    }
}
