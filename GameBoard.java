import java.util.*;

import javax.swing.JOptionPane;

public final class GameBoard {
    private ArrayList<Player> players;
    
    // Cards
    private ArrayList<Card> allCards;
    private ArrayList<WeaponCard> weaponCards;
    private ArrayList<SuspectCard> suspectCards;
    private ArrayList<RoomCard> roomCards;
    
    //Tokens
    private ArrayList<WeaponToken> weaponTokens;
    private ArrayList<SuspectToken> suspectTokens;
    
    
    private Room location[][]; 
    
    private Solution solution;
    
    //int numOfPlayers = players.size();
    
    private boolean isOver;
    
    private int currentTurn = 0;
    
    ArrayList<Room> validMoves;
    
   

    
    /**   
     *GameBoard constructor
     */
    public GameBoard(){
       //tokens
       this.weaponTokens = new ArrayList<WeaponToken>();
       this.suspectTokens = new ArrayList<SuspectToken>();
       
       this.players = new ArrayList<Player>();
       this.allCards = new ArrayList<Card>();
       this.weaponCards = new ArrayList<WeaponCard>();
       this.suspectCards = new ArrayList<SuspectCard>();
       this.roomCards = new ArrayList<RoomCard>();
       
       this.validMoves  = new ArrayList<Room>();
       
       //7x7 room
       this.location = new Room[7][7];
       getPlayers(); 
       makeCards();
       makeRooms();
       pickSolution();
       combineDeck();
       shuffleAll(allCards);
       this.isOver = false;
       
    }
    
    public int getCurrentTurn(){
    	return this.currentTurn;
    }
    
    
    /**
     * Players getter
     * @return
     */
    public ArrayList<Player> getPlayers() {
		return this.players;		
	}

    /**
     * Shuffle all the cards
     * @param cards
     * @return an ArrayList with the cards shuffled
     */
	public ArrayList<Card> shuffleAll(ArrayList<Card> cards){
        Collections.shuffle(cards);
        return cards;
    }
    
	/**
	 * Shuffle the weapons
	 * @return an ArrayList with the weapons shuffled
	 */
    public ArrayList<WeaponCard> shuffleWeapons(){
        Collections.shuffle(this.weaponCards);
        return this.weaponCards;
    }

    /**
     * Shuffle the characters
     * @return an ArrayList with the characters shuffled
     */
    public ArrayList<SuspectCard> shuffleSuspects(){
        Collections.shuffle(this.suspectCards);
        return this.suspectCards;
    }    
 
    /**
     * Shuffle the rooms
     * @return an ArrayList with the rooms shuffled
     */
    public ArrayList<RoomCard> shuffleRooms(){
        Collections.shuffle(this.roomCards);
        return this.roomCards;
    } 
    
    /**
     * Picking the solution of the game
     */
    public final void pickSolution(){
        //Weapon sWeapon = 
        SuspectCard character = shuffleSuspects().get(0);
        this.suspectCards.remove(character);
        
        WeaponCard weapon = shuffleWeapons().get(0);
        this.weaponCards.remove(weapon);
        
        RoomCard roomCard = shuffleRooms().get(0);
        this.roomCards.remove(roomCard);        
        
        this.solution = new Solution(character, weapon, roomCard);        
    }
    
    /**
     * Combine the deck of cards by adding characters, weapons and room cards
     */
    public void combineDeck(){
        this.allCards.addAll(suspectCards);
        this.allCards.addAll(weaponCards);
        this.allCards.addAll(roomCards); 
    }
    
    /**
     * Deal the cards to all the players
     * @param cards
     */
    public void dealCards( ArrayList<Card> cards){
    	
    	Iterator<Card> iter = cards.iterator();
    	
    	/* Variable not being used
        int currentPlayer = 0;
        */
    	
        while(iter.hasNext()){
        	for (Player p : this.players)
	    	{
                    p.addCard(iter.next());
	    	}       
        }
    }    
    
    /**
     * Adding a player to the game
     * @param player
     */
    public void addPlayer(Player player){
    	if(this.players.size() < 7){
    		this.players.add(player);
    	}
    	else{
    		System.out.println("Maximum number of players. Cannot add more players");
    	}
    }
    
