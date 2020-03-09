package echec;

public class Pion extends Piece {
	
	public Pion(String c) {
		super(c);
		this.setRepresentation('P');
	}
	
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
	/**	
	 * 		La port�e contient un tableau de Case o� la Piece peut se deplacer.
	 * 		CalculerPortee remplit ce tableau de Case en respectant les r�gles
	 * 		de d�placement de la Piece et en prenant en compte l'�tat du plateau.	
	 * 
	 * 		Sp�cificit�s Pion:
	 * 		-avance de 1 case "en avant"
	 * 		-possibilit� d'avancer de deux cases s'il n'a jamais boug�
	 * 		-peut se deplacer en diagonale "avant" s'il y a une piece (qu'il va alors manger)
	 * 		
	 * 	*/
	@Override
	public void calculerPortee(Plateau plateau) {
		Portee p = new Portee();
		
		int simulationCoordoY=super.laCaseDeLaPiece().getLigne(); //Pour manipuler des coordonn�es sans bouger la pi�ce.
		int simulationCoordoX=super.laCaseDeLaPiece().getColonne(); //Pour manipuler des coordonn�es sans bouger la pi�ce.
		
		Couleur enBlanc= new Couleur("blanc"); //Pour comparer cette couleur � la couleur de la pi�ce.
		
		/* 
		 * M�thode �crite par K�vin
		 * 
		 * 
		 * Pr�cisions/Logique :
		 * 
		 *  Pour les d�placements simul�s, 
		 *  on se place du point de vue
		 *  du joueur blanc.
		 * 
		 */
		

		//=================================POUR LA COULEUR BLANCHE=================================
		if (plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY).getPiece().getCouleurPiece().equals(enBlanc)) {
			//----------------------------AVANCER 1 CASE TOUT DROIT-------------------------------------
			
			//On v�rifie d'abord si le pion ne se trouve pas tout au bout(en haut) du plateau, dans ce cas il ne pourra pas avancer.
			
			if (simulationCoordoY+1 <= 8) {
				if (plateau.estLibre(simulationCoordoX,simulationCoordoY+1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1);
					p.ajouterPortee(c1);
				}
			}
			
			//----------------------------AVANCER 2 CASES TOUT DROIT-------------------------------------
			
			//On v�rifie d'abord que le pion ne s'est jamais d�plac� avant de v�rifier si il peut avancer de 2 cases en avant.
			if (super.dejaDeplace()==false) {
				
				//Si dans la case du plateau il n'y a aucune pi�ce (alli�e ou ennemie) ALORS on ajoutera ce d�placement dans Port�e.
				if ((plateau.estLibre(simulationCoordoX, simulationCoordoY+1) == true) && (plateau.estLibre(simulationCoordoX, simulationCoordoY+2) == true)) { 
					
					super.setAiJeAvanceeDeDeux(true);
					Case c2 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+2);
					p.ajouterPortee(c2);
				}
			}
			
			//----------------------------AVANCER UNE CASE EN DIAGONALE (EN HAUT ET A GAUCHE)-------------------------------------
			
