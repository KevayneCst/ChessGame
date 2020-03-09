package echec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Plateau {
	
	private final int maximum=8; //Nombre pratique pour créer des tableaux
	private Case[][] plateau; //Matrice de case (qui correspond à l'échiquier)
	private int nbrPieceRestantes=32; //Nombre de pièces sur le plateau, instancié à 32 et recalculé à chaque fois que l'on get cet attribut
	private Piece[] cimetiereBlanc; //Là on l'on range les pièces blanches mangée par les Noir
	private Piece[] cimetiereNoir; //Là on l'on range les pièces noires mangée par les Blanc
	
	public Plateau() {
		plateau = new Case[maximum][maximum]; //L'échiquier fait une taille de 8 par 8
		cimetiereBlanc = new Piece[2*maximum]; //Au maximum on aura dans le tableau 16 pieces d'une même couleur (techniquement 15 puisque le roi ne rentrera jamais dedans)
		cimetiereNoir = new Piece[2*maximum]; //Au maximum on aura dans le tableau 16 pieces d'une même couleur (techniquement 15 puisque le roi ne rentrera jamais dedans)
		creerPlateau(); //On appelle la fonctionne qui va remplir toute la matrice de cases (avec la bonne couleur de cases et leur coordonnées etc)
		remplirPlateau(); //On appelle la fonction qui permet de mettre sur chaque case de l'échiquier la pièce qu'il faut, conformément aux règles du jeu d'échec
		
	}
	
	public Plateau clone() { //Méthode pour copier un type Plateau (utile pour tout nos tests de simulation de déplacement)
		
		Plateau leClone = new Plateau();
		for (int i=0; i<8; i++) {
			
			for (int j=0; j<8; j++) {
				leClone.plateau[i][j]=this.plateau[i][j].clone();
			}
		}
		
		leClone.nbrPieceRestantes=this.nbrPieceRestantes;
		leClone.cimetiereBlanc=this.cimetiereBlanc.clone();
		leClone.cimetiereNoir=this.cimetiereNoir.clone();
		return leClone;
	}
	
	
	public void setNbrPieceRestantes(int nbrPieceRestantes) {
		this.nbrPieceRestantes = nbrPieceRestantes;
	}

	public int getNbrPieceRestantes() { //Méthode get un peu modifiée, qui va recalculer (en regardant le nombre de pièce présente sur l'échiquier) le nombre de pièces restantes
		int nbrPieces=0;
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (this.plateau[i][j].getPiece()!=null) {
					nbrPieces++;
				}
			}
		}
		return nbrPieces;
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}

	public void setCimetiereBlanc(Piece[] cimetiereBlanc) {
		this.cimetiereBlanc = cimetiereBlanc;
	}

	public void setCimetiereNoir(Piece[] cimetiereNoir) {
		this.cimetiereNoir = cimetiereNoir;
	}

	public void creerPlateau() { //Méthode pour associé, à chaque coordonnées de la matrice de cases, des cases que l'on instancie
		
		boolean alternanceCouleur = true;
		
		for(int i=0; i<8; i++) { //Les i c'est la COLONNE
			alternanceCouleur = true;
			
			if (i%2==0 ) { //Dès que le numero de ligne est pair on inverse variable alternanceCouleur
				alternanceCouleur = false;
			}
			
			for(int j=0; j<8;j++) { //Les j c'est la LIGNE
				
				if (alternanceCouleur == false) { //Quand alternanceCouleur==false on va créer des cases de couleur noir
					
					
					plateau[i][j] = new Case (i+1,j+1,"noir");
					//System.out.println("["+(i+1)+"]["+(j+1)+"]"+plateau[i][j].getCouleurCase());
					alternanceCouleur = true;
					
				} else { //Quand alternanceCouleur n'est pas égal à false (donc ==true) on va créer des cases de couleur noir
					
					plateau[i][j] = new Case (i+1,j+1,"blanc");
					//System.out.println("["+(i+1)+"]["+(j+1)+"]"+plateau[i][j].getCouleurCase());
					alternanceCouleur=false;
				}
			}
		}	
	}
	
	public void remplirPlateau() {
		
		/**
		 * Méthode qui va
		 * remplir le plateau
		 * de toutes les pièces
		 * qu'il faut pour pouvoir
		 * commencer à jouer.
		 * 
		 * A chaque fois on va mettre la pièce créer dans la case (au bonnes coordonnées)
		 * ET on va associé à la pièce la case sur laquelle elle est.
		 */

		//-----------Création des pièces de couleur blanche--------------
		
		Tour a1 = new Tour("blanc");
		plateau[0][0].setPieceDansCase(a1);
		a1.setLaCaseDeLaPiece(plateau[0][0]);
		
		Cavalier b1 = new Cavalier("blanc");
		plateau[1][0].setPieceDansCase(b1);
		b1.setLaCaseDeLaPiece(plateau[1][0]);
		
		Fou c1 = new Fou("blanc");
		plateau[2][0].setPieceDansCase(c1);
		c1.setLaCaseDeLaPiece(plateau[2][0]);
		
		Reine d1 = new Reine("blanc");
		plateau[3][0].setPieceDansCase(d1);
		d1.setLaCaseDeLaPiece(plateau[3][0]);
		
		Roi e1 = new Roi("blanc");
		plateau[4][0].setPieceDansCase(e1);
		e1.setLaCaseDeLaPiece(plateau[4][0]);
		
		Fou f1 = new Fou("blanc");
		plateau[5][0].setPieceDansCase(f1);
		f1.setLaCaseDeLaPiece(plateau[5][0]);
		
		Cavalier g1 = new Cavalier("blanc");
		plateau[6][0].setPieceDansCase(g1);
		g1.setLaCaseDeLaPiece(plateau[6][0]);
		
		Tour h1 = new Tour("blanc");
		plateau[7][0].setPieceDansCase(h1);
		h1.setLaCaseDeLaPiece(plateau[7][0]);
		
		
		Pion a2 = new Pion("blanc");
		plateau[0][1].setPieceDansCase(a2);
		a2.setLaCaseDeLaPiece(plateau[0][1]);
		
		Pion b2 = new Pion("blanc");
		plateau[1][1].setPieceDansCase(b2);
		b2.setLaCaseDeLaPiece(plateau[1][1]);
		
		Pion c2 = new Pion("blanc");
		plateau[2][1].setPieceDansCase(c2);
		c2.setLaCaseDeLaPiece(plateau[2][1]);
		
		Pion d2 = new Pion("blanc");
		plateau[3][1].setPieceDansCase(d2);
		d2.setLaCaseDeLaPiece(plateau[3][1]);
		
		Pion e2 = new Pion("blanc");
		plateau[4][1].setPieceDansCase(e2);
		e2.setLaCaseDeLaPiece(plateau[4][1]);
		
		Pion f2 = new Pion("blanc");
		plateau[5][1].setPieceDansCase(f2);
		f2.setLaCaseDeLaPiece(plateau[5][1]);
		
		Pion g2 = new Pion("blanc");
		plateau[6][1].setPieceDansCase(g2);
		g2.setLaCaseDeLaPiece(plateau[6][1]);
		
		Pion h2 = new Pion("blanc");
		plateau[7][1].setPieceDansCase(h2);
		h2.setLaCaseDeLaPiece(plateau[7][1]);
		
		//-----------Création des pièces de couleur noir--------------
		
		Tour a8 = new Tour("noir");
		plateau[0][7].setPieceDansCase(a8);
		a8.setLaCaseDeLaPiece(plateau[0][7]);
		
		Cavalier b8 = new Cavalier("noir");
		plateau[1][7].setPieceDansCase(b8);
		b8.setLaCaseDeLaPiece(plateau[1][7]);
		
		Fou c8 = new Fou("noir");
		plateau[2][7].setPieceDansCase(c8);
		c8.setLaCaseDeLaPiece(plateau[2][7]);
		
		Reine d8 = new Reine("noir");
		plateau[3][7].setPieceDansCase(d8);
		d8.setLaCaseDeLaPiece(plateau[3][7]);
		
		
		Roi e8 = new Roi("noir");
		plateau[4][7].setPieceDansCase(e8);
		e8.setLaCaseDeLaPiece(plateau[4][7]);
		
		Fou f8 = new Fou("noir");
		plateau[5][7].setPieceDansCase(f8);
		f8.setLaCaseDeLaPiece(plateau[5][7]);
		
		Cavalier g8 = new Cavalier("noir");
		plateau[6][7].setPieceDansCase(g8);
		g8.setLaCaseDeLaPiece(plateau[6][7]);
		
		Tour h8 = new Tour("noir");
		plateau[7][7].setPieceDansCase(h8);
		h8.setLaCaseDeLaPiece(plateau[7][7]);
		
		
		Pion a7 = new Pion("noir");
		plateau[0][6].setPieceDansCase(a7);
		a7.setLaCaseDeLaPiece(plateau[0][6]);

		Pion b7 = new Pion("noir");
		plateau[1][6].setPieceDansCase(b7);
		b7.setLaCaseDeLaPiece(plateau[1][6]);
		
		Pion c7 = new Pion("noir");
		plateau[2][6].setPieceDansCase(c7);
		c7.setLaCaseDeLaPiece(plateau[2][6]);
		
		Pion d7 = new Pion("noir");
		plateau[3][6].setPieceDansCase(d7);
		d7.setLaCaseDeLaPiece(plateau[3][6]);
		
		Pion e7 = new Pion("noir");
		plateau[4][6].setPieceDansCase(e7);
		e7.setLaCaseDeLaPiece(plateau[4][6]);
		
		Pion f7 = new Pion("noir");
		plateau[5][6].setPieceDansCase(f7);
		f7.setLaCaseDeLaPiece(plateau[5][6]);
		
		Pion g7 = new Pion("noir");
		plateau[6][6].setPieceDansCase(g7);
		g7.setLaCaseDeLaPiece(plateau[6][6]);
		
		Pion h7 = new Pion("noir");
		plateau[7][6].setPieceDansCase(h7);
		h7.setLaCaseDeLaPiece(plateau[7][6]);
		
	}
	
	
	
	
	public Piece[] getCimetiereBlanc() {
		 return cimetiereBlanc;
	}
	

	public Piece[] getCimetiereNoir() {
		return cimetiereNoir;
	}

	public boolean estLibre(int Xcolonne, int Yligne) { //Méthode pour vérifier, si aux coordonnés en paramètre, la case contient une pièce ou pas
		
		Piece pieceReference = this.plateau[Xcolonne-1][Yligne-1].getPiece();
		if (pieceReference==null) { //Ici obligation de mettre un == et pas un equals pour comparer avec un null
			return true; //Oui elle est libre
		} else {
			return false; //Non il y a une pièce sur la case
		}
	}
	
	public Case getCaseAtCoordo(int x, int y) {
		return plateau[x-1][y-1];
	}
	
	public void setCimetiere(Couleur c, Piece p) {
		if (c.getCouleur().equals("blanc")) {
			cimetiereBlanc[16-restePiecesBlanches()]=p;
		} else if (c.getCouleur().equals("noir")) {
			cimetiereNoir[16-restePiecesNoires()]=p;
		}
	}
	
	public void reinitialiserPions(Couleur c) { //Pour réinitialiser pour chaque Pion l'attribut "aiJeAvanceeDeDeux"
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (this.plateau[i][j].getPiece()!=null) {
					if (this.plateau[i][j].getPiece() instanceof Pion) {
						if (this.plateau[i][j].getPiece().getCouleurPiece().equals(c)) {
							this.plateau[i][j].getPiece().setAiJeAvanceeDeDeux(false);
						}
					}
				}
			}
		}
	}
	
	public Case[][] getPlateau() {
		return plateau;
	}

	public int restePiecesBlanches() {
		int nbrPieces=16;
		for (int i=0; i<this.cimetiereBlanc.length; i++) {
			if (cimetiereBlanc[i]==null) {
				return nbrPieces;
			} else {
				nbrPieces--;
			}
		}
		return nbrPieces;
	}
	
	public int restePiecesNoires() {
		int nbrPieces=16;
		for (int i=0; i<this.cimetiereNoir.length; i++) {
			if (cimetiereNoir[i]==null) {
				return nbrPieces;
			} else {
				nbrPieces--;
			}
		}
		return nbrPieces;
	}
	
	
	public void actualiserPortees() { //Méthode qui va recalculer les Portee de toutes les pièces de l'échiquier
		Piece[] toutesPieces = new Piece[32];
		int indiceDuTableau=0;
		
		for (int i=0; i<8; i++) { //Ici c'est les COLONNES (X)
			
			for (int j=0; j<8; j++) { //Ici c'est les LIGNES (Y)
				
				if (plateau[i][j].getPiece()!=null) { //Si la case contient BIEN UNE PIECE
					toutesPieces[indiceDuTableau]=plateau[i][j].getPiece();
					indiceDuTableau++;					
				}		
			}	
		}
		
		for (int i=0; i<indiceDuTableau; i++) {
			//System.out.println("Je calcule la portée de "+toutesPieces[i].getRepresentation()+" de couleur"+toutesPieces[i].getCouleurPiece());
			toutesPieces[i].calculerPortee(this);	
		}
	}
	
	public void afficherCimetiere(Couleur c) {
		if (c.getCouleur().equals("blanc")) {
			
			int indice=0;
			String leCimetiere="CimetiereBlanc {";
			while (cimetiereBlanc[indice] != null) {
				leCimetiere+=cimetiereBlanc[indice];
				indice++;
			}
			System.out.println(leCimetiere+"}");
			
		} else if (c.getCouleur().equals("noir")) {
			
			int indice=0;
			String leCimetiere="CimetiereNoir {";
			while (cimetiereNoir[indice] != null) {
				leCimetiere+=cimetiereNoir[indice];
				indice++;
			}
			System.out.println(leCimetiere+"}");
			
		}
	}
	
	public void afficherPlateau() { //Méthode qui va afficher directement (dès que l'on appelle la fonction) l'état de l'échiquier en regardant les pièces sur les cases de l'échiquier si il y en a
		int k=8, asciiValue=97;
		String lePlateau="";
		
		for (int i=8;i>0;i--) {
			lePlateau+=k+"   ";
			for (int j=1;j<9;j++) {
				
				if (this.getCaseAtCoordo(j, i).getPiece()==null) { //SI LA CASE NE CONTIENT PAS DE PIECE
					
					lePlateau+="..  "; //Remplacer le caractère par celui choisit pour représenter une case VIDE
					
				} else { //SI LA CASE CONTIENT UNE PIECE
					if ((this.getCaseAtCoordo(j, i)).getPiece().getCouleurPiece().equals(new Couleur("blanc"))) {
						lePlateau+=this.getCaseAtCoordo(j, i).getPiece().getRepresentation()+"b  "; //On utilise le caractère qu'on a attributé aux pièces pour l'afficher sur le plateau
					} else {
						lePlateau+=this.getCaseAtCoordo(j, i).getPiece().getRepresentation()+"n  "; //On utilise le caractère qu'on a attributé aux pièces pour l'afficher sur le plateau
					}
				}	
			}
			lePlateau+="\n\n";
			k--;
		}
		lePlateau+="\n    ";
		for (int j=1;j<9;j++) {
			lePlateau+=(char)asciiValue+"   "; //Avec '(char)' suivi d'un int, on va obtenir le caractère ASCII associé au nombre. (65=A, 66=B etc...)
			asciiValue++;
		}
		System.out.println(lePlateau+"\n");
	}
	
	public Roi findTheKing(Couleur c) { //Trouve le roi de la couleur passé en paramètre #Cersei
		for (int i=0; i<8; i++) {
			for (int j=0;j<8;j++) {
				if (plateau[i][j].getPiece()!=null) {
					if ((plateau[i][j].getPiece().getRepresentation()=='K') && (plateau[i][j].getPiece().getCouleurPiece().equals(c))) {
						Roi theKing = (Roi)plateau[i][j].getPiece();
						return theKing;
					}
				}	
			}
		}
		return null;
		
	}
	
	public String choisirPromotion() { 
		
		/**
		 * L'utilisateur va être invité à saisir en quel nouvelle pièce le pion va-t-il se promouvoir,
		 * et la méthode, une fois la saisie valide, va retourner le caractère 
		 * (ici j'ai délibérément choisi de retourner un type String au lieu d'un char)
		 * de la pièce que l'on a saisi.
		 */
		
		
		boolean saisieCorrecte=false;
		String retour="";
		
		while (saisieCorrecte==false) {
			
			saisieCorrecte=true;
			System.out.println("\nPar quelle piece voulez-vous remplacer votre pion?");
			System.out.println("Pour cavalier rentrez: C");
			System.out.println("Pour fou rentrez: F");
			System.out.println("Pour tour rentrez: T");
			System.out.println("Pour reine rentrez: Q");
			
			try {
				
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        String piece = bufferRead.readLine();
					
		        if (piece.equals("C")) {
		        	retour= "C";
		        } else if (piece.equals("F")) {
		        	retour= "F";
		        } else if (piece.equals("T")) {
		        	retour= "T";
		        } else if (piece.equals("Q")) {
		        	retour= "Q";
		        } else {
		        	saisieCorrecte=false;
					System.out.println("Saisie incorrecte, veuillez ressaisir une lettre");
		        }
			} catch(IOException e) {
		        e.printStackTrace();
		    }
		}
		return retour;
	}
	
	
	public void saisirDeplacement(Couleur c) {
		
		/**
		 * Méthode écrite par Kévin
		 * 
		 * 
		 * Dans cette méthode (malheuresement trop longue à mon goût) je m'occupe des choses suivantes:
		 * 
		 * -Saisie et vérification des informations saisies au clavier (pour le déplacement)
		 * -Vérification que le déplacement saisi de met pas en échec son propre Roi (dans ce cas ressaisir)
		 * -Vérification que le déplacement saisi ne correspond pas à un roque
		 * -Vérification que le déplacement saisi permette de sortir de la situation d'échec de son Roi (uniquement si le Roi est en échec évidemment)
		 * 
		 * Pour ce qui est de l'échec et mat / pat, cette vérification ce fait avant de rentrer dans cette méthode donc cela veut dire que
		 * si le système accède à cette méthode, c'est qu'il y a forcément une solution à une situation d'échec, ou forcément un coup à jouer légal.
		 * 
		 * J'ai essayé de compartimenté le code en 3 sections, en espérant que le tout reste compréhensible.
		 * 
		 */
		
		
		//---------------------Saisie et vérification des informations saisies au clavier---------------------
		
		int coordoXDepart=0;
		int coordoYDepart=0;
		int coordoXArrivee=0;
		int coordoYArrivee=0;

		System.out.println("~-~-~-~-~ Tour du joueur "+c.getCouleur()+" ~-~-~-~-~\n");
		boolean hasMove=false;
		
		String expReguliereLettreUn ="[a-h]"; //Expression régulières qui détecte si c'est une lettre minuscule entre a et h (donc a, b, c, d, e, f, g, h)
		String expReguliereLettreDeux ="[A-H]"; //Expression régulières qui détecte si c'est une lettre minuscule entre A et H (donc A, B, C, D, E, F, G, H)
		String expReguliereChiffre ="[1-8]"; //Expression régulières qui détecte si c'est un chiffre compris entre 1 et 8 (donc 1, 2, 3, 4, 5, 6, 7, 8)
		
		while (hasMove==false) { //Est-ce qu'au final, le déplacement a eu lieu ? Si non, on oblige le joueur à ressaisir un déplacement tant que le déplacement n'aura pas été effectué
			boolean depart=false; //Variable que j'utilise pour saisir les coordonnées de départ tant qu'on a pas une saisie valide
			boolean arrivee=false; //Variable que j'utilise pour saisir les coordonnées d'arrivée tant qu'on a pas une saisie valide
			
			this.afficherPlateau();
			
			try {
				while (depart==false) {
					
					if (this.findTheKing(c).enEchec(this)) {
						System.out.println("Votre Roi est en échec !");
					}
					
					System.out.println("\nDépart: Veuillez saisir les coordonnées de la pièce à deplacer:");
					BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
					String caseDepart = bufferRead.readLine();
					if (caseDepart.length()==2) {
						String premier=caseDepart.substring(0, 1);
						String deuxieme=caseDepart.substring(1);
						
						if (deuxieme.matches(expReguliereChiffre)) {
							
							if ((premier.matches(expReguliereLettreUn)) || (premier.matches(expReguliereLettreDeux))) {
								
								if (premier.matches(expReguliereLettreUn)) { //La lettre est en minuscule ==> On va upperCase la lettre
									premier=premier.toUpperCase();
								} 
								switch (premier) {
								case "A":
									coordoXDepart=1;
									break;
								case "B":
									coordoXDepart=2;
									break;
								case "C":
									coordoXDepart=3;
									break;
								case "D":
									coordoXDepart=4;
									break;
								case "E":
									coordoXDepart=5;
									break;
								case "F":
									coordoXDepart=6;
									break;
								case "G":
									coordoXDepart=7;
									break;
								case "H":
									coordoXDepart=8;
									break;
								}
								coordoYDepart=Integer.valueOf(deuxieme);
								if (plateau[coordoXDepart-1][coordoYDepart-1].getPiece()!=null) { //Si la case de départ contient bien une pièce
									if (plateau[coordoXDepart-1][coordoYDepart-1].getPiece().getCouleurPiece().equals(c)) { //Si la pièce sur la case de départ est bien une pièce d'une autre couleur que celle du joueur qui joue
										depart=true;
									} else {
										System.out.println("Vous ne pouvez pas déplacer une pièce de votre adversaire !\n");
									}
								} else {
									System.out.println("Il n'y a aucune pièce sur cette case !\n");
								}
							} else {
								System.out.println("La lettre de la colonne saisie ne se situe pas entre 'a' et 'h'\n");
							}	
						} else {
							System.out.println("Le numéro de ligne saisi ne se situe pas entre 1 et 8\n");
						}
					} else {
						System.out.println("Votre saisie ne contient pas 2 caractères\n");
					}
				}
				
				while (arrivee==false) {
					
					System.out.println("\nDestination: Veuillez saisir les coordonnées de la case de destination:");
					BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
					String caseArrivee = bufferRead.readLine();
					if (caseArrivee.length()==2) {
						String premier=caseArrivee.substring(0, 1);
						String deuxieme=caseArrivee.substring(1);
						
						if (deuxieme.matches(expReguliereChiffre)) {
							
							if ((premier.matches(expReguliereLettreUn)) || (premier.matches(expReguliereLettreDeux))) { 
								
								if (premier.matches(expReguliereLettreUn)) { //La lettre est en minuscule ==> On va upperCase la lettre
									premier=premier.toUpperCase();
								} 
								switch (premier) {
								case "A":
									coordoXArrivee=1;
									break;
								case "B":
									coordoXArrivee=2;
									break;
								case "C":
									coordoXArrivee=3;
									break;
								case "D":
									coordoXArrivee=4;
									break;
								case "E":
									coordoXArrivee=5;
									break;
								case "F":
									coordoXArrivee=6;
									break;
								case "G":
									coordoXArrivee=7;
									break;
								case "H":
									coordoXArrivee=8;
									break;
								}
								coordoYArrivee=Integer.valueOf(deuxieme);
								arrivee=true;
							} else {
								System.out.println("La lettre de la colonne saisie ne se situe pas entre 'a' et 'h'\n");
							}
						} else {
							System.out.println("Le numéro de ligne saisi ne se situe pas entre 1 et 8\n");
						}
					} else {
						System.out.println("Votre saisie ne contient pas 2 caractères\n");
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			this.actualiserPortees();
			
			//---------------------Vérification que le déplacement saisi ne met pas son propre Roi en échec---------------------
			
			/*
			 * Idée générale de cette partie du code:
			 * 
			 * Si le Roi (avant le déplacement saisi) n'était pas en échec
			 * MAIS QUE après avoir saisi un déplacement
			 * le système se rend compte qu'il met son propre Roi en échec, 
			 * ALORS on va demander au joueur de ressaisir un déplacement.
			 * 
			 * Comment faire un "retour en arrière après un déplacement" ?
			 * 
			 * On va créer un nouveau "Plateau" qui sera une copie conforme
			 * du "Plateau" actuel, et c'est dans cette copie qu'on va faire
			 * le déplacement (c'est une "simulation" !). Si dans cette
			 * simulation on se rend compte que le joueur met son propre
			 * Roi en échec, alors on va demande au joueur de ressaisir 
			 * un déplacement. SINON, on va faire le déplacement pour
			 * de "vrai" dans le "vrai" "Plateau".
			 * 
			 */
			
			
			if (this.findTheKing(c).enEchec(this)==false) { //Si le Roi n'est pas encore en échec (on ne s'est pas déplacé pour l'instant !!!!)
				
				Plateau simulation = this.clone();
				boolean hasMoveInSimulation=false;
				simulation.actualiserPortees();
				
				
				//-------------------Partie vérification que les coordonnées saisie de correspondent à un roque-------------------
				
				//On regarde qu'il s'agit d'un déplacement de 2 pièces (et pas d'une pièce dans une case vide par exemple)
				if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece()!=null) && (simulation.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece()!=null)) {

					//On vérifie d'abord que ce sont des pièces de même couleur (sinon ça serait un déplacement vers une pièce ennemie et non pas un roque)
					if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().getCouleurPiece().equals(c)) && (simulation.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().getCouleurPiece().equals(c))) {
						
						//(Si la case de départ contient un Roi et la case d'arrivée une Tour) OU (si la case de départ contient une Tour et la case d'arrivée un Roi)
						if (((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece() instanceof Tour) && (simulation.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece() instanceof Roi)) || ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece() instanceof Roi) && (simulation.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece() instanceof Tour))) {
							
							//On va maintenant séparer les deux conditons ci-dessus pour savoir auquel des deux conditions on a affaire:
							
							//Ici c'est le cas où le joueur à saisit d'abord les coordonnées de sa Tour puis celle de son Roi
							if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece() instanceof Tour) && (simulation.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece() instanceof Roi)) {
								
								//On vérifie si les deux pièces ne ce sont jamais déplacées
								if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().isDejaDeplace()==false) && (simulation.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().isDejaDeplace()==false)) {
									
									//On doit maintenant, séparer les possibilités de roque selon la couleur de la pièce
									
									//Donc si la couleur de la pièce est blanche
									if (c.equals(new Couleur("blanc"))) { 
										
										//Ensuite on doit regarder si le joueur veut faire le "petit" ou le "grand" roque, pour ça on regarde la position de la Tour saisie (qui est sur sa position de départ car si on est dans cette partie du code, c'est qu'on a jamais déplacé sa Tour (en plus de son Roi)
										
										//Pour le grand roque (le Roi va VERS LA GAUCHE du point du vu du joueur blanc)
										if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getColonne()==1) && (simulation.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==1)) {
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(2, 1)) && (simulation.estLibre(3, 1)) && (simulation.estLibre(4, 1))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(4, 1))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(3, 1))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(3, 1)); 
													this.getCaseAtCoordo(3, 1).setPieceDansCase(this.getCaseAtCoordo(5, 1).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(4, 1)); 
													this.getCaseAtCoordo(4, 1).setPieceDansCase(this.getCaseAtCoordo(1, 1).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}	
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}	
										} else { //C'est donc ici le petit roque (le Roi va VERS LA DROITE du point de vu du joueur blanc)
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(6, 1)) && (simulation.estLibre(7, 1))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(6, 1))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(7, 1))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(7, 1)); 
													this.getCaseAtCoordo(7, 1).setPieceDansCase(this.getCaseAtCoordo(5, 1).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(6, 1)); 
													this.getCaseAtCoordo(6, 1).setPieceDansCase(this.getCaseAtCoordo(8, 1).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
													
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}
										}
									} else { //Donc si la couleur de la pièce est noire
										
										//Ensuite on doit regarder si le joueur veut faire le "petit" ou le "grand" roque, pour ça on regarde la position de la Tour saisie (qui est sur sa position de départ car si on est dans cette partie du code, c'est qu'on a jamais déplacé sa Tour (en plus de son Roi)
										
										//Pour le grand roque (le Roi va VERS LA GAUCHE du point du vu du joueur blanc)
										if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getColonne()==1) && (simulation.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==8)) {
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(2, 8)) && (simulation.estLibre(3, 8)) && (simulation.estLibre(4, 8))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(4, 8))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(3, 8))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(3, 8)); 
													this.getCaseAtCoordo(3, 8).setPieceDansCase(this.getCaseAtCoordo(5, 8).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(4, 8)); 
													this.getCaseAtCoordo(4, 8).setPieceDansCase(this.getCaseAtCoordo(1, 8).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}	
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}	
										} else { //C'est donc ici le petit roque (le Roi va VERS LA DROITE du point de vu du joueur blanc)
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(6, 8)) && (simulation.estLibre(7, 8))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(6, 8))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(7, 8))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(7, 8)); 
													this.getCaseAtCoordo(7, 8).setPieceDansCase(this.getCaseAtCoordo(5, 8).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(6, 8)); 
													this.getCaseAtCoordo(6, 8).setPieceDansCase(this.getCaseAtCoordo(8, 8).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
													
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}
										}
									}
								} else { //Si on rentre dans le else ça veut dire que les pièces ont déjà bougé
									System.out.println("Une des deux pièces (voir les deux) ont déjà été déplacé, le roque est donc impossible !\n");
								}
							} else { //Et ici (dans le else) c'est donc le cas inverse, c'est-à-dire que le joueur à d'abord saisi les coordonnées de son Roi puis celle de sa Tour
	
								int tempXD=coordoXDepart;
								int tempYD=coordoYDepart;
								int tempXA=coordoXArrivee;
								int tempYA=coordoYArrivee;
								
								coordoXDepart=tempXA;
								coordoYDepart=tempYA;
								coordoXArrivee=tempXD;
								coordoYArrivee=tempYD;
								
								//On vérifie si les deux pièces ne ce sont jamais déplacées
								if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().isDejaDeplace()==false) && (simulation.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().isDejaDeplace()==false)) {
									
									//On doit maintenant, séparer les possibilités de roque selon la couleur de la pièce
									
									//Donc si la couleur de la pièce est blanche
									if (c.equals(new Couleur("blanc"))) { 
										
										//Ensuite on doit regarder si le joueur veut faire le "petit" ou le "grand" roque, pour ça on regarde la position de la Tour saisie (qui est sur sa position de départ car si on est dans cette partie du code, c'est qu'on a jamais déplacé sa Tour (en plus de son Roi)
										
										//Pour le grand roque (le Roi va VERS LA GAUCHE du point du vu du joueur blanc)
										if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getColonne()==1) && (simulation.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==1)) {
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(2, 1)) && (simulation.estLibre(3, 1)) && (simulation.estLibre(4, 1))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(4, 1))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(3, 1))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(3, 1)); 
													this.getCaseAtCoordo(3, 1).setPieceDansCase(this.getCaseAtCoordo(5, 1).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(4, 1)); 
													this.getCaseAtCoordo(4, 1).setPieceDansCase(this.getCaseAtCoordo(1, 1).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}	
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}	
										} else { //C'est donc ici le petit roque (le Roi va VERS LA DROITE du point de vu du joueur blanc)
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(6, 1)) && (simulation.estLibre(7, 1))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(6, 1))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(7, 1))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(7, 1)); 
													this.getCaseAtCoordo(7, 1).setPieceDansCase(this.getCaseAtCoordo(5, 1).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(6, 1)); 
													this.getCaseAtCoordo(6, 1).setPieceDansCase(this.getCaseAtCoordo(8, 1).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
													
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}
										}
									} else { //Donc si la couleur de la pièce est noire
										
										//Ensuite on doit regarder si le joueur veut faire le "petit" ou le "grand" roque, pour ça on regarde la position de la Tour saisie (qui est sur sa position de départ car si on est dans cette partie du code, c'est qu'on a jamais déplacé sa Tour (en plus de son Roi)
										
										//Pour le grand roque (le Roi va VERS LA GAUCHE du point du vu du joueur blanc)
										if ((simulation.plateau[coordoXDepart-1][coordoYDepart-1].getColonne()==1) && (simulation.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==8)) {
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(2, 8)) && (simulation.estLibre(3, 8)) && (simulation.estLibre(4, 8))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(4, 8))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(3, 8))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(3, 8)); 
													this.getCaseAtCoordo(3, 8).setPieceDansCase(this.getCaseAtCoordo(5, 8).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(4, 8)); 
													this.getCaseAtCoordo(4, 8).setPieceDansCase(this.getCaseAtCoordo(1, 8).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}	
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}	
										} else { //C'est donc ici le petit roque (le Roi va VERS LA DROITE du point de vu du joueur blanc)
											
											//On va maintenant vérifier que toutes les cases entre le Roi et la Tour sont libres
											if ((simulation.estLibre(6, 8)) && (simulation.estLibre(7, 8))) {
												
												//On regarde maintenant si le Roi n'est pas en échec dans les cases par lesquelles il va "passer" pour roquer
												if ((simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(6, 8))==false) && (simulation.findTheKing(c).enEchec(simulation, simulation.getCaseAtCoordo(7, 8))==false)) {
													
													//On a maintenant toutes les conditions nécéssaire pour faire le roque souhaité par le joueur
													//On va devoir déplacer les pièces sans utiliser la méthode "deplacer" car celle-ci regarderait dans la Portee des pièces et dirait (avec raison) que le déplacement serait impossible
													//On va faire le déplacement "à la main" sur le "vrai" Plateau et pas notre simulation car on sait que tout est OK pour faire le roque pour de vrai
													
													//----Deplacement manuel pour le Roi----
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(7, 8)); 
													this.getCaseAtCoordo(7, 8).setPieceDansCase(this.getCaseAtCoordo(5, 8).getPiece());
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].enleverPiece();
														
													//----Deplacement manuel pour la Tour----
													this.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(this.getCaseAtCoordo(6, 8)); 
													this.getCaseAtCoordo(6, 8).setPieceDansCase(this.getCaseAtCoordo(8, 8).getPiece());
													this.plateau[coordoXDepart-1][coordoYDepart-1].enleverPiece();
													
													this.actualiserPortees();
													hasMove=true;
													
													
													
												} else { //Donc le roi est en échec dans une des cases par laquelle il doit "passer" pour roquer
													System.out.println("Le roque est impossible car le Roi est en échec quand il passe sur les cases pour aller roquer !\n");
												}
											} else { //Il y a des pièces entre le Roi et la Tour, le roque est donc impossible
												System.out.println("Il y a des pièces entre le Roi et la Tour sélectionnée !\n");
											}
										}
									}
								} else { //Si on rentre dans le else ça veut dire que les pièces ont déjà bougé
									System.out.println("Une des deux pièces (voir les deux) ont déjà été déplacé, le roque est donc impossible !\n");
								}
							}	
						} else { //Si on entre dans ce else cela veut dire que le joueur à saisi en case de départ et de destination des pièces de même couleur qui sont autre qu'une Tour et un Roi. OR il est interdit (sauf donc, l'exception du roque avec Roi et Tour) d'essayer de déplacer 2 pièces de même couleur.
							System.out.println("Vous ne pouvez pas déplacer deux pièces de même couleur ! (Sauf pour le roque)\n");
						}
					} else { //On entre dans cette partie du code si les deux pièces saisie de sont pas de même couleur (le déplacement n'est pas un roque)
					
						hasMoveInSimulation=simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().deplacer(simulation.plateau[coordoXDepart-1][coordoYDepart-1], simulation.plateau[coordoXArrivee-1][coordoYArrivee-1], simulation);
						simulation.actualiserPortees();
	
						if ((hasMoveInSimulation==true) && (simulation.findTheKing(c).enEchec(simulation)==true)) { //Si dans la simulation du déplacement on a pu se déplacer ET que le roi se met en échec	
							System.out.println("Vous ne pouvez pas mettre votre propre Roi en échec ! Veuillez rejouer !\n");
						} else {
							if (hasMoveInSimulation==true) {
								this.actualiserPortees();
								int nbrPiecesAvantDeplacement=this.nbrPieceRestantes;
								hasMove = plateau[coordoXDepart-1][coordoYDepart-1].getPiece().deplacer(plateau[coordoXDepart-1][coordoYDepart-1], plateau[coordoXArrivee-1][coordoYArrivee-1], this);
									
								if (hasMove==false) { //Si le déplacement ne s'est pas fait		
									System.out.println("Déplacement impossible, veuillez rejouer !\n");			
								} else {
									//-----------------------------------------------------------------------------------------------------
									//On regarde ici pour la promotionPion OU pour priseEnPassant, donc d'abord si la pièce déplacée est un Pion
									if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece() instanceof Pion) {
										
										if (c.equals(new Couleur("blanc"))) { //Si le pion est blanc
											
											if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==8) {
												Pion p = (Pion)this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece();
												p.promotionPion(this, this.choisirPromotion());	
											} else if ((this.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==5) && (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==6)) {
												if (nbrPiecesAvantDeplacement==this.nbrPieceRestantes) {
													
													//Deplacement en diagonale gauche
													if (coordoXDepart>coordoXArrivee) {
														
														this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece(), this);
														this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
														this.plateau[coordoXDepart-2][coordoYDepart-1].enleverPiece();
														
													} else if (coordoXDepart<coordoXArrivee) { //Deplacement diagonale droite
														
														this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart][coordoYDepart-1].getPiece(), this);
														this.plateau[coordoXDepart][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
														this.plateau[coordoXDepart][coordoYDepart-1].enleverPiece();
														
													}
												}
											}
											
										} else { //Si il est noir
											
											if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==1) {
												Pion p = (Pion)this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece();
												p.promotionPion(this, this.choisirPromotion());
											} else if ((this.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==4) && (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==3)) {
												if (nbrPiecesAvantDeplacement==this.nbrPieceRestantes) {
													
													//Deplacement en diagonale gauche
													if (coordoXDepart>coordoXArrivee) {
														
														this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece(), this);
														this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
														this.plateau[coordoXDepart-2][coordoYDepart-1].enleverPiece();
														
													} else if (coordoXDepart<coordoXArrivee) { //Deplacement diagonale droite
														
														this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart][coordoYDepart-1].getPiece(), this);
														this.plateau[coordoXDepart][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
														this.plateau[coordoXDepart][coordoYDepart-1].enleverPiece();
														
													}
												}
											}
										}
									}
									this.actualiserPortees();
									//-----------------------------------------------------------------------------------------------------
								}
							} else {
								System.out.println("Déplacement impossible, veuillez rejouer !\n");
							}
						
						}
					}	
				} else { //On entre dans cette partie du code si les deux pièces saisie de sont pas de même couleur (le déplacement n'est pas un roque)
					
					hasMoveInSimulation=simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().deplacer(simulation.plateau[coordoXDepart-1][coordoYDepart-1], simulation.plateau[coordoXArrivee-1][coordoYArrivee-1], simulation);
					simulation.actualiserPortees();

					if ((hasMoveInSimulation==true) && (simulation.findTheKing(c).enEchec(simulation)==true)) { //Si dans la simulation du déplacement on a pu se déplacer ET que le roi se met en échec	
						System.out.println("Vous ne pouvez pas mettre votre propre Roi en échec ! Veuillez rejouer !\n");
					} else {
						if (hasMoveInSimulation==true) {
							this.actualiserPortees();	
							int nbrPiecesAvantDeplacement=this.nbrPieceRestantes;
							hasMove = plateau[coordoXDepart-1][coordoYDepart-1].getPiece().deplacer(plateau[coordoXDepart-1][coordoYDepart-1], plateau[coordoXArrivee-1][coordoYArrivee-1], this);
								
							if (hasMove==false) { //Si le déplacement ne s'est pas fait		
								System.out.println("Déplacement impossible, veuillez rejouer !\n");			
							} else {	
								//-----------------------------------------------------------------------------------------------------
								//On regarde ici pour la promotionPion OU pour priseEnPassant, donc d'abord si la pièce déplacée est un Pion
								if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece() instanceof Pion) {
									
									if (c.equals(new Couleur("blanc"))) { //Si le pion est blanc
										
										if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==8) {
											Pion p = (Pion)this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece();
											p.promotionPion(this, this.choisirPromotion());	
										} else if ((this.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==5) && (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==6)) {
											if (nbrPiecesAvantDeplacement==this.nbrPieceRestantes) {
												
												//Deplacement en diagonale gauche
												if (coordoXDepart>coordoXArrivee) {
													
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece(), this);
													this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
													this.plateau[coordoXDepart-2][coordoYDepart-1].enleverPiece();
													
													
												} else if (coordoXDepart<coordoXArrivee) { //Deplacement diagonale droite
													
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart][coordoYDepart-1].getPiece(), this);
													this.plateau[coordoXDepart][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
													this.plateau[coordoXDepart][coordoYDepart-1].enleverPiece();
													
												}
											}
										}
										
									} else { //Si il est noir
										
										if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==1) {
											Pion p = (Pion)this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece();
											p.promotionPion(this, this.choisirPromotion());
										} else if ((this.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==4) && (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==3)) {
											if (nbrPiecesAvantDeplacement==this.nbrPieceRestantes) {
												
												//Deplacement en diagonale gauche
												if (coordoXDepart>coordoXArrivee) {
													
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece(), this);
													this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
													this.plateau[coordoXDepart-2][coordoYDepart-1].enleverPiece();
													
													
												} else if (coordoXDepart<coordoXArrivee) { //Deplacement diagonale droite
													
													this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart][coordoYDepart-1].getPiece(), this);
													this.plateau[coordoXDepart][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
													this.plateau[coordoXDepart][coordoYDepart-1].enleverPiece();
													
												}
											}
										}
									}
								}
								this.actualiserPortees();
								//-----------------------------------------------------------------------------------------------------
							}
						} else {
							System.out.println("Déplacement impossible, veuillez rejouer !\n");
						}
					}
				}
			}
			//---------------------Vérification de si on sort de la situation d'échec vécue par le Roi---------------------
			
			else if (this.findTheKing(c).enEchec(this)==true) { //On sait dans cette partie du code que le roi n'est pas en échec et mat mais simplement en échec donc il y a une solution possible
				
				Plateau simulation = this.clone();
				boolean hasMoveInSimulation=false;
				
				hasMoveInSimulation = simulation.plateau[coordoXDepart-1][coordoYDepart-1].getPiece().deplacer(simulation.plateau[coordoXDepart-1][coordoYDepart-1], simulation.plateau[coordoXArrivee-1][coordoYArrivee-1], simulation);
			
				if ((hasMoveInSimulation==true) && (simulation.findTheKing(c).enEchec(simulation)==false)) { //Si dans la simulation du déplacement on a pu se déplacer ET que le roi n'est plus en échec
					int nbrPiecesAvantDeplacement=this.nbrPieceRestantes;
					hasMove = plateau[coordoXDepart-1][coordoYDepart-1].getPiece().deplacer(plateau[coordoXDepart-1][coordoYDepart-1], plateau[coordoXArrivee-1][coordoYArrivee-1], this);
					//Comment on sait que le déplacement permet de se sortir de la situation d'échec, alors on va pouvoir faire le déplacement sur le VRAI plateau de jeu (et pas dans une simulation)

					//-----------------------------------------------------------------------------------------------------
					//On regarde ici pour la promotionPion OU pour priseEnPassant, donc d'abord si la pièce déplacée est un Pion
					if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece() instanceof Pion) {
						
						if (c.equals(new Couleur("blanc"))) { //Si le pion est blanc
							
							if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==8) {
								Pion p = (Pion)this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece();
								p.promotionPion(this, this.choisirPromotion());	
							} else if ((this.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==5) && (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==6)) {
								if (nbrPiecesAvantDeplacement==this.nbrPieceRestantes) {
									
									//Deplacement en diagonale gauche
									if (coordoXDepart>coordoXArrivee) {
										
										this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece(), this);
										this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
										this.plateau[coordoXDepart-2][coordoYDepart-1].enleverPiece();
										
										
									} else if (coordoXDepart<coordoXArrivee) { //Deplacement diagonale droite
										
										this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart][coordoYDepart-1].getPiece(), this);
										this.plateau[coordoXDepart][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
										this.plateau[coordoXDepart][coordoYDepart-1].enleverPiece();
										
									}
								}
							}
							
						} else { //Si il est noir
							
							if (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==1) {
								Pion p = (Pion)this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece();
								p.promotionPion(this, this.choisirPromotion());
							} else if ((this.plateau[coordoXDepart-1][coordoYDepart-1].getLigne()==4) && (this.plateau[coordoXArrivee-1][coordoYArrivee-1].getLigne()==3)) {
								if (nbrPiecesAvantDeplacement==this.nbrPieceRestantes) {
									
									//Deplacement en diagonale gauche
									if (coordoXDepart>coordoXArrivee) {
										
										this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece(), this);
										this.plateau[coordoXDepart-2][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
										this.plateau[coordoXDepart-2][coordoYDepart-1].enleverPiece();
										
										
									} else if (coordoXDepart<coordoXArrivee) { //Deplacement diagonale droite
										
										this.plateau[coordoXArrivee-1][coordoYArrivee-1].getPiece().manger(this.plateau[coordoXDepart][coordoYDepart-1].getPiece(), this);
										this.plateau[coordoXDepart][coordoYDepart-1].getPiece().setLaCaseDeLaPiece(null);
										this.plateau[coordoXDepart][coordoYDepart-1].enleverPiece();
										
									}
								}
							}
						}
					}
					this.actualiserPortees();
					//-----------------------------------------------------------------------------------------------------

				} else {
					
					System.out.println("Le déplacement saisi ne permet pas de sortir de la situation d'échec, veuillez rejouer !\n");
				}
			}
		}
	}
}
