import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents a game card.
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