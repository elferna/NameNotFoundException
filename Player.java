import javax.swing.JOptionPane;


public class Player implements PlayerInterface{

	String Character = null;
	Boolean turn = false;
	int Location = -1;
	public Player() {	}
	
	
	public Suggestion takeTurn()
	{
		return new Suggestion("Person",	Rooms.Ballroom," Weapon");
	}
	
	public void displayPopUp(Suggestion temp)
	{
		//pop up menu
		JOptionPane.showMessageDialog(null, "Consider Yourself notifed of" + temp.toString());
		
	}
}
