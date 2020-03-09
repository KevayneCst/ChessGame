package echec;

public class Pion extends Piece {
	
	public Pion(String c) {
		super(c);
		this.setRepresentation('P');
	}
	
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/**	
	 * 		La portée contient un tableau de Case où la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les règles
	 * 		de déplacement de la Piece et en prenant en compte l'état du plateau.	
	 * 
	 * 		Spécificités Pion:
	 * 		-avance de 1 case "en avant"
	 * 		-possibilité d'avancer de deux cases s'il n'a jamais bougé
	 * 		-peut se deplacer en diagonale "avant" s'il y a une piece (qu'il va alors manger)
	 * 		
	 * 	*/
	@Override
	public void calculerPortee(Plateau plateau) {
		Portee p = new Portee();
		
		int simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Pour manipuler des coordonnées sans bouger la pièce.
		int simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Pour manipuler des coordonnées sans bouger la pièce.
		
		Couleur enBlanc= new Couleur("blanc"); //Pour comparer cette couleur à la couleur de la pièce.
		
		/* 
		 * Méthode écrite par Kévin
		 * 
		 * 
		 * Précisions/Logique :
		 * 
		 *  Pour les déplacements simulés, 
		 *  on se place du point de vue
		 *  du joueur blanc.
		 * 
		 */
		

		//=================================POUR LA COULEUR BLANCHE=================================
		if (plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY).getPiece().getCouleurPiece().equals(enBlanc)) {
			//----------------------------AVANCER 1 CASE TOUT DROIT-------------------------------------
			
			//On vérifie d'abord si le pion ne se trouve pas tout au bout(en haut) du plateau, dans ce cas il ne pourra pas avancer.
			
			if (simulationCoordoY+1 <= 8) {
				if (plateau.estLibre(simulationCoordoX,simulationCoordoY+1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1);
					p.ajouterPortee(c1);
				}
			}
			
			//----------------------------AVANCER 2 CASES TOUT DROIT-------------------------------------
			
			//On vérifie d'abord que le pion ne s'est jamais déplacé avant de vérifier si il peut avancer de 2 cases en avant.
			if (super.dejaDeplace()==false) {
				
				//Si dans la case du plateau il n'y a aucune pièce (alliée ou ennemie) ALORS on ajoutera ce déplacement dans Portée.
				if ((plateau.estLibre(simulationCoordoX, simulationCoordoY+1) == true) && (plateau.estLibre(simulationCoordoX, simulationCoordoY+2) == true)) { 
					
					super.setAiJeAvanceeDeDeux(true);
					Case c2 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+2);
					p.ajouterPortee(c2);
				}
			}
			
			//----------------------------AVANCER UNE CASE EN DIAGONALE (EN HAUT ET A GAUCHE)-------------------------------------
			
