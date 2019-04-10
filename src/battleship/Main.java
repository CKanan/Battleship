package battleship;

public class Main {
	
	public static void main(String[] args) {
	
		System.out.println("Welcome to Naval Battleship!\n");
	
		User Kanan=new User("Kanan");
		User Nazir=new User("Nazir");
		
		while(true) {
			if(Kanan.play(Nazir)) break;
			if(Nazir.play(Kanan)) break;
		}
	}

}
