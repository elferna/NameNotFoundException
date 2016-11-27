/**
 * Manages all game sessions. This class
 * creates/destroys games sessions and
 * adds/removes players from game session.
 * 
 * @author Amanda
 *
 */

import java.util.HashSet;
import java.util.Set;
import javax.websocket.Session;

public class GameSessionManager {

	private Set<GameSession> gameSessions = new HashSet<>();
	private Set<Session> websocketSessions = new HashSet<>(); 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
