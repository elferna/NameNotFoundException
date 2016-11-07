/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zac
 */
public class SuspectCard extends Card{
    
   private String type = "";
   private Suspects suspect;
   
   /**
    * Character constructor
     * @param s
    * @param fileName
    */
   public SuspectCard(Suspects s, String fileName){      
       super(s.toString(), fileName);
       this.type = "SUSPECT";  
       this.suspect = s;
   }   
   
   
   public Suspects getSuspect(){
       return this.suspect;
   }
}
