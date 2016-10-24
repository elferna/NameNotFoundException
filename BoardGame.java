import java.util.*;

public class BoardGame {

	List<Player>AllUsers;
	
	public BoardGame() {
		List<Player>AllUsers = new ArrayList<Player>();
		
		//AllUsers.add(e)
	}

	public static void main(String[] args) {
		
		for (Player user : AllUsers)
		{
			Suggestion guess = user.takeTurn();
			for(Player otherUsers : AllUsers)
			{
				if (otherUsers.equals(user))
				{
					continue;
				}
				if (!guess.equals(null))
				{
					otherUsers.displayPopUp(guess);
				}
			}
		}
	}
	public void header(int i)
	{
		System.out.println("****************************\n"
						+  "**      Player "+i+" MENU	  **\n"
						+  "****************************\n");
	}
	public void inUnblockedHallwayOption(int i)
	{
		header(i);
		System.out.println("Please Select what you would like to do:\n"
						+  "1. Move into a room\n"
						+  "2. Make an accusation\n");
		
		Scanner userInput = new Scanner(System.in);
		int option = 0; 
		try
		{
			option = userInput.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Invalid input\n\nPlease try again \n");
		}
		switch(option)
		{
			case 1: goToRoom();
				break;
			case 2: accusation();
				break;
			default: 
				System.out.println("Invalid input\n\nPlease try again \n");
				break;
		}
		

		
	}
}
