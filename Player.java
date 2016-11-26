import java.util.ArrayList;
import javax.swing.*;


public class Player implements PlayerInterface{
    private ArrayList<Card> hand;
    private ArrayList<String>turnHistory ;
	private String playerName;
	private Card Suspect;
	private boolean isTurn;
	private boolean alreadyAccusedASuspect;
	
	public Player(String playerName) {	
		this.isTurn = false;
		this.playerName = playerName;
		this.hand = new ArrayList<Card>();
		this.alreadyAccusedASuspect = false;
		this.turnHistory = new ArrayList<String>();
	}

	public ArrayList<String> getTurnHistory() {
		return turnHistory;
	}

	public void setTurnHistory(ArrayList<String> turnHistory) {
		this.turnHistory = turnHistory;
	}

	public boolean getAlreadyAccusedASuspect() {
		return alreadyAccusedASuspect;
	}
	public void setAlreadyAccusedASuspect(boolean alreadyAccusedASuspect) {
		this.alreadyAccusedASuspect = alreadyAccusedASuspect;
	}
	public boolean getTurn(){
		return this.isTurn;
	}
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
    public void addCard(Card card){
        hand.add(card);
    }
	public void setPlayerCharacter(Card suspect){
		this.Suspect = suspect; 
	}
	public Card getPlayerCharacter(){
		return this.Suspect;
	}


	@Override
	public void displayPopUp(String temp) {
		//JOptionPane.showMessageDialog(null,temp.toString());
		//JOptionPane.showMessageDialog(null, "Consider Yourself notifed its " + temp + "turn.\n");
		System.out.println(temp);
	}
	@Override
	public Solution sendSolution(Solution guess) {
		return guess;
	}
}
