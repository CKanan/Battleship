package battleship;

import java.util.Random;
import java.util.Scanner;

public class Field {
	
	private int border=1;
	private  int row = 10;
	private int column = 10;
	private int maxShip = 5;
	private Ship[] ships = new Ship[maxShip];
	private int[] nSize= {1,2,1,1};
	private char[][] grid = new char[row][column];
	
	public int getColumn() {return this.column;}
	public int getRow() {return this.row;}
	
	Random rand =new Random(); 
	
	public int getRandomIntegerBetweenRange(int min,int max){
		int x=rand.nextInt(max - min + 1) + min;
	    return x;
	}
	
	public char getRandomChar() {
		char c = rand.nextBoolean() ? 'v' : 'h';
		return c;
	}
	
	public Field(String name) {
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++)
				grid[i][j]='.';
		}
		Position pos=new Position();
		int size=0;
		char orient=0;
		boolean stat,status,st;
		
		Scanner input = new Scanner(System.in);
		for(int i=0;i<maxShip;i++) {
			st=true;
			while(st) {
				status=true;
				while(status) {
					stat=true;
					while(stat) {
						if(name=="BOT") {
							size=getRandomIntegerBetweenRange(2, 5);
						}
						else{
							System.out.println("Give "+(i+1)+" Ship size: "); 
							size=input.nextInt();
						}	
						if(size<2 || size>5 || nSize[size-2]==0) {
							if(name!="BOT") System.out.println(" Wrong size or inappropriate input!");
						}
						else {
							nSize[size-2]=nSize[size-2]-1;
							stat=false;
						}
					}
					stat=true;	
					while(stat) {
						int x=0,y=0;
						if(name=="BOT") {
							x=getRandomIntegerBetweenRange(0,column-1);
							y=getRandomIntegerBetweenRange(0, row-1);
						}
						else {
							System.out.println("Give beginnig position of ship: ");
							x=input.nextInt();
							y=input.nextInt();
						}	
						pos=new Position(x, y);
						if(!pos.inBorder(column,row)) 
							System.out.println(" Positions have to between [0,"+(row-1)+"]!");
						stat=!pos.inBorder(column,row);
					}
					stat=true;
					while(stat) {
						if(name=="BOT") {
							orient=getRandomChar();
						}
						else {
							System.out.println("Give orientation: "); 
							orient=input.next().charAt(0);
						}	
						if(orient=='v' || orient=='V' || orient=='h' || orient=='H')
							stat=false;
						else System.out.println(" Orientation have to (v,V or h,H)");
					}	
					status=isCrashed(pos, size, orient,i,name);
				}
				st=ShipInOut(pos, size, orient,name);
			}
			ships[i]=new Ship(pos, size, orient,grid);
		}
		if(name=="BOT") System.out.println("\n YOU CAN'T SEE BOT's FIELD\n"); 
		else printGrid();
	}
	
	public void printGrid() {
		System.out.println();
		for(int i=0;i<column;i++) {
			for(int j=0;j<row;j++)
				System.out.print(" "+grid[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	public boolean isCrashed(Position position,int size,char orient,int maxship,String name) {
		for(int i=0;i<maxship;i++) {
			for(int j=0;j<size;j++) {
				for(int k=0;k<ships[i].getSize();k++) {
					if(orient=='v' || orient=='V') {
						if(position.x==ships[i].getPosition()[k].x && position.y+j==ships[i].getPosition()[k].y) {
							if(name!="BOT") System.out.println(" Ships are intersect\n Try Again!");
							nSize[size-2]=nSize[size-2]+1;
							return true;	
						}
						if((position.y+j+border==ships[i].getPosition()[k].y && position.x==ships[i].getPosition()[k].x) || (position.y+j-border==ships[i].getPosition()[k].y && position.x==ships[i].getPosition()[k].x) || (position.x+border==ships[i].getPosition()[k].x && position.y+j-border==ships[i].getPosition()[k].y) || (position.x+border==ships[i].getPosition()[k].x && position.y+j+border==ships[i].getPosition()[k].y) || (position.x-border==ships[i].getPosition()[k].x && position.y+j-border==ships[i].getPosition()[k].y) || (position.x-border==ships[i].getPosition()[k].x && position.y+j+border==ships[i].getPosition()[k].y)) {
							if(name!="BOT") System.out.println(" Ships are side by side\n Try Again!");
							nSize[size-2]=nSize[size-2]+1;
							return true;
						}
					}
					if(orient=='H' || orient=='h') {
						if(position.x+j==ships[i].getPosition()[k].x && position.y==ships[i].getPosition()[k].y) {
							if(name!="BOT") System.out.println(" Ships are intersect\n Try Again!");
							nSize[size-2]=nSize[size-2]+1;
							return true;
						}
						if((position.x+j+border==ships[i].getPosition()[k].x && position.y==ships[i].getPosition()[k].y) || (position.x+j-border==ships[i].getPosition()[k].x && position.y==ships[i].getPosition()[k].y) || (position.x+j+border==ships[i].getPosition()[k].x && position.y-border==ships[i].getPosition()[k].y) || (position.x+j+border==ships[i].getPosition()[k].x && position.y+border==ships[i].getPosition()[k].y) || (position.x+j-border==ships[i].getPosition()[k].x && position.y-border==ships[i].getPosition()[k].y) || (position.x+j-border==ships[i].getPosition()[k].x && position.y+border==ships[i].getPosition()[k].y)) {
							if(name!="BOT") System.out.println(" Ships are side by side\n Try Again!");
							nSize[size-2]=nSize[size-2]+1;
							return true;
						}
					}
				}
			}
		}	
		return false;
	}
	
	public boolean ShipInOut(Position pos, int size,char orientation,String name) {
		for(int i=0;i<size;i++) {
			if(orientation == 'V' || orientation=='v') {
				if(pos.x<0 || pos.x>=column || pos.y+i<0 || pos.y+i>=row) {				
					nSize[size-2]=nSize[size-2]+1;
					if(name!="BOT") System.out.println(" Ship is in out!\n Try again");
					return true;	
				}	
			}
			if(orientation == 'H' || orientation=='h') {
				if(pos.x+i<0 || pos.x+i>=column || pos.y<0 || pos.y>=row) {
					nSize[size-2]=nSize[size-2]+1;
					if(name!="BOT") System.out.println(" Ship is in out!\n Try again");
					return true;
				}	
			}
		}
		return false;		
	}
	
	public int isFired(Position pos) {
		if(!pos.inBorder(column,row)) {
			System.out.println(" Position is in out!\n Choose another position:");
			return 0;
		}
		if(grid[pos.y][pos.x]!='.' && grid[pos.y][pos.x]!='A' && grid[pos.y][pos.x]!='B' && grid[pos.y][pos.x]!='C' && grid[pos.y][pos.x]!='D'){
			System.out.println(" Cannot shoot more than one\n Choose another position:");
			return 0;
		}
		for(int i=0;i<maxShip;i++) {
			if(ships[i].isFired(pos)==true) { 
				grid[pos.y][pos.x]='X';
				System.out.println(" Ship has been shot!");
				return 1;
			}	
		}
		grid[pos.y][pos.x]='O';
		System.out.println(" Ship has been missed!");
		return 2;
	}
	
	public boolean isDestroyed(){
		for(int i=0;i<maxShip;i++) {
			if(!ships[i].isDestroyed()) return false;
		}
		return true;
	}
}
