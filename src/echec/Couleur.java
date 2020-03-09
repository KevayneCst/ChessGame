package echec;

public class Couleur {
	private String couleur;
	
	public Couleur(String s) {
		if (CouleurValide(s)) {
			this.couleur=s;
		}
	}
	
	
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	public String getCouleur() {
		return couleur;
	}

	//-------La couleur est bien "noir" ou "blanc"-------
	public static boolean CouleurValide(String s) {			//On veut s'assurer que la couleur n'est pas autre chose que du blanc ou du noir
		if ((s.equals("blanc")) || (s.equals("noir"))) {  //Si une couleur est "blanc" OU "noir", alors on retourne true, sinon false.
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Couleur {couleur=" + couleur + "}";
	}
	
	
	//On utilise le equals sur les Strings 
	public boolean equals(Couleur c) {
		if (this.couleur.equals(c.getCouleur())) {
			return true;
		} else {
			return false;
		}
	}
	
	//-inverse la couleur de "noir" à "blanc" ou de "blanc" à "noir"-
	public Couleur invertionCouleur() {
		if (this.equals(new Couleur("blanc"))) {
			return new Couleur("noir");
		} else {
			return new Couleur("blanc");
		}
	}
	
}
