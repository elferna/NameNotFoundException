
public enum Rooms {
	Dining, Library, Billiards, Kitchen, Ballroom, 
	Lounge, Conservatory, Study, Hall;
        
        @Override
    public String toString(){
        String name = "";
        switch(this){
            case Dining:
                name = "Dining Room";
            case Library:
                name =  "Library";
            case Billiards:
                name =  "Billiard Room";
            case Kitchen:
                name =  "Kitchen";
            case Ballroom:
                name = "Ballroom";
            case Lounge:
                name =  "Lounge";
            case Conservatory:
                name = "Conservatory";
            case Study:
                name = "Study";
            case Hall:
                name = "Hall";
        }
        return name;
    }
}