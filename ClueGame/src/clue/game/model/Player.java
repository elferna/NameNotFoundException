package clue.game.model;

import java.util.ArrayList;
import javax.swing.*;
import javax.websocket.Session;


/**
 * Represents a player once they successfully
 * establish a websocket connection
 * to the server.
 * 
 * @author Amanda
 *
 */

public class Player {

	private String id;
	private ArrayList<Card> hand;
	private ArrayList<String>turnHistory ;
	private String playerName;
	private Card suspect;
	private String suspectName;
    private boolean isTurn;
    private boolean hasMadeAccusation;
    private Room currentRoom;
    private SuspectToken token;
	
  
  /**
   * Constructor for creating a Player  
   * @param name
   * @param session
   */
    public Player(String name, Session session) {
    	this.playerName = name;
    	this.id = session.getId();
		this.isTurn = false;
		this.hand = new ArrayList<Card>();
		this.hasMadeAccusation = false;
		this.turnHistory = new ArrayList<String>();
    }
    
    /**
     * Gets the session id for the player.
	 * @return the sessionId
	 */
	public String getId() {
		return this.id;
	}
	
	public ArrayList<String> getTurnHistory() {
		return turnHistory;
	}

	public void setTurnHistory(ArrayList<String> turnHistory) {
		this.turnHistory = turnHistory;
	}

	public boolean hasMadeAccusation() {
		return this.hasMadeAccusation;
	}
	
	public void sethasMadeAccusation(boolean madeAccusation) {
		this.hasMadeAccusation = madeAccusation;
	}
	
	public boolean isTurn(){
		return this.isTurn;
	}
	
	public void setIsTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
    public void addCard(Card card){
        this.hand.add(card);
    }
    
	public void setPlayerCharacter(Card suspect){
		this.suspect = suspect; 
	}
	
	// Overloading to make things easier...
	public void setPlayerCharacter(String suspectName) {
		this.suspectName = suspectName;
	}
	
	public Card getPlayerCharacter(){
		return this.suspect;
	}
	
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the currentRoom
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * @param currentRoom the currentRoom to set
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	/**
	 * @return the token
	 */
	public SuspectToken getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(SuspectToken token) {
		this.token = token;
	}

	public void displayPopUp(String temp) {
		//JOptionPane.showMessageDialog(null,temp.toString());
		//JOptionPane.showMessageDialog(null, "Consider Yourself notifed its " + temp + "turn.\n");
		System.out.println(temp);
	}
	
	public Solution sendSolution(Solution guess) {
		return guess;
	}

}	
	
