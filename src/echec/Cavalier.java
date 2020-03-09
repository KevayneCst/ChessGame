package echec;

public class Cavalier extends Piece {
	
	public Cavalier(String c) {
		super(c);
		this.setRepresentation('C');
	}
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/*	
	 * 		La port�e contient un tableau de Case o� la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les r�gles
	 * 		de d�placement de la Piece et en prenant en compte l'�tat du plateau.	
	 * 
	 * 		Sp�cificit� Cavalier:
	 * 		-Se d�place en "L"
	 * 		-Peut "sauter" au dessus d'autres pi�ces
	 * 	*/
	
		@Override
		public void calculerPortee(Plateau plateau) {
			Portee p = new Portee();
		
			int simulationCoordoY=super.laCaseDeLaPiece().getLigne();
			int simulationCoordoX=super.laCaseDeLaPiece().getColonne();
		
			
			/**
			 * M�thode �crite par Elo�se
			 * 
			 * 
			 * MAJ de la m�thode:
			 * 
			 * Pareil que pour la classe Roi, on a
			 * pas besoin de comparer/conna�tre la
			 * couleur de notre cavalier pour nos 
			 * d�placements car il n'a pas de sens
			 * "unique" de d�placement comme le 
			 * pion
			 * 
			 * 
			 * MAJ de la m�thode:
			 * 
			 * La cavalier ne fait pas X+2 et Y+3 mais X+2 Y+1
			 * ==> Le cavalier "saute" au dessus de 3 cases au
			 * total et pas 5 cases... M�me chose pour le reste
			 * des d�placements du cavalier, pas X-2 et Y+3 mais
			 * X-2 et Y+1 etc
			 * 
			 * 
			 * MAJ de la m�thode:
			 * 
			 * Il manquait le fait d'ajouter dans la port�e la case d'une 
			 * pi�ce d'une autre couleur si la case n'�tait pas libre
			 * (pour pouvoir la manger plus tard)
			 * 
			 */
			
			//Pour y=+-2 ou 1 ET x=+-2 ou 1 
		
			//le cheval avance et va � droite : X+1 Y+2
			if ((simulationCoordoX+1 <= 8) && (simulationCoordoY+2 <= 8)) { 
				if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY+2) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+2);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+2).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+2);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va � droite puis avance : X+2 Y+1
			if ((simulationCoordoX+2 <= 8) && (simulationCoordoY+1 <= 8)) { 
				if (plateau.estLibre(simulationCoordoX+2,simulationCoordoY+1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va � droite puis recule : X+2 Y-1
			if ((simulationCoordoX+2 <= 8) && (simulationCoordoY-1 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX+2,simulationCoordoY-1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval recule et va � droite : X+1 Y-2
			if ((simulationCoordoX+1 <= 8) && (simulationCoordoY-2 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY-2) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-2).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				}
			}
			
		
			//le cheval recule et va � gauche : X-1 Y-2
			if ((simulationCoordoX-1 >= 1) && (simulationCoordoY-2 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX-1,simulationCoordoY-2) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-2).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va � gauche puis recule : X-2 Y-1
			if ((simulationCoordoX-2 >= 1) && (simulationCoordoY-1 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX-2, simulationCoordoY-1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va � gauche puis avance : X-2 Y+1
			if ((simulationCoordoX-2 >= 1) && (simulationCoordoY+1 <= 8)) { 
				if (plateau.estLibre(simulationCoordoX-2, simulationCoordoY+1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval avance et va � gauche : X-1 Y+2
			if ((simulationCoordoX-1 >= 1) && (simulationCoordoY+2 <= 8)) { 
				if (plateau.estLibre(simulationCoordoX-1,simulationCoordoY+2) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+2);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+2).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+2);
					p.ajouterPortee(c1);
				}
			}
		
			this.setLaPortee(p);

	}
}
