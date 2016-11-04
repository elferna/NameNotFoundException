import java.util.*;

import javax.swing.JOptionPane;

public final class GameBoard {
    private ArrayList<Player> players;
    private ArrayList<Card> allCards;
    private ArrayList<Weapon> weapons;
    private ArrayList<Character> characters;
    private ArrayList<RoomCard> roomCards;
    
    private Room location[][]; 
    
    private Solution solution;
    
    //int numOfPlayers = players.size();
    
    private boolean isOver;
    
    private int currentTurn = 0;
    
    /**   
     *GameBoard constructor
     */
    public GameBoard(){
       this.players = new ArrayList<Player>();
       this.allCards = new ArrayList<Card>();
       this.weapons = new ArrayList<Weapon>();
       this.characters = new ArrayList<Character>();
       this.roomCards = new ArrayList<RoomCard>();
       this.location = new Room[5][5];
       getPlayers(); 
       makeCards();
       makeRooms();
       pickSolution();
       combineDeck();
       shuffleAll(allCards);
       this.isOver = false;
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
    public ArrayList<Weapon> shuffleWeapons(){
        Collections.shuffle(this.weapons);
        return this.weapons;
    }

    /**
     * Shuffle the characters
     * @return an ArrayList with the characters shuffled
     */
    public ArrayList<Character> shuffleCharacters(){
        Collections.shuffle(this.characters);
        return this.characters;
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
        Character character = shuffleCharacters().get(0);
        this.characters.remove(character);
        
        Weapon weapon = shuffleWeapons().get(0);
        this.weapons.remove(weapon);
        
        RoomCard roomCard = shuffleRooms().get(0);
        this.roomCards.remove(roomCard);        
        
        this.solution = new Solution(character, weapon, roomCard);        
    }
    
    /**
     * Combine the deck of cards by adding characters, weapons and room cards
     */
    public void combineDeck(){
        this.allCards.addAll(characters);
        this.allCards.addAll(weapons);
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
	    	{/*This logic needs to be changed for to make sure 
	    	that people all have the same amount of cards*/
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
    	/*Variable not being used
    	Room startRoom = p.currentRoom;
    	*/
    	
        Solution value = null;
    	
    	System.out.println("****************************\n"
                +  "** Player "+ player.getName() + " MENU **\n"
                		+ "** You are in" + player.getCurrentRoom().getName() + "**\n"
                +  "****************************\n");
    	//check neighboring rooms for options
    	//
    	ArrayList<Room> validMoves = getValidMoves(player.getCurrentRoom());
    	
    	if(validMoves.isEmpty())
    	{
    		System.out.println("You have been blocked! You may:\n"
    				+ "1. Make an accusation\n"
    				+ "2. End turn.\n");
    	}
    	else if(player.getCurrentRoom().isInHallway())
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
		Character _c = null;
		Weapon _w = null;
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
				
			case 2: //Solution();
				
					System.out.println("Which weapon" + this.weapons.toString());
					_w = this.weapons.get(userInput.nextInt());
					
					System.out.println("Current room is please type in the correct index" + player.getCurrentRoom());
					/*------------this is wrong*/
					_r = this.roomCards.get(userInput.nextInt());
					
					System.out.println("Which Character" + this.characters.toString());
					_c = this.characters.get(userInput.nextInt());
					
					value = new Solution( _c, _w, _r );
					break;
					
			case 3://accusation
					System.out.println("Which weapon" + this.weapons.toString());
					_w = this.weapons.get(userInput.nextInt());
					
					System.out.println("Which cards" + this.roomCards.toString());
					_r = this.roomCards.get(userInput.nextInt());
					
					System.out.println("Which Character" + this.characters.toString());
					_c = this.characters.get(userInput.nextInt());
					
					value = new Solution( _c, _w, _r );
					break;
			default: 
					System.out.println("Invalid input\n\nPlease try again \n");
					break;
		
        }
        return value;
    }
    
    /*
    print the valid move options using the arrayList "validMoves"
    */


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
     	ArrayList<Room> validMoves = new ArrayList<Room>();
        int x = room.getXPos();
        int y = room.getYPos();
        
        if(room.getXPos() == -1 || room.getXPos() == 5 || room.getYPos() ==-1 ||room.getYPos() == 5)
        {
        	validMoves.clear();
        	switch(room.getXPos())
        	{
	        	case -1:	
	        		validMoves.add(location[room.getXPos()+1][room.getYPos()]);
	        		break;
	        	case 5:
	        		validMoves.add(location[room.getXPos()-1][room.getYPos()]);
	        		break;
	        	default:
	        		break;
        	}
        	
        	switch(room.getYPos())
        	{
	        	case -1:	
	        		validMoves.add(location[room.getXPos()][room.getYPos()+1]);
	        		break;
	        	case 5:
	        		validMoves.add(location[room.getXPos()][room.getYPos()-1]);
	        		break;
	        	default:
	        		break;
        	}
        }
        
        else if(room.isInHallway()){
            
            if(x == 0 || x == 2 || x ==4){
                validMoves.add(location[x][room.getYPos()+1]);
                validMoves.add(location[x][room.getYPos()-1]);
            }
            else{
                validMoves.add(location[x + 1][room.getYPos()]);
                validMoves.add(location[x - 1][room.getYPos()]);
            }
        }
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

    /**
     * Creating a new room
     * @param name
     * @param x
     * @param y
     * @param h
     * @param s
     * @return a new Room
     */
    public Room newRoom(String name, int xPos, int yPos, boolean isInHallway, boolean hasSecretPassage){
        return new Room(name, xPos, yPos, isInHallway, hasSecretPassage);
    }
    
    /**
     * Making rooms
     */
    public void makeRooms(){ 
        location[0][0] = newRoom("Study", 0, 0, false, true);
        location[0][1] = newRoom("hallway1", 0, 1, true, false);
        location[0][2] = newRoom("Hall", 0, 2, false, false);
        location[0][3] = newRoom("hallway2", 0, 3, true, false);
        location[0][4] = newRoom("Lounge", 0, 4, false, true);
        
        location[1][0] = newRoom("hallway3", 1, 0, true, false);
        location[1][2] = newRoom("hallway4", 1, 2, true, false);
        location[1][4] = newRoom("hallway5", 1, 4, true, false);
        
        location[2][0] = newRoom("Libray", 2, 0, false, false);
        location[2][1] = newRoom("hallway6", 2, 1, true, false);
        location[2][2] = newRoom("Billiard Room", 2, 2, false, false);
        location[2][3] = newRoom("hallway7", 2, 3, true, false);
        location[2][4] = newRoom("Dining Room", 2, 4, false, false); 
        
        location[3][0] = newRoom("hallway8", 3, 0, true, false);
        location[3][2] = newRoom("hallway9", 3, 2, true, false);
        location[3][4] = newRoom("hallway10", 3, 4, true, false);
        
        location[4][0] = newRoom("Conservatory", 4, 0, false, true);
        location[4][1] = newRoom("hallway6", 4, 1, true, false);
        location[4][2] = newRoom("Ballroom", 4, 2, false, false);
        location[4][3] = newRoom("hallway7", 4, 3, true, false);
        location[4][4] = newRoom("Kitchen", 4, 4, false, true);          
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
        Character mustard = new Character("Colonel Mustard", "REPLACE W/ IMG FILE");
        characters.add(mustard);
        Character scarlet = new Character("Miss Scarlet", "REPLACE W/ IMG FILE");
        characters.add(scarlet);
        Character plum = new Character("Professor Plum", "REPLACE W/ IMG FILE");
        characters.add(plum);
        Character green = new Character("Mr. Green", "REPLACE W/ IMG FILE");
        characters.add(green);
        Character white = new Character("Mrs. White", "REPLACE W/ IMG FILE");
        characters.add(white);
        Character peacock = new Character("Mrs. Peacock", "REPLACE W/ IMG FILE");
        characters.add(peacock);
        
        //////////////////////////////////////
        
        Weapon rope = new Weapon("Rope", "REPLACE W/ IMG FILE");
        weapons.add(rope);
        Weapon pipe = new Weapon("Lead Pipe", "REPLACE W/ IMG FILE");
        weapons.add(pipe);
        Weapon knife = new Weapon("Knife", "REPLACE W/ IMG FILE");
        weapons.add(knife);
        Weapon wrench = new Weapon("Wrench", "REPLACE W/ IMG FILE");
        weapons.add(wrench);
        Weapon candlestick = new Weapon("Candlestick", "REPLACE W/ IMG FILE");
        weapons.add(candlestick);
        Weapon revolver = new Weapon("Revolver", "REPLACE W/ IMG FILE");
        weapons.add(revolver);
        
        /////////////////////////////////////
        
        RoomCard study = new RoomCard("Study", "REPLACE W/ IMG FILE");
        roomCards.add(study);
        RoomCard hall = new RoomCard("Hall", "REPLACE W/ IMG FILE");
        roomCards.add(hall);
        RoomCard lounge = new RoomCard("Lounge", "REPLACE W/ IMG FILE");
        roomCards.add(lounge);
        RoomCard library = new RoomCard("Library", "REPLACE W/ IMG FILE");
        roomCards.add(library);
        RoomCard billiard = new RoomCard("Billiard Room", "REPLACE W/ IMG FILE");
        roomCards.add(billiard);
        RoomCard dining = new RoomCard("Dining Room", "REPLACE W/ IMG FILE");
        roomCards.add(dining);
        RoomCard conservatory = new RoomCard("Conservatory", "REPLACE W/ IMG FILE");
        roomCards.add(conservatory);
        RoomCard ballroom = new RoomCard("Ballroom", "REPLACE W/ IMG FILE");
        roomCards.add(ballroom);
        RoomCard kitchen = new RoomCard("Kitchen", "REPLACE W/ IMG FILE");
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
