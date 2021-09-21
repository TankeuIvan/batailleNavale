package serveur;

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
		private Joueur joueur;
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
				
		}
	}
	
}
