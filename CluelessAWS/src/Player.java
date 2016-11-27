import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Represents a player once they successfully
 * establish a websocket connection
 * to the server.
 * 
 * @author Amanda
 *
 */

public class Player {

	private ArrayList<Card> hand;
    private String name;
    private boolean isTurn;
    private ArrayList<Card> toShow;
    private Room currentRoom;
    private Character person;
    private SuspectToken token;
  
    /**
     * Player constructor
     * @param name
     * @param room
     */
    public Player(String name, Room room){
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.currentRoom = room;
    }
    
    /**
     * Update the character
     * @param room
     */
    public void UpdateCharacter(Room room){
    	//Update String and Character
        this.currentRoom.removePlayer(this);
        room.addPlayer(this);
        this.currentRoom = room;
        
        //move token
        this.token.setLocation(room.getXPos(), room.getYPos());
    }
    
    public SuspectToken getToken(){
        return this.token;
    }
    
    public void setToken(SuspectToken t){
        this.token = t;
    }
    
    /**
     * Adding a card to the hand
     * @param card
     */
    public void addCard(Card card){
        hand.add(card);
    }
    
    /**
     * Verify if player is in hallway
     * @return
     */
    public boolean isInHallway(){
        return this.currentRoom.isHallway();
    }
    
    /**
     * Verify if the player has the card
     * @param card
     * @return true if the player has the card, false otherwise
     */
    public boolean hasCard(Card card){
        return this.hand.contains(card);
    }
    
    /**
     * empties the list of possible cards to show after a suggestion
     * is made
     */
    public void emptyToShow(){
        this.toShow.clear();
    }
    
    /**
     * returns a list of cards to show after a suggestion is made
     * @param c
     */
    public void addToShow(Card c){
        this.toShow.add(c);
    }
    
    public ArrayList<Card> getToShow(){
        return this.toShow;
    }

  
    /**
	 * Print the action of a player??? Confirm this comment
	 */
	public void displayPopUp(Solution guess) {
//		System.out.println("A Player has guessed" + guess.getCharacterCard().toString()+ "did it" +
//				"with a " + guess.getWeaponCard().toString() + "in the " + guess.getRoomCard().toString());
//		
	}

	/**
	 * Display the turn of player
	 */
	public void displayTurn(String guesser) {
	    		JOptionPane.showMessageDialog(null, "Consider Yourself notifed its " + guesser + "turn.\n");
		
	}
	
	/**
	 * Name getter
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * currentRoom getter
	 * @return
	 */
	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public boolean isTurn() {
		return this.isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
}
