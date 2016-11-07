/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Zac
 */
public enum Suspects {
    SCARLET, MUSTARD, WHITE, GREEN, PEACOCK, PLUM;
    
    @Override
    public String toString(){
        String name = "";
        switch(this){
            default:
                name = "Miss Scarlet";
            case MUSTARD:
                name =  "Col. Mustard";
            case WHITE:
                name =  "Mrs. White";
            case GREEN:
                name =  "Mr. Green";
            case PEACOCK:
                name = "Mrs. Peacock";
            case PLUM:
                name =  "Prof. Plum";
        }
        return name;
    }
}
