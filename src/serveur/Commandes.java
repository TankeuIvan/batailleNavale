package serveur;

import serveur.ThreadGame.ThreadJoueur;
//import client.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Commandes extends Thread {
	private List<ThreadJoueur> listeJoueur = new ArrayList<ThreadJoueur>();
	private String message;
	private ThreadJoueur ThJoueur;
	//private boolean endBuild = false;
	

	
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
		} catch (IOException e) {e.printStackTrace();}
	}
	
	
	public void buildShip(String message, int cas) {
			ThJoueur.grilleJoueur.buildCoordGrille(message, cas);
			//ThJoueur.grilleJoueur.showGrille();
	}
	
	
	public void etapeUne(String message) {
			switch(ThJoueur.etapeBuild) {
				case 1:
					buildShip(message,1);
					break;
				case 2:
					buildShip(message,2);
					break;
				case 3:
					buildShip(message,3);
					/*if(ThJoueur.compteurShip==1)ThJoueur.pwJoueur.println("Cordonnees Croiseur (3 cases) :");
					if(ThJoueur.compteurShip==2) ThJoueur.etapeBuild=4;
					ThJoueur.compteurShip=2;
					if(ThJoueur.etapeBuild==4) {
						ThJoueur.compteurShip=1;
						ThJoueur.pwJoueur.println("Cordonnees CUIRASSE (4 cases) :");
					}*/
					break;
				case 4:
					buildShip(message,4);
					break;
				case 5:
					buildShip(message,5);
					break;
				case 6:
					buildShip(message,6);
					break;
				case 7:
					buildShip(message,7);
					break;
			}
	}
	
	
	@Override
	public void run() {
		//buildShip(message,5);
		if((message.charAt(0)=='+') && message.length()==6) {
				message = message.substring(1,6);
				etapeUne(message);
		}else if(message.charAt(0)=='#') {
				broadCast(message,ThJoueur.joueur.socketJoueur);
		}else {
				ThJoueur.pwJoueur.println("Coordonnees incorectes.");
				}
		
	}

}