    /**
     * 
     * @param player
     * @return
     */
    public Solution takeTurn(Player player){
     	
        Solution value = null;
    	
    	System.out.println("****************************\n"
                +  "** Player "+ player.getName() + " MENU **\n"
                		+ "** You are in" + player.getCurrentRoom().getName() + "**\n"
                +  "****************************\n");
    	//check neighboring rooms for options
    	//
    	validMoves = getValidMoves(player.getCurrentRoom());
    	
    	if(validMoves.isEmpty())
    	{
    		System.out.println("You have been blocked! You may:\n"
    				+ "1. Make an accusation\n"
    				+ "2. End turn.\n");
    	}
    	else if(player.getCurrentRoom().isHallway())
    	{
    		System.out.println("Please Select what you would like to do:\n"
    				+  "1. Move\n"
    				+  "2. Make an accusation\n"
    				+  "3. End Turn");
    	}
    	else 
    	{
        	System.out.println("Please Select what you would like to do:\n"
				+  "1. Move\n"
				+  "2. Make a Suggestion\n"
				+  "3. Make an accusation\n"
				+  "4. End Turn");
    	}	
        
		Scanner userInput = new Scanner(System.in);
		int option = 0;
		
		try
		{
			option = userInput.nextInt();
			if(option > 4 || option<0)
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			System.out.println("Invalid input. Select one of the numbers \n\nPlease try again... \n");
		}
		
		int x = 0;
		int y = 0;
		SuspectCard _c = null;
		WeaponCard _w = null;
		RoomCard _r = null;
		switch(option)
		{
			case 1: //move();
					//p.check if he was moved here or if he moved himself
					//if(p.currentRoom)
				
					System.out.println("Please select your requested location" + validMoves.toString()+ "type in '3 enter 0 enter,");
					try{
						x = userInput.nextInt();
						y = userInput.nextInt();
					}
					catch(Exception e)
					{
					    System.out.println("Invalid input. Select one of the numbers \n\nPlease try again... \n");
					}
					//Update GUI
                                        
					player.UpdateCharacter(location[x][y]);
                                                                                
					break;
				
			case 2: //Suggestion();
				
					System.out.println("Which weapon" + this.weaponCards.toString());
					_w = this.weaponCards.get(userInput.nextInt());
					
					System.out.println("Current room is " + player.getCurrentRoom().toString());
					/*------------this is wrong*/
                                        
                                        Room current = player.getCurrentRoom();
                                        for(int i =0; i < roomCards.size(); i++){
                                            if(current.toString().equals(roomCards.get(i).toString())){
                                                _r = roomCards.get(i);
                                            }
                                        }
					//_r = this.roomCards.get(userInput.nextInt());
					
					System.out.println("Which Character" + this.suspectCards.toString());
					_c = this.suspectCards.get(userInput.nextInt());
					
					value = new Solution( _c, _w, _r );
                                        
                                        //move player and weapon to room.
                                        
					break;
					
			case 3://accusation			
					
					System.out.println("Which weapon" + this.weaponCards.toString());
					_w = this.weaponCards.get(userInput.nextInt());
					
					System.out.println("Which Room" + this.roomCards.toString());
					_r = this.roomCards.get(userInput.nextInt());
					
					System.out.println("Which Character" + this.suspectCards.toString());
					_c = this.suspectCards.get(userInput.nextInt());
					
					value = new Solution( _c, _w, _r );
					break;
			default: 
					System.out.println("Invalid input\n\nPlease try again \n");
					break;
		
        }
        return value;
    }
    
    public void disproveSuggestion(Player p, Solution s){
    	int otherIndex;
    	
    	// current turn is the last player
    	if((currentTurn + 1) >= players.size()){
    		otherIndex = 0;
    	}
    	// current turn is not the last players
    	else{
    		otherIndex = currentTurn++;
    	}    	
    	
    	// get the next player
    	Player otherPlayer = players.get(otherIndex);
    	while(!p.equals(otherPlayer)){    		
    		ArrayList<Card> showableCards = cardsToShow(otherPlayer, s);
			
    		//player has no cards in the suggestion
			if(showableCards.isEmpty()){
				System.out.println("YOU CANNOT DISPROVE THIS SUGGESTION");
			}
			//player has cards to disprove suggestion
			else{
				// display the cards with the UI
    			/////////////////////////////////
				System.out.println("Choices: ");
				for(int j = 0; j < showableCards.size(); j++){
					System.out.println(showableCards.get(j).toString());
				}
				
				///////////////
				//have player choose a card to show
				///////////////
				
			}
			
			// move on to the next player
			if((otherIndex + 1) >= players.size()){
	    		otherIndex = 0;
	    	}
	    	else{
	    		otherIndex = currentTurn++;
	    	}
			
			otherPlayer = players.get(otherIndex);		
    		
    	}
    	
    }
    
    
    /**
     * makes a list of possible cards for each player to show to
     * prove a suggestion
     * @param p the current player's hand to check
     * @param s the suggestion 
     * @return 
     */
    public ArrayList<Card> cardsToShow(Player p, Solution s){
        p.emptyToShow();
        if(p.hasCard(s.getCharacterCard())){
            p.addToShow(s.getCharacterCard());
        }
        if(p.hasCard(s.getWeaponCard())){
            p.addToShow(s.getWeaponCard());
        }
        if(p.hasCard(s.getRoomCard())){
            p.addToShow(s.getRoomCard());
        }
        return p.getToShow();    
    }

    /**
     * Checking if a player's accusation is correct
     * @param s player's accusation
     * @return true if the accusation is correct. false otherwise.
     */
    public boolean correctAccusation(Solution s){
        if( this.solution.getCharacterCard() == s.getCharacterCard() &&
                this.solution.getWeaponCard() == s.getWeaponCard() &&
                this.solution.getRoomCard() == s.getRoomCard()){
            return true;
        }        
        
        return false;
    }   


    /**
     * Giving turn to players
     * @return
     */
    public int nextTurn(){
        if(this.currentTurn < players.size()/*I think this might need to be player.size()-1*/){//I think the current logic is correct
        																					   //No need to be player.size()-1, if we start at
        																					   //0, then 0 to the number less than the size,
        																					   //we will cover all the players
        	this.currentTurn++;
        }
        else{
            this.currentTurn = 0;
        }
        return this.currentTurn;
    }

    /**
     * Gets all the possible moves from a given room
     * @param room
     * @return An ArrayList that contains all the possibles moves for a given room
     */
    public ArrayList<Room> getValidMoves(Room room){
     	validMoves.clear();
        int x = room.getXPos();
        int y = room.getYPos();
        
        
        //  starting spots 
        // room is in the top row
        if(x == 0){
            validMoves.add(location[x+1][y]);
        }
        //bottom row
        if(x == 6){
            validMoves.add(location[x-1][y]);
        }
        //left column
        if(y == 0){
            validMoves.add(location[x][y+1]);
        }
        //right column
        if(y == 6){
            validMoves.add(location[x][y-1]);
        }    
        
        
        //for every room except the billard room in the middle
        if(x == 1 || x == 5 || y == 1 || y == 5)
        {
        	
        	switch(x)
        	{
	        	case 1:	
	        		validMoves.add(location[x+1][y]);
	        		break;
	        	case 5:
	        		validMoves.add(location[x-1][y]);
	        		break;
	        	default:
	        		break;
        	}
        	
        	switch(y)
        	{
	        	case 1:	
	        		validMoves.add(location[x][y+1]);
	        		break;
	        	case 5:
	        		validMoves.add(location[x][y-1]);
	        		break;
	        	default:
	        		break;
        	}
        }
        
        //hallways
        else if(room.isHallway()){
            
            if(x == 1 || x == 3 || x == 5){
                validMoves.add(location[x][y + 1]);
                validMoves.add(location[x][y - 1]);
            }
            else{
                validMoves.add(location[x + 1][y]);
                validMoves.add(location[x - 1][y]);
            }
        }
        
        //billiard room
        else{
            
            //check up
            if((x-1) >= 0){
                Room roomUp = location[x-1][y];
                if(!roomUp.isBlocked()){
                    validMoves.add(roomUp);
                }
            }
            
            //check right
            if((y+1) <= 4){
                Room roomRight = location[x][y+1];
                if(!roomRight.isBlocked()){
                    validMoves.add(roomRight);
                }
            }
            
            //check down
            if((x+1) <= 4){
                Room roomDown = location[x+1][y];
                if(!roomDown.isBlocked()){
                    validMoves.add(roomDown);
                }
            }
            
            //check left
            if((y-1) >= 0){
                Room roomLeft = location[x][y-1];
                if(!roomLeft.isBlocked()){
                    validMoves.add(roomLeft);
                }
            }
            
            //secret passages
            if(room.hasSecretPassage()){
                validMoves.add(getSecretPassage(room));
            }
            
        }  
        
        return validMoves;
    }
    
