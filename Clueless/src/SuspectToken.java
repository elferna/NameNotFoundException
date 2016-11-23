
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public class SuspectToken implements TokenInterface {

    
    
    //public static enum Name{SCARLET, MUSTARD, WHITE, GREEN, PEACOCK, PLUM};
    Suspects name;
    int x;
    int y;
    GameBoard gb;
    Player player = null;
    
    //token image? 
    private BufferedImage img = null;
        
    public SuspectToken(GameBoard _gb, Suspects n, int _x, int _y, String fileName) {
        this.x = _x;
        this.y=_y;
        this.name = n;
        this.gb = _gb;
        try {
            this.img = ImageIO.read(new File(fileName));
        }        
        catch (IOException e) {
        }
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public void setPlayer(Player p){
        this.player = p;
    }
    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public String getLocation(){
        Room current = this.gb.getRoom(this.x,this.y);  
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
    
    @Override
    public BufferedImage getImage() {
        return this.img;
    }
    
}
