
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public interface TokenInterface {
    
    
    int getX();
    
    int getY();
    
    void setLocation(int x, int y);
    
  
    String getName();
    
    String getLocation();
    
    BufferedImage getImage();

    
}
