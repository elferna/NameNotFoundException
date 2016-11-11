/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public class RoomCard extends Card{
    
    private String type = "";
    Rooms room;
    
    /**
     * RoomCard constructor
     * @param name
     * @param fileName
     */
    public RoomCard(Rooms r, String fileName){
        super(r.toString(), fileName);
        this.type = "ROOM";
        this.room = r;
        
    }
    
    public Rooms getRoom(){
        return this.room;
    }
}
