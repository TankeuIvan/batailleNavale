package client;

import java.net.Socket;

public class Joueur {
	public Socket socketJoueur;
	public String nomJoueur;
	public int id;
	
	public Joueur(int id) {
		this.id = id;
	}
	
	public Joueur() {
		
	}
	
	public Joueur(Socket s, String nom) {
		this.socketJoueur = s;
		this.nomJoueur = nom;	
		
	}


	
	

}
