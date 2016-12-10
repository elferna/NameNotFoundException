package clue.game.model;

import java.util.*;
import javax.swing.JOptionPane;

import clue.game.model.Player;
import clue.game.model.Position;
import clue.game.model.Card;
import clue.game.model.Solution;

public class BoardGame {

	private ArrayList<Player>allPlayers = new ArrayList<>();
	private ArrayList<Card>roomCards = new ArrayList<>();  
	private ArrayList<Card>weaponCards = new ArrayList<>();
    private ArrayList<Card>suspectCards = new ArrayList<>(); 
    public enum Cards{ Room, Suspect, Weapon};
    String gameID;
    private int[][] location;
    Solution answer;
	
	private ArrayList<String> yesAndNo;
 
	public BoardGame(ArrayList<Player> allPlayers, String gameID) {
		this.allPlayers = allPlayers;
		this.gameID = gameID;
		this.location = new int[7][7];
		this.roomCards = new ArrayList<Card>();  
		this.weaponCards = new ArrayList<Card>();
	    this.suspectCards = new ArrayList<Card>();
		this.yesAndNo = new ArrayList<String>();
            this.yesAndNo.add("yes");
            this.yesAndNo.add("no");
	}
	
	public BoardGame(String gameID) {
		this.gameID = gameID;
		this.location = new int[7][7];
		this.roomCards = new ArrayList<Card>();  
		this.weaponCards = new ArrayList<Card>();
	    this.suspectCards = new ArrayList<Card>();
		this.yesAndNo = new ArrayList<String>();
            this.yesAndNo.add("yes");
            this.yesAndNo.add("no");
	}
	
	public BoardGame() {
		
	}
	
	//Initializations
	public void initializeRooms()
	{
		this.roomCards.add(new Card("Study" ,1,5,"//room", 'R'));
		this.roomCards.add(new Card("Hall" ,3,5,"//room", 'R'));
		this.roomCards.add(new Card("Lounge" ,5,5,"//room", 'R'));
	       
		this.roomCards.add(new Card("Library" ,1,3,"//room", 'R'));
		this.roomCards.add(new Card("Billards" ,3,3,"//room", 'R'));
		this.roomCards.add(new Card("Dining Room" ,5,3,"//room", 'R'));
	       
		this.roomCards.add(new Card("Conservatory" ,1,1,"//room", 'R'));
		this.roomCards.add(new Card("Ballroom" ,3,1,"//room", 'R'));
		this.roomCards.add(new Card("Kitchen" ,5,1,"//room", 'R'));
	    //Collections.shuffle(this.roomCards);

	}
	public void initializeHallways()
	{		   
		this.roomCards.add(new Card("hallway1" ,2,5,"//room", 'R'));
		this.roomCards.add(new Card("hallway2" ,4,5,"//room", 'R'));
		   
		this.roomCards.add(new Card("hallway3" ,1,4,"//room", 'R'));
		this.roomCards.add(new Card("hallway4" ,3,4,"//room", 'R'));
		this.roomCards.add(new Card("hallway5" ,5,4,"//room", 'R'));
		
		this. roomCards.add(new Card("hallway6" ,2,3,"//room", 'R'));
		this.roomCards.add(new Card("hallway7" ,4,3,"//room", 'R'));
		   
		this.roomCards.add(new Card("hallway8" ,1,2,"//room", 'R'));
		this.roomCards.add(new Card("hallway9" ,3,2,"//room", 'R'));
		this.roomCards.add(new Card("hallway10" ,5,2,"//room", 'R'));
		
		this.roomCards.add(new Card("hallway11" ,2,1,"//room", 'R'));
		this.roomCards.add(new Card("hallway12" ,4,1,"//room", 'R'));
		//Collections.shuffle(this.roomCards);
	}
	public void initializeWeapons()
	{
		this.weaponCards.add(new Card( "ROPE", "//ROPE", 'W'));
		this.weaponCards.add(new Card( "Lead Pipe", "//ROPE", 'W'));
		this.weaponCards.add(new Card( "Knife", "//ROPE", 'W'));
		this.weaponCards.add(new Card( "Wrench", "//ROPE", 'W'));
		this.weaponCards.add(new Card( "Candle Stick", "//ROPE", 'W'));
		this.weaponCards.add(new Card( "Revolver", "//ROPE", 'W'));
		//Collections.shuffle(weaponCards);
    
	}
	public void randomlyPlaceWeapons(){
		
       for (int i=0; i<weaponCards.size(); i++) /*randomize room distribution*/
       {
    	   Card randomRoom = this.roomCards.get(i);
    	   this.weaponCards.get(i).setX(randomRoom.getX());
    	   this.weaponCards.get(i).setY(randomRoom.getY()); 
       }
	}
	public void  initializeSuspects()
	{
		this.suspectCards.add(new Card( "Miss Scarlet",4,6, "//ROPE", 'C'));
		this.suspectCards.add(new Card( "Col. Mustard",6,4, "//ROPE", 'C'));
		this.suspectCards.add(new Card( "Mrs. White",4,0, "//ROPE", 'C'));
		this.suspectCards.add(new Card( "Mr. Green",2,0, "//ROPE", 'C'));
		this.suspectCards.add(new Card( "Mrs. Peacock",0,2, "//peacock", 'C'));
		this.suspectCards.add(new Card( "Prof. Plum",0,4, "//Plum", 'C')); 
		//Collections.shuffle(this.suspectCards);
	       
	}
	public void initializeGame(ArrayList<Player> players)
	{	   
		initializeRooms();  
		initializeWeapons();
		randomlyPlaceWeapons();
	    initializeSuspects();
	    chooseAnAvatar();
	    
	    this.answer = new Solution(this.suspectCards.get(0),this.roomCards.get(0), this.weaponCards.get(0)); 
	    this.suspectCards.remove(0);
	    this.roomCards.remove(0);
	    this.weaponCards.remove(0);
	    
	    ArrayList<Card>allCards = new ArrayList<Card>();
        allCards.addAll(this.suspectCards);
        allCards.addAll(this.weaponCards);
        allCards.addAll(this.roomCards); 
	    dealCards(allCards,players);
	    
	    initializeHallways();//rooms and hallways are separated for distribution of 
	    
	    this.suspectCards.add(answer.Person);
	    this.roomCards.add(answer.Place);
	    this.weaponCards.add(answer.Weapon);
	    
	}
	/**
     * Deal the cards to all the players
     * @param cards
     */
    public void dealCards( ArrayList<Card> cards, ArrayList<Player> players){
    	
    	Iterator<Card> iter = cards.iterator();
    	
        while(iter.hasNext()){
        	for (Player p : players)
	    	{
                    p.addCard(iter.next());
	    	}       
        }
    }   
	public void chooseAnAvatar()
	{
		ArrayList<Card>characters = new ArrayList<Card>();//creating a deep copy of an array to modify
		characters.addAll(this.suspectCards);
		for(Player player:this.allPlayers)
		{		
			System.out.println("Please Select a Character: \n");
			for(int i=0;i<characters.size(); i++){
				System.out.println("\t"+(i+1)+ " "+ characters.get(i));
			}
		    int choice = respondToPlayerInput(characters.size());
		    player.setPlayerCharacter(characters.get(choice-1));
		    characters.remove(choice-1);
	   }
	}
	
	
	
