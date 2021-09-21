package client;

import serveur.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//import serveur.ThreadGame.ThreadJoueur;

public class ThreadJoueur extends Thread{
	/*private Joueur joueur;
	private int idJoueur;
	//private int temoin =0;
	
	
	
	public ThreadJoueur(Joueur joueur, int idJoueur) {
		this.joueur = joueur;
		this.idJoueur = idJoueur;
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
		//Streams Joueur
		try {
			InputStream isJoueur = joueur.socketJoueur.getInputStream();
			InputStreamReader isrJoueur = new InputStreamReader(isJoueur);
			BufferedReader brJoueur = new BufferedReader(isrJoueur);
			PrintWriter pwJoueur = new PrintWriter(joueur.socketJoueur.getOutputStream(), true);
			pwJoueur.println("Bienvenu Joueur "+idJoueur+" dans la Partie "+nombrePartie+" ! Entrer votre nom : ");
			joueur.nomJoueur = brJoueur.readLine();
			
			System.out.println("~Connexion établie avec @"+joueur.nomJoueur+" Joueur"+idJoueur+"/Partie"+nombrePartie+" Ip: "+joueur.socketJoueur.getRemoteSocketAddress());
			//temoin++;
			//System.out.println("temoin="+temoin);
			
			while(true) {
				String requeteJoueur = brJoueur.readLine().toString();
				String messageClient = "\n"+joueur.nomJoueur+": "+requeteJoueur;		
				broadCast(messageClient,joueur.socketJoueur);
				System.out.println("[!]Nouveau message de "+joueur.nomJoueur);	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		//if(temoin==2) System.out.println("~La Partie "+nombrePartie+" vient de commencer.");
			
	}*/
}