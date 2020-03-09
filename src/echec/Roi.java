package echec;

public class Roi extends Piece {
	
	public Roi(String c) {
		super(c);
		this.setRepresentation('K');
	}
	
	
	//------------------------------------CALCULER PORTEE----------------------------------------------------
		/*	
		 * 		La port�e contient un tableau de Case o� la Piece peut se deplacer.
		 * 		CalculerPortee remplit ce tableau de Case en respectant les r�gles
		 * 		de d�placement de la Piece et en prenant en compte l'�tat du plateau.	
		 * 
		 * 		Sp�cificit� Roi:
		 * 		- Se d�place d'une case tout autour de lui
		 * 		- S'il peut se d�placer, on regarde s'il n'est pas en �chec sur la case concern�e
		 * 
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
		 * J'ai enlev� le if qui regardait si la couleur de la pi�ce �tait blanche
		 * car c'est inutile dans notre cas car le roi peut se d�placer
		 * tout autour de lui et pas dans un seul sens comme le pion.
		 * 
		 * 
		 * MAJ de la m�thode:
		 * 
		 * Il manquait le fait d'ajouter dans la port�e la case d'une 
		 * pi�ce d'une autre couleur si la case n'�tait pas libre
		 * (pour pouvoir la manger plus tard)
		 * 
		 * 
		 * MAJ de la m�thode:
		 * 
		 * Dans la simulation des d�placements en diagonales, 
		 * il manquait des "=" pour les "<=" ou ">="
		 * 
		 * 
		 * MAJ de la m�thode:
		 * 
		 * Dans les conditions pour v�rifier si l'on ne
		 * sortait pas du plateau, on regardait d'abord 
		 * le Y puis le X, j'ai invers� pour rendre �a
		 * plus logique (on a toujours v�rifi� d'abord
		 * le X puis le Y).
		 * 
		 * Mais le code en soi reste le m�me et �tait
		 * juste.
		 * 
		 * 
		 */
		//le roi avance d'une case: Y+1
		if (simulationCoordoY+1 <= 8) { //ne d�passe pas la taille Y du plateau quand y+1
			if (plateau.estLibre(simulationCoordoX,simulationCoordoY+1) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
				
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY+1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} 
		}
		//le roi va � droite: X+1
		if (simulationCoordoX+1 <= 8) { //ne d�passe pas la taille Y du plateau quand x+1
			if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} 
		}
		//le roi recule d'une case: Y-1
		if (simulationCoordoY-1 >= 1) {
			if (plateau.estLibre(simulationCoordoX,simulationCoordoY-1) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX, simulationCoordoY-1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} 
		}
		//le roi va � gauche: X-1
		if (simulationCoordoX-1 >= 1) {
			if (plateau.estLibre(simulationCoordoX-1,simulationCoordoY) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} 
		}
		//le roi avance d'une case+droite: X+1 Y+1
		if ((simulationCoordoX+1 <= 8) && (simulationCoordoY+1 <= 8) ) {
			if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY+1) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY+1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} 
		}
		//le roi recule d'une case+droite:X+1 Y-1
		if ((simulationCoordoX+1 <= 8) && (simulationCoordoY-1 >= 1) ) {
			if (plateau.estLibre(simulationCoordoX+1,simulationCoordoY-1) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX+1, simulationCoordoY-1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} 
		}	
		//le roi avance d'une case + gauche: X-1 Y+1
		if ((simulationCoordoX-1 >= 1) && (simulationCoordoY+1 <= 8) ) {
			if (plateau.estLibre(simulationCoordoX-1,simulationCoordoY+1) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY+1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			}
		}
		//le roi recule d'une case + gauche: X-1 Y-1
		if ((simulationCoordoX-1 >= 1) && (simulationCoordoY-1 >= 1)) {
			if (plateau.estLibre(simulationCoordoX-1,simulationCoordoY-1) == true) { 
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			} else if (!(plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1).getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si pas libre et pas de mm couleur
				Case c1 = plateau.getCaseAtCoordo(simulationCoordoX-1, simulationCoordoY-1);
				if (enEchec(plateau,c1)==false) { //Si il n'y a pas d'echec dans la case d'arriv�e
					p.ajouterPortee(c1);
				}
			}
		}
		
		this.setLaPortee(p);
	}
	
	public boolean enEchec(Plateau plateau) {
		boolean enEchec=false;
		Case caseDuRoi = super.getLaCaseDeLaPiece();
		Piece[] bonnesPieces = new Piece[16];
		int indiceDuTableau=0;
		
		for (int i=0; i<8; i++) { //Ici c'est les COLONNES (X)
					
			for (int j=0; j<8; j++) { //Ici c'est les LIGNES (Y)
				
				if (plateau.getPlateau()[i][j].getPiece()!=null) { //Si la case contient BIEN UNE PIECE
					
					if (plateau.getPlateau()[i][j].getPiece().getRepresentation()!='K') {
					
						if (!(plateau.getPlateau()[i][j].getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si la piece N'EST PAS DE LA MEME COULEUR
							
							bonnesPieces[indiceDuTableau]=plateau.getPlateau()[i][j].getPiece();
							indiceDuTableau++;			
						}	
					}
				}		
			}	
		}
		
		for (int i=0; i<indiceDuTableau; i++) {
			bonnesPieces[i].calculerPortee(plateau);	
			for(int j = 0 ; j<bonnesPieces[i].getLaPortee().getNbrCases();j++) {
				if (bonnesPieces[i].getLaPortee().getCases()[j].equals(caseDuRoi)) {
					enEchec=true;
				}
			}
		}
		return enEchec;
	}
	

	public boolean enEchec(Plateau plateau, Case caseAVerifier) {
		boolean enEchec=false;
		Piece[] bonnesPieces = new Piece[16];
		int indiceDuTableau=0;
		for (int i=0; i<8; i++) { //Ici c'est les COLONNES (X)
					
			for (int j=0; j<8; j++) { //Ici c'est les LIGNES (Y)
				
				if (plateau.getPlateau()[i][j].getPiece()!=null) { //Si la case contient BIEN UNE PIECE
					
					if (plateau.getPlateau()[i][j].getPiece().getRepresentation()!='K') {
						
						if (!(plateau.getPlateau()[i][j].getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) { //Si la piece N'EST PAS DE LA MEME COULEUR
							//System.out.println(plateau.getPlateau()[i][j].getPiece());
							bonnesPieces[indiceDuTableau]=plateau.getPlateau()[i][j].getPiece();
							indiceDuTableau++;			
						}		
					}
				}		
			}	
		}
		Plateau simulation = plateau.clone();
		

		simulation.getPlateau()[caseAVerifier.getColonne()-1][caseAVerifier.getLigne()-1].enleverPiece(); //On vire la pi�ce de la case
		
		int ancienneCol=simulation.getPlateau()[this.laCaseDeLaPiece().getColonne()-1][this.laCaseDeLaPiece().getLigne()-1].getColonne();
		int ancienneLig=simulation.getPlateau()[this.laCaseDeLaPiece().getColonne()-1][this.laCaseDeLaPiece().getLigne()-1].getLigne();
		

		simulation.getPlateau()[caseAVerifier.getColonne()-1][caseAVerifier.getLigne()-1].setPieceDansCase(simulation.getPlateau()[this.laCaseDeLaPiece().getColonne()-1][this.laCaseDeLaPiece().getLigne()-1].getPiece());
		//On met le Roi dans la case de la pi�ce qu'on a vir� juste avant
		

		simulation.getPlateau()[this.laCaseDeLaPiece().getColonne()-1][this.laCaseDeLaPiece().getLigne()-1].getPiece().setLaCaseDeLaPiece(caseAVerifier);
		//On dit que la case � la pi�ce qu'on vient de bouger
		
		simulation.getPlateau()[ancienneCol-1][ancienneLig-1].enleverPiece();
		
		for (int i=0; i<indiceDuTableau; i++) {
			bonnesPieces[i].calculerPortee(simulation);
			//System.out.println(bonnesPieces[i].getRepresentation()+" "+bonnesPieces[i].getLaPortee().toString());
			for(int j = 0 ; j<bonnesPieces[i].getLaPortee().getNbrCases();j++) {
				if (bonnesPieces[i].getLaPortee().getCases()[j].equals(caseAVerifier)) {
					enEchec=true;
					return enEchec;
				}
			}
		}
		
		
		
		return enEchec;
	}
	//--------------------------------------------------------------------------------------------
	
	//-----------------------------------ECHEC ET MAT---------------------------------------------
	/*
	 * 	Echec et Mat verifie: 
	 * 		- Si le roi est en �chec sur sa case.
	 * 		- Si sa port�e est nulle.
	 * 		- Si aucune pi�ce ne peut se mettre en travers de la pi�ce qui met en �chec le roi.
	 * 		- Si aucune pi�ce ne peut manger la pi�ce qui met en �chec le roi.
	 * 
	 */
	public boolean enEchecEtMat(Plateau plateau) {
		/*on verifie:
		 * 	-le roi est en echec sur sa case
		 * 	-portee == null
		 * 	-aucune piece ne peut se mettre devant/manger celui/ceux qui le mettent en echec
		 * 
		 * 
		 * Calculer l'espace entre le roi et la ou les pi�ces qui mettent en echec
		 * verifier pour chaque pi�ce allier de voir si elole peuvent se mettre dans une des ces cases poour le proteger fnrogr
		 * 
		 * Simuler le deplacement d'une pi�ce alli�e et si le roi (donc en v�rifie avec enEchec() ) n'est plus en echec alos le d�placement est valide pour cover le roi  
		 */
		boolean estEnEchecEtMat=false;
		
		/*aucune piece...*/
		
		Plateau simulation = plateau.clone();
		
		//Piece p= new Piece(this.getCouleurPiece().invertionCouleur().getCouleur());
		int bonneColonne=0;
		int bonneLigne=0;
		//System.out.println("de p avant"+p.hashCode());
		
		
		//(1)Le roi est en �chec sur sa case + il a une port�e nulle
		
		if ((this.enEchec(simulation)==true) && this.getLaPortee().getNbrCases()==0) {
			
		//fin (1)
			
			
		//Savoir si une autre pi�ce peut intervenir(2)
			
			//SAVOIR QUELLE PIECE MET EN ECHEC LE ROI (3)
			
			for (int i=0; i<8; i++) { //Ici c'est les COLONNES (X)
						
				for (int j=0; j<8; j++) { //Ici c'est les LIGNES (Y)
					
					if (simulation.getPlateau()[i][j].getPiece()!=null) { //Si la case contient BIEN UNE PIECE
						
						if (!(simulation.getPlateau()[i][j].getPiece().getCouleurPiece().equals(super.getCouleurPiece()))) {
							
							//regarder si la case de la roi est dans la port�e de la pi�ce
							for(int r=0; r<simulation.getPlateau()[i][j].getPiece().getLaPortee().getNbrCases() ; r++) {
								
								if( simulation.getPlateau()[i][j].getPiece().getLaPortee().getCases()[r].equals(this.getLaCaseDeLaPiece())) {
									
									bonneColonne=i;
									bonneLigne=j;
									
									//p= plateau.getPlateau()[i][j].getPiece().clone(); //p est la piece qui met le roi en echec
									//System.out.println("de p apr�s"+p.hashCode());
								}
							}
						}
					}
				}
			}
			//fin (3)
			
			
			
			
			
			
			for (int i=0; i<8; i++) { //Ici c'est les COLONNES (X)
				
				for (int j=0; j<8; j++) { //Ici c'est les LIGNES (Y)
					
					if (simulation.getPlateau()[i][j].getPiece()!=null) { //Si la case contient BIEN UNE PIECE
						
						if (simulation.getPlateau()[i][j].getPiece().getCouleurPiece().equals(super.getCouleurPiece())) {//m�me couleur que le roi//
							
							for(int r=0; r<simulation.getPlateau()[i][j].getPiece().getLaPortee().getNbrCases() ; r++) {
									
								//for(int l=0; l<p.getLaPortee().getNbrCases() ; l++) { //port�e de p
								for(int l=0; l<simulation.getPlateau()[bonneColonne][bonneLigne].getPiece().getLaPortee().getNbrCases() ; l++) { //port�e de p	
									for(int m=0; m<simulation.getPlateau()[i][j].getPiece().getLaPortee().getNbrCases() ; m++) { //port�e de la pi�ce potentiellement sauveuse
										
										//on regarde si la pi�ce est sauveuse
										//if(simulation.getPlateau()[i][j].getPiece().getLaPortee().getCases()[m].equals(p.getLaPortee().getCases()[l])) {
										if(simulation.getPlateau()[i][j].getPiece().getLaPortee().getCases()[m].equals(simulation.getPlateau()[bonneColonne][bonneLigne].getPiece().getLaPortee().getCases()[l])) {	
											
											//simulation.getPlateau()[i][j].getPiece().deplacer(simulation.getPlateau()[i][j], p.getLaPortee().getCases()[l], simulation);
											simulation.getPlateau()[i][j].getPiece().deplacer(simulation.getPlateau()[i][j], simulation.getPlateau()[bonneColonne][bonneLigne].getPiece().getLaPortee().getCases()[l], simulation);
											

											
											//if(  (this.enEchec(simulation)==true) /*ne peut pas se mettre entre la pi�ce et le roi*/  ||  ((simulation.getPlateau()[i][j].getPiece().getLaPortee().getCases()[r].equals(p.getLaCaseDeLaPiece()))==false) /*ne peut pas manger*/ ){
											if(  (this.enEchec(simulation)==true) /*ne peut pas se mettre entre la pi�ce et le roi*/  ||  ((simulation.getPlateau()[i][j].getPiece().getLaPortee().getCases()[r].equals(simulation.getPlateau()[bonneColonne][bonneLigne].getPiece().getLaCaseDeLaPiece()))==false) /*ne peut pas manger*/ ){
												estEnEchecEtMat=true;
												return estEnEchecEtMat;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		//(2)
		return estEnEchecEtMat ;
	}
		
	
	
	//-----------------------------------ECHEC ET PAT---------------------------------------------
		/*
		 * 	Echec et Pat verifie: 
		 * 		- Si le roi n'est pas en �chec sur sa case
		 * 		- Si sa port�e est nulle
		 * 		- Si aucune pi�ce  du camp ali� ne peut se d�placer sans mettre son roi en echec
		 * 
		 */
	
	public boolean enEchecEtPat(Plateau plateau) {	
		Plateau simulation = plateau.clone();
		Plateau simulation2 = plateau.clone();
		int portee=0;

		if(plateau.getNbrPieceRestantes()==2) {
			return true;
		}
		//------------------------MORCEAU LIE AU FAIT QUE LE ROI NE PEUT PAS METTRE EN ECHEC L'AUTRE ROI--------------
		if(this.enEchec(plateau)==false) {
			
			for (int i=0; i<this.getLaPortee().getNbrCases(); i++) { //parcours de la portee du roi courant
				if(plateau.estLibre(this.getLaPortee().getCases()[i].getColonne(), this.getLaPortee().getCases()[i].getLigne())==false) { 
					//si une case de la port�e est occup�e
					if(this.getLaPortee().getCases()[i].getPiece().getCouleurPiece().equals(this.getCouleurPiece().invertionCouleur())){ 
						//par une pi�ce couleur oppos�e
						simulation2.getCaseAtCoordo(this.getLaPortee().getCases()[i].getColonne(), this.getLaPortee().getCases()[i].getLigne()).enleverPiece();
						
						//on enl�ve la pi�ce dans la simu

						simulation2.actualiserPortees(); //on actualise les port�es dans la simu
						simulation2.afficherPlateau();
						for(int j=0; j<simulation2.findTheKing(this.getCouleurPiece().invertionCouleur()).getLaPortee().getNbrCases();j++) { //parcours port�e roi oppos�
							if(simulation2.findTheKing(this.getCouleurPiece().invertionCouleur()).getLaPortee().getCases()[j].equals(this.getLaPortee().getCases()[i])) { 
								//si port�e la m�me => portee++
								portee++;
							}
							
						}
					}
					
				}
				else {//on augmente aussi portee si il y a correspondance entre les deux port�es sans que la case soit occup�e
					for(int j=0; j<plateau.findTheKing(this.getCouleurPiece().invertionCouleur()).getLaPortee().getNbrCases();j++) {
						if(plateau.findTheKing(this.getCouleurPiece().invertionCouleur()).getLaPortee().getCases()[j].equals(this.getLaPortee().getCases()[i])) {
							portee++;
						}
						
					}
				}
			}

			if(portee==this.getLaPortee().getNbrCases()) {
				boolean deplacementAutrePiece = false;
				for (int i=0; i<8; i++) {
					for (int j=0; j<8; j++) {
						if (simulation2.getPlateau()[i][j].getPiece() != null) {
							if (simulation2.getPlateau()[i][j].getPiece().getCouleurPiece().equals(this.getCouleurPiece())) {
								if (simulation2.getPlateau()[i][j].getPiece().getLaPortee().getNbrCases()!=0) {
									deplacementAutrePiece=true;
								}
							}
						}
					}
				}
				if (deplacementAutrePiece==false) {
					return true;
				}
			}
		}
		//-----------------------------------------------------------------------------------------------------------------------------
		
		
		else if(this.enEchec(plateau)==false && this.getLaPortee().getNbrCases()==0) {
			
			for (int i=0; i<8; i++) { //Ici c'est les COLONNES (X)
				
				for (int j=0; j<8; j++) { //Ici c'est les LIGNES (Y)
					
					if (plateau.getPlateau()[i][j].getPiece()!=null) { //Si la case contient BIEN UNE PIECE
						
						if (plateau.getPlateau()[i][j].getPiece().getCouleurPiece().equals(super.getCouleurPiece())) {//m�me couleur que le roi//
							
							for(int r=0; r<plateau.getPlateau()[i][j].getPiece().getLaPortee().getNbrCases() ; r++) {

								simulation.getPlateau()[i][j].getPiece().deplacer(simulation.getPlateau()[i][j], simulation.getPlateau()[i][j].getPiece().getLaPortee().getCases()[r], simulation);
									if(this.enEchec(simulation)==false) {
										return false;
									}
								simulation=plateau.clone();
							}
						}
					}
				}
			}
			
			
			return true;
		}

		return false;
	}
}
