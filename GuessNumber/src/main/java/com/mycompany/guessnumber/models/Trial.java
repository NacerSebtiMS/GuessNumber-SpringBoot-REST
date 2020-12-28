/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.models;

/**
 *
 * @author nacer
 */
public class Trial {
   
    private int id;
    private int gameId;
    private String playerNumber;
    private int exact;
    private int partial;
    private String trialDate;
    
    public boolean equals(Trial other){
        return this.id == other.getId() && this.gameId == other.getGameId() && this.playerNumber.equals(other.getPlayerNumber()) && this.exact== other.getExact() && this.partial == other.getPartial() && this.trialDate == other.getTrialDate();
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    /**
     * @return the playerNumber
     */
    public String getPlayerNumber() {
        return playerNumber;
    }

    /**
     * @param playerNumber the playerNumber to set
     */
    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * @return the exact
     */
    public int getExact() {
        return exact;
    }

    /**
     * @param exact the exact to set
     */
    public void setExact(int exact) {
        this.exact = exact;
    }

    /**
     * @return the partial
     */
    public int getPartial() {
        return partial;
    }

    /**
     * @param partial the partial to set
     */
    public void setPartial(int partial) {
        this.partial = partial;
    }
    
        /**
     * @return the tryDate
     */
    public String getTrialDate() {
        return trialDate;
    }

    /**
     * @param tryDate the tryDate to set
     */
    public void setTrialDate(String trialDate) {
        this.trialDate = trialDate;
    }

}
