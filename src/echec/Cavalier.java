package echec;

public class Cavalier extends Piece {
	
	public Cavalier(String c) {
		super(c);
		this.setRepresentation('C');
	}
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/*	
	 * 		La portée contient un tableau de Case où la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les règles
	 * 		de déplacement de la Piece et en prenant en compte l'état du plateau.	
	 * 
	 * 		Spécificité Cavalier:
	 * 		-Se déplace en "L"
	 * 		-Peut "sauter" au dessus d'autres pièces
	 * 	*/
	
		@Override
		public void calculerPortee(Plateau plateau) {
			Portee p = new Portee();
		
			int simulationCoordoY=super.laCaseDeLaPiece().getLigne();
			int simulationCoordoX=super.laCaseDeLaPiece().getColonne();
		
			
			/**
			 * Méthode écrite par Eloïse
			 * 
			 * 
			 * MAJ de la méthode:
			 * 
			 * Pareil que pour la classe Roi, on a
			 * pas besoin de comparer/connaître la
			 * couleur de notre cavalier pour nos 
			 * déplacements car il n'a pas de sens
			 * "unique" de déplacement comme le 
			 * pion
			 * 
			 * 
			 * MAJ de la méthode:
			 * 
			 * La cavalier ne fait pas X+2 et Y+3 mais X+2 Y+1
			 * ==> Le cavalier "saute" au dessus de 3 cases au
			 * total et pas 5 cases... Même chose pour le reste
			 * des déplacements du cavalier, pas X-2 et Y+3 mais
			 * X-2 et Y+1 etc
			 * 
			 * 
			 * MAJ de la méthode:
			 * 
			 * Il manquait le fait d'ajouter dans la portée la case d'une 
			 * pièce d'une autre couleur si la case n'était pas libre
			 * (pour pouvoir la manger plus tard)
			 * 
			 */
			
			//Pour y=+-2 ou 1 ET x=+-2 ou 1 
		
			//le cheval avance et va à droite : X+1 Y+2
			if ((simulationCoordoX+1 <= 8) && (simulationCoordoY+2 <= 8)) { 
				if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY+2) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+2);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+2).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+2);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va à droite puis avance : X+2 Y+1
			if ((simulationCoordoX+2 <= 8) && (simulationCoordoY+1 <= 8)) { 
				if (plateau.estLibre(simulationCoordoX+2,simulationCoordoY+1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va à droite puis recule : X+2 Y-1
			if ((simulationCoordoX+2 <= 8) && (simulationCoordoY-1 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX+2,simulationCoordoY-1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval recule et va à droite : X+1 Y-2
			if ((simulationCoordoX+1 <= 8) && (simulationCoordoY-2 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY-2) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-2).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				}
			}
			
		
			//le cheval recule et va à gauche : X-1 Y-2
			if ((simulationCoordoX-1 >= 1) && (simulationCoordoY-2 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX-1,simulationCoordoY-2) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-2).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-2);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va à gauche puis recule : X-2 Y-1
			if ((simulationCoordoX-2 >= 1) && (simulationCoordoY-1 >= 1)) { 
				if (plateau.estLibre(simulationCoordoX-2, simulationCoordoY-1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY-1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval va à gauche puis avance : X-2 Y+1
			if ((simulationCoordoX-2 >= 1) && (simulationCoordoY+1 <= 8)) { 
				if (plateau.estLibre(simulationCoordoX-2, simulationCoordoY+1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-2, simulationCoordoY+1);
					p.ajouterPortee(c1);
				}
			}
			
			//le cheval avance et va à gauche : X-1 Y+2
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
