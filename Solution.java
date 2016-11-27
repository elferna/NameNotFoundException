
public class Solution {

	Card Person;
	Card Place;
	Card Weapon;
	boolean accuse;
		
	public Card getPerson() {
		return Person;
	}

	public void setPerson(Card person) {
		Person = person;
	}

	public Card getPlace() {
		return Place;
	}

	public void setPlace(Card place) {
		Place = place;
	}

	public Card getWeapon() {
		return Weapon;
	}

	public void setWeapon(Card weapon) {
		Weapon = weapon;
	}

	public boolean getAccuse() {
		return accuse;
	}

	public void setAccuse(boolean accuse) {
		this.accuse = accuse;
	}

	public Solution() 
	{
		this.Person = null;
		this.Place = null;
		this.Weapon = null;
		this.accuse = false;
	}
	
	public Solution(Card _person, Card _place, Card _weapon)
	{
		this.Person = _person;
		this.Place = _place;
		this.Weapon = _weapon;	
		this.accuse = false;
	}

	@Override
	public String toString() {
		return this.Person.toString()+" did it \n" + "in the " + this.Place.toString() + " with a " + this.Weapon.toString()+"\n";
	}

	@Override
	public boolean equals(Object obj) {
		boolean equivalent = false;
		try{
			Solution sln = (Solution)obj;
			if(this.Person!=null){	
				if(this.Person.equals(sln.Person)){
					if(this.Place.equals(sln.Place)){
						if(this.Weapon.equals(sln.Weapon)){
							equivalent = true;
						}
					}
				}	
			}
			else{
				if(this.Person == null && this.Place == null && this.Weapon==null){
					equivalent = true;
				}
			}
		}
		catch(Exception e){
			System.out.println("incompatible obj types");
		}
		return equivalent;
	}


}