    //SuspectToken(GameBoard _gb, Name n, int _x, int _y, String fileName)
    public void makeTokens(){
        SuspectToken scarlet = 
                new SuspectToken(this, Suspects.SCARLET, 0, 4, "IMAGE FILE");
        SuspectToken mustard = 
                new SuspectToken(this, Suspects.MUSTARD, 2, 6, "IMAGE FILE");
        SuspectToken white = 
                new SuspectToken(this, Suspects.WHITE, 6, 4, "IMAGE FILE");
        SuspectToken green = 
                new SuspectToken(this, Suspects.GREEN, 6, 2, "IMAGE FILE");
        SuspectToken peacock = 
                new SuspectToken(this, Suspects.PEACOCK, 2, 0, "IMAGE FILE");
        SuspectToken plum = 
                new SuspectToken(this, Suspects.PLUM, 4, 0, "IMAGE FILE");
        
        //WeaponToken(GameBoard _gb, Weapons n, int _x, int _y, String fileName){
        WeaponToken rope = 
                new WeaponToken(this, Weapons.ROPE, 0, 0,"IMAGE FILE");
        WeaponToken leadpipe = 
                new WeaponToken(this, Weapons.LEADPIPE, 0, 0,"IMAGE FILE");
        WeaponToken knife = 
                new WeaponToken(this, Weapons.KNIFE, 0, 0,"IMAGE FILE");
        WeaponToken wrench = 
                new WeaponToken(this, Weapons.WRENCH, 0, 0,"IMAGE FILE");
        WeaponToken candlestick = 
                new WeaponToken(this, Weapons.CANDLESTICK, 0, 0,"IMAGE FILE");
        WeaponToken revolver = 
                new WeaponToken(this, Weapons.REVOLVER, 0, 0,"IMAGE FILE");
    }
    
    public void readyPlayer(Player p, SuspectToken t){
        p.setToken(t);
    }

    /**
     * Creating a new room
     * @param name
     * @param x
     * @param y
     * @param h
     * @param s
     * @return a new Room
     */
    public Room newRoom(Rooms r, int xPos, int yPos, boolean isInHallway, boolean hasSecretPassage){
        return new Room(r, xPos, yPos, isInHallway, hasSecretPassage);
    }
    
    /**
     * Making rooms
     */
    public void makeRooms(){ 
        // row column
        //name x y hallway passage
        
        
        //starting spots
        location[0][4] = new Room("ScarletStart", 0, 4, false, false);
        location[2][0] = new Room("PlumStart", 2, 0, false, false);
        location[4][0] = new Room("PeacockStart", 4, 0, false, false);
        location[2][6] = new Room("MustardStart", 2, 6, false, false);
        location[6][2] = new Room("GreenStart", 6, 2, false, false);
        location[6][4] = new Room("WhiteStart", 6, 4, false, false);
             
        location[1][1] = newRoom(Rooms.Study, 1, 1, false, true);
        location[1][2] = new Room("hallway1", 1, 2, true, false);
        location[1][3] = newRoom(Rooms.Hall, 1, 3, false, false);
        location[1][4] = new Room("hallway2", 1, 4, true, false);
        location[1][5] = newRoom(Rooms.Lounge, 1, 5, false, true);
        
        location[2][1] = new Room("hallway3", 2, 1, true, false);
        location[2][3] = new Room("hallway4", 2, 3, true, false);
        location[2][5] = new Room("hallway5", 2, 5, true, false);
        
        location[3][1] = newRoom(Rooms.Library, 3, 1, false, false);
        location[3][2] = new Room("hallway6", 3, 2, true, false);
        location[3][3] = newRoom(Rooms.Billiards, 3, 3, false, false);
        location[3][4] = new Room("hallway7", 3, 4, true, false);
        location[3][5] = newRoom(Rooms.Dining, 3, 5, false, false); 
        
        location[4][1] = new Room("hallway8", 4, 1, true, false);
        location[4][3] = new Room("hallway9", 4, 3, true, false);
        location[4][5] = new Room("hallway10", 4, 5, true, false);
        
        location[5][1] = newRoom(Rooms.Conservatory, 5, 1, false, true);
        location[5][2] = new Room("hallway6", 5, 2, true, false);
        location[5][3] = newRoom(Rooms.Ballroom, 5, 3, false, false);
        location[5][4] = new Room("hallway7", 5, 4, true, false);
        location[5][5] = newRoom(Rooms.Kitchen, 5, 5, false, true);          
    }
    
