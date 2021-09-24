package serveur;

import serveur.ThreadGame.ThreadJoueur;


public class Grille extends Thread {
	
	private String[] ligne = {"# ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	private String[] colonne = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10"};
	private String matrice[][] = new String[10][10]; //ligne ensuite colonne
	private ThreadJoueur ThJoueur;
	public boolean estVide;
	private int indexY=0;
	private int indexX=0;
	private boolean sensVertical=false;
	
	
	public static void main(String[] args) {
		//new Grille().start();
	}

	public Grille(ThreadJoueur ThJ) {
		this.ThJoueur = ThJ;
		this.ThJoueur.grilleJoueur = this;
		
		for (int i = 0; i<10; i++) {		
			for(int j=0; j<10; j++) {
			matrice[i][j] = ".";
			}
		}
		
		estVide=true;
		
	}
	
	public void buildCoordGrille(String navire, int taille) {
		if( (navire.charAt(0)-'A') ==(navire.charAt(3)-'A') ) sensVertical= true; 
		else sensVertical= false;
		
		if(sensVertical==true) {
			switch (taille){
				case 1: //+B3
					//char x = navire.charAt(1);
					//char y = navire.charAt(2);
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
					break;
				
				case 2: //+C5:C6 ou +C6:C5
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
					break;
				
				case 3: //+C5:C7
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
					matrice[(navire.charAt(4)-'2')][(navire.charAt(3)-'A')] = "O";
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
					break;
				
				case 4: //+C5:C8
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O"; //C6
					matrice[(navire.charAt(4)-'3')][(navire.charAt(3)-'A')] = "O"; //C7
					matrice[(navire.charAt(4)-'2')][(navire.charAt(3)-'A')] = "O"; //C8
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O"; //C9
					break;
				
				case 5: //+C5:C9
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O"; //C5
					matrice[(navire.charAt(4)-'4')][(navire.charAt(3)-'A')] = "O"; //C6
					matrice[(navire.charAt(4)-'3')][(navire.charAt(3)-'A')] = "O"; //C7
					matrice[(navire.charAt(4)-'2')][(navire.charAt(3)-'A')] = "O"; //C8
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O"; //C9
					break;
			}
		}else {
			
			switch (taille){
				case 1: //+B3
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
				break;
				
				case 2: //C5:D5
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
					break;
					
				case 3: //C5:E5 -> C5 D5 E5
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
					matrice[(navire.charAt(1)-'1')][(navire.charAt(3)-'B')] = "O";
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
					break;
					
				case 4: //C5:F5 -> C5 D5 E5 F5
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
					matrice[(navire.charAt(1)-'1')][(navire.charAt(3)-'C')] = "O";
					matrice[(navire.charAt(1)-'1')][(navire.charAt(3)-'B')] = "O";
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
					break;
					
				case 5: //C5:F5 -> C5 D5 E5 F5
					matrice[(navire.charAt(1)-'1')][(navire.charAt(0)-'A')] = "O";
					matrice[(navire.charAt(1)-'1')][(navire.charAt(3)-'D')] = "O";
					matrice[(navire.charAt(1)-'1')][(navire.charAt(3)-'C')] = "O";
					matrice[(navire.charAt(1)-'1')][(navire.charAt(3)-'B')] = "O";
					matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
					break;
			}
			
		}
		//estVide = false;
	}
	
	
	
	
	
	
	
	public void showGrille() {
		ThJoueur.pwJoueur.println();
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
