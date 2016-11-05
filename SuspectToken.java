/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue;

/**
 *
 * @author Zac
 */
public class SuspectToken implements Token {
    
    public static enum Name{SCARLET, MUSTARD, WHITE, GREEN, PEACOCK, PLUM};
    Name name;
    int x;
    int y;
    GameBoard gb;
        
    public SuspectToken(GameBoard _gb, Name n, int _x, int _y) {
        this.x = _x;
        this.y=_y;
        this.name = n;
        this.gb = _gb;
    }
    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public String getLocation(){
        Room current = this.gb.location[this.x][this.y];  
        return current.getName();        
    }   
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public String getName(){
        switch(name){
            default:
                return this.name.toString();
            case SCARLET:
                return "Miss Scarlet";
            case MUSTARD:
                return "Col. Mustard";
            case WHITE:
                return "Mrs. White";
            case GREEN:
                return "Mr. Green";
            case PEACOCK:
                return "Mrs. Peacock";
            case PLUM:
                return "Prof. Plum";
        }
            
    }
    
}
