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
public class WeaponToken implements Token {
    
    int x;
    int y;
    GameBoard gb;

    public static enum Name{ROPE, LEADPIPE, KNIFE, WRENCH, CANDLESTICK, REVOLVER};
    Name name;

    public WeaponToken(GameBoard _gb, Name n, int _x, int _y){
        this.name = n;
        this.x = _x;
        this.y = _y;
        this.gb = _gb;
    }
    
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String getLocation(){
        Room current = this.gb.location[this.x][this.y];  
        return current.getName();        
    }    
    
    @Override
    public String getName(){
        switch(name){
            default:
                return this.name.toString();
            case ROPE:
                return "Rope";
            case LEADPIPE:
                return "Lead Pipe";
            case KNIFE:
                return "Knife";
            case WRENCH:
                return "Wrench";
            case CANDLESTICK:
                return "Candle Stick";
            case REVOLVER:
                return "Revolver";
                    
        }
    }
    
}
