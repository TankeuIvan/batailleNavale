/*package client;

import serveur.*;
import serveur.Grille;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class ThreadJoueur extends Thread{
	//Joueur joueur;  //disponible dans le package serveur
	private int nombrePartie;
	protected List<ThreadJoueur> listeJoueur = new ArrayList<ThreadJoueur>();
	public Socket socketJoueur;
	public String nomJoueur;
	//public int id;
	private int idJoueur;
	public BufferedReader brJoueur;
	public PrintWriter pwJoueur;
	public Grille grilleJoueur;
	public int etapeJeu = 1;
	public int etapeBuild = 1;
	public int compteurShip =1;
	public boolean placementEnd = false;

	/*public ThreadJoueur(Joueur joueur, int id) {
		this.joueur = joueur;
		this.idJoueur = id;
		try {
			this.brJoueur = new BufferedReader(new InputStreamReader(joueur.socketJoueur.getInputStream()));
			this.pwJoueur = new PrintWriter(joueur.socketJoueur.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	/*public ThreadJoueur(int idJ, int nbrePartie, Socket s) {
		this.idJoueur = idJ;
		this.nombrePartie = nbrePartie;
		this.socketJoueur=s;
		try {
			this.brJoueur = new BufferedReader(new InputStreamReader(this.socketJoueur.getInputStream()));
			this.pwJoueur = new PrintWriter(this.socketJoueur.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		//Streams Joueur
		try {
			
			pwJoueur.println("Bienvenu Joueur "+idJoueur+" dans la Partie "+nombrePartie+" ! Entrer votre nom : ");
			this.nomJoueur = brJoueur.readLine();
			
			System.out.println("~Connexion établie avec @"+this.nomJoueur+" Joueur"+idJoueur+"/Partie"+nombrePartie+" Ip: "+this.socketJoueur.getRemoteSocketAddress());
			
			pwJoueur.println("\nVoici la Grille sur laquelle vous placerez les navires constituant votre flotte :");
			new Grille(this).showGrille();
			pwJoueur.println("Vous avez droit : 1 Sous-marin, 1 Torpilleur, 2 Croiseurs, 2 CUIRASSE et 1 Porte-avion.\n");
			pwJoueur.println("Cordonnees du Sous-marin (Une case) :");
			while(etapeJeu==1) {
				String requeteJoueur = brJoueur.readLine().toString();
				//String messageJoueur = "\n"+joueur.nomJoueur+": "+requeteJoueur;
				new Commandes(listeJoueur, requeteJoueur, this).start();
				//System.out.println("[!]Nouveau message de "+joueur.nomJoueur);	
			}
			
			//while(etapeJeu==2) {
				//new Bataille(threadJoueur1,threadJoueur2).start();
			//}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
}
*/