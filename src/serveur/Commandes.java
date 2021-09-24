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
	private boolean endBuild = false;
	

	
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
	
	
	public void buildShip(String message, int taille) {
		if(message.charAt(0)=='#') {
			broadCast(message,ThJoueur.joueur.socketJoueur);
		}else 
		{ 
			ThJoueur.grilleJoueur.buildCoordGrille(message, taille);
			ThJoueur.grilleJoueur.showGrille();
		}
	}
	
	
	
	@Override
	public void run() {
		//buildShip(message,5);
		
		if(ThJoueur.etapeJeu==1) {
			
			switch(ThJoueur.etapeBuild) {
				case 1:
					buildShip(message,1);
					ThJoueur.etapeBuild=2;
					ThJoueur.pwJoueur.println("Cordonnees navire 2");
					break;
				case 2:
					buildShip(message,2);
					ThJoueur.etapeBuild=3;
					ThJoueur.pwJoueur.println("Cordonnees du navire 3 numero 1");
					break;
				case 3:
					buildShip(message,3);
					if(ThJoueur.compteurShip==1)ThJoueur.pwJoueur.println("Cordonnees du navire 3 numero 2");
					if(ThJoueur.compteurShip==2) ThJoueur.etapeBuild=4;
					ThJoueur.compteurShip=2;
					if(ThJoueur.etapeBuild==4) {
						ThJoueur.compteurShip=1;
						ThJoueur.pwJoueur.println("Cordonnees du navire 4 numero 1");
					}
					break;
				case 4:
					buildShip(message,4);
					if(ThJoueur.compteurShip==1)ThJoueur.pwJoueur.println("Cordonnees du navire 4 numero 2");
					if(ThJoueur.compteurShip==2) ThJoueur.etapeBuild=5;
					ThJoueur.compteurShip=2;
					if(ThJoueur.etapeBuild==5) {
						ThJoueur.compteurShip=1;
						ThJoueur.pwJoueur.println("Cordonnees du navire 5 numero 1");
					}
					break;
				case 5:
					buildShip(message,5);
					if(ThJoueur.compteurShip==1)ThJoueur.pwJoueur.println("Cordonnees du navire 5 numero 2");
					if(ThJoueur.compteurShip==2) ThJoueur.etapeBuild=0;
					ThJoueur.compteurShip=2;
					if(ThJoueur.etapeBuild==0) {
						ThJoueur.compteurShip=0;
					}
					break;
			}
		
			
		}
		
	}

}
