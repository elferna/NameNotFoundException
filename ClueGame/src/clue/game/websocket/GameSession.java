package clue.game.websocket;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a game that a group of players belong to.
 * 
 * @author Amanda
 *
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import clue.game.model.BoardGame;
import clue.game.model.Player;

public class GameSession {

	private final Set<Player> players = Collections.synchronizedSet(new HashSet<>());
	private final int PLAYER_LIMIT  = 6;
	private Integer playerCount = 0;
	private Stack<String> suspectsArray = new Stack<String>();
	private BoardGame boardGame;
	
	/**
	 * Constructor
	 */
	public GameSession() {
				
		this.suspectsArray.push("Mustard");
		this.suspectsArray.push("White");
		this.suspectsArray.push("Green");
		this.suspectsArray.push("Yellow");
		this.suspectsArray.push("Red");	
		
		this.boardGame = new BoardGame(generateBoardGameID());
	}

	public void addPlayer(Player player) {
		
		// If there are less than 6 players in the
		// game session and the player is not already
		// in the session, add the player
		if(players.size() < PLAYER_LIMIT && !players.contains(player)) {
			Player temp = player;
			// Assign the player the next available suspect
			player.setPlayerCharacter(this.suspectsArray.pop());
			
			// Add the player to the game and update the count
			players.add(player);
			boardGame.addPlayer(temp);
			playerCount++;
		} else {
			System.out.println("********************** GAME SESSION IS FULL **********************");
		}
		
	}
	
	public Set<Player> getAllPlayers() {
		return players;
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
	}

	/**
	 * @return the playerCount
	 */
	public Integer getPlayerCount() {
		return playerCount;
	}	
	
	public Player getPlayer(String id) {
		
		Player result = null;
		Iterator<Player> iterator = this.players.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().getId() == id) {
				result = iterator.next();
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Starts the game. Need someplace to call this...
	 */
	@SuppressWarnings("unchecked")
	public void startGame() {
		this.boardGame.initializeGame((ArrayList<Player>)getAllPlayers());
		this.boardGame.play();
	}
	/**
	 * Generates an ID for the game board instance
	 * @return
	 */
	private String generateBoardGameID() {
		String resultId = "";
		Random coinToss = new Random();
		Random chooseLetter = new Random();
		Random chooseNum = new Random();
		
		String alphabet[] = {"a", "b", "c", "d", "e", "f", "g",
		                     "h", "i", "j", "k", "l", "m", "n",
		                     "o", "p", "q", "r", "s", "t", "u",
		                     "v", "w", "x", "y", "z"};
		String numbers[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		
		for(int i=0; i < 20; i++) {
			
			if(coinToss.nextInt(2) == 0) {
				resultId = resultId.concat(alphabet[chooseLetter.nextInt(26)]);
			}
			
			else if (coinToss.nextInt(2) == 1) {
				resultId = resultId.concat(numbers[chooseNum.nextInt(10)]);
			}
		}
		return resultId;
	}
	
}
