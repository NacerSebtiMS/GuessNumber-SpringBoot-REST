/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.service;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Guess;
import com.mycompany.guessnumber.models.Trial;
import java.util.List;

/**
 *
 * @author nacer
 */
public interface GuessNumberServiceLayerInterface {
    // Game rules methods
    public String generateRandomNumber(int length);
    public int exactPositions(int gameId, String playerNumber);
    public int partialPositions(int gameId, String playerNumber);
    public boolean isInputCorrect(String playerNumber);
    // Bridge methods for calling dao
    public Game createGame();
    public Trial createTrial(Guess guess);
    public List<Game> getAllGames();
    public Game findGameById(int id);
    public List<Trial> getTrialsByGameId(int id);
    public boolean winGame(Game game);
}
