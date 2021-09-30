package serveur;

//import client.*;
//import serveur.ThreadGame.ThreadJoueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

	public class MainServeur extends Thread {
	private int nombrePartie = 1;

	public static void main(String[] args) {
		new MainServeur().start();
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println("Démarrage du serveur...\n");
			ServerSocket ss1 = new ServerSocket(1234);
		
			while (true) {
				//Joueur j1 = new Joueur(1);
				Socket socketJoueur1 = ss1.accept();
				ThreadJoueur ThJoueur1 = new ThreadJoueur(1,nombrePartie, socketJoueur1);
				//ThJoueur1.socketJoueur = ss1.accept();
				System.out.println("*****Nouvelle Partie : Partie "+nombrePartie+"*****");
				System.out.println("Tentative de connexion au Joueur 1/Partie"+nombrePartie+" : Player~"+ ThJoueur1.socketJoueur.getRemoteSocketAddress());
				
				/*BufferedReader brJ1 = new BufferedReader(new InputStreamReader(ThJoueur1.socketJoueur.getInputStream()));
				PrintWriter pwJ1 = new PrintWriter(ThJoueur1.socketJoueur.getOutputStream(), true);*/
				ThJoueur1.pwJoueur.println("En attente d'un adversaire...");
				
				Socket socketJoueur2 = ss1.accept();
				ThreadJoueur ThJoueur2 = new ThreadJoueur(2, nombrePartie,socketJoueur2);
				//ThJoueur2.socketJoueur = ss1.accept();
				System.out.println("Tentative de connexion au Joueur 2/Partie"+nombrePartie+" : Player~"+ ThJoueur2.socketJoueur.getRemoteSocketAddress());
				
				ThJoueur1.listeJoueur.add(ThJoueur2);
				ThJoueur2.listeJoueur.add(ThJoueur1);
				
				new ThreadGame(ThJoueur1, ThJoueur2, nombrePartie).start();
				nombrePartie++;
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
