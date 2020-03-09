package echec;

public class Reine extends Piece {
	
	public Reine(String c) {
		super(c);
		this.setRepresentation('Q');
	}
	
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/*	
	 * 		La portée contient un tableau de Case où la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les règles
	 * 		de déplacement de la Piece et en prenant en compte l'état du plateau.	
	 * 
	 * 		Spécificité Reine:
	 * 		-Se déplace en diagonale et tout droit
	 * 	*/
	
	@Override
	public void calculerPortee(Plateau plateau) {
		Portee p = new Portee();
		
		int simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Pour manipuler des coordonnées sans bouger la pièce.
		int simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Pour manipuler des coordonnées sans bouger la pièce.
		boolean pieceDevant=false;
		boolean stop; //Boolean qui va servir a arrêter les boucles while quand on rencontre une pièce
		int curseur;
	

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
			} else { //Si c'est pas libre, et de la même couleur
				pieceDevant=true;
			}
		}
		
		simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Réinitialisation de la variable a sa valeur d'origine
		
		
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
			} else { //Si c'est pas libre, et de la même couleur
				pieceDevant=true;
			}
		}
		
		simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Réinitialisation de la variable a sa valeur d'origine
		
		
		
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
			} else { //Si c'est pas libre, et de la même couleur
				pieceDevant=true;
			}
		}
		
		simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Réinitialisation de la variable a sa valeur d'origine
		 
		
		
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
			} else { //Si c'est pas libre, et de la même couleur
				pieceDevant=true;
			}
		}
		simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Réinitialisation de la variable a sa valeur d'origine
		
		//---------------POUR LES DEPLACEMENTS DU FOU---------------
		
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
