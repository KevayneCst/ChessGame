package echec;

public class Tour extends Piece {
	
	public Tour(String c) {
		super(c);
		this.setRepresentation('T');
	}
	
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/*	
	 * 		La port�e contient un tableau de Case o� la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les r�gles
	 * 		de d�placement de la Piece et en prenant en compte l'�tat du plateau.	
	 * 
	 * 		Sp�cificit� Tour:
	 * 		-Se d�place tout droit
	 * 	*/
	
	@Override
	public void calculerPortee(Plateau plateau) {
		Portee p = new Portee();
		
		int simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Pour manipuler des coordonn�es sans bouger la pi�ce.
		int simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Pour manipuler des coordonn�es sans bouger la pi�ce.
		boolean pieceDevant=false;
	
		/**
		 * M�thode �crite par Marine
		 * 
		 * 
		 * MAJ de la m�thode:
		 * 
		 * Il manquait le fait d'ajouter dans la port�e la case d'une 
		 * pi�ce d'une autre couleur si la case n'�tait pas libre
		 * (pour pouvoir la manger plus tard)
		 * 
		 * 
		 * 
		 */
		
		
	
		//---------------------AVANCER n CASE TOUT DROIT---------------------
		//tant que simulation pas au bout
		 
		while((simulationCoordoY+1 <= 8)&&(pieceDevant==false)) {
		
			
			if (plateau.estLibre(simulationCoordoX,simulationCoordoY+1) == true) { //Si c'est libre
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1);
				p.ajouterPortee(c1);
				simulationCoordoY++;
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1);
				p.ajouterPortee(c1);
				pieceDevant=true;
			} else { //Si c'est pas libre, et de la m�me couleur
				pieceDevant=true;
			}
		}
		
		simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //R�initialisation de la variable a sa valeur d'origine
		simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //R�initialisation de la variable a sa valeur d'origine
		
		
		//---------------------RECULER n CASE TOUT DROIT---------------------
		
		
		pieceDevant=false;
		
		while ((simulationCoordoY-1 >= 1)&&(pieceDevant==false)) {
			if (plateau.estLibre(simulationCoordoX,simulationCoordoY-1) == true) { //Si c'est libre
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1);
				p.ajouterPortee(c1);
				simulationCoordoY--;
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1);
				p.ajouterPortee(c1);
				pieceDevant=true;
			} else { //Si c'est pas libre, et de la m�me couleur
				pieceDevant=true;
			}
		}
		
		simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //R�initialisation de la variable a sa valeur d'origine
		simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //R�initialisation de la variable a sa valeur d'origine
		
		
		
		//----------------------------AVANCER n case gauche-------------------------------------
		
		pieceDevant=false;
		
		while ((simulationCoordoX-1 >= 1)&&(pieceDevant==false)) {
			if (plateau.estLibre(simulationCoordoX-1,simulationCoordoY) == true) { //Si c'est libre 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY);
				p.ajouterPortee(c1);
				simulationCoordoX--;
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY);
				p.ajouterPortee(c1);
				pieceDevant=true;
			} else { //Si c'est pas libre, et de la m�me couleur
				pieceDevant=true;
			}
		}
		
		simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //R�initialisation de la variable a sa valeur d'origine
		simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //R�initialisation de la variable a sa valeur d'origine
		 
		
		
		//----------------------------AVANCER n CASES droite-------------------------------------
		
		pieceDevant=false;
		
		while ((simulationCoordoX+1 <= 8)&&(pieceDevant==false)) { 
			if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY) == true) { //Si c'est libre
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY);
				p.ajouterPortee(c1);
				simulationCoordoX++;
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY);
				p.ajouterPortee(c1);
				pieceDevant=true;
			} else { //Si c'est pas libre, et de la m�me couleur
				pieceDevant=true;
			}
		}
		simulationCoordoX=super.laCaseDeLaPiece().getLigne(); //R�initialisation de la variable a sa valeur d'origine
		
		this.setLaPortee(p); //On retourne le type Portee p qui contiendra tout les d�placements possible pour cette tour dans un tableau.
	}
}
