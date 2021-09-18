package serveur;

import client.*;

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
		super.run();
		
		try {
			System.out.println("Démarrage du serveur...\n");
			ServerSocket ss1 = new ServerSocket(1234);
			ServerSocket ss2 = new ServerSocket(1243);

			
			while (true) {
				
				Joueur j1 = new Joueur(1);
				j1.socketJoueur = ss1.accept();
				System.out.println("Nouvelle Partie : Partie "+nombrePartie);
				System.out.println("Tentative de connexion au Joueur 1/Partie"+nombrePartie+" : Player~"+ j1.socketJoueur.getRemoteSocketAddress());
				
				InputStream isJ1 = j1.socketJoueur.getInputStream() ;
				InputStreamReader isrJ1 = new InputStreamReader(isJ1);
				BufferedReader brJ1 = new BufferedReader(isrJ1);
				PrintWriter pwJ1 = new PrintWriter(j1.socketJoueur.getOutputStream(), true);
				pwJ1.println("En attente d'un adversaire...");
				
				Joueur j2 = new Joueur(2);
				j2.socketJoueur = ss2.accept();
				System.out.println("Tentative de connexion au Joueur 2/Partie"+nombrePartie+" : Player~"+ j2.socketJoueur.getRemoteSocketAddress());
				new ThreadGame(j1, nombrePartie,j1.id).start();
				new ThreadGame(j2, nombrePartie,j2.id).start();
				nombrePartie++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	

}
