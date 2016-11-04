/**
 *
 * @author Zac
 */
public class Solution {
	
    private Character character;   
    private Weapon weapon;
    private RoomCard roomCard;
    
    /**
     * Solution constructor
     * @param character
     * @param weapon
     * @param roomCard
     */
    public Solution(Character character, Weapon weapon, RoomCard roomCard ){
        this.character = character;
        this.weapon = weapon;
        this.roomCard = roomCard;        
    }
    
    /**
     * Character getter
     * @return the Character variable value
     */
    public Character getCharacter(){
        return this.character;
    }
    
    /**
     * Weapon getter
     * @return the Weapon variable value
     */
    public Weapon getWeapon(){
        return this.weapon;
    }
    
    /**
     * RoomCard getter
     * @return the RoomCard variable value
     */
    public RoomCard getRoomCard(){
        return this.roomCard;       
    }
}