    /**
     * Making a new Card
     * @param name
     * @param fileName
     * @return A new card
     */
    public Card newCard(String name, String fileName){
        return new Card(name, fileName);
    }
    
    /**
     * Making all the cards
     */
    public void makeCards(){
        SuspectCard mustard = new SuspectCard(Suspects.MUSTARD, "REPLACE W/ IMG FILE");
        suspectCards.add(mustard);
        SuspectCard scarlet = new SuspectCard(Suspects.SCARLET, "REPLACE W/ IMG FILE");
        suspectCards.add(scarlet);
        SuspectCard plum = new SuspectCard(Suspects.PLUM, "REPLACE W/ IMG FILE");
        suspectCards.add(plum);
        SuspectCard green = new SuspectCard(Suspects.GREEN, "REPLACE W/ IMG FILE");
        suspectCards.add(green);
        SuspectCard white = new SuspectCard(Suspects.WHITE, "REPLACE W/ IMG FILE");
        suspectCards.add(white);
        SuspectCard peacock = new SuspectCard(Suspects.PEACOCK, "REPLACE W/ IMG FILE");
        suspectCards.add(peacock);
        
        //////////////////////////////////////
        
        WeaponCard rope = new WeaponCard(Weapons.ROPE, "REPLACE W/ IMG FILE");
        weaponCards.add(rope);
        WeaponCard pipe = new WeaponCard(Weapons.LEADPIPE, "REPLACE W/ IMG FILE");
        weaponCards.add(pipe);
        WeaponCard knife = new WeaponCard(Weapons.KNIFE, "REPLACE W/ IMG FILE");
        weaponCards.add(knife);
        WeaponCard wrench = new WeaponCard(Weapons.WRENCH, "REPLACE W/ IMG FILE");
        weaponCards.add(wrench);
        WeaponCard candlestick = new WeaponCard(Weapons.CANDLESTICK, "REPLACE W/ IMG FILE");
        weaponCards.add(candlestick);
        WeaponCard revolver = new WeaponCard(Weapons.REVOLVER, "REPLACE W/ IMG FILE");
        weaponCards.add(revolver);
        
        /////////////////////////////////////
        
        RoomCard study = new RoomCard(Rooms.Study, "REPLACE W/ IMG FILE");
        roomCards.add(study);
        RoomCard hall = new RoomCard(Rooms.Hall, "REPLACE W/ IMG FILE");
        roomCards.add(hall);
        RoomCard lounge = new RoomCard(Rooms.Lounge, "REPLACE W/ IMG FILE");
        roomCards.add(lounge);
        RoomCard library = new RoomCard(Rooms.Library, "REPLACE W/ IMG FILE");
        roomCards.add(library);
        RoomCard billiard = new RoomCard(Rooms.Billiards, "REPLACE W/ IMG FILE");
        roomCards.add(billiard);
        RoomCard dining = new RoomCard(Rooms.Dining, "REPLACE W/ IMG FILE");
        roomCards.add(dining);
        RoomCard conservatory = new RoomCard(Rooms.Conservatory, "REPLACE W/ IMG FILE");
        roomCards.add(conservatory);
        RoomCard ballroom = new RoomCard(Rooms.Ballroom, "REPLACE W/ IMG FILE");
        roomCards.add(ballroom);
        RoomCard kitchen = new RoomCard(Rooms.Kitchen, "REPLACE W/ IMG FILE");
        roomCards.add(kitchen);
                
    }
    
