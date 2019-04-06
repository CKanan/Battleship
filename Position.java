package battleship;

import java.util.Scanner;

public class Position {
	
	public int x,y;
	
	void givePosition () {
		Scanner sc = new Scanner(System.in);
		this.x = sc.nextInt();
		this.y = sc.nextInt();
	}
	
	public Position(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position() {
		this.x = 0;
		this.y = 0;
	}
	
	public boolean inBorder() {
		if(this.x < Field.column && this.x >= 0 && this.y < Field.row && this.y >= 0 )
			return true;
		return false;
	}
}

