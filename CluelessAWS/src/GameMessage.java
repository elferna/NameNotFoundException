/**
 * A POJO that represents a message
 * sent from the server to the client.
 * 
 * @author Amanda
 *
 */
public class GameMessage {

	private Player player;
	private String request;

	public GameMessage(Player player) {
		super();
		this.player = player;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	
	/**
	 * @return the playerRequest
	 */
	public String getPlayerRequest() {
		return this.request;
	}

	/**
	 * @param playerRequest the playerRequest to set
	 */
	public void setPlayerRequest(String request) {
		this.request = request;
	}
	
}
