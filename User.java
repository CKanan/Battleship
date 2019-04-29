package battleship;

import java.util.Scanner;

public class User {
	
	private  String name;
	private Field field;
	
	public User(String name) {
		this.name=name;
		System.out.println("USER: "+this.name);
		field=new Field(this.name);
	}
		
	public boolean isDestroyed() {
		return this.field.isDestroyed();
	}
	
	public int isFired(Position pos) {
		return this.field.isFired(pos);
	}
	
	public void printGrid() {
		System.out.print("\n     "+this.name+"'s field");
		field.printGrid();
	}
	
	public boolean play(User a) {
		Position pos=new Position();
		int stat=0;
		
		System.out.println("\n"+this.name+", give position to hit: ");
		while(stat==0) {
			Scanner sc = new Scanner(System.in);
			int x=0,y=0;
			if(name=="BOT") {
				x=this.field.getRandomIntegerBetweenRange(0,field.getColumn()-1);
				y=this.field.getRandomIntegerBetweenRange(0,field.getRow()-1);
			}
			else {
				x=sc.nextInt();
				y=sc.nextInt();
			}	
			pos=new Position(x, y);
			stat=a.isFired(pos);
		}
		if(name=="BOT") a.printGrid();
		if(a.isDestroyed()) {
			System.out.println(this.name+" WON!");
		}
		return a.isDestroyed();
	}	
}
