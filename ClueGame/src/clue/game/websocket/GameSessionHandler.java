package clue.game.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

import clue.game.model.Player;

@ApplicationScoped
public class GameSessionHandler {
	private GameSession gameSession = new GameSession();
	private final Set<Session> sessions = new HashSet<>();

	public GameSessionHandler(){
		
	}
	
	// This add a session depending on how many users. For now,
	// we're assuming only one game session exists for the sake
	// of time.
	public synchronized void addSession(Session session){
		this.sessions.add(session);
		Player player = new Player("P".concat(gameSession.getPlayerCount().toString()), session);
		
		this.gameSession.addPlayer(player);
		System.out.println("%%%%%%%%%%%%%%%%%%%%% " + player.getPlayerName() + " %%%%%%%%%%%%%%%%%%%%%");				
		this.sendToAllConnectedSessions(this.createChatMessage("Notification: " + player.getPlayerName() + " is now connected."));
	}
	
	public void removeSession(Session session){
		this.sessions.remove(session);
	}
	
	//Broadcasts a chat message to all players
	public void broadcastMsg(Session session, String message) {
	    String tempMessageWithName = "";
		for(Player player : gameSession.getAllPlayers()){
			if(player.getId() == session.getId()){
				tempMessageWithName = player.getPlayerName() + " : " + message;
			}
		}
		//Creates JSON object and send
		JsonObject tempMessage = createChatMessage(tempMessageWithName);
		sendToAllConnectedSessions(tempMessage);
	}
	
//    public void removePlayer(Player player) {
//        if (player != null) {
//            gameSession.removePlayer(player);
//            JsonProvider provider = JsonProvider.provider();
//            JsonObject removeMessage = provider.createObjectBuilder()
//                    .add("action", "remove")
//                    .add("id", player.getId())
//                    .build();
//            sendToAllConnectedSessions(removeMessage);
//        }
//    }
    
    private JsonObject createChatMessage(String chatMessage) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject tempMessage = provider.createObjectBuilder()
                .add("type", "chat")
                .add("message", chatMessage)
                .build();
        return tempMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(GameSessionHandler.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
	
}
