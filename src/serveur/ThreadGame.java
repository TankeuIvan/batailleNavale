package serveur;

import client.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadGame extends Thread{
	private Joueur joueur;
	private int idJ;
	private int nombrePartie;
	private int temoin =0;
	
	public ThreadGame(Joueur j, int i, int id) {
		this.joueur = j;
		this.nombrePartie = i;
		this.idJ = id;
	}
	
	@Override
	public void run() {
		super.run();
		//Streams Joueur
		try {
			InputStream isJoueur = joueur.socketJoueur.getInputStream();
			InputStreamReader isrJoueur = new InputStreamReader(isJoueur);
			BufferedReader brJoueur = new BufferedReader(isrJoueur);
			PrintWriter pwJoueur = new PrintWriter(joueur.socketJoueur.getOutputStream(), true);
			pwJoueur.println("Bienvenu Joueur "+idJ+" dans la Partie "+nombrePartie+" ! Entrer votre nom : ");
			joueur.nomJoueur = brJoueur.readLine();
			
			System.out.println("~Connexion établie avec "+joueur.nomJoueur+" Joueur"+idJ+"/Partie"+nombrePartie+" Ip: "+joueur.socketJoueur.getRemoteSocketAddress());
			temoin++;
			System.out.println("temoin="+temoin);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(temoin==2) System.out.println("~La Partie "+nombrePartie+" vient de commencer.");
			
	}
	
	

	
}
