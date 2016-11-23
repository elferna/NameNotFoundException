/**
 *
 * @author Zac
 */
public class Solution {
	
    private Card suspect;
	private SuspectCard character;   
    private WeaponCard weapon;
    private RoomCard roomCard;
    
    /**
     * Solution constructor
     * @param character
     * @param weapon
     * @param roomCard
     */
    public Solution(SuspectCard character, WeaponCard weapon, RoomCard roomCard ){
        this.character = character;
        this.weapon = weapon;
        this.roomCard = roomCard;        
    }
    
    /**
     * Character getter
     * @return the Character variable value
     */
    public SuspectCard getCharacterCard(){
        return this.character;
    }
    
    /**
     * Weapon getter
     * @return the Weapon variable value
     */
    public WeaponCard getWeaponCard(){
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