	//Major Functions
	public int respondToPlayerInput(int choices)//options are the number of choices
	{
		Scanner userInput = new Scanner(System.in);
		int option = 0;
		boolean badValue =true;
		while (badValue)
		{
			try
			{
				option = userInput.nextInt();
				if(option > choices || option<1)
				{
					throw new Exception();
				}
				badValue = false;
			}
			catch(Exception e)
			{
				System.out.println("Invalid input. Select one of the numbers \n\nPlease try again... \n");
			}
		}
		return option;
	}
	public String respondToPlayerStringInput(ArrayList<String>Options)//options are the number of choices
	{
		Scanner userInput = new Scanner(System.in);
		String option = null;
		boolean badValue =true;
		while (badValue)
		{
			try
			{
				option = userInput.nextLine();
				if(Character.isDigit(option.charAt(0))){
					if(Integer.valueOf(option).intValue()>0&&Integer.valueOf(option).intValue()<=Options.size()){
						option = Options.get(Integer.valueOf(option)-1);
					}
				}
				else{
					option.toLowerCase();
				}
				if(option == null || !Options.contains(option) )
				{
					throw new Exception();
				}
				badValue = false;
			}
			catch(Exception e)
			{
				System.out.println("Invalid input. Please type the option name. \n\nPlease try again... \n");
			}
		}
		return option;
	}
	public ArrayList<String> printOptionsMenu(ArrayList<Position>validMoves, Player user)
	{
		ArrayList<String>options = new ArrayList<String>();
		options.add("accuse");
		if(!validMoves.isEmpty()&& !user.getTurnHistory().contains("move")){
			options.add("move");
    	}
    	if(!user.getTurnHistory().contains("suggest")&&!isInHallway(user.getPlayerCharacter().getX(), user.getPlayerCharacter().getY())){
    		options.add("suggest");
    	}
    	if(!user.getTurnHistory().isEmpty()||validMoves.isEmpty()){
    		options.add("pass");
    	}
    	System.out.println("What would you like to do?\n"
    			+ this.printStringArrayValues(options));
    	
    	return options;
	}

    /**
     * Gets all the possible moves from a given room
     * @param room
     * @return An ArrayList that contains all the possibles moves for a given room
     */
    public ArrayList<Position> getValidMoves(Position p){
    	ArrayList<Position>validMoves = new ArrayList<Position>();
        int x = p.getX();
        int y = p.getY();
        //  starting spots 
        // room is in the top row
        if(x == 0 || x == 6 || y == 0||y == 6)
        {
	        if(x == 0){
	            validMoves.add(new Position(x+1,y));
	        }
	        if(x == 6){
	            validMoves.add(new Position(x-1,y));
	        }
	        if(y == 0){
	            validMoves.add(new Position(x,y+1));
	        }
	        if(y == 6){
	            validMoves.add(new Position(x,y-1));
	        }    
        }
        else//not as starting point
        {
        //Hallway logic
        	if(this.isInHallway(x, y))
        	{ 
	            if(y == 1 || y == 3 || y == 5){
	                validMoves.add(new Position(x+1,y));
	                validMoves.add(new Position(x-1,y));
	            }
	            if(x == 1 || x == 3 || x == 5){
	                validMoves.add(new Position(x,y+1));
	                validMoves.add(new Position(x,y-1));
	            }
	        }
        	else//room logic
	        {
	        	
	        	switch(x)
	        	{
		        	case 1:	
		        		validMoves.add(new Position(x+1,y));
		        		break;
		        	case 3:	
		        		validMoves.add(new Position(x+1,y));
		        		validMoves.add(new Position(x-1,y));
		        		break;
		        	case 5:
		        		validMoves.add(new Position(x-1,y));
		        		break;
		        	default:
		        		break;
	        	}
	        	
	        	switch(y)
	        	{
		        	case 1:	
		        		validMoves.add(new Position(x,y+1));
		        		break;
		        	case 3:	
		        		validMoves.add(new Position(x,y+1));
		        		validMoves.add(new Position(x,y-1));
		        		break;	
		        	case 5:
		        		validMoves.add(new Position(x,y-1));
		        		break;
		        	default:
		        		break;
	        	}
	        	
	        	//call the contains secret tunnel function
	            //secret passages
	            if(isSecretPassage(p)){
	                validMoves.add(getSecretPassage(p));
	            }
	            //call is room blocked?
	            validMoves = removeBlockedRooms(validMoves);
	        }  
        }
        
        return validMoves;
    }
	
