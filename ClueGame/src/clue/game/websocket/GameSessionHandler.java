package clue.game.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

import org.example.model.Device;
import org.example.model.User;


@ApplicationScoped
public class GameSessionHandler {
	private int deviceId = 0;
	private final Set<Session> sessions = new HashSet<>();
	private final Set<Device> devices = new HashSet<>();
	private final Set<User> users = new HashSet<>();
	private final Stack<String> namesArray = new Stack<String>();
	
	//Constructor with given names, this is just to try the concept
	public GameSessionHandler(){
		this.namesArray.push("Mustard");
		this.namesArray.push("White");
		this.namesArray.push("Green");
		this.namesArray.push("Yellow");
		this.namesArray.push("Red");
	}
	
	//This add a session depending on how many users
	public synchronized void addSession(Session session){
		this.sessions.add(session);
		
		User user = null;
		//It gets the names oout of a stack, this force that no repeated name will be given
		if(!this.namesArray.isEmpty()){
			if(!this.sessions.equals(session)){
				user = new User(this.namesArray.pop(),session.getId());
				this.users.add(user);
			}
		}
		
		
		for (Device device : devices) {
			JsonObject addMessage = createAddMessage(device);
			sendToSession(session, addMessage);
		}
		
		this.sendToAllConnectedSessions(this.createChatMessage(user.name + " : " + user.sessionId));
	}
	
	public void removeSession(Session session){
		this.sessions.remove(session);
	}
	
	public List<Device> getDevices(){
		return new ArrayList<>(devices);
	}
	
	public void addDevice(Device device) {
		device.setId(deviceId);
        devices.add(device);
        deviceId++;
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);
    }
	
	//Function to broadcast the message from the chat to all users
	public void broadcastMsg(Session session, String message){
		//Creates a messages String from the message taken from the chat
	    String tempMessageWithName = "";
		for(User user : this.users){
			if(user.sessionId == session.getId()){
				tempMessageWithName = user.name + " : " + message;
			}
		}
		//Creates JSON object and send
		JsonObject tempMessage = createChatMessage(tempMessageWithName);
		sendToAllConnectedSessions(tempMessage);
	}

    public void removeDevice(int id) {
    	Device device = getDeviceById(id);
        if (device != null) {
            devices.remove(device);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public void toggleDevice(int id) {
    	JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            if ("On".equals(device.getStatus())) {
                device.setStatus("Off");
            } else {
                device.setStatus("On");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggle")
                    .add("id", device.getId())
                    .add("status", device.getStatus())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

    private Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Device device) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("status", device.getStatus())
                .add("description", device.getDescription())
                .build();
        return addMessage;
    }
    
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
