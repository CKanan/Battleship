package battleship;

public class User {
	private Field field = new Field();
	public  String name;
	
	public User(String name) {
		this.name=name;
	}
		
	public boolean isDestroyed() {
		return this.field.isDestroyed();
	}
	
	public int isFired(Position pos) {
		return this.field.isFired(pos);
	}
	
	public boolean play(User a) {
		Position pos=new Position();
		int stat=0;
		
		System.out.println(this.name+" give position to hit: ");
		while(stat==0) {
			pos.givePosition();
			stat=a.isFired(pos);
		}
		if(a.isDestroyed()) {
			System.out.println(this.name+" won!");
		}
		return a.isDestroyed();
	}
	
}