			//On vérifie d'abord si les coordonnées de la case d'arrivée sont toujours dans le plateau
			if (simulationCoordoX-1 >= 1 && simulationCoordoY+1 <= 8) {
				
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX-1, simulationCoordoY+1);
				
				
				//Si la case n'est pas libre, ET que c'est une pièce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce déplacement dans Portee.
				if  (isLibre==false) {
					
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c3 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1);
						p.ajouterPortee(c3);
					}
					
				} else {
					//Si il y a bien une pièce à la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pièce ennemie (donc de la couleur opposé)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pièce situé à sa gauche a avancé de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
							if (plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getAiJeAvanceeDeDeux()==true) {
								
								//plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().setAiJeAvanceeDeDeux(false);
								Case c3 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1);
								p.ajouterPortee(c3);
							}
						}		
					}
				}
			}
				
			//----------------------------AVANCER UNE CASE EN DIAGONALE (EN HAUT ET A DROITE)-------------------------------------

			//On vérifie d'abord si les coordonnées de la case d'arrivée sont toujours dans le plateau
			if (simulationCoordoX+1 <= 8 && simulationCoordoY+1 <=8) {
				
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX+1, simulationCoordoY+1);
					
				//Si la case n'est pas libre, ET que c'est une pièce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce déplacement dans Portee.
				if (isLibre==false) {
					
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1);
						p.ajouterPortee(c4);
					}	
				} else {
					//Si il y a bien une pièce à la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pièce ennemie (donc de la couleur opposé)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pièce situé à sa gauche a avancé de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
							if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getAiJeAvanceeDeDeux()==true) {
								
								//plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().setAiJeAvanceeDeDeux(false);
								Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1);
								p.ajouterPortee(c4);
							}
						}		
					}
				}
			} 

			this.setLaPortee(p); //On set le type Portee p qui contiendra tout les déplacements possible pour ce pion dans un tableau.
			
		} else { //=================================POUR LA COULEUR NOIRE=================================
			
			//----------------------------AVANCER 1 CASE TOUT DROIT-------------------------------------
			
			//On vérifie d'abord si le pion ne se trouve pas tout au bout(en haut) du plateau, dans ce cas il ne pourra pas avancer.
			if (simulationCoordoY-1 >= 1) {
				if (plateau.estLibre(simulationCoordoX,simulationCoordoY-1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1);
					p.ajouterPortee(c1);
				}
			}
			
			//----------------------------AVANCER 2 CASES TOUT DROIT-------------------------------------
			
			//On vérifie d'abord que le pion ne s'est jamais déplacé avant de vérifier si il peut avancer de 2 cases en avant.
			if (super.dejaDeplace()==false) {
				
				//Si dans la case du plateau il n'y a aucune pièce (alliée ou ennemie) ALORS on ajoutera ce déplacement dans Portée.
				if ((plateau.estLibre(simulationCoordoX, simulationCoordoY-1) == true) && (plateau.estLibre(simulationCoordoX, simulationCoordoY-2) == true)) { 
					
					super.setAiJeAvanceeDeDeux(true);
					Case c2 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-2);
					p.ajouterPortee(c2);
				}
			}
			
			//----------------------------AVANCER UNE CASE EN DIAGONALE (EN BAS ET A GAUCHE)-------------------------------------
			
			//On vérifie d'abord si les coordonnées de la case d'arrivée sont toujours dans le plateau
			if (simulationCoordoX-1 >= 1 && simulationCoordoY-1 >= 1) {
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX-1, simulationCoordoY-1);
					
				//Si la case n'est pas libre, ET que c'est une pièce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce déplacement dans Portee.
				if  (isLibre==false) {
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c3 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1);
						p.ajouterPortee(c3);
					}
					
				} else {
					//Si il y a bien une pièce à la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pièce ennemie (donc de la couleur opposé)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pièce situé à sa gauche a avancé de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
							if (plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getAiJeAvanceeDeDeux()==true) {
								
								//plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().setAiJeAvanceeDeDeux(false);
								Case c3 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1);
								p.ajouterPortee(c3);
							}
						}		
					}
				}
			} 
				
			//----------------------------AVANCER UNE CASE EN DIAGONALE (EN BAS ET A DROITE)-------------------------------------

			//On vérifie d'abord si les coordonnées de la case d'arrivée sont toujours dans le plateau
			if (simulationCoordoX+1 <= 8 && simulationCoordoY-1 >=1) {
				
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX+1, simulationCoordoY-1);
				
				
				//Si la case n'est pas libre, ET que c'est une pièce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce déplacement dans Portee.
				if (isLibre==false) {
					
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1);
						p.ajouterPortee(c4);
					}
				} else {
					//Si il y a bien une pièce à la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pièce ennemie (donc de la couleur opposé)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pièce situé à sa gauche a avancé de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
							if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getAiJeAvanceeDeDeux()==true) {
								
								//plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().setAiJeAvanceeDeDeux(false);
								Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1);
								p.ajouterPortee(c4);
							}
						}		
					}
				} 
			} 
			
			this.setLaPortee(p); //On retourne le type Portee p qui contiendra tout les déplacements possible pour ce pion dans un tableau.
		}
	}
	
	//---------------------------------------------------------------------------------------------------
	
	
	//------------------------------------PROMOTION PION-------------------------------------------------
	/*
	 * 		Dans Plateau: Lorsqu'un pion atteint la dernière ligne de l'échiquier, on demande
	 * 		au joueur en quoi il veut le changer => enQuoiJeDoisChanger. 
	 * 
	 * 		PromotionPion enlève le pion de la case et créée une nouvelle Piece
	 * 		du type en paramètre.
	 * 

	 */
	
	
	public void promotionPion(Plateau p, String enQuoiJeDoisChanger) {
		//demander quelle piece le joueur veut rajouter
		

		if (enQuoiJeDoisChanger.equals("C")) { //En Cavalier
			
			//création nouvelle Piece (cavalier)
			Piece promu = new Cavalier(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		} else if (enQuoiJeDoisChanger.equals("F")) { //En Fou
			
			//création nouvelle Piece (fou)
			Piece promu = new Fou(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		} else if (enQuoiJeDoisChanger.equals("T")) { //En Tour
			
			//création nouvelle Piece (tour)
			Piece promu = new Tour(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		} else  { //Le dernier cas ici est en Reine
			
			//création nouvelle Piece (reine)
			Piece promu = new Reine(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		}
	} 
	//------------------------------------------------------------------------------------------------
	
	

}
