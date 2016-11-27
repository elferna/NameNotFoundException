/**
 * The server-side websocket imp[ementation.
 */

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;

@ApplicationScoped
public class GameSessionWebsocketServer {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("GameSessionWebsocketServer :: CONNECTION OPENED!!!");
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("GameSessionWebsocketServer :: CONNECTION TERMINATED!!!");
	}
	
	@OnMessage
	public void onMessage(Session session) {
		System.out.println("GameSessionWebsocketServer :: MESSAGE RECEIVED!!!");
	}
	
	@OnError
	public void onError(Session session) {
		System.out.println("GameSessionWebsocketServer :: AW SNAP! SOMETHING WENT WRONG...");
	}
	
}
