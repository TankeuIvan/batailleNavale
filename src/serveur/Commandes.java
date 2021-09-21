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
	private Joueur joueur;
	
	public Commandes(List<ThreadJoueur> liste, String message, Joueur j) {
		this.listeJoueur = liste;
		this.message = message;
		this.joueur =j;
	}
	
	
	public void broadCast(String message, Socket socketJ) {
		try {
			for(ThreadJoueur player : listeJoueur) {
				if(player.joueur.socketJoueur!=socketJ) {
					PrintWriter printWriter = new PrintWriter(player.joueur.socketJoueur.getOutputStream(),true);
					printWriter.println(message);
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public void run() {
		
		
		broadCast(message,joueur.socketJoueur);
		
	}

}
