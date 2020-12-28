/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Trial;
import java.util.List;

/**
 *
 * @author nacer
 */
public interface GuessNumberDao {
    
    // Create a game
    Game createGame(Game game);
    
    // Create a Try
    Trial createTrial(Trial trial);
    
    // Get all games
    List<Game> getAllGames();
    
    // Get all trials
    List<Trial> getAllTrials();
    
    // Get a specific game
    Game findGameById(int id);
    
    // Get list of rounds for a game
    List<Trial> findTrialsByGameId(int id);
    
    // Update game
    boolean updateGame(Game game);
    
    // Delete game
    boolean deleteGameById(int gameId);
    
    // Delete trial
    boolean deleteTrialById(int trialId);
}
