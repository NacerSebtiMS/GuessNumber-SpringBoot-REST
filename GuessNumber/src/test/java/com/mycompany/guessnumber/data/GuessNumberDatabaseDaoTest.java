/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.TestApplicationConfiguration;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Trial;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author nacer
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GuessNumberDatabaseDaoTest {
    
    @Autowired
    GuessNumberDao dao;
    
    public GuessNumberDatabaseDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Game> games = dao.getAllGames();
        for(Game game : games){
            dao.deleteGameById( game.getId() );
        }
        
        List<Trial> trials = dao.getAllTrials();
        for(Trial trial : trials){
            dao.deleteTrialById( trial.getId() );
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createGame method, of class GuessNumberDatabaseDao.
     */
    @Test
    public void testCreateGame() {
        
        Game game = new Game();    
        game.setMysteryNumber("1234");
        game.setFinished(false);
        game = dao.createGame(game);
        
        Game fromDao = dao.findGameById( game.getId() );
        
        assertTrue(game.equals(fromDao));      
    }

    /**
     * Test of createTrial method, of class GuessNumberDatabaseDao.
     */
    @Test
    public void testCreateTrial() {
        
        Game game = new Game();    
        game.setMysteryNumber("1234");
        game.setFinished(false);
        game = dao.createGame(game);
        
        Trial trial = new Trial();    
        trial.setGameId(game.getId());
        trial.setPlayerNumber("4589");
        trial.setExact(0);
        trial.setPartial(2);
        trial = dao.createTrial(trial);
        
        Trial fromDao = dao.findTrialById( trial.getId() );
        
        trial.setTrialDate(fromDao.getTrialDate());
        
        assertTrue(trial.equals(fromDao));  
    }

    /**
     * Test of getAllGames method, of class GuessNumberDatabaseDao.
     */
    @Test
    public void testGetAllGames() {
        
        Game game1 = new Game();    
        game1.setMysteryNumber("1234");
        game1.setFinished(false);
        game1 = dao.createGame(game1);
        
        Game game2 = new Game();    
        game2.setMysteryNumber("5678");
        game2.setFinished(false);
        game2 = dao.createGame(game2);
        
        List<Game> games = dao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue( game1.equals(games.get(0)) || game1.equals(games.get(1)) );
        assertTrue( game2.equals(games.get(0)) || game2.equals(games.get(1)));
    }
    
    /**
     * Test of testGetAllTrials method, of class GuessNumberDatabaseDao.
     */
    @Test
    public void testGetAllTrials(){
        
        Game game = new Game();    
        game.setMysteryNumber("1234");
        game.setFinished(false);
        game = dao.createGame(game);
        
        Trial trial = new Trial();    
        trial.setGameId(game.getId());
        trial.setPlayerNumber("4589");
        trial.setExact(0);
        trial.setPartial(2);
        trial = dao.createTrial(trial);
        
        Trial trial2 = new Trial();    
        trial2.setGameId(game.getId());
        trial2.setPlayerNumber("1234");
        trial2.setExact(3);
        trial2.setPartial(1);
        trial2 = dao.createTrial(trial2);
        
        List<Trial> trials = dao.getAllTrials();
        
        trial.setTrialDate(trials.get(0).getTrialDate());
        trial2.setTrialDate(trials.get(1).getTrialDate());
        
        assertEquals(2, trials.size());
        assertTrue( trial.equals(trials.get(0)) || trial.equals(trials.get(1)) );
        assertTrue( trial2.equals(trials.get(0)) || trial2.equals(trials.get(1)) );
    }

    /**
     * Test of findGameById method, of class GuessNumberDatabaseDao.
     */
    @Test
    public void testFindGameById() {
    }

    /**
     * Test of findTrialsByGameId method, of class GuessNumberDatabaseDao.
     */
    @Test
    public void testFindTrialsByGameId() {
    }

    /**
     * Test of updateGame method, of class GuessNumberDatabaseDao.
     */
    @Test
    public void testUpdateGame() {
        
        Game game = new Game();    
        game.setMysteryNumber("1234");
        game.setFinished(false);
        game = dao.createGame(game);
        
        Game fromDao = dao.findGameById( game.getId() );
        
        assertTrue( game.equals(fromDao) );
        
        game.setFinished(true);
        
        dao.updateGame(game);
        
        assertFalse( game.equals(fromDao) );
        
        fromDao = dao.findGameById( game.getId() );
        
        assertTrue( game.equals(fromDao) );
    }
    
}