	public int confirmAccusation(){
        System.out.println("Make an accusation?");
        System.out.println("1. " + this.yesAndNo.get((0)));
        System.out.println("2. " + this.yesAndNo.get((1)));
                    
        int response = respondToPlayerInput(yesAndNo.size());
        
        return response;
    }
	
	
    public Solution takeTurn(Player player){
        printHeader(player);    	
        
     	ArrayList<Position>validMoves = new ArrayList<Position>();
        Position p = new Position(player.getPlayerCharacter().getX(),player.getPlayerCharacter().getY());
    	validMoves = getValidMoves(p);
     	
		ArrayList<String>choices = printOptionsMenu(validMoves,player);
		String option = this.respondToPlayerStringInput(choices);

                boolean done = false;
                
		Solution value = null;                  
                
                while(!done){
                    if (option.equals("accuse")){
                        if(confirmAccusation() == 2){
                            printOptionsMenu(validMoves,player);
                            option = this.respondToPlayerStringInput(choices);
                        }
                        else{
                            value = accuse();
                            player.getTurnHistory().add("accuse");
                            player.setIsTurn(false);
                            done = true;
                        }
                            
                    }
                    else if(option.equals("move")){
                            move(validMoves,player);
                            player.getTurnHistory().add("move");
                            done = true;
                    }
                    else if(option.equals("suggest")){			
                            value = suggest(p);
                            value.getPerson().updateLocation(p, true);
                            player.getTurnHistory().add("suggest");
                            player.getPlayerCharacter().setMovedHere(false);
                            done = true;
                    }
                    else if(option.equals("pass")){			
                            player.setIsTurn(false);
                            player.getTurnHistory().clear();
                            done = true;
                    }
                }
                    
        return value;
    }
	public Solution accuse(){
		Solution value;
		Card _w;
		Card _r;
		Card _s;
		System.out.println("Which weapon\n" + this.printArrayValues(this.weaponCards));
		_w = this.weaponCards.get(this.respondToPlayerInput(6)-1);
		
		ArrayList<Card>OnlyRooms = new ArrayList<Card>(); //We take out hallways to elimiate them as an option
		for(Card room: this.roomCards){						//for choosing
			if(!this.isInHallway(room.getX(), room.getY())){
				OnlyRooms.add(room);
			}
		}
		System.out.println("Which Room\n" + this.printArrayValues(OnlyRooms));
		_r = OnlyRooms.get(this.respondToPlayerInput(9)-1);
		System.out.println("Which Character\n" + this.printArrayValues(this.suspectCards));
		_s = this.suspectCards.get(this.respondToPlayerInput(6)-1);
		value = new Solution( _s, _r,_w);
        value.setAccuse(true);//logic behind accusation requires this boolean update
        
        return value;
	}
	public Solution suggest(Position p){
		Solution value;
		Card _w;
		Card _r;
		Card _s;
		System.out.println("Which weapon\n" + this.printArrayValues(this.weaponCards));
		_w = this.weaponCards.get(this.respondToPlayerInput(6)-1);
		_r = findRoomFromPosition(p);
		System.out.println("Which Character\n" + this.printArrayValues(this.suspectCards));
		_s = this.suspectCards.get(this.respondToPlayerInput(6)-1);
		value = new Solution( _s, _r,_w );
		return value;
	}
	public void move(ArrayList<Position>validMoves, Player player){
		System.out.println("Please select your requested location:\n" + this.printPositionArrayValues(validMoves));
		int choice = this.respondToPlayerInput(validMoves.size());                  
		player.getPlayerCharacter().updateLocation(validMoves.get(choice-1), true);
	}
    public boolean disproveSuggestion(Player curr, Player next, Solution guess){
    	ArrayList<Card>disprovingCards = cardsToShow(next,guess);
    	boolean disproved = false;
    	if(!disprovingCards.isEmpty()){
    		disproved = true;
    		next.displayPopUp("Which card would you like to show " + 
    				next.toString() + " to disprove their suggestion:\n" +
    				printArrayValues(disprovingCards));
    		int choice = this.respondToPlayerInput(disprovingCards.size());
    		curr.displayPopUp(disprovingCards.get(choice-1).toString());
    	}
    	return disproved;
    }
    
