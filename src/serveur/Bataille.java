package serveur;

import java.util.ArrayList;
import java.util.List;

import serveur.ThreadGame.ThreadJoueur;

public class Bataille extends Thread {
	private ThreadJoueur ThJoueur1;
	private ThreadJoueur ThJoueur2;
	
	public Bataille(ThreadJoueur thJ1,ThreadJoueur thJ2) {
		this.ThJoueur1 = thJ1;
		this.ThJoueur2 = thJ2;
	}
	
	
	
	@Override
	public void run() {
		
		ThJoueur1.pwJoueur.println("Ca marche");
	}

}
