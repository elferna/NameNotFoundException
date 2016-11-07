/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public enum Weapons {
    ROPE, LEADPIPE, KNIFE, WRENCH, CANDLESTICK, REVOLVER;
    
    public String toString(){
      
        switch(this){    
            default:
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
