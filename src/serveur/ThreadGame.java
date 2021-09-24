package serveur;

//import serveur.Grille.Coordonnees;
import client.*;
//import tp_prog_res.ServeurChat.ThreadClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ThreadGame extends Thread{
	private Joueur j1;
	private Joueur j2;
	private int nombrePartie;
	protected List<ThreadJoueur> listeJoueur = new ArrayList<ThreadJoueur>();
	
	public ThreadGame() {
			
	}
	public ThreadGame(Joueur J1, Joueur J2, int id) {
		this.j1 = J1;
		this.j2 = J2;
		this.nombrePartie=id;
		
	}
	
	
	@Override
	public void run() {
		ThreadJoueur threadJoueur1 = new ThreadJoueur(j1, j1.id);
		listeJoueur.add(threadJoueur1);
		threadJoueur1.start();
		ThreadJoueur threadJoueur2 = new ThreadJoueur(j2, j2.id);
		listeJoueur.add(threadJoueur2);
		threadJoueur2.start();
	}
	
	
	
	
	
	
	
	
	public class ThreadJoueur extends Thread{
		Joueur joueur;  //disponible dans le package serveur
		private int idJoueur;
		public BufferedReader brJoueur;
		public PrintWriter pwJoueur;
		public Grille grilleJoueur;
		public int etapeJeu = 1;
		public int etapeBuild = 1;
		public int compteurShip =1;
		
		
		
		public ThreadJoueur(Joueur joueur, int idJoueur) {
			this.joueur = joueur;
			this.idJoueur = idJoueur;
			try {
				this.brJoueur = new BufferedReader(new InputStreamReader(joueur.socketJoueur.getInputStream()));
				this.pwJoueur = new PrintWriter(joueur.socketJoueur.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			//Streams Joueur
			try {
				
				pwJoueur.println("Bienvenu Joueur "+idJoueur+" dans la Partie "+nombrePartie+" ! Entrer votre nom : ");
				joueur.nomJoueur = brJoueur.readLine();
				
				System.out.println("~Connexion établie avec @"+joueur.nomJoueur+" Joueur"+idJoueur+"/Partie"+nombrePartie+" Ip: "+joueur.socketJoueur.getRemoteSocketAddress());
				
				pwJoueur.println("\nVoici la Grille sur laquelle vous placerez vos navires :");
				new Grille(this).showGrille();
				pwJoueur.println("Cordonnees du navire 1");
				while(true) {
					String requeteJoueur = brJoueur.readLine().toString();
					//String messageJoueur = "\n"+joueur.nomJoueur+": "+requeteJoueur;
					new Commandes(listeJoueur, requeteJoueur, this).start();
					//System.out.println("[!]Nouveau message de "+joueur.nomJoueur);	
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		}
	}
	
}
