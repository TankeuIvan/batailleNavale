package serveur;

import serveur.ThreadGame.ThreadJoueur;


public class Grille extends Thread {
	
	private String[] ligne = {"# ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	private String[] colonne = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10"};
	private String matrice[][] = new String[10][10];
	ThreadJoueur ThJoueur;
	public boolean estVide;
	int indexY=0;
	int indexX=0;
	
	public static void main(String[] args) {
		//new Grille().start();
	}

	public Grille(ThreadJoueur ThJ) {
		this.ThJoueur = ThJ;
		this.ThJoueur.grilleJoueur = this;
		
		for (int i = 0; i<10; i++) {		
			for(int j=0; j<10; j++) {
			matrice[i][j] = "*";
			}
		}
		
		estVide=true;
		
	}
	
	public void showGrille() {
		for(int i=0; i<=10; i++) {
			ThJoueur.pwJoueur.print(ligne[i]+"   ");	
		}
		ThJoueur.pwJoueur.println();
		for (int i = 0; i<10; i++) {
			ThJoueur.pwJoueur.print(colonne[i]+"   ");	
			for(int j=0; j<10; j++) {
			ThJoueur.pwJoueur.print(matrice[i][j]+"   ");	
			}
			ThJoueur.pwJoueur.println();
			ThJoueur.pwJoueur.println();
		}
	}
	
	public void buildCoordGrille(String canon) {
		String x= ""+canon.charAt(1);
		String y= ""+canon.charAt(2);
		int j=0;
		if (x==ligne[j]) indexY = j;
		for(int i=0; i<10;i++) {
			j++;
			if (x==ligne[j]) indexY = j;
			if (y==colonne[i]) indexX = i;
		}
		matrice[indexX][indexY] = "0";
		//estVide = false;
	}
	
	public void testCanon(){
		if(matrice[indexX][indexY] == "*") matrice[indexX][indexY] = "-";
		else matrice[indexX][indexY] = "#";	
	}
	
	@Override
	public void run() {
		
		}
	
	
	
	/*public class Coordonnees {
		private String x;
		private String y;
		private String canon;
		int a=0;
		int b=0;
		
		public Coordonnees(String canon) {
			super();
			this.x = ""+canon.charAt(1);
			this.y = ""+canon.charAt(2);
			this.canon = canon;
			
			for(int i=0; i<ligne.length;i++) {
				if (x==ligne[i]) a = i;
				if (y==colonne[i]) b = i;
			}
			
		}
		
		public void buildCoord() {
			matrice[b][a] = "[]";
		}
		
		public void canon(){
			if(matrice[b][a] == "*") matrice[b][a] = "O";
			else matrice[b][a] = "#";	
		}
		
		
		
	}*/
	
}
