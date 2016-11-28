// Example taken from : http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/HomeWebsocket/WebsocketHome.html#section4

window.onload = init;

var websocket = new WebSocket('ws://localhost:8080/CluelessAWS/gameSessionEvents');

websocket.onmessage = function onMessage(msg) {
	var gameSession = JSON.parse(msg.data);
	if(gameSession.action === 'create') {
		printGameSessionId(gameSession);
	}
};

function createNewGameSession(player){
	var gameSession = {
			action: 'create',
			player: player
	};
	websocket.send(JSON.stringify(gameSession));
};

var sendMessage = function () {
	//send message to server
};

function printGameSessionId(gameSession) {
	var content = document.getElementById('content');
	
	var sessionDiv = document.createElement('div');
	sessionDiv.setAttribute("id", gameSession.id);
    content.appendChild(deviceDiv);
    
    var sessionId = document.createElement("span");
    sessionId.setAttribute("class", "sessionId");
    sessionId.innerHTML = gameSession.id;
    sessionDiv.appendChild(sessionId);
};

function init(){
	console.log('CLIENT SESSION INITIALIZED');
};