			//On v�rifie d'abord si les coordonn�es de la case d'arriv�e sont toujours dans le plateau
			if (simulationCoordoX-1 >= 1 && simulationCoordoY+1 <= 8) {
				
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX-1, simulationCoordoY+1);
				
				
				//Si la case n'est pas libre, ET que c'est une pi�ce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce d�placement dans Portee.
				if  (isLibre==false) {
					
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c3 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1);
						p.ajouterPortee(c3);
					}
					
				} else {
					//Si il y a bien une pi�ce � la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pi�ce ennemie (donc de la couleur oppos�)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pi�ce situ� � sa gauche a avanc� de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
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

			//On v�rifie d'abord si les coordonn�es de la case d'arriv�e sont toujours dans le plateau
			if (simulationCoordoX+1 <= 8 && simulationCoordoY+1 <=8) {
				
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX+1, simulationCoordoY+1);
					
				//Si la case n'est pas libre, ET que c'est une pi�ce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce d�placement dans Portee.
				if (isLibre==false) {
					
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1);
						p.ajouterPortee(c4);
					}	
				} else {
					//Si il y a bien une pi�ce � la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pi�ce ennemie (donc de la couleur oppos�)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pi�ce situ� � sa gauche a avanc� de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
							if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getAiJeAvanceeDeDeux()==true) {
								
								//plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().setAiJeAvanceeDeDeux(false);
								Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1);
								p.ajouterPortee(c4);
							}
						}		
					}
				}
			} 

			this.setLaPortee(p); //On set le type Portee p qui contiendra tout les d�placements possible pour ce pion dans un tableau.
			
		} else { //=================================POUR LA COULEUR NOIRE=================================
			
			//----------------------------AVANCER 1 CASE TOUT DROIT-------------------------------------
			
			//On v�rifie d'abord si le pion ne se trouve pas tout au bout(en haut) du plateau, dans ce cas il ne pourra pas avancer.
			if (simulationCoordoY-1 >= 1) {
				if (plateau.estLibre(simulationCoordoX,simulationCoordoY-1) == true) { 
					Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1);
					p.ajouterPortee(c1);
				}
			}
			
			//----------------------------AVANCER 2 CASES TOUT DROIT-------------------------------------
			
			//On v�rifie d'abord que le pion ne s'est jamais d�plac� avant de v�rifier si il peut avancer de 2 cases en avant.
			if (super.dejaDeplace()==false) {
				
				//Si dans la case du plateau il n'y a aucune pi�ce (alli�e ou ennemie) ALORS on ajoutera ce d�placement dans Port�e.
				if ((plateau.estLibre(simulationCoordoX, simulationCoordoY-1) == true) && (plateau.estLibre(simulationCoordoX, simulationCoordoY-2) == true)) { 
					
					super.setAiJeAvanceeDeDeux(true);
					Case c2 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-2);
					p.ajouterPortee(c2);
				}
			}
			
			//----------------------------AVANCER UNE CASE EN DIAGONALE (EN BAS ET A GAUCHE)-------------------------------------
			
			//On v�rifie d'abord si les coordonn�es de la case d'arriv�e sont toujours dans le plateau
			if (simulationCoordoX-1 >= 1 && simulationCoordoY-1 >= 1) {
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX-1, simulationCoordoY-1);
					
				//Si la case n'est pas libre, ET que c'est une pi�ce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce d�placement dans Portee.
				if  (isLibre==false) {
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c3 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1);
						p.ajouterPortee(c3);
					}
					
				} else {
					//Si il y a bien une pi�ce � la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pi�ce ennemie (donc de la couleur oppos�)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pi�ce situ� � sa gauche a avanc� de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
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

			//On v�rifie d'abord si les coordonn�es de la case d'arriv�e sont toujours dans le plateau
			if (simulationCoordoX+1 <= 8 && simulationCoordoY-1 >=1) {
				
				//Variables temporaires pour rendre le code du IF ci-dessous plus lisible
				boolean isLibre=plateau.estLibre(simulationCoordoX+1, simulationCoordoY-1);
				
				
				//Si la case n'est pas libre, ET que c'est une pi�ce d'une AUTRE couleur(pour pouvoir la manger) qui se trouve dessus, ALORS on ajoutera ce d�placement dans Portee.
				if (isLibre==false) {
					
					boolean isSameColor=plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
					
					if (!isSameColor) {
						Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1);
						p.ajouterPortee(c4);
					}
				} else {
					//Si il y a bien une pi�ce � la gauche 
					if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece()!=null) {
						
						//Si c'est une pi�ce ennemie (donc de la couleur oppos�)
						boolean isSameColor= plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece());
						
						if (!isSameColor) {
							
							//Si la pi�ce situ� � sa gauche a avanc� de 2 cases juste avant (on le voit avec aiJeAvanceeDeDeux)
							if (plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getAiJeAvanceeDeDeux()==true) {
								
								//plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().setAiJeAvanceeDeDeux(false);
								Case c4 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1);
								p.ajouterPortee(c4);
							}
						}		
					}
				} 
			} 
			
			this.setLaPortee(p); //On retourne le type Portee p qui contiendra tout les d�placements possible pour ce pion dans un tableau.
		}
	}
	
	//---------------------------------------------------------------------------------------------------
	
	
	//------------------------------------PROMOTION PION-------------------------------------------------
	/*
	 * 		Dans Plateau: Lorsqu'un pion atteint la derni�re ligne de l'�chiquier, on demande
	 * 		au joueur en quoi il veut le changer => enQuoiJeDoisChanger. 
	 * 
	 * 		PromotionPion enl�ve le pion de la case et cr��e une nouvelle Piece
	 * 		du type en param�tre.
	 * 

	 */
	
	
	public void promotionPion(Plateau p, String enQuoiJeDoisChanger) {
		//demander quelle piece le joueur veut rajouter
		

		if (enQuoiJeDoisChanger.equals("C")) { //En Cavalier
			
			//cr�ation nouvelle Piece (cavalier)
			Piece promu = new Cavalier(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		} else if (enQuoiJeDoisChanger.equals("F")) { //En Fou
			
			//cr�ation nouvelle Piece (fou)
			Piece promu = new Fou(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		} else if (enQuoiJeDoisChanger.equals("T")) { //En Tour
			
			//cr�ation nouvelle Piece (tour)
			Piece promu = new Tour(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		} else  { //Le dernier cas ici est en Reine
			
			//cr�ation nouvelle Piece (reine)
			Piece promu = new Reine(super.getCouleurPiece().getCouleur());
			promu.setLaCaseDeLaPiece(super.getLaCaseDeLaPiece());
			p.getPlateau()[this.getLaCaseDeLaPiece().getColonne()-1][this.getLaCaseDeLaPiece().getLigne()-1].setPieceDansCase(promu);
			
		}
	} 
	//------------------------------------------------------------------------------------------------
	
	

}
