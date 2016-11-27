
public class Position {
	private int x;
	private int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		
		return "("+String.valueOf(this.x)+","+String.valueOf(this.y)+")";
	}
	@Override
	public boolean equals(Object obj) {
		boolean equivalent = false;
		if (this == null && obj == null){
			equivalent = true;
		}
		else{
			try{
				Position p = (Position)obj;
				if(p.getX()==this.getX()){
					if(p.getY()==this.getY()){
						equivalent = true;
					}
				}
			}
			catch(Exception e){
				System.out.println("incompatible position type");
			}
		}
		return equivalent;
	}
	
	
}
