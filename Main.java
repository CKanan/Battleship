package battleship;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
	
		System.out.println("Welcome to Naval Battleship!\n");
		
		User Kanan=new User("Kanan");
		
		System.out.println("Choose your opponent options:\n true-for USER,false-for BOT");
		Scanner sc = new Scanner(System.in);
		boolean opponent=sc.nextBoolean();
	
		if(opponent) {
			User Nazir=new User("Nazir");
			while(true) {
				if(Kanan.play(Nazir)) break;
				if(Nazir.play(Kanan)) break;
			}
		}
		else {
			User BOT=new User("BOT");
			while(true) {
				if(Kanan.play(BOT)) break;
				if(BOT.play(Kanan)) break;
			}
		}
	}

}
