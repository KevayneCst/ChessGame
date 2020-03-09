package echec;

public class Fou extends Piece {
	
	public Fou(String c) {
		super(c);
		this.setRepresentation('F');
	}
	 
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/*	
	 * 		La port�e contient un tableau de Case o� la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les r�gles
	 * 		de d�placement de la Piece et en prenant en compte l'�tat du plateau.	
	 * 
	 * 		Sp�cificit� FOU:
	 * 		- Se d�place en diagonale (multisens)
	 * 
	 * 	*/
	@Override
	public void calculerPortee(Plateau plateau) {
		Portee p = new Portee();
		
		int simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Pour manipuler des coordonn�es sans bouger la pi�ce.
		int simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Pour manipuler des coordonn�es sans bouger la pi�ce.
		
		boolean stop; //Boolean qui va servir a arr�ter les boucles while quand on rencontre une pi�ce
		int curseur;  
		
		/**
		 * M�thode �crite par Julie et K�vin
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
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY+curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, ce n'est pas libre et de la couleur oppos� (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY+curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pi�ce est de la m�me couleur que le fou 
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
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY+curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, ce n'est pas libre et de la couleur oppos� (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY+curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pi�ce est de la m�me couleur que le fou 
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
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY-curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, si ce n'est pas libre et de la couleur oppos� (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX-curseur, simulationCoordoY-curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pi�ce est de la m�me couleur que le fou 
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
			} else if (!plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY-curseur).getPiece().getCouleurPiece().equals(super.getCouleurPiece())) { //Sinon, si ce n'est pas libre et de la couleur oppos� (pour pouvoir la manger plus tard)
				Case c = plateau.getCaseAtCoordo(simulationCoordoX+curseur, simulationCoordoY-curseur);
				p.ajouterPortee(c);
				stop=true;
			} else { //Sinon, si la case n'est pas libre et que la pi�ce est de la m�me couleur que le fou 
				stop=true;
			}
			
			curseur++;			
		}
		
		//--------------SET LA PORTEE---------------
		
		this.setLaPortee(p);
		
	}	
	
}
