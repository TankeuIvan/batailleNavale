package serveur;

import serveur.ThreadGame.ThreadJoueur;
import client.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Commandes extends Thread {
	private List<ThreadJoueur> listeJoueur = new ArrayList<ThreadJoueur>();
	private String message;
	private ThreadJoueur ThJoueur;
	
	public Commandes(List<ThreadJoueur> liste, String message, ThreadJoueur thJ) {
		this.listeJoueur = liste;
		this.message = message;
		this.ThJoueur =thJ;
	}
	
	
	public void broadCast(String message, Socket socketJ) {
		try {
			for(ThreadJoueur player : listeJoueur) {
				if(player.joueur.socketJoueur!=socketJ) {
					PrintWriter printWriter = new PrintWriter(player.joueur.socketJoueur.getOutputStream(),true);
					String messageJoueur = "\n"+ThJoueur.joueur.nomJoueur+": "+message;
					printWriter.println(messageJoueur);
					
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public void buildShip(String message) {
		if(message.charAt(0)=='+') {
			ThJoueur.grilleJoueur.buildCoordGrille(message);
			ThJoueur.grilleJoueur.showGrille();
		}
	}
	
	
	
	@Override
	public void run() {
		while (ThJoueur.grilleJoueur.estVide==true) {
			buildShip(message);
		}
		
		
		broadCast(message,ThJoueur.joueur.socketJoueur);
		
	}

}
