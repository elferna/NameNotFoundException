/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public class Weapon extends Card{
    
    String type = "";
    
    /**
     * Weapon constructor
     * @param name
     * @param fileName
     */
    public Weapon(String name, String fileName) {
        super(name, fileName);
        this.type = "WEAPON";
    }
    
}
