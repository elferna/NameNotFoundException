//Creating the socket
window.onload = init;
var loc = window.location, newUri;
if (loc.protocol === "https:") {
    newUri = "wss:";
} else {
    newUri = "ws:";
}
newUri += "//" + loc.host;
newUri += loc.pathname + "actions";
var socket = new WebSocket(newUri);
socket.onmessage = onMessage;


//Function to initialize the GUI
function init() {
	initializeUI();
    initializeGameComponents(cards);
    initializeListeners();
    //initialize socket
}

//****** Data coming from server. ********/
//As for now this is set up, but this will change based on the information received from the server
var cards = ["Ballroom.jpg","Kitchen.jpg","Knife.jpg","Rope.jpg","Library.jpg","Lead Pipe.jpg", "Mustard.jpg"];

//This is the default position of the player, this matrix will be received from the server
var rowPlayerPosition0 = ["0","0","0","0","6","0","0"];
var rowPlayerPosition1 = ["0","0","0","0","0","0","0"];
var rowPlayerPosition2 = ["1","0","0","0","0","0","5"];
var rowPlayerPosition3 = ["0","0","0","0","0","0","0"];
var rowPlayerPosition4 = ["4","0","0","0","0","0","0"];
var rowPlayerPosition5 = ["0","0","0","0","0","0","0"];
var rowPlayerPosition6 = ["0","0","3","0","2","0","0"];
var playersPositionMap = [rowPlayerPosition0,rowPlayerPosition1,rowPlayerPosition2,rowPlayerPosition3,rowPlayerPosition4,rowPlayerPosition5,rowPlayerPosition6];

//^***** Data coming from server. *******^/
//This are the images, they are painting the board 
var rowImages0 = ["Outside Tile.jpg","Outside Tile.jpg","Outside Tile.jpg","Outside Tile.jpg","Tiles.jpg", "Outside Tile.jpg","Outside Tile.jpg"];
var rowImages1 = ["Outside Tile.jpg","Study Board.jpg","Tiles.jpg","Hall Board.jpg","Tiles.jpg","Lounge Board.jpg","Outside Tile.jpg"];
var rowImages2 = ["Tiles.jpg","Tiles.jpg","Outside Tile.jpg","Tiles.jpg","Outside Tile.jpg","Tiles.jpg","Tiles.jpg"];
var rowImages3 = ["Outside Tile.jpg","Library Board.jpg","Tiles.jpg","Billard Room Board.jpg","Tiles.jpg","Dining Room Board.jpg","Outside Tile.jpg"];
var rowImages4 = ["Tiles.jpg","Tiles.jpg","Outside Tile.jpg","Tiles.jpg","Outside Tile.jpg","Tiles.jpg","Outside Tile.jpg"];
var rowImages5 = ["Outside Tile.jpg","Conservatory Board.jpg","Tiles.jpg","Ballroom Board.jpg","Tiles.jpg","Kitchen Board.jpg","Outside Tile.jpg"];
var rowImages6 = ["Outside Tile.jpg","Outside Tile.jpg","Tiles.jpg","Outside Tile.jpg","Tiles.jpg","Outside Tile.jpg","Outside Tile.jpg"];

var colorMap1 = [rowImages0,rowImages1,rowImages2,rowImages3,rowImages4,rowImages5,rowImages6];

//Function to initialize the listeners
function initializeListeners(){
	//This function put a check mark on the notepad when clicked
	//erases the checkmark if there is one already
	$("#noteTakingTableBottom div .row div").click(function(){
		if($(this).html() != ''){
			$(this).html("");
		}
		else{
			$(this).html("&#10004");
		}
	});
	
	//This function put a check mark on the notepad when clicked
	//erases the checkmark if there is one already
	$("#noteTakingTableMiddle div .row div").click(function(){
		if($(this).html() != ''){
			$(this).html("");
		}
		else{
			$(this).html("&#10004");
		}
	});

	//This function put a check mark on the notepad when clicked
	//erases the checkmark if there is one already
	$("#noteTakingTableTop div .row div").click(function(){
		if($(this).html() != ''){
			$(this).html("");
		}
		else{
			$(this).html("&#10004");
		}
	});

	//This is to send the information after clicking the accusation or suggestion button
	$("#suggestionButton, #accusationButton").click(function(event){
    	handleForm(event);
	});
	
	//This is to send the message in the input chat
	$("#chatSendBtn").click(function(event){
    	sendChatMessage();
	});
}

//Function to send a message 
function sendChatMessage() {
	var tempMsg = $("#chatMsgSpace").val();
	//Creating the JSON
	var chatMsg = {
    	type: "chat",
        message: tempMsg
    };
	//Sending through the socket
    socket.send(JSON.stringify(chatMsg));
}

//This is not being used now, it was used during early development
function setGameNotificationMessage(Message){
	$("#gameNotificationsSection").text(Message);
	console.log(this.id);
}

//Function to draw the components on the GUI
function initializeGameComponents(cards){
	drawCards(cards);
    //drawPlayers(playersPositionMap);
    drawBoard(colorMap1);
    drawPlayers(playersPositionMap);
}

