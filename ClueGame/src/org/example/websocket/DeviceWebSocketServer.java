package org.example.websocket;

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
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {
	
	@Inject
	private DeviceSessionHandler sessionHandler;
	
	@OnOpen
    public void open(Session session) {
		System.out.println("connection request");
		sessionHandler.addSession(session);
	}

	@OnClose
    public void close(Session session) {
		sessionHandler.removeSession(session);
	}

	@OnError
    public void onError(Throwable error) {
		Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
	}

	@OnMessage
    public void handleMessage(String message, Session session) {
		 try (JsonReader reader = Json.createReader(new StringReader(message))) {
	            JsonObject jsonMessage = reader.readObject();
	            
	            //If the JSON message is of type chat, do this
	            //It uses the sessionHandler.broadcastMsg to send the message from the chat
	            if ("chat".equals(jsonMessage.getString("type"))) {
//	                Device device = new Device();
//	                device.setName(jsonMessage.getString("name"));
//	                device.setDescription(jsonMessage.getString("description"));
//	                device.setType(jsonMessage.getString("type"));
//	                device.setStatus("Off");
	                sessionHandler.broadcastMsg(session, jsonMessage.getString("message"));
	            }

	            if ("heartbeat".equals(jsonMessage.getString("type"))) {
	                int id = (int) jsonMessage.getInt("id");
	                sessionHandler.removeDevice(id);
	            }

	            if ("toggle".equals(jsonMessage.getString("action"))) {
	                int id = (int) jsonMessage.getInt("id");
	                sessionHandler.toggleDevice(id);
	            }
	        }
	    }
	}

