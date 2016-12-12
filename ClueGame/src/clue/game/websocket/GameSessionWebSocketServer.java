package clue.game.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.example.model.Device; 
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/gameActions")
public class GameSessionWebSocketServer {
	
	@Inject
	private GameSessionHandler sessionHandler;
	
	@OnOpen
    public void open(Session session) {
		System.out.println("Connection requested : " + session.getId());
		sessionHandler.addSession(session);
	}

	@OnClose
    public void close(Session session) {
		System.out.println("Connection closing : " + session.getId());
		sessionHandler.removeSession(session);
	}

	@OnError
    public void onError(Throwable error) {
		Logger.getLogger(GameSessionWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
	}

	@OnMessage
    public void handleMessage(String message, Session session) {
		 try (JsonReader reader = Json.createReader(new StringReader(message))) {
	            JsonObject jsonMessage = reader.readObject();
	            
	            //If the JSON message is of type chat, do this
	            //It uses the sessionHandler.broadcastMsg to send the message from the chat
	            if ("chat".equals(jsonMessage.getString("type"))) {
	                sessionHandler.broadcastMsg(session, jsonMessage.getString("message"));
	            }
	            
	            //Starts the game when "Start Game" button is clicked
	            if ("start".equals(jsonMessage.getString("type"))) {
	            	//TODO: Instead of "type" we could also use "action"
	            	//This depends on how the javascript code is formatted
	            }
	            
	            if ("move".equals(jsonMessage.getString("type"))) {
	            	//TODO
	            }

	            //If the JSON message is of type suggestion, 
	            if ("suggestion".equals(jsonMessage.getString("type"))) {
	            	//TODO
	            }
	            
	            if ("accusation".equals(jsonMessage.getString("type"))) {
	            	//TODO
	            }
	            
	            //If the JSON message is of type startGame, then start the game session
	            else if ("startGame".equals(jsonMessage.getString("type"))) {
	            	sessionHandler.getGameSession().startGame();
	            }
	            
	            //If the JSON message is of type card, then only the player that made a suggestion
	            //will be shown the notified of which card is "shown"
	            if ("card".equals(jsonMessage.getString("type"))) {
	            	//TODO
	            }
	            
	        }
	    }
	}

