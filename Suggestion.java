/*This class will be used as 
 * 
 * 
 * */
public class Suggestion
{
	String Person;
	Rooms Place;
	String Weapon;
	
	public Suggestion() 
	{
		Person = null;
		Place = null;
		Weapon = null;		
	}
	
	public Suggestion(String _person, Rooms _place, String _weapon)
	{
		this.Person = _person;
		this.Place = _place;
		this.Weapon = _weapon;	
	}

}
