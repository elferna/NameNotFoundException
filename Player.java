/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Player implements PlayerInterface{
	
    private ArrayList<Card> hand;
    private String name;
    private boolean isTurn;
    private ArrayList<Card> toShow;
    private Room currentRoom;
    private Character person;
  
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
        return this.currentRoom.isInHallway();
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
     * Not sure what this does
     */
    public void emptyToShow(){
        this.toShow.clear();
    }
    
    /**
     * Not sure what this does
     * @param c
     */
    public void addToShow(Card c){
        this.toShow.add(c);
    }

  
    /**
	 * Print the action of a player??? Confirm this comment
	 */
	public void displayPopUp(Solution guess) {
		System.out.println("A Player has guessed" + guess.getCharacter().toString()+ "did it" +
				"with a " + guess.getWeapon().toString() + "in the " + guess.getRoomCard().toString());
		
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