    //Utility Functions
    public void OrganizePlayerTurns(){//This function reorders AllUsers to be in character order.
    	ArrayList<Player>RearrangeOrder = new ArrayList<Player>();
    	String [] array = {"Miss Scarlet","Col. Mustard","Mrs. White","Mr. Green","Mrs. Peacock", "Prof. Plum"};
    	for(int i=0; i<array.length; i++){
    		for(int j=0; j<this.allPlayers.size(); j++){
	    		if(this.allPlayers.get(j).getPlayerCharacter().toString().equals(array[i])){
		    		RearrangeOrder.add(this.allPlayers.get(j));
		    		break;
	    		}
    		}
    	}
    	this.allPlayers = RearrangeOrder;
    }
	public void printHeader(Player player)
	{
		Card Character = player.getPlayerCharacter();
		Card CurrRoom = findRoomFromPosition(new Position(Character.getX(), Character.getY()));
		if (CurrRoom == null){
			System.out.println("****************************\n"
			        +  "** Player "+ Character.toString() + " MENU **\n"
			        + "** You are in X: " + String.valueOf(Character.getX()) + " Y: " + String.valueOf(Character.getY()) + "     **\n"
			        +  "****************************\n");
		}
		else{
			System.out.println("****************************\n"
	        +  "** Player "+ Character.toString() + " MENU **\n"
	        + "** You are in X: " + String.valueOf(Character.getX()) + " Y: " + String.valueOf(Character.getY()) + "     **\n"
	        + "**   Room " + CurrRoom.toString() + "        **\n"
	        +  "****************************\n");
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
        ArrayList<Card>pHand = p.getHand();
        ArrayList<Card>disprovingCards = new ArrayList<Card>();
        if(pHand.contains(s.getPerson())){
        	disprovingCards.add(s.getPerson());
        }
        if(pHand.contains(s.getWeapon())){
        	disprovingCards.add(s.getWeapon());
        }
        if(pHand.contains(s.getPlace())){
        	disprovingCards.add(s.getPlace());
        }

        return disprovingCards;    
    }
	public Card findRoomFromPosition(Position p){
		Card room = null;
		for(Card c: this.roomCards){
			if(c.getX()==p.getX()){
				if(c.getY()==p.getY()){
					room = c;
					break;
				}
			}
		}
		return room;
	}
	public void notifyAllPlayers(String guess){
		for(Player p : allPlayers)
		{
				p.displayPopUp(guess);
		}
	}
	public int nextPlayerIndex(int index){
		int nextPlayer;
		if(	index == allPlayers.size()-1){
			nextPlayer = 0;
		}
		else{
			nextPlayer = index+1;
		}
		return nextPlayer;
	}
    /**
     * SecretPassage getter
     * @param room
     * @return the secret passage
     */
    public boolean isSecretPassage(Position p){
    	boolean hasSecret = false;
    	if(p.getX()==1||p.getX()==5)
    	{
    		if(p.getY()==1||p.getY()==5)
    		{
    			hasSecret = true;
    		}
    	}
       return hasSecret;
    }
    public Position getSecretPassage(Position p){
    	Position secretRoom = new Position(1,1);//
    	int x = p.getX();
    	int y = p.getY();
    	if(x==1){
    		secretRoom.setX(5);
    	}
    	else {
    		secretRoom.setX(1);
    	}
    	if(y==5){
    		secretRoom.setY(1);
    	}
    	else{
    		secretRoom.setY(5);
    	}
       return secretRoom;
    }
    ArrayList<Position> removeBlockedRooms( ArrayList<Position>validMoves)
    {
    	for(Player player : this.allPlayers)
    	{
    		Position p = new Position (player.getPlayerCharacter().getX(),player.getPlayerCharacter().getY());
    		if(isInHallway(p.getX(),p.getY()))
    		{
    			if(validMoves.contains(p))
    			{
    				validMoves.remove(p);
    			}
    		}
    		
    	}
    	return validMoves;
    }
	boolean isInHallway(int x, int y)
	{
		boolean hallway = false;
		if(x==2||x==4)
		{
			hallway = true;
		}
		if(y==2||y==4)
		{
			hallway = true;
		}
		return hallway;
	}
	public String printArrayValues(ArrayList<Card>array){
		StringBuilder str = new StringBuilder();
		for(int i=0; i<array.size(); i++){
			str.append(String.valueOf(i+1)+ ". " + array.get(i).toString()+"\n");
		}
		return str.toString();
	}
	public String printPositionArrayValues(ArrayList<Position>array){
		StringBuilder str = new StringBuilder();
		for(int i=0; i<array.size(); i++){
			str.append(String.valueOf(i+1)+ ". " + array.get(i).toString()+"\n");
		}
		return str.toString();
	}
	public String printStringArrayValues(ArrayList<String>array){
		StringBuilder str = new StringBuilder();
		for(int i=0; i<array.size(); i++){
			str.append(String.valueOf(i+1)+ ". " + array.get(i).toString()+"\n");
		}
		return str.toString();
	}
	public void initPlayerOptions(Player user){
		/*ArrayList<Position>validMoves = new ArrayList<Position>();
		Position p = new Position (user.getPlayerCharacter().getX(),user.getPlayerCharacter().getY()); 
		validMoves = getValidMoves(p);
     	
		int choices = printOptionsMenu(validMoves, p );*/
	}
	
	/**
	 * Add a player to the game board.
	 * @param player
	 */
	public void addPlayer(Player player) {
		this.allPlayers.add(player);
	}
	
	//Main Methods
    public void play()
	{
		boolean gameOver = false;
		OrganizePlayerTurns();
		while(!gameOver)
		{
			for (int i=0; i<allPlayers.size();i++)
			{
				Player player = allPlayers.get(i);
				if(player.hasMadeAccusation()){
					continue;
				}
				player.setIsTurn(true);
				notifyAllPlayers("Its " + player.toString() + "'s ("+ player.getPlayerCharacter().toString()+ ")turn.");

				while (player.isTurn())
				{
			    	Solution guess = takeTurn(player);
			    	boolean disproved = true;
			    	
			    	//update character position
			    	if(guess != null && !guess.getAccuse()){
			    		notifyAllPlayers(guess.toString());
			    		for(int j=0; j<allPlayers.size()-1;j++){//minus one is to not include guesser in disprover cycle
			    			Player next = allPlayers.get(nextPlayerIndex(i+j));
			    			
			    			disproved = disproveSuggestion(player,next,guess);
			    			if(disproved)	
			    				break;
			    		}
				    	if(!disproved){
				    		notifyAllPlayers("Hmmmm. that is an interesting suggestion " + player.toString()+"\n");
				    	}
		    		}
					if(guess!=null && guess.getAccuse())
					{
						if(guess.equals(answer)){
							notifyAllPlayers("The game is Over." + player.toString() + "Solved the solution.\n"
									+ "The Solution was " + guess.toString());
							gameOver = true;
							
						}
						else{
							//kill game player functionality
							player.sethasMadeAccusation(true);
							notifyAllPlayers(player.getPlayerName() + "(" +player.getPlayerCharacter().toString()+ ")" 
							+ "accused wrong and can no longer win." +"\n");
						}
					}
				}
			}
		}
	}
}
