/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public class WeaponCard extends Card{
    
    String type = "";
    Weapons weapon;
    
    /**
     * Weapon constructor
     * @param name
     * @param fileName
     */
    public WeaponCard(Weapons w, String fileName) {
        super(w.toString(), fileName);
        this.type = "WEAPON";
        weapon = w;
    }
    
    public Weapons getWeapon(){
        return this.weapon;
    }
    
    
    
}
