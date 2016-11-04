/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public class Character extends Card{
    
   private String type = "";
   
   /**
    * Character constructor
    * @param name
    * @param fileName
    */
   public Character(String name, String fileName){
       super(name, fileName);
       this.type = "CHARACTER";
   }   
}
