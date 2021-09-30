package serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//import serveur.ThreadGame.ThreadJoueur;

public class Bataille extends Thread {
	private ThreadJoueur ThJoueur1;
	private ThreadJoueur ThJoueur2;
	private int nombrePartie;
	private String message1="";
	private String message2="";
	private int tour=1;
	
	public Bataille(ThreadJoueur thJ1,ThreadJoueur thJ2, int nbrePartie) {
		this.ThJoueur1 = thJ1;
		this.ThJoueur2 = thJ2;
		this.nombrePartie = nbrePartie;
	}
	
	public void messageTo(String message, ThreadJoueur thJ) {
				if(thJ == ThJoueur1) {
					String messageJoueur = "\n"+ThJoueur2.nomJoueur+": "+message;
					ThJoueur1.pwJoueur.println(messageJoueur);
				}else {
					String messageJoueur = "\n"+ThJoueur1.nomJoueur+": "+message;
					ThJoueur2.pwJoueur.println(messageJoueur);
				}
	}
	
	
	
	
	@Override
	public void run() {
		
		ThJoueur1.pwJoueur.println("Ca marche Joueur 1");
		ThJoueur2.pwJoueur.println("Ca marche Joueur 2");
		
		ThJoueur1.pwJoueur.println("La flotte de votre adversaire :");
		ThJoueur2.grilleJoueur.showMatriceGen();
		
		ThJoueur2.pwJoueur.println("La flotte de votre adversaire :");
		ThJoueur1.grilleJoueur.showMatriceGen();
		
		while(true) {
			try {
				
				ThJoueur1.pwJoueur.println("********************************************************");
				ThJoueur2.pwJoueur.println("********************************************************");
				
				ThJoueur1.pwJoueur.println("Entrer les coordonees de votre rocket:");
				
				message1 = ThJoueur1.brJoueur.readLine().toString();
				//message1 = "\n"+ThJoueur2.nomJoueur+": "+message1;
				if(ThJoueur2.grilleJoueur.testCanon(message1)==true) {
					ThJoueur2.grilleJoueur.showMatriceGen();
					ThJoueur1.pwJoueur.println(message1+"<----- TOUCHE. Bravo!");
					ThJoueur2.pwJoueur.println("-----> Oups! Vous avez ete TOUCHE en "+message1);
					ThJoueur2.grilleJoueur.showGrille();
				}else {
					ThJoueur2.grilleJoueur.showMatriceGen();
					ThJoueur1.pwJoueur.println(message1+"<----- MANQUE. Dommage!");
					ThJoueur2.pwJoueur.println("-----> Super! Vous avez ete MANQUE en "+message1);
					ThJoueur2.grilleJoueur.showGrille();
				}
				
				
				ThJoueur2.pwJoueur.println("Entrer les coordonees de votre rocket:");
				
				message2 = ThJoueur2.brJoueur.readLine().toString();
				if(ThJoueur1.grilleJoueur.testCanon(message2)==true) {
					ThJoueur1.grilleJoueur.showMatriceGen();
					ThJoueur2.pwJoueur.println(message2+"<----- TOUCHE. Bravo!");
					ThJoueur1.pwJoueur.println("-----> Oups! Vous avez ete TOUCHE en "+message2);
					ThJoueur1.grilleJoueur.showGrille();
				}else {
					ThJoueur1.grilleJoueur.showMatriceGen();
					ThJoueur2.pwJoueur.println(message2+"<----- MANQUE. Dommage");
					ThJoueur1.pwJoueur.println("-----> Super! Vous avez ete MANQUE en "+message2);
					ThJoueur1.grilleJoueur.showGrille();
				}
				
				
				//ThJoueur2.pwJoueur.println(message1);
				
				//message2 = ThJoueur2.brJoueur.readLine().toString();
				//message2 = "\n"+ThJoueur2.nomJoueur+": "+message2;
				//ThJoueur1.pwJoueur.println(message1);
				
				System.out.println("Tour = "+tour);
				tour++;
				/*if(message1.charAt(0)=='#' || message1.charAt(0)=='#') {
					
					System.out.println("Le br fonctionne");
				} */
				
				/*if (message2.charAt(0)=='*') {
					message1 = message1.substring(1, message1.length());
					messageTo(message1, ThJoueur2);
				}
				
				if ((ThJoueur2.brJoueur.readLine().toString()).charAt(0)=='*') {
					message2 = message2.substring(1, message2.length());
					messageTo(message2, ThJoueur1);
				}*/
				
				
				
				
				
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
