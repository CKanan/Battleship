package battleship;

import java.util.Scanner;

public class Field {
	
	private  int row = 11;
	private int column = 11;
	private int maxShip = 1;
	private Ship[] ships = new Ship[maxShip];
	private int[] nSize= {1,2,1,1};
	private int[][] grid = new int[column][row];
	
	public Field() {
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++)
				grid[i][j]=0;
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
						System.out.println("Give "+(i+1)+" ship size: "); 
						size=input.nextInt();
						if(size<2 || size>5 || nSize[size-2]==0) {
							System.out.println("Wrong size or inappropriate input!\nTry Again");
						}
						else {
							nSize[size-2]=nSize[size-2]-1;
							stat=false;
						}
					}
					stat=true;	
					while(stat) {
						System.out.println("Give beginnig position of ship: ");
						int x=input.nextInt();
						int y=input.nextInt();
						pos=new Position(x, y);
						if(!pos.inBorder(column,row)) 
							System.out.println("Positions have to between [0,10]!");
						stat=!pos.inBorder(column,row);
					}
					stat=true;
					while(stat) {
						System.out.println("Give orientation: "); 
						orient=input.next().charAt(0);
						if(orient=='v' || orient=='V' || orient=='h' || orient=='H')
							stat=false;
						else System.out.println("Orientation have to (v,V or h,H)");
					}	
					
					ships[i]=new Ship(pos, size, orient,grid);

					status=isCrashed(pos, size, orient,i);
				}
				st=ShipInOut(ships[i],size);
			}
		}
		printGrid();
//		for (int i = 0; i < 50; ++i) System.out.println();
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
	
	public boolean isCrashed(Position position,int size,char orient,int maxship) {
		for(int i=0;i<maxship;i++) {
			for(int j=0;j<size;j++) {
				for(int k=0;k<ships[i].getSize();k++) {
					if(orient=='v' || orient=='V' || orient=='H' || orient=='h') {
						if((position.x==ships[i].getPosition()[k].x && position.y+j==ships[i].getPosition()[k].y) || (position.x+j==ships[i].getPosition()[k].x && position.y==ships[i].getPosition()[k].y)) {
							System.out.println("Ships are intersect\nTry Again!");
							nSize[size-2]=nSize[size-2]+1;
							return true;
						}
					}
				}
			}
		}	
		return false;
	}
	
	public boolean ShipInOut(Ship ship, int size) {
		for(int i=0;i<ship.getSize();i++) {
			if(!ship.getPosition()[i].inBorder(column,row)) { 
				nSize[size-2]=nSize[size-2]+1;
				System.out.println("Ship is in out!\nTry again");
				return true;
			}
		}
		return false;
	}
	
	public int isFired(Position pos) {
		if(!pos.inBorder(column,row)) {
			System.out.println("Position is in out!");
			return 0;
		}
		if(grid[pos.y][pos.x]!=0 && grid[pos.y][pos.x]!=3){
			System.out.println("Cannot shoot more than one");
			return 0;
		}
		for(int i=0;i<maxShip;i++) {
			if(ships[i].isFired(pos)==true) { 
				grid[pos.y][pos.x]=1;
				System.out.println("Ship has been shot!");
				return 1;
			}	
		}
		grid[pos.y][pos.x]=2;
		System.out.println("Ship has been missed!");
		return 2;
	}
	
	public boolean isDestroyed(){
		for(int i=0;i<maxShip;i++) {
			if(ships[i].isDestroyed()==true) return true;
		}
		return false;
	}
}