//Function to draw cards
function drawCards(cards){
	var allColumnsCardSection = $("#cardSection div .row div");
	//This is a for loop
	allColumnsCardSection.each(function(index) {
 		 //$(this).text("Este es el row: " + index);
 		 var tempImageString = "<img src='<image_placeholder>' style='width:100%; height:100%;'>"
 		 $(this).html(tempImageString.replace("<image_placeholder>","img/" + cards[index]));
	});
}

function drawBoard(boardMap){
	var allRowsOnGameBoard = $("#gameBoard .row");
    //console.log(allRowsOnGameBoard.length);
	//This behaves like a double for loop
	allRowsOnGameBoard.each(function(indexRows){
		var allColumnsOnGameBoardRow = $(this).children();
		 //console.log(allColumnsOnGameBoardRow.length);
		allColumnsOnGameBoardRow.each(function(indexColumns) {		 	
			 var tempImageString = "<img src='<image_placeholder>' style='width:100%; height:100%;'>";
 		 	$(this).html(tempImageString.replace("<image_placeholder>","img/" + colorMap1[indexRows][indexColumns]));
 		 });
	});
}

//Function to draw the players
function drawPlayers(playersPositionMap){
	var allRowsOnGameBoard = $("#gameBoard .row");
	// console.log(allRowsOnGameBoard.length);
	//This behaves like a double for loop
	allRowsOnGameBoard.each(function(indexRows){
		var allColumnsOnGameBoardRow = $(this).children();
		// console.log(allColumnsOnGameBoardRow.length);
		allColumnsOnGameBoardRow.each(function(indexColumns) {
			 if(playersPositionMap[indexRows][indexColumns] !== "0"){
			 	
			 	var tempPlayer = "<div style='position:absolute; top: 0px;'><span class='<icon_placeholder>'></span><br/>" + playersPositionMap[indexRows][indexColumns] + "</div>";
 		 			
			 	if(playersPositionMap[indexRows][indexColumns] == "1"){
 					$(this).html($(this).html() + tempPlayer.replace("<icon_placeholder>","glyphicon glyphicon-user"));
 		 		}
 		 		else if(playersPositionMap[indexRows][indexColumns] == "2"){
 					$(this).html($(this).html() + tempPlayer.replace("<icon_placeholder>","glyphicon glyphicon-star"));
 		 		}
 		 		else if(playersPositionMap[indexRows][indexColumns] == "3"){
 					$(this).html($(this).html() + tempPlayer.replace("<icon_placeholder>","glyphicon glyphicon-heart"));
 		 		}
 		 		else if(playersPositionMap[indexRows][indexColumns] == "4"){
 					$(this).html($(this).html() + tempPlayer.replace("<icon_placeholder>","glyphicon glyphicon-flag"));
 		 		}
 		 		else if(playersPositionMap[indexRows][indexColumns] == "5"){
 					$(this).html($(this).html() + tempPlayer.replace("<icon_placeholder>","glyphicon glyphicon-tag"));
 		 		}
 		 		else if(playersPositionMap[indexRows][indexColumns] == "6"){
 					$(this).html($(this).html() + tempPlayer.replace("<icon_placeholder>","glyphicon glyphicon-qrcode"));
 		 		}
 	
 		 	}
 		 });
	});
}

//Variables needed to make it more generic
//They are used to help the drawing
var gameBoardColumnsNeeded = 7;
var maxNumberOfCards = 7;
var noteTakingColumnsNeeded = 6;


//This is the map behind the actual images in the board, this is not needed. It doesn't matter if it remains unchanged
var row0 = ["gray","gray","gray","gray","blue","gray","gray"];
var row1 = ["gray","green","white","orange","white","green","gray"];
var row2 = ["blue","white","gray","white","gray","white","blue"];
var row3 = ["gray","orange","white","orange","white","orange","gray"];
var row4 = ["blue","white","gray","white","gray","white","gray"];
var row5 = ["gray","green","white","orange","white","green","gray"];
var row6 = ["gray","gray","blue","gray","blue","gray","gray"];
var colorMap = [row0,row1,row2,row3,row4,row5,row6];

//This is the map behind the actual images in the board, this is not needed. It doesn't matter if it remains unchanged
var rowWords0 = ["","","","","","",""];
var rowWords1 = ["","Study","Hallway","Hall","Hallway","Lounge",""];
var rowWords2 = ["","Hallway","","Hallway","","Hallway",""];
var rowWords3 = ["","Library","Hallway","Billard Room","Hallway","Dining Room",""];
var rowWords4 = ["","Hallway","","Hallway","","Hallway",""];
var rowWords5 = ["","Conservatory","Hallway","Ballroom","Hallway","Kitchen",""];
var rowWords6 = ["","","","","","",""];
var wordsMap = [rowWords0, rowWords1, rowWords2, rowWords3, rowWords4, rowWords5, rowWords6];
//The reason of this matrices is that they were used during earlier development

