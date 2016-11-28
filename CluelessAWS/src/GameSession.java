/**
 * Represents a game session.
 * 
 * @author Amanda
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSession {

	private String id;
	private boolean isActive;
	private List<Player> players = new ArrayList<Player>();
	
	public GameSession() {
		this.id = generateId();
		this.isActive = false;
	}
	
	public GameSession(List<Player> players) {
		super();
		this.id = generateId();
		this.players = players;
		this.isActive = false;
	}

	public void addPlayer(Player player) {
		// If there are less than 6 players in the
		// game session and the player is not already
		// in the session, add the player
		if(players.size() < 6 && !players.contains(player))
		players.add(player);
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * Generates a 15-character sequence of letters 
	 * and numbers to represent an id for the game session.
	 * 
	 * @return id
	 */
	private String generateId() {
		String resultId = null;
		Random coinToss = new Random();
		Random chooseLetter = new Random();
		Random chooseNum = new Random();
		
		String alphabet[] = {"a", "b", "c", "d", "e", "f", "g",
		                     "h", "i", "j", "k", "l", "m", "n",
		                     "o", "p", "q", "r", "s", "t", "u",
		                     "v", "w", "x", "y", "z"};
		String numbers[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		
		for(int i=0; i < 15; i++) {
			
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
