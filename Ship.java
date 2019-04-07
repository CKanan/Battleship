package battleship;

import java.util.Scanner;

public class Ship {
	
	private int size;
	private char orientation;
	private Position[] positions;
	
	public Ship(Position position,int length,char orientation,int[][] grid) {
		this.positions = new Position[length];
		this.size = length;
		this.orientation = orientation;
		
		for(int i=0;i<this.size;i++) {
			positions[i] = new Position();
		}
		for(int i=0;i<this.size;i++) {
			if(orientation == 'V' || orientation=='v') {
				positions[i].x = position.x;
				positions[i].y = position.y+i;
			}
			if(orientation == 'H' || orientation=='h') {
				positions[i].x = position.x+i;
				positions[i].y = position.y;
			}
				grid[positions[i].y][positions[i].x]=3;
		}
	}
		
	public Position[] getPosition() {return this.positions;}
	public int getSize() {return this.size;}
	
	boolean isFired(Position pos) {
		for(int i=0;i<positions.length;i++) {
			if(positions[i].x == pos.x && positions[i].y == pos.y) {
				this.size=this.size-1;
				return true;
			}
		}
		return false;
	}
	
	boolean isDestroyed() {
		if(this.size == 0) return true;
		return false;
	}
}
