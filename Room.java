/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;


public class Room {
	
    private ArrayList<Player> players;
    private boolean isInHallway;
    private boolean hasSecretPassage;
    private int xPos;
    private int yPos;  
    private String name;

    /**
     * Room constructor
     * @param name
     * @param xPos
     * @param yPos
     * @param isHallway
     * @param hasSecretPassage
     */
    public Room(String name, int xPos, int yPos, boolean isInHallway, boolean hasSecretPassage){
        players = new ArrayList<Player>();
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.isInHallway = isInHallway;
        this.hasSecretPassage = hasSecretPassage;
    }
    
    /**
     * Name getter
     * @return name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * hasSecretPassage getter
     * @return hasSecretPassage
     */
    public boolean hasSecretPassage(){
        return this.hasSecretPassage;
    }
    
    /**
     * xPos getter
     * @return xPos
     */
    public int getXPos(){
        return this.xPos;
    }
    
    /**
     * yPos getter
     * @return yPos
     */
    public int getYPos(){
        return this.yPos;
    }
    
    /**
     * isHallwway getter
     * @return isHallway
     */
    public boolean isInHallway(){
        return this.isInHallway;
    }
    
    /**
     * Verify if room is blocked
     * @return true if room is blocked, false otherwise
     */
    public boolean isBlocked(){
        if(this.isInHallway()){
            if(this.players.size() > 0){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
    	return this.name;
    }

}
