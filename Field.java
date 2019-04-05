package battleship;

import java.util.Scanner;

public class Field {
	public static final int row = 11;
	public static final int column = 11;
	public static int maxCell = row * column;
	public static final int maxShip = 2;
	private Ship[] ships = new Ship[maxShip];
	private int[] isShooted =  new int[maxCell]; //0-not shot,1-shoot ship,2-shoot sea,3-ship exist
	private int[] nSize= {1,2,1,1};
	
	
	public Field() {
		for(int i=0;i<maxCell;i++) isShooted[i]=0;
		Position pos=new Position();
		int size=0;
		char orient=0;
		boolean stat;
		boolean status;
		
		Scanner input = new Scanner(System.in);
		for(int i=0;i<maxShip;i++) {
			status=true;
			while(status) {
				stat=true;
				while(stat) {
					System.out.println("Give ship size: "); 
					size=input.nextInt();
					if(size<2 || size>5 || nSize[size-2]==0) {
						System.out.println("Wrong size or inappropriate input!");
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
					stat=!pos.inBorder();
				}
				stat=true;
				while(stat) {
					System.out.println("Give orientation: "); 
					orient=input.next().charAt(0);
					if(orient=='v' || orient=='V' || orient=='h' || orient=='H')
						stat=false;
				}	
				
				ships[i]=new Ship(pos, size, orient);
				
				status=isCrashed(pos, size, orient,i);
			}	
//				Ship compareShip=ships[0];
//				if(i>0) {
//					for(int k=0;k<compareShip.getSize();k++) {
//						for(int j=0;j<ships[i].getSize();i++) {
//							if(compareShip.getPosition()[k].x==ships[i].getPosition()[j].x && compareShip.getPosition()[k].y==ships[i].getPosition()[j].y) {
//							 	System.out.println("Ships are intersected\n"); 
//							 	i=i-1;
//							 	isCrashed=true;
//							}
//							else {
//								compareShip=ships[i];
//								isCrashed=false;
//							}
//							
//						}
//					}
//				}
//			}				
		}
		for (int i = 0; i < 10; ++i) System.out.println();
	}
	
	public boolean isCrashed(Position position,int size,char orient,int maxShip) {
		for(int i=0;i<maxShip;i++) {
			for(int j=0;j<size;j++) {
				for(int k=0;k<ships[i].getSize();k++) {
					if(orient=='v' || orient=='V') {
						if(position.x==ships[i].getPosition()[k].x && position.y==ships[i].getPosition()[k].y+j) {
							System.out.println("Ships are intersect");
							return true;
						}
					}
					if(orient == 'H' || orient=='h') {
						if(position.x==ships[i].getPosition()[k].x+j && position.y==ships[i].getPosition()[k].y) {
							System.out.println("Ships are intersect");
							return true;
						}
					} 
				}
			}
		}	
		return false;
	}
	
	public int isFired(Position pos) {
		if(!pos.inBorder()) {
			System.out.println("Positions are in out!!");
			return 0;
		}
		if(isShooted[column*pos.y+pos.x]!=0 && isShooted[column*pos.y+pos.x]!=3){
			System.out.println("Cannot shoot more than one");
			return 0;
		}
		for(int i=0;i<maxShip;i++) {
			if(ships[i].isFired(pos)==true) { 
				isShooted[column*pos.y+pos.x]=1;
				System.out.println("Ship has been shot!");
				return 1;
			}	
		}
		isShooted[column*pos.y+pos.x]=2;
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