//Function to initialize the User Interface
function initializeUI(){
	//$("#board-matrix").click(function(){

	//These is storing the amount of rows, this is used to draw the columns
	var allBoardRows = $("#gameBoard > .row");
	var allRowsCardSection = $("#cardSection div .row");
	var allRowsNoteTakingTableTop = $("#noteTakingTableTop div .row");
	var allRowsNoteTakingTableMiddle = $("#noteTakingTableMiddle div .row");
	var allRowsNoteTakingTableBottom = $("#noteTakingTableBottom div .row");

	//Cretaing the columns for each row

	allBoardRows.each(function(index) {
 		 //$(this).text("Este es el row: " + index);
 		 $(this).html(createColumns(index,gameBoardColumnsNeeded));
	});

	allRowsCardSection.each(function(index) {
 		 //$(this).text("Este es el row: " + index);
 		 $(this).html(createColumnsCardSection(index,maxNumberOfCards));
	});

	allRowsNoteTakingTableTop.each(function(index){
		if(index >= 1){$(this).html(createNoteTakingColumns(index, noteTakingColumnsNeeded, false))}
		else{$(this).html(createNoteTakingColumns(index, noteTakingColumnsNeeded, true))}
	});

	allRowsNoteTakingTableMiddle.each(function(index){
		$(this).html(createNoteTakingColumns(index, noteTakingColumnsNeeded, false))
	});

	allRowsNoteTakingTableBottom.each(function(index){
		$(this).html(createNoteTakingColumns(index, noteTakingColumnsNeeded, false))
	});

		//alert("hello");
	//});
}

//Function to create the columns of the board
function createColumns(row,columnsNeeded){
	var column_template = "<div class='col-md-1' style='background-color: <color_placeholder>; padding: 0px; position: relative; height: 100%; width: 14.28%; font-size: 75%; border-style: solid;<margin_placeholder>'><text_placeholder></div>";
	var retVal = "";
	for (i = 0; i < columnsNeeded; i++) {
		retVal = retVal + (column_template.replace("<color_placeholder>", colorMap[row][i])).replace("<text_placeholder>",wordsMap[row][i]);
	}
	return retVal;
}

//Function to create the columns on the card section
function createColumnsCardSection(row, columnsNeeded){
	var column_template = "<div class='col-md-1' style='background-color: gray; height: 100%; border-style: solid; padding: 0px;'></div>";
	var retVal = "";

	for (i = 0; i < columnsNeeded; i++) { 
    	retVal = retVal + column_template;
	}
	return retVal;
}

//Function to create the columns of the notepad
function createNoteTakingColumns(row, columnsNeeded, isTop){
	var column_template = "<div class='col-md-2' style='background-color: #add8e6; height: 100%; border-style: solid;'></div>";
	var retVal = "";
	for (i = 0; i < columnsNeeded; i++) {
		if(isTop && i == 0){
			column_template = "<div class='col-md-2' style='background-color: #add8e6; height: 100%; border-style: solid;'><p>1</p></div>";
		}
		else if(isTop && i == 1){
			column_template = "<div class='col-md-2' style='background-color: #add8e6; height: 100%; border-style: solid;'><p>2</p></div>";
		}
		else if(isTop && i == 2){
			column_template = "<div class='col-md-2' style='background-color: #add8e6; height: 100%; border-style: solid;'><p>3</p></div>";
		}
		else if(isTop && i == 3){
			column_template = "<div class='col-md-2' style='background-color: #add8e6; height: 100%; border-style: solid;'><p>4</p></div>";
		}
		else if(isTop && i == 4){
			column_template = "<div class='col-md-2' style='background-color: #add8e6; height: 100%; border-style: solid;'><p>5</p></div>";
		}
		else if(isTop && i == 5){
			column_template = "<div class='col-md-2' style='background-color: #add8e6; height: 100%; border-style: solid;'><p>6</p></div>";
		}
    	retVal = retVal + column_template;
	}
	return retVal;	
}

//Function to send the items from the dropdown menu
function handleForm(event){

	var data = {}
	data.action = event.target.name; 
	data.who = $('#accusationForm').find('select[name="who"]').val(); 
	data.what = $('#accusationForm').find('select[name="what"]').val();
	data.where = $('#accusationForm').find('select[name="where"]').val();

	// Send this information to socket.

	//if remove comment of the line below, you will receive a notification of the stuff from the dropdown menus
	//alert(JSON.stringify(data));
	event.preventDefault();
}

//Function to display the chat messages
function displayChatMessage(message){
	var tempMsg = $("#chatMsgArea");
	tempMsg.val(tempMsg.val() + "<br>" + message); 
	$("#chatMsgArea").html(tempMsg.val());
}

function onMessage(event) {
    var data = JSON.parse(event.data);
    if (data.type === "chat") {
        displayChatMessage(data.message);
        console.log(data.message);
    }
//    if (device.action === "remove") {
//        document.getElementById(device.id).remove();
//        //device.parentNode.removeChild(device);
//    }
//    if (device.action === "toggle") {
//        var node = document.getElementById(device.id);
//        var statusText = node.children[2];
//        if (device.status === "On") {
//            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
//        } else if (device.status === "Off") {
//            statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
//        }
//    }
}
