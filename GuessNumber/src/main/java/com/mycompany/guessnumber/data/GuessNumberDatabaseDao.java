/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Trial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nacer
 */
@Repository
public class GuessNumberDatabaseDao implements GuessNumberDao {

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GuessNumberDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Game createGame(Game game) {
        final String sql = "INSERT INTO Game (MysteryNumber, Finished) VALUES (?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getMysteryNumber());
            statement.setBoolean(2, game.isFinished());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public Trial createTrial(Trial trial) {
        final String sql = "INSERT INTO Trial (GameId, PlayerNumber, Exact, Partial, TryDate) VALUES (?,?,?,?,NOW());";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, trial.getGameId());
            statement.setString(2, trial.getPlayerNumber());
            statement.setInt(3, trial.getExact());
            statement.setInt(4, trial.getPartial());
            return statement;

        }, keyHolder);

        trial.setId(keyHolder.getKey().intValue());

        return trial;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT GameId, MysteryNumber, Finished FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findGameById(int id) {
        final String sql = "SELECT GameId, MysteryNumber, Finished "
                + "FROM Game WHERE GameId = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public List<Trial> findTrialsByGameId(int id) {
        final String sql = "SELECT TrialId, GameId, PlayerNumber, Exact, Partial, TryDate "
                + "FROM Trial WHERE GameId = ? "
                + "ORDER BY TryDate;";

        return jdbcTemplate.query(sql, new TrialMapper(), id);
    }

    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE Game SET "
                + "MysteryNumber = ?, "
                + "Finished = ? "
                + "WHERE GameId = ?;";

        return jdbcTemplate.update(sql,
                game.getMysteryNumber(),
                game.isFinished(),
                game.getId()) > 0;
    }

    @Override
    public boolean deleteGameById(int gameId) {
        final String sql = "DELETE FROM Game WHERE GameId = ?;";
        return jdbcTemplate.update(sql, gameId) > 0;
    }

    @Override
    public boolean deleteTrialById(int trialId) {
        final String sql = "DELETE FROM Game WHERE TrialId = ?;";
        return jdbcTemplate.update(sql, trialId) > 0;
    }
    
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("GameId"));
            game.setMysteryNumber(rs.getString("mysteryNumber"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
    
    private static final class TrialMapper implements RowMapper<Trial> {

        @Override
        public Trial mapRow(ResultSet rs, int index) throws SQLException {
            Trial trial = new Trial();
            trial.setId(rs.getInt("TrialId"));
            trial.setGameId(rs.getInt("GameId"));
            trial.setPlayerNumber(rs.getString("playerNumber"));
            trial.setExact(rs.getInt("exact"));
            trial.setPartial(rs.getInt("partial"));
            trial.setTrialDate(rs.getString("tryDate"));
            return trial;
        }
    }
    
}
