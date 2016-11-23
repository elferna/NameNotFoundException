/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Zac
 */
public class Card {
    
    private String name;
    private BufferedImage img = null;
    
    /**
     * Card constructor
     * @param _name
     * @param fileName
     */
    public Card(String name, String fileName){
        this.name = name;
        try {
            this.img = ImageIO.read(new File(fileName));
        }        
        catch (IOException e) {
        }
    }
    
    /**
     * Name getter
     * @return the name variable value
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Img getter
     * @return
     */
    public BufferedImage getImage(){
        return this.img;
    }  
    
    @Override
    public String toString(){
    	return this.name;
    }
}
