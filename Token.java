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
public interface Token {
    
    int getX();
    
    int getY();
    
    void setLocation(int x, int y);
    
  
    String getName();
    
    String getLocation();

    
}
