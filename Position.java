package battleship;

import java.util.Scanner;

public class Position {
	
	public int x,y;
	
	public Position(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position() {
		this.x = 0;
		this.y = 0;
	}
	
	public boolean inBorder(int c,int r) {
		if(this.x < c && this.x >= 0 && this.y < r && this.y >= 0 )
			return true;
		return false;
	}
}

