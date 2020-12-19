/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.controllers;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Guess;
import com.mycompany.guessnumber.models.Trial;
import com.mycompany.guessnumber.service.GuessNumberServiceLayer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nacer
 */
@RestController
@RequestMapping("")
public class GuessNumberController {
    private final GuessNumberServiceLayer service;
    
    public GuessNumberController(GuessNumberServiceLayer service){
        this.service = service;
    }
    /*   
    "begin"
    POST
        - Starts a game
        - Generates an answer
        - Sets the correct status. 
    Should return a 201 CREATED message as well as the created gameId.
    */
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame() {
        return service.createGame();
    }
    
    /*
    "guess"
    POST 
        – Makes a guess by passing the guess and gameId in as JSON.
        - The program must calculate the results of the guess and mark the game finished if the guess is correct.
    It returns the Round object with the results filled in.
    */
    @PostMapping("/guess")
    public ResponseEntity<Trial> guessNumber(@RequestBody Guess guess){
        Trial trial = service.createTrial(guess);
        if( trial == null ){
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(trial);        
    }
    
    /*
    "game"
    GET
        – Returns a list of all games.
    Be sure in-progress games do not display their answer.
    */
    @GetMapping("/game")
    public List<Game> all() {
        return service.getAllGames();
    }
    
    /*
    "game/{gameId}"
    GET 
        – Returns a specific game based on ID.
    Be sure in-progress games do not display their answer.
    */
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> findGameById(@PathVariable int id) {
        Game result = service.findGameById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    /*
    "rounds/{gameId}
        – GET – Returns a list of rounds for the specified game sorted by time.
    */
    @GetMapping("/rounds/{id}")
    public ResponseEntity<List<Trial>> findRoundsByGameId(@PathVariable int id) {
        List<Trial> result = service.getTrialsByGameId(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
}
