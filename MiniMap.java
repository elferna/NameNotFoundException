import java.util.Scanner;


public class MiniMap {

	public static void main(String[] args) {
		
		int SIZE = 5;
		
		String map[][] = new String[SIZE][SIZE];
			
		boolean keepPlaying = true;
		
		String PLAYER = "P";
		
		int playerXPos = 0;
		int playerYPos = 4;
		
		map = initializeMap(SIZE, SIZE, playerXPos, playerYPos, PLAYER);
		
		Scanner input = new Scanner(System.in);
		
		String move;
		
		String response;
		
		while(keepPlaying){
			
			printMap(map, SIZE, SIZE);
			
			System.out.println("Move: W = up, S = right, A = left, Z = down");
			move = input.next();
			
			if(move.equals("W")){
				if(playerXPos > 0){
					map[playerXPos - 1][playerYPos] = PLAYER;
	                map[playerXPos][playerYPos] = "_";
	                playerXPos -= 1;
                }       
                else
                {
              	  cantGoThroughWall();
                }
				
			}
			else if(move.equals("S")){
				if(playerYPos < (SIZE - 1))
	              {
	                  map[playerXPos][playerYPos + 1] = PLAYER;
	                  map[playerXPos][playerYPos] = "_";
	                  playerYPos += 1;
	              }
	              else
                  {
              	      cantGoThroughWall();
                  }   	
			}
			else if(move.equals("A")){
				if(playerYPos > 0)
	            {
	                  map[playerXPos][playerYPos - 1] = PLAYER;
	                  map[playerXPos][playerYPos] = "_";
	                  playerYPos += 1;
	            }
				printMap(map, SIZE, SIZE);
				
			}
			else if(move.equals("Z")){
				 if(playerXPos < (SIZE - 1))
	              {
	                  map[playerXPos + 1][playerYPos] = PLAYER;
	                  map[playerXPos][playerYPos] = "_";
	                  playerXPos += 1;
                 }

	              else
                 {
               	  cantGoThroughWall();
                 }
				
			}

			
		

		}
		

	}
	
	public static void printMap(String[][]map, int sizeX, int sizeY)
    {//Loop to print the map
        for(int i = 0; i < sizeX; i++)
        {
            for(int j = 0; j < sizeY; j++)
		    {   
		        System.out.print(map[i][j]);
		    }
		    System.out.println();
	    }
        System.out.println();
    }
	
	public static String[][] initializeMap(int sizeX, int sizeY, int playerPosX, int playerPosY, String player){

	    String[][] map = new String[sizeX][sizeY];
	    //Loop to put "." on the whole map
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++){
				map[i][j] = "_";
			}
		}

		map[playerPosX][playerPosY] = player;
		
		return map;
	}
	
	public static void cantGoThroughWall()
    {
    	System.out.println("Can't go through the wall.");
    }
}
