package echec;

public class Portee {
	private final int PORTEEMAXIMALE=27; //Taille maximale uniquement pour de l'optimatisation de m�moire...
	private Case[] cases;	//Cavalier 8maximum //Tour 14maximum //Fou 13maximum //Pion 4maximum //Reine 27maximum //Roi 8maximum 
	private int nbrCases=0;
	
	
	public Case[] getCases() {
		return cases;
	}
	
	
	@Override
	public Portee clone() {
		Portee laPortee = new Portee();
		laPortee.cases=this.cases.clone();
		laPortee.nbrCases=this.nbrCases;
		return laPortee;
	}
	
	public int getNbrCases() {
		return nbrCases;
	}

	
	public Portee() {
		cases=new Case[PORTEEMAXIMALE];
	}
	
	//ajoute la case en param�tre dans le tableau de case de la port�e
	public void ajouterPortee(Case c) {
		if (nbrCases<PORTEEMAXIMALE) {
			cases[nbrCases]=c;
			nbrCases++;
		}
	}
	

	
	@Override
	public String toString() {
		String chaine="Portee {";
		for (int i=0; i<nbrCases;i++){
			chaine+=this.cases[i].toString();
		}
		return chaine+"}";
	}
	
}
