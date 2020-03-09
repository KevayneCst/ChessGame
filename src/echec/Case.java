package echec;

public class Case {
	private int colonne; // De 1 a 8 colonnes
	private int ligne; // De 1 a 8 lignes
	private Piece piece; // La piece sur cette case (si il y a)
	private Couleur couleurCase; // a voir si on fait comme ça parce que couleur case != couleur pion ext

	public Case(int col, int lig, String c) {
		this.colonne = col;
		this.ligne = lig;
		this.piece = null;
		this.couleurCase = new Couleur(c);
	}

	// -------------------Retourne une case clone de la case courante (avec la Piece
	// dessus)-----------------------------
	@Override
	public Case clone() {
		Case laCase = new Case(this.colonne, this.ligne, this.couleurCase.getCouleur());
		Piece laPiece = null;

		if (this.piece == null) { // Ici on ne peux pas utiliser la méthode clone pour la pièce, car cette méthode
									// appelle celle-ci (et créer une boucle infinie comme chacune appelle l'autre
									// méthode)

			laCase.piece = null;
			return laCase;

		} else if (this.piece instanceof Pion) { // Si la piece est un Pion

			laPiece = new Pion(this.piece.getCouleurPiece().getCouleur());

		} else if (this.piece instanceof Tour) { // Si la piece est une Tour

			laPiece = new Tour(this.piece.getCouleurPiece().getCouleur());

		} else if (this.piece instanceof Cavalier) { // Si la piece est un Cavalier

			laPiece = new Cavalier(this.piece.getCouleurPiece().getCouleur());

		} else if (this.piece instanceof Fou) { // Si la piece est un Fou

			laPiece = new Fou(this.piece.getCouleurPiece().getCouleur());

		} else if (this.piece instanceof Reine) { // Si la piece est une Reine

			laPiece = new Reine(this.piece.getCouleurPiece().getCouleur());

		} else { // Si c'est un Roi du coup

			laPiece = new Roi(this.piece.getCouleurPiece().getCouleur());

		}
		laPiece.setDejaDeplace(this.piece.isDejaDeplace());
		laPiece.setEnVie(this.piece.isEnVie());
		laPiece.setLaCaseDeLaPiece(this.piece.getLaCaseDeLaPiece());
		laPiece.setLaPortee(this.piece.getLaPortee());
		laPiece.setAiJeAvanceeDeDeux(this.piece.getAiJeAvanceeDeDeux());
		laCase.piece = laPiece;
		return laCase;
	}

	public int getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public Piece getPiece() {
		return piece;
	}

	public Couleur getCouleurCase() {
		return couleurCase;
	}

	public void setPieceDansCase(Piece piece) {
		this.piece = piece;
	}

	public void enleverPiece() {
		this.piece = null;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public void setCouleurCase(Couleur couleurCase) {
		this.couleurCase = couleurCase;
	}

	@Override
	public String toString() {
		return "Case {colonne=" + colonne + ", ligne=" + ligne + ", piece=" + piece + ", couleurCase=" + couleurCase
				+ "}";
	}

	// On regarde si le numLigne et numColonne sont les m�mes et si la couleur est
	// la m�me.
	public boolean equals(Case c) {
		if ((this.colonne == c.colonne) && (this.ligne == c.ligne) && (this.couleurCase.equals(c.couleurCase))) {
			return true;
		} else {
			return false;
		}
	}

}
