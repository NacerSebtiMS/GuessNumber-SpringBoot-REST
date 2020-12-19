/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.service;

import com.mycompany.guessnumber.data.GuessNumberDao;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Guess;
import com.mycompany.guessnumber.models.Trial;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacer
 */
@Service
public class GuessNumberServiceLayer implements GuessNumberServiceLayerInterface {
    private final GuessNumberDao dao;
    
    public GuessNumberServiceLayer(GuessNumberDao dao){
        this.dao = dao;
    }

    /*
    generateRandomNumber:
        Input :
            int length, length of the number to return.
        Output :
            int number, Random number with no duplicate digits
    */
    @Override
    public String generateRandomNumber(int length) {
        ArrayList<Integer> digits = new ArrayList<>();
        int digit;
        String temp = "";
        for(int i=0; i<length;i++){
            digit = this.rndNoDupes(0, 9, digits);
            digits.add(digit);
            temp += digit + "";
        }
        return temp;
    }
    
    // Returns a number between min and max included
    private int rnd(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt(max-min+1);     
    }
    
    // Returns a random number between min and max excluded those in given list
    private int rndNoDupes(int min, int max, ArrayList<Integer> L){
        int randomNumber; 
        do{
            randomNumber = rnd(min,max);
        }while( L.contains(randomNumber) );
        return randomNumber;
    }
    
    
    /*
    exactPositions
        Input :
            int gameId, ID of current game.
            int playerNumber, the guess of the player for the current try
        Output :
            int exact, Number of digits at the exact position
    */

    @Override
    public int exactPositions(int gameId, String playerNumber) {
        String mysteryNumber = this.noHideFindGameById(gameId).getMysteryNumber();
        int exact = 0;
        for(int i=0; i<mysteryNumber.length(); i++){
            if(mysteryNumber.charAt(i) == playerNumber.charAt(i)){
                exact+=1;
            }
        }
        return exact;
    }

    /*
    partialPositions
        Input :
            int gameId, ID of current game.
            int playerNumber, the guess of the player for the current try
        Output :
            int partial, Number of digits at the partial position
    */
    @Override
    public int partialPositions(int gameId, String playerNumber) {
        String mysteryNumber = this.noHideFindGameById(gameId).getMysteryNumber();
        int partial = 0;
        for(int i=0; i<mysteryNumber.length(); i++){
            for(int j=0; j<mysteryNumber.length();j++){
                if( i != j && mysteryNumber.charAt(i) == playerNumber.charAt(j) ){
                    partial+=1;
                }
            }
        }
        return partial;
    }
    
    /*
    isInputCorrect
        Input :
            int playerNumber, the guess of the player for the current try
        Output :
            boolean isCorrect, true if the number that the player inputs is correct, false if not.
    */
    @Override
    public boolean isInputCorrect(String playerNumber) {
        return playerNumber.length()==4 && noDupesInString(playerNumber);
    }
    
    private boolean noDupesInString(String input){
        for(int i=0; i<input.length()-1; i++){
            for(int j=i+1; j<input.length(); j++){
                if(input.charAt(i) == input.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    /*
        DAO call methods
    */
    @Override
    public Game createGame() {
        Game game = new Game();
        game.setFinished(false);
        game.setMysteryNumber( this.generateRandomNumber(4) );
        dao.createGame(game);
        game.setMysteryNumber("????");
        return game;
    }

    @Override
    public Trial createTrial(Guess guess) {
        Game game = this.noHideFindGameById( guess.getGameId() );
        if( this.isInputCorrect(guess.getNumber()) && !game.isFinished()){
            Trial trial = new Trial();
            trial.setGameId(guess.getGameId());
            trial.setExact( this.exactPositions(guess.getGameId(),guess.getNumber()) );
            if(trial.getExact() == 4){
                this.winGame(game);
            }
            trial.setPartial( this.partialPositions(guess.getGameId(),guess.getNumber()) );
            trial.setPlayerNumber( guess.getNumber() );
            return dao.createTrial(trial);
        }
        return null;       
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = dao.getAllGames();
        for(Game game: games){
            if(!game.isFinished()){
                game.setMysteryNumber("????");
            }
        }
        return games;
    }

    @Override
    public Game findGameById(int id) {
        Game game = dao.findGameById(id);
        if(!game.isFinished()){
            game.setMysteryNumber("????");
        }
        return game;
    }
    
    private Game noHideFindGameById(int id){
        return dao.findGameById(id);
    }

    @Override
    public List<Trial> getTrialsByGameId(int id) {
        return dao.findTrialsByGameId(id);
    }

    @Override
    public boolean winGame(Game game) {
        game.setFinished(true);
        return dao.updateGame(game);
    }

    
    
}
