package echec;

public class Piece {
	private char representation;
	private boolean dejaDeplace; //Pour le pion(avancer de 2 cases lors de son 1er d�placement OU pour le roc (tour et roi)
	private boolean enVie; //Mang�e ou pas
	private Couleur couleurPiece; //
	private Portee laPortee; 
	private Case laCaseDeLaPiece; 
	private boolean aiJeAvanceeDeDeux;
	
	public Piece(String c) {
		this.couleurPiece=new Couleur(c);
		this.dejaDeplace=false;
		this.enVie=true;
		this.aiJeAvanceeDeDeux=false;
	}
	

	@Override
	public Piece clone() {
		Piece laPiece = new Piece(this.couleurPiece.getCouleur());
		laPiece.dejaDeplace = this.dejaDeplace;
		laPiece.enVie = this.enVie;
		laPiece.laCaseDeLaPiece=this.laCaseDeLaPiece.clone();
		laPiece.laPortee=this.laPortee.clone();
		laPiece.aiJeAvanceeDeDeux=this.aiJeAvanceeDeDeux;
		return laPiece;
	}
	
	
	public Couleur getCouleurPiece() {
		return couleurPiece;
	}

	public Case getLaCaseDeLaPiece() {
		return laCaseDeLaPiece;
	}

	public boolean isDejaDeplace() {
		return dejaDeplace;
	}


	public boolean getAiJeAvanceeDeDeux() {
		return aiJeAvanceeDeDeux;
	}


	public void setAiJeAvanceeDeDeux(boolean aiJeAvanceeDeDeux) {
		this.aiJeAvanceeDeDeux = aiJeAvanceeDeDeux;
	}


	public boolean isEnVie() {
		return enVie;
	}


	public Portee getLaPortee() {
		return laPortee;
	}
	
	public char getRepresentation() {
		return representation;
	}

	public void setLaPortee(Portee laPortee) {
		this.laPortee = laPortee;
	}

	public void setRepresentation(char representation) {
		this.representation = representation;
	}
	
	public void setLaCaseDeLaPiece(Case laCaseDeLaPiece) {
		this.laCaseDeLaPiece = laCaseDeLaPiece;
	}
	
	public void setEnVie(boolean enVie) {
		this.enVie = enVie;
	}
	
	
	public void setDejaDeplace(boolean dejaDeplace) {
		this.dejaDeplace = dejaDeplace;
	}

	@Override
	public String toString() {
		return "Piece {representation=" + representation + ", dejaDeplace=" + dejaDeplace + ", enVie="+ enVie + ", laCouleur=" + couleurPiece + "}";
	}
	
	public boolean dejaDeplace() {
		return dejaDeplace;
	}
	
	
	public Case laCaseDeLaPiece() {
		return laCaseDeLaPiece;
	}
	

	public void avancerColonne(int direction, int coefficient) { //On utilise la m�thode quand on est s�r qu'on peut se d�placer
	 
	}
	
	public void calculerPortee(Plateau plateau) {

	}
	
	/**
	 * A voir plus tard pour �crire dans un fichier
	 * tout les d�placements effectu�s (une sorte d'historique)
	 * 
	 */
	
	public boolean deplacer(Case depart, Case arrivee, Plateau p) {
		
		//System.out.println("longueur de portee "+getLaPortee().getCases().length); //Ce sur quoi �a bouclait avant
		//System.out.println("nbr de cases dans la portee "+getLaPortee().getNbrCases()); //Ce sur quoi �a boucle maintenant
		
		for(int i = 0 ; i<getLaPortee().getNbrCases();i++) {    //on parcourt le tableau port�e pour voir si la case d'arriv�e est dans la port�e 
			
			//System.out.println("CASE D'ARRIVE "+arrivee);
			//System.out.println("UNE CASE DANS LA PORTEE "+getLaPortee().getCases()[i]);
		
			if(arrivee.equals(getLaPortee().getCases()[i])) {
				
				//System.out.println("IL Y A MATCH ENTRE LA CASE D'ARRIVEE ET UNE DES CASES DANS PORTEE, DEPLACEMENT POSSIBLE");
	   
				if (!(p.estLibre(arrivee.getColonne(), arrivee.getLigne()))  &&  (depart.getColonne()!=arrivee.getColonne())) { //on rajoute 
					//qu'il faut que la colonne de la destination soit diff de la colonne arriv�e (donc que ce soit forc�ment en diagonal)
					
					//System.out.println(this+" a mang� "+arrivee.getPiece()+"!");
					this.manger(arrivee.getPiece(), p);
					setDejaDeplace(true);
					
				} else { //Cas o� la pi�ce se d�place simplement sans rien manger(avance tout droit)
					//System.out.println(this+" s'est d�plac� dans la case "+arrivee.getColonne()+" "+arrivee.getLigne());
					setDejaDeplace(true);
				}
				
				//-------------on change la pi�ce de place----------------------------------------

				
				depart.enleverPiece();
				this.setLaCaseDeLaPiece(arrivee); //on met la case dans les attributs de la piece
				arrivee.setPiece(this); //on met la piece dans la case
				

				return true; //Oui on a trouv�
			}  
		}
		
		return false;
		
	}
	
		//� overrider  (pion if il y a une piece en diagonale //FAIT
	public void manger(Piece piece, Plateau p) {
		p.setNbrPieceRestantes(p.getNbrPieceRestantes()-1);
		piece.setEnVie(false);
		p.setCimetiere(piece.getCouleurPiece(), piece);
	}


}