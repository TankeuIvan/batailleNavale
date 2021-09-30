package serveur;

//import client.*;
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
	public String matriceGen[][] = new String[10][10];
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

	public ThreadJoueur(int idJ, int nbrePartie, Socket s) {
		this.idJoueur = idJ;
		this.nombrePartie = nbrePartie;
		this.socketJoueur=s;
		for (int i = 0; i<10; i++) {		
			for(int j=0; j<10; j++) {
			matriceGen[i][j] = ".";
			}
		}
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
				System.out.println("Joueur"+idJoueur+" etapeBuild="+this.etapeBuild+" etapeJeu="+this.etapeJeu+"   "+"Joueur"+listeJoueur.get(0).idJoueur+" etapeBuild="+listeJoueur.get(0).etapeBuild+" etapeJeu="+listeJoueur.get(0).etapeJeu);
			}
			
			//while(etapeJeu==2) {
			System.out.println("Joueur"+this.idJoueur+"- Début de la boucle etapeJeu = 1 !");
			
			//int i=0;
			while(listeJoueur.get(0).etapeJeu==1) {
				//this.pwJoueur.println("Joueur1 etapeJeu ="+this.etapeJeu+" Joueur2 etapeJeu = "+listeJoueur.get(0).etapeJeu);
				//if(i==0) this.pwJoueur.println("En attente de l'autre joueur...");
				//i++;
			}
			
			System.out.println("Joueur"+this.idJoueur+"- Fin de la boucle etapeJeu = 1 ! Joueur"+this.idJoueur+" etapeJeu="+this.etapeJeu+" et Joueur"+listeJoueur.get(0).idJoueur+" etapeJeu="+listeJoueur.get(0).etapeJeu);
			
			if(this.idJoueur==1)
				new ThreadGame(this, listeJoueur.get(0), nombrePartie).start();
			else
				new ThreadGame(listeJoueur.get(0), this, nombrePartie).start();
			
				
			//else new ThreadGame(listeJoueur.get(0), this, nombrePartie).start();
			//}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
}
