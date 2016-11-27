/**
 * Represents a room on the game board.
 */

import java.util.ArrayList;


public class Room {
	
    private ArrayList<Player> players;
    private boolean isHallway;
    private boolean hasSecretPassage;
    private int xPos;
    private int yPos;  
    private String name;
    
//    private Rooms room;
//
//    /**
//     * Room constructor
//     * @param name
//     * @param xPos
//     * @param yPos
//     * @param isHallway
//     * @param hasSecretPassage
//     */
//    public Room(Rooms r, int xPos, int yPos, boolean isHallway, boolean hasSecretPassage){
//        players = new ArrayList<Player>();
//        this.name = r.toString();
//        this.xPos = xPos;
//        this.yPos = yPos;
//        this.isHallway = isHallway;
//        this.hasSecretPassage = hasSecretPassage;
//        this.room = r;
//    }
//    
//    public Room(String n, int xPos, int yPos, boolean isHallway, boolean hasSecretPassage){
//        players = new ArrayList<Player>();
//        this.name = n;
//        this.xPos = xPos;
//        this.yPos = yPos;
//        this.isHallway = isHallway;
//        this.hasSecretPassage = hasSecretPassage;
//        
//    }
//    
//    /**
//     * Name getter
//     * @return name
//     */
//    public String getName(){
//        return this.name;
//    }
//    
//    /**
//     * hasSecretPassage getter
//     * @return hasSecretPassage
//     */
//    public boolean hasSecretPassage(){
//        return this.hasSecretPassage;
//    }
//    
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
    public boolean isHallway(){
        return this.isHallway;
    }
//    
//    /**
//     * Verify if room is blocked
//     * @return true if room is blocked, false otherwise
//     */
//    public boolean isBlocked(){
//        if(this.isHallway()){
//            if(this.players.size() > 0){
//                return true;
//            }
//        }
//        return false;
//    }
//    
    @Override
    public String toString(){
    	return this.name;
    }
    
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    
    public void addPlayer(Player p){
        this.players.add(p);
    }
    
    public void removePlayer(Player p){
        players.remove(p);
    }

}