    /**
     * Room getter
     * @param x
     * @param y
     * @return Room variable value
     */
    public Room getRoom(int x, int y){
        return this.location[x][y];
    }
    
    /**
     * SecretPassage getter
     * @param room
     * @return the secret passage
     */
    public Room getSecretPassage(Room room){
        if(room.getXPos() == 0 && room.getYPos() == 0){
            return this.location[4][4];
        }
        else if(room.getXPos() == 0 && room.getYPos() == 4){
            return this.location[4][0];
        }
        else if(room.getXPos() == 4 && room.getYPos() == 0){
            return this.location[0][4];
        }
        else{
            return this.location[0][0];
        }        
    }
    
    /**
     * Notify all players
     * @param guess
     * @param players
     */
    public static void notifyAllPlayers(Solution guess, ArrayList<Player> players){
    	for(Player p : players)
        {
    		p.displayPopUp(guess);           
        }
    }
    
    /**
     * Notify all players whose turn is
     * @param guesser
     * @param players
     */
    public static void notifyAllPlayersWhoseTurnItIs(String guesser, ArrayList<Player>players){
    	 
    	for(Player p : players)
        {
    		p.displayTurn(guesser);
        }
    }
    
    /**
     * Know when the game is over
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver(){
        return this.isOver;
    }
    
    public void setIsOver(boolean b){
    	this.isOver = b;
    }
    
    /**
     * Test the game board
     * @param gameBoard
     */
    public void testGameBoard(GameBoard gameBoard){

        gameBoard.isOver = false;
        gameBoard.addPlayer(new Player("Player 1", new Room("Start1",-1,3,true,false)));
        gameBoard.addPlayer(new Player("Player 2", new Room("Start2",1,5,true,false)));
        gameBoard.addPlayer(new Player("Player 3", new Room("Start3",5,1,true,false)));
        gameBoard.addPlayer(new Player("Player 4", new Room("Start4",5,3,true,false)));
        gameBoard.addPlayer(new Player("Player 5", new Room("Start5",3,-1,true,false)));
        gameBoard.addPlayer(new Player("Player 6", new Room("Start6",1,-1,true,false)));
              
        gameBoard.dealCards(gameBoard.allCards);
        
        //System.out.println(gb.location[0][0].getName());
        /*System.out.println(gb.players.get(0).hand.get(0).getName());
        System.out.println(gb.players.get(1).hand.get(0).getName());
        System.out.println(gb.players.get(2).hand.get(0).getName());
        
        System.out.println(gb.getSecretPassage(gb.getRoom(0, 0)).getName());
        System.out.println(gb.getSecretPassage(gb.getRoom(0, 4)).getName());
        System.out.println(gb.getSecretPassage(gb.getRoom(4, 0)).getName());
        System.out.println(gb.getSecretPassage(gb.getRoom(4, 4)).getName());*/        
    }
    /*
    just used for testing at the moment
    */
    public static void main(String[] args){
    	
        GameBoard gameBoard = new GameBoard();
        gameBoard.testGameBoard(gameBoard);
        
        //Room r = gb.getRoom(0, 0)
        
        ArrayList<Player> allPlayers = gameBoard.getPlayers();
        
        while(!gameBoard.isGameOver())
        {
            for(Player p : allPlayers)
            {
            	/*Not being used
            	ArrayList<Integer> Actions = new ArrayList<Integer>();
            	*/
            	
            	notifyAllPlayersWhoseTurnItIs(p.getName(), allPlayers);
            	
            	p.setTurn(true);
            	
            	while (p.isTurn())
            	{
	            	Solution guess = null;
	    			guess = gameBoard.takeTurn(p);
		        	 if(!guess.equals(null))
		             {
		             	notifyAllPlayers(guess, allPlayers);
		             } 
            	}
            }
            
        }
        
    }
    
}
