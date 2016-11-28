/**
 * Manages all game sessions. This class
 * creates/destroys games sessions and
 * adds/removes players from game session.
 * 
 * Based one example from https://www.youtube.com/watch?v=2xgnSpcAVzE
 * @author Amanda
 *
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
public class GameSessionHandler {

	private Set<GameSession> gameSessions = Collections.synchronizedSet(new HashSet<>());
	private Map<Session, Player> connectedPlayers = Collections.synchronizedMap(new HashMap<>()); 
	
	/**
	 * Create a new instance
	 * of Player, associate the instance with the session
	 * by setting the player's id to the session's id, and
	 * add the association as a key/value pair.
	 * 
	 * @param session
	 */
	public void addPlayer(Session session) {
		connectedPlayers.put(session, new Player(session.getId()));
		System.out.println("PLAYER CREATED :: " +connectedPlayers.get(session.getId()).getSessionId());
	}
	
	public Player getPlayer(String id) {
		return connectedPlayers.get(id);
	}
	
	public void addNewGameSession(Player player) {
		GameSession newGame = new GameSession();
		newGame.addPlayer(player);
		gameSessions.add(newGame);
	}
	
	/**
	 * Adds a player to the first game session
	 * found that has less than 6 players
	 * and hasn't started the round yet
	 * 
	 * @param player
	 */
	public void joinExistingGameSession(Player player) {
		
	}
	
	public void removeGameSession(String gameSessionId) {
		
	}

	/**
	 * Starts a game for the game session
	 * with the id passed in.
	 * @param gameSessionId
	 */
	public void startGame(String gameSessionId) {
		
	}
	
	private JsonObject buildAddSessionMessage(GameSession gameSession) {
		return null;
	}
	
	private void sentToAllConnectedPlayers(JsonObject msg) {
		
	}
	
	private void sentToPlayer(Session session, JsonObject msg) {
		
	}

}
