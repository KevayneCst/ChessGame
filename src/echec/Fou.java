package echec;

public class Fou extends Piece {
	
	public Fou(String c) {
		super(c);
		this.setRepresentation('F');
	}
	 
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/*	
	 * 		La portée contient un tableau de Case où la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les règles
	 * 		de déplacement de la Piece et en prenant en compte l'état du plateau.	
	 * 
	 * 		Spécificité FOU:
	 * 		- Se déplace en diagonale (multisens)
	 * 
	 * 	*/
	@Override
	public void calculerPortee(Plateau plateau) {
		Portee p = new Portee();
		
		int simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Pour manipuler des coordonnées sans bouger la pièce.
		int simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Pour manipuler des coordonnées sans bouger la pièce.
		
		boolean stop; //Boolean qui va servir a arrêter les boucles while quand on rencontre une pièce
		int curseur;  
		
		/**
		 * Méthode écrite par Julie et Kévin
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		//--------------EN HAUT A GAUCHE------------------
		
		stop=false;
		curseur=1;
		
		while ((simulationCoordoX-curseur>=1 && simulationCoordoY+curseur<=8) && stop==false) {
			
			boolean isLibre=plateau.estLibre(simulationCoordoX-curseur, simulationCoordoY+curseur);
			//boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY+curseur).getPiece().getCouleur().equals(super.getCouleur());
			 
			if (isLibre == true) { //Si la case est libre
				Case c = plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY+curseur);
				p.ajouterPortee(c);
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY+curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, ce n'est pas libre et de la couleur opposé (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY+curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pièce est de la même couleur que le fou 
				stop=true;
			}
			
			curseur++;			
		}
		
		//---------------EN HAUT A DROITE-----------------
		
		stop=false;
		curseur=1;
		
		while ((simulationCoordoX+curseur<=8 && simulationCoordoY+curseur<=8) && stop==false) {
					
			boolean isLibre=plateau.estLibre(simulationCoordoX+curseur, simulationCoordoY+curseur);
			//boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY+curseur).getPiece().getCouleur().equals(super.getCouleur());
			 
			if (isLibre == true) { //Si la case est libre
				Case c = plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY+curseur);
				p.ajouterPortee(c);
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY+curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, ce n'est pas libre et de la couleur opposé (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY+curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pièce est de la même couleur que le fou 
				stop=true;
			}
			
			curseur++;			
		}
		
		//--------------EN BAS A GAUCHE-----------------------
		
		stop=false;
		curseur=1;
		
		while ((simulationCoordoX-curseur>=1 && simulationCoordoY-curseur>=1) && stop==false) {
			
			boolean isLibre=plateau.estLibre(simulationCoordoX-curseur, simulationCoordoY-curseur);
			//boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY-curseur).getPiece().getCouleur().equals(super.getCouleur());
			 
			if (isLibre == true) { //Si la case est libre
				Case c = plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY-curseur);
				p.ajouterPortee(c);
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY-curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, si ce n'est pas libre et de la couleur opposé (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY-curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pièce est de la même couleur que le fou 
				stop=true;
			}
			
			curseur++;			
		}
		
		//---------------EN BAS A DROITE------------------------
		
		stop=false;
		curseur=1;
		
		while ((simulationCoordoX+curseur<=8 && simulationCoordoY-curseur>=1) && stop==false) {
			
			boolean isLibre=plateau.estLibre(simulationCoordoX+curseur, simulationCoordoY-curseur);
			//boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY-curseur).getPiece().getCouleur().equals(super.getCouleur());
			 
			if (isLibre == true) { //Si la case est libre
				Case c = plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY-curseur);
				p.ajouterPortee(c);
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY-curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, si ce n'est pas libre et de la couleur opposé (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY-curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pièce est de la même couleur que le fou 
				stop=true;
			}
			
			curseur++;			
		}
		
		//--------------SET LA PORTEE---------------
		
		this.setLaPortee(p);
		
	}	
	
}
