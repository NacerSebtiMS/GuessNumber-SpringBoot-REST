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
public class Game {

    private int id;
    private String mysteryNumber;
    private boolean finished;
    
    public boolean equals(Game other){
        return this.id == other.getId() && this.mysteryNumber.equals(other.getMysteryNumber()) && this.finished == other.isFinished();
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
     * @return the mysteryNumber
     */
    public String getMysteryNumber() {
        return mysteryNumber;
    }

    /**
     * @param mysteryNumber the mysteryNumber to set
     */
    public void setMysteryNumber(String mysteryNumber) {
        this.mysteryNumber = mysteryNumber;
    }

    /**
     * @return the finished
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @param finished the finished to set
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    /**
     * @return the trials
     */

    
}
