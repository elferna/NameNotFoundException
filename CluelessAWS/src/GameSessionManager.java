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

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/gameSessionManager", decoders = {GameMessageDecoder.class}, encoders = {GameMessageEncoder.class})
public class GameSessionManager {

	private Set<GameSession> gameSessions = Collections.synchronizedSet(new HashSet<>());
	private Map<Session, Player> connectedPlayers = Collections.synchronizedMap(new HashMap<>()); 
	
	/**
	 * When a connection is opened, create a new instance
	 * of Player, associate the instance with the session
	 * by setting the player's id to the session's id, and
	 * add the association as a key/value pair.
	 * 
	 * @param session - The websocket session
	 */
	@OnOpen
	public void onOpen(Session session) {
		connectedPlayers.put(session, new Player(session.getId()));
	}
	
	/**
	 * When a connection is closed, mark the player instance
	 * associated with the session for garbage collection.
	 *  
	 * @param session - The websocket session
	 */
	@OnClose
	public void onClose(Session session) {
		connectedPlayers.get(session.getId()).destroy();
	}
	// onMessage - When server receives a message from
	// the client, check the message type. If the message
	// a game request: if player sends a request to start a game, spawn a new session
	// and add the player. If player sends a request to join
	// an existing game, add the player to the first session found
	// to have less than 3 players connected
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
