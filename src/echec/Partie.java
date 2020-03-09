package echec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Partie {
	
	
	public void lancerPartie() {
		boolean rejouer = true; //Variable pour jouer des parties à l'infini si cet dernière est toujours à true
		boolean onJoue = true; //Variable pour continuer de jouer (et donc alterner les tours en les joueurs) tant qu'elle est à true
		Couleur leBlanc = new Couleur("blanc"); //Joueur 1 sera blanc et jouera quand tour=true;
		Couleur leNoir = new Couleur("noir"); //Joueur 2 sera noir et jouera quand tour=false;
		
		while (rejouer==true) { //On boucle une première fois pour la première partie, et ensuite on boucle à l'infinie tant que wannaReplayAgain() retourne true
			
			onJoue=true;
			Plateau echiquier = new Plateau();
			echiquier.actualiserPortees();
			
			
			boolean tour=true; //Tour est un boolean correspondant à la couleur du joueur qui joue. true=>BLANC ; false=>NOIR
			int numeroTour=1; //int que l'on affiche qui va simplement montrer le n° de tour auquel on est

			while (onJoue==true) { //Tant qu'il n'y a pas d'échec et mat OU d'échec et pat on boucle
				
				if (tour==true) { //Partie de code pour le joueur blanc
					
					//Si il n'y a pas échec et mat au joueur blanc et qu'il n'y a pas échec et pat (peu importe la couleur du joueur)
					if ((echiquier.findTheKing(leBlanc).enEchecEtMat(echiquier)==false)&&((echiquier.findTheKing(leBlanc).enEchecEtPat(echiquier)) == false)&&((echiquier.findTheKing(leNoir).enEchecEtPat(echiquier)) == false)) { 
						
						System.out.println("\n             |-Tour n°"+numeroTour+"-|\n");
						numeroTour++;
						echiquier.saisirDeplacement(leBlanc);
						tour=false;
						echiquier.reinitialiserPions(leNoir);
					}
					
					//Sinon, si il y a échec et pat (peu importe la couleur du joueur)
					else if (echiquier.findTheKing(leBlanc).enEchecEtPat(echiquier)||(echiquier.findTheKing(leNoir).enEchecEtPat(echiquier))) {
						echiquier.afficherPlateau();
						System.out.println("\n\nEchec et pat ! Egalité!\n\n");
						onJoue=false;
					}
					
					//Else (donc si il y a Echec et mat sur le roi de la couleur actuel)
					else {
						echiquier.afficherPlateau();
						System.out.println("\n\nEchec et mat ! Victoire du joueur noir\n\n");
						onJoue=false;
					}
					
				} else { //Partie de code pour le joueur noir
					
					//Si il n'y a pas échec et mat au joueur blanc et qu'il n'y a pas échec et pat (peu importe la couleur du joueur)
					if ((echiquier.findTheKing(leNoir).enEchecEtMat(echiquier)==false)&&((echiquier.findTheKing(leNoir).enEchecEtPat(echiquier)) == false &&((echiquier.findTheKing(leBlanc).enEchecEtPat(echiquier)) == false))) { //Si il n'y a pas échec et mat au joueur noir
						System.out.println("\n             |-Tour n°"+numeroTour+"-|\n");
						numeroTour++;
						echiquier.saisirDeplacement(leNoir);
						tour=true;
						echiquier.reinitialiserPions(leBlanc);
					} 
					
					//Sinon, si il y a échec et pat (peu importe la couleur du joueur)
					else if (echiquier.findTheKing(leNoir).enEchecEtPat(echiquier) || (echiquier.findTheKing(leBlanc).enEchecEtPat(echiquier))) {
						echiquier.afficherPlateau();
						System.out.println("\n\nEchec et pat ! Egalité!\n\n");
						onJoue=false;
					}
					
					//Else (donc si il y a Echec et mat sur le roi de la couleur actuel)
					else {
						echiquier.afficherPlateau();
						System.out.println("\n\nEchec et mat ! Victoire du joueur blanc\n\n");
						onJoue=false;
					}
				}	
			}
			
			//On demande après avoir fini une partie, si l'on doit relancer une partie
			if (wannaReplayAgain()==false) { //Si l'utilisateur choisi de ne pas relancer de partie alors
				rejouer=false; //Mettre rejouer à false va permettre d'arrêter de boucler et donc va permettre de stopper les futurs parties
			}
		}
		
	}
	
	public boolean wannaReplayAgain() { 
		/**
		 * On demande si on rejoue une partie de nouveaux 
		 * en demandant de saisir le chiffre 1 pour OUI rejouer
		 * ou AUTRE CHOSE pour NON je ne souhaite pas rejouer
		 * 
		 */
		System.out.println("\nVoulez vous rejouer ? Tapez 1 pour rejouer ou autre chose pour quitter");
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String choix = bufferRead.readLine();
			if (choix.matches("1")) {
				System.out.println("Nouvelle partie !");
				return true; //Oui on va relancer une nouvelle partie
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Merci d'avoir joué, à très vite ! EJiK & Mat ©");
		return false; //Non on arrête de jouer
	}
}
