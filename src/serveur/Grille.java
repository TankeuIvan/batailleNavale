package serveur;

import serveur.ThreadGame.ThreadJoueur;


public class Grille {
	
	private String[] ligne = {"#  ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	private String[] colonne = {"0  ", "1  ", "2  ", "3  ", "4  ", "5  ", "6  ", "7  ", "8  ", "9  "};
	private String matrice[][] = new String[10][10]; //ligne ensuite colonne
	private ThreadJoueur ThJoueur;
	public boolean estVide;
	private int indexY=0;
	private int indexX=0;
	private boolean sensVertical=false;
	private boolean testFormatCoord;
	
	
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
	
	public void buildCoordGrille(String navire, int cas) {  
		//(navire.charAt(0)-'A') ==(navire.charAt(3)-'A') ) 
		
		if(navire.charAt(0) == navire.charAt(3)) { //Sens vertical
			switch (cas){// Format : A0/A1 ou C5/D5
			
				case 1: //  B3/B3 //Sous-marin
					if(navire.charAt(4)-navire.charAt(1)==0) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees Torpilleur (2 cases) :");
						ThJoueur.etapeBuild=2;
					}else {
						ThJoueur.etapeBuild = 1;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
				
				case 2: //	C5:C6  // Torpilleur
					if(navire.charAt(4)-navire.charAt(1)==1) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du premier Croiseur (3 cases) :");
						ThJoueur.etapeBuild=3;
					}else {
						ThJoueur.etapeBuild = 2;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
				
				case 3: //	C5:C7 //premier Croiseur
					if(navire.charAt(4)-navire.charAt(1)==2) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du deuxieme Croiseur (3 cases) :");
						ThJoueur.etapeBuild=4;
					}else {
						ThJoueur.etapeBuild = 3;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
					
				case 4: //	C5:C7 //deuxième Croiseur
					if(navire.charAt(4)-navire.charAt(1)==2) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du premier CUIRASSE (4 cases :");
						ThJoueur.etapeBuild=5;
					}else {
						ThJoueur.etapeBuild = 4;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
				
				case 5: //	C5:C8 // premier cuirasse
					if(navire.charAt(4)-navire.charAt(1)==3) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O"; //C6
						matrice[(navire.charAt(4)-'2')][(navire.charAt(3)-'A')] = "O"; //C7
						matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O"; //C8
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O"; //C9
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du deuxieme CUIRASSE (4 cases) :");
						ThJoueur.etapeBuild=6;
					}else {
						ThJoueur.etapeBuild = 5;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
					
				case 6: //	C5:C8 // deuxieme cuirasse
					if(navire.charAt(4)-navire.charAt(1)==3) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O"; //C6
						matrice[(navire.charAt(4)-'2')][(navire.charAt(3)-'A')] = "O"; //C7
						matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O"; //C8
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O"; //C9
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du Porte-avions (5 cases) :");
						ThJoueur.etapeBuild=7;
					}else {
						ThJoueur.etapeBuild = 6;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
				
				case 7: //	C5:C9 //porte-avions
					if(navire.charAt(4)-navire.charAt(1)==4) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O"; //C5
						matrice[(navire.charAt(4)-'3')][(navire.charAt(3)-'A')] = "O"; //C6
						matrice[(navire.charAt(4)-'2')][(navire.charAt(3)-'A')] = "O"; //C7
						matrice[(navire.charAt(4)-'1')][(navire.charAt(3)-'A')] = "O"; //C8
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O"; //C9
						ThJoueur.etapeBuild=0;
						ThJoueur.compteurShip=0;
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Construction de votre flotte reussie ! Entrer \"pret\"");
						ThJoueur.etapeJeu=2;
					}else {
						ThJoueur.etapeBuild = 7;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
			}
		}else if(navire.charAt(1) == navire.charAt(4)) { //Sens horizontal
			
			switch (cas){ //Format: C5/D5
				case 1: //	B3/B3 //Sous-marin
					if(navire.charAt(3)-navire.charAt(0)== 0) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees Torpilleur (2 cases) :");
						ThJoueur.etapeBuild = 2;
					}else { 
						ThJoueur.etapeBuild = 1;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
				
				case 2: //C5:D5 //Torpilleur
					if(navire.charAt(3)-navire.charAt(0)== 1) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du premier Croiseur (3 cases) :");
						ThJoueur.etapeBuild = 3;
					}else {
						ThJoueur.etapeBuild = 2;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
					
				case 3: //C5:E5 -> C5 D5 E5// Premier Croiseur
					if(navire.charAt(3)-navire.charAt(0)== 2) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'B')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du deuxieme Croiseur (3 cases) :");
						ThJoueur.etapeBuild = 4;
					}else {
						ThJoueur.etapeBuild = 3;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
					
				case 4: //C5:E5 -> C5 D5 E5 // Deuxième Croiseur
					if(navire.charAt(3)-navire.charAt(0)== 2) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'B')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du premier CUIRASSE (4 cases) :");
						ThJoueur.etapeBuild = 5;
					}else {
						ThJoueur.etapeBuild = 4;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
					
				case 5: //C5:F5 -> C5 D5 E5 F5 // premier CUIRASSE
					if(navire.charAt(3)-navire.charAt(0)== 3) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'C')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'B')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du deuxieme CUIRASSE (4 cases) :");
						ThJoueur.etapeBuild = 6;
					}else {
						ThJoueur.etapeBuild = 5;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
					
				case 6: //C5:F5 -> C5 D5 E5 F5 // deuxième CUIRASSE
					if(navire.charAt(3)-navire.charAt(0)== 3) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'C')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'B')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.pwJoueur.println("Cordonnees du Porte-avions (5 cases) :");
						ThJoueur.etapeBuild = 7;
					}else {
						ThJoueur.etapeBuild = 6;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
					
				case 7: //C5:F5 -> C5 D5 E5 F5 //Porte-avion
					if(navire.charAt(3)-navire.charAt(0)== 4) {
						matrice[(navire.charAt(1)-'0')][(navire.charAt(0)-'A')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'D')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'C')] = "O";
						matrice[(navire.charAt(1)-'0')][(navire.charAt(3)-'B')] = "O";
						matrice[(navire.charAt(4)-'0')][(navire.charAt(3)-'A')] = "O";
						ThJoueur.grilleJoueur.showGrille();
						ThJoueur.etapeBuild=0;
						ThJoueur.compteurShip=0;
						ThJoueur.pwJoueur.println("Construction de votre flotte reussie ! Entrer \\\"pret\\\"\"");
						ThJoueur.etapeJeu=2;
					}else {
						ThJoueur.etapeBuild = 7;
						ThJoueur.pwJoueur.println("Coordonnees incorectes.");
					}
					break;
			}
			
		}else { // Sens ni vertical ni horizontal
			ThJoueur.pwJoueur.println("Coordonnees incorectes.");
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
	
	
}
