/**
 * The server-side websocket implementation.
 */

import java.io.IOException;
import java.io.StringReader;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

@ApplicationScoped
@ServerEndpoint("/gameSessionEvents")
public class GameSessionWebsocketServer {

	@Inject
	private GameSessionHandler sessionHandler = new GameSessionHandler();
	
	/**
	 * When a connection is opened, add
	 * the player to the list of connected
	 * players.
	 * 
	 * @param session - The websocket session
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("CONNECTION ESTABLISHED :: " +session.isOpen());
		System.out.println("CONNECTION INFO :: " +session.getId());
		sessionHandler.addPlayer(session);
		try {
			session.getBasicRemote().sendText(sessionHandler.getPlayer(session).getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * When a connection is closed, mark the player instance
	 * associated with the session for garbage collection.
	 *  
	 * @param session - The websocket session
	 */
	@OnClose
	public void onClose(Session session) {
		//connectedPlayers.get(session.getId()).destroy();
	}
	
	// onMessage - When server receives a message from
	// the client, check the message type. If the message
	// a game request: if player sends a request to start a game, spawn a new session
	// and add the player. If player sends a request to join
	// an existing game, add the player to the first session found
	// to have less than 3 players connected
//	@OnMessage
//	public void onMessage(GameMessage message, Session session) throws Exception {
//		try {
//			JsonReader reader = Json.createReader(new StringReader(message.toString()));
//			JsonObject jsonMessage = reader.readObject();
//			
//			if(jsonMessage.getString("action").equals("create")) {
//				if(message.getPlayer().getSessionId() == session.getId() && message.getPlayerRequest() == "create") {
//					sessionHandler.addNewGameSession(message.getPlayer());
//				}
//			}
//		
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
	
}
