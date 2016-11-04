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
    
    /**
     * RoomCard constructor
     * @param name
     * @param fileName
     */
    public RoomCard(String name, String fileName){
        super(name, fileName);
        this.type = "ROOM";
    }
}
