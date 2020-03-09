package echec;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EchecMainTest {

    @Test
    void testDeplacement() {

        Plateau p1 = new Plateau();
        // création du plateau p1
        
        p1.afficherPlateau();
        p1.actualiserPortees();
        // ========= PION =========
        
        //pion blanc avance de 2 
        assertEquals((p1.getPlateau()[6][1].getPiece().deplacer(p1.getPlateau()[6][1], p1.getPlateau()[6][3], p1)),true);
        // cette commande vérifie que le pion blanc s'est bien déplace
        
        assertEquals(p1.getPlateau()[6][3].getLigne(), 4);
        // cette commande vérifie que le pion a été déplacé à la ligne 4
       
        assertEquals(p1.getPlateau()[6][3].getColonne(), 7);
        // cette commande vérifie que le pion a été déplacé à la colonne 7
        
        // puis de 1
        p1.actualiserPortees();
        assertEquals((p1.getPlateau()[6][3].getPiece().deplacer(p1.getPlateau()[6][3], p1.getPlateau()[6][4], p1)),true);
        
        // autre pion blanc qui avance de 1
        p1.actualiserPortees();
        assertEquals((p1.getPlateau()[4][1].getPiece().deplacer(p1.getPlateau()[4][1], p1.getPlateau()[4][2], p1)),true);
        
        // pion noir avance de 2
        
        p1.getPlateau()[2][6].getPiece().calculerPortee(p1);
        
        p1.getPlateau()[2][6].getPiece().deplacer(p1.getPlateau()[2][6], p1.getPlateau()[2][5], p1);
        
        assertEquals(p1.getPlateau()[2][5].getColonne(), 3);
        p1.actualiserPortees();
        p1.afficherPlateau();
        assertEquals((p1.getPlateau()[6][6].getPiece().deplacer(p1.getPlateau()[6][6], p1.getPlateau()[6][5], p1)),true);
        p1.afficherPlateau();
        
        
        // pion avance de 2
        
        p1.getPlateau()[3][6].getPiece().calculerPortee(p1);
        
        p1.getPlateau()[3][6].getPiece().deplacer(p1.getPlateau()[3][6], p1.getPlateau()[3][4], p1);
        
        assertEquals(p1.getPlateau()[3][4].getColonne(), 4);
        
        // et d'une case encore
        
        p1.getPlateau()[3][4].getPiece().calculerPortee(p1);
        
        p1.getPlateau()[3][4].getPiece().deplacer(p1.getPlateau()[3][4], p1.getPlateau()[3][3], p1);
        
        assertEquals(p1.getPlateau()[3][3].getLigne(), 4);
        
        // ============= TOUR ================ 
        
        //la tour blanche avance de 3 
        p1.getPlateau()[7][0].getPiece().calculerPortee(p1);
        p1.getPlateau()[7][0].getPiece().deplacer(p1.getPlateau()[7][0], p1.getPlateau()[7][3], p1);
        assertEquals(p1.getPlateau()[7][3].getLigne(), 4);
        assertNotSame(p1.getPlateau()[7][3].getLigne(), 3);

        
        // ============== FOU ===================
        p1.actualiserPortees();
        // le fou blanc avance à droite
        p1.afficherPlateau();
        assertEquals(p1.getPlateau()[2][0].getPiece().deplacer(p1.getPlateau()[2][0], p1.getPlateau()[3][1], p1), false);
        // cette commande vérifie que le fou blanc s'est bien déplace
        assertEquals(p1.getPlateau()[2][0].getLigne(), 1);
        // cette commande vérifie que le fou a été déplacé à la ligne 1
        assertEquals(p1.getPlateau()[2][0].getColonne(), 3);
        // cette commande vérifie que le fou a été déplacé à la colonne 3
        p1.actualiserPortees();
        // on actualise la portée
        
        // le fou blanc avance ensuite à gauche
        assertEquals(p1.getPlateau()[3][1].getPiece().deplacer(p1.getPlateau()[3][1], p1.getPlateau()[3][2], p1), true);
        // cette commande vérifie que le fou blanc s'est bien déplace
        assertEquals(p1.getPlateau()[0][4].getLigne(), 5);
        // cette commande vérifie que le fou a été déplacé à la ligne 5
        assertEquals(p1.getPlateau()[0][4].getColonne(), 1);
        // cette commande vérifie que le fou a été déplacé à la colonne 3
        p1.afficherPlateau();
        //le fou noir avance
        assertEquals(p1.getPlateau()[2][7].getPiece().deplacer(p1.getPlateau()[2][7], p1.getPlateau()[7][2], p1),true);
        // cette commande vérifie que le fou noir s'est pas déplacé
        assertEquals(p1.getPlateau()[7][2].getLigne(), 3);
        // cette commande vérifie que le fou a été déplacé à la ligne 3
        assertEquals(p1.getPlateau()[7][2].getColonne(), 8);
        // cette commande vérifie que le fou a été déplacé à la colonne 8
        
        p1.actualiserPortees();
        
        // le fou noir recule ensuite de 2 cases
        p1.afficherPlateau();
        assertEquals(p1.getPlateau()[5][7].getPiece().deplacer(p1.getPlateau()[5][7], p1.getPlateau()[7][5], p1),true);
        // cette commande vérifie que le fou noir s'est bien déplace
        assertEquals(p1.getPlateau()[7][5].getLigne(), 6);
        // cette commande vérifie que le fou a été déplacé à la ligne 3
        assertEquals(p1.getPlateau()[7][5].getColonne(), 8);
        // cette commande vérifie que le fou a été déplacé à la colonne 8
        
        
        System.out.println(p1.findTheKing(new Couleur(Couleur.BLANC)).toString());
        System.out.println(p1.findTheKing(new Couleur(Couleur.NOIR)).toString());
        p1.afficherCimetiere(new Couleur(Couleur.BLANC));
        p1.afficherCimetiere(new Couleur(Couleur.NOIR));
        System.out.println(p1.restePiecesBlanches());
        System.out.println(p1.restePiecesNoires());
        System.out.println(p1.choisirPromotion());
    }
    
    
    @Test
    void testCouleur() {
        Couleur leBlanc = new Couleur(Couleur.BLANC);
        Couleur leNoir = new Couleur(Couleur.NOIR);
        Couleur laMauvaise = new Couleur("jaune");
        Couleur aVerifier1 = new Couleur(leBlanc.invertionCouleur().getCouleur());
        Couleur aVerifier2 = new Couleur(leNoir.invertionCouleur().getCouleur());
        Couleur aSet = new Couleur("test");
        aSet.setCouleur(Couleur.BLANC);
        System.out.println("laMauvaise: "+laMauvaise+", aVerifier1: "+aVerifier1+", aVerifier2: "+aVerifier2);
        assertEquals(aVerifier1.getCouleur(),leNoir.getCouleur());
    }
    
    @Test
    void testCase() {
        Case laCase = new Case (1,1,Couleur.NOIR);
        Case laCaseClone = laCase.clone();
        assertEquals(laCase.getColonne(), laCaseClone.getColonne());
    }
    @Test
    void testPortee() {
        
        //-----------Pour tester la Portee du cavalier-----------
        Plateau lePlateau = new Plateau();
        Portee unePortee = new Portee();
        
        lePlateau.getCaseAtCoordo(7, 1).getPiece().calculerPortee(lePlateau);
        unePortee=lePlateau.getCaseAtCoordo(7, 1).getPiece().getLaPortee().clone(); //Le cavalier en g1
        
        System.out.println("La portee du cavalier en g1:"+ unePortee.toString());
        lePlateau.getCaseAtCoordo(7, 1).getPiece().deplacer(lePlateau.getCaseAtCoordo(7, 1), lePlateau.getCaseAtCoordo(6, 3), lePlateau);
        assertNotNull(lePlateau.getCaseAtCoordo(6, 3).getPiece());
        
        lePlateau.getCaseAtCoordo(6, 3).getPiece().calculerPortee(lePlateau);
        lePlateau.getCaseAtCoordo(6, 3).getPiece().deplacer(lePlateau.getCaseAtCoordo(6, 3), lePlateau.getCaseAtCoordo(4, 4), lePlateau);
        
        assertNotNull(lePlateau.getCaseAtCoordo(4, 4).getPiece());
        lePlateau.getCaseAtCoordo(4, 4).getPiece().calculerPortee(lePlateau);
        
        //-----------Pour tester la Portee du roi-----------
        Plateau lePl = new Plateau();
        lePl.actualiserPortees();
        lePl.getCaseAtCoordo(4, 2).getPiece().deplacer(lePl.getCaseAtCoordo(4, 2), lePl.getCaseAtCoordo(4, 3), lePl); //On déplace le pion situé en d2 en d3 
        lePl.actualiserPortees();
        lePl.getCaseAtCoordo(5, 1).getPiece().deplacer(lePl.getCaseAtCoordo(5, 1), lePl.getCaseAtCoordo(4, 2), lePl); //On déplace le Roi situé en e1 en d2
        lePl.actualiserPortees();
        lePl.getCaseAtCoordo(4, 2).getPiece().deplacer(lePl.getCaseAtCoordo(4, 2), lePl.getCaseAtCoordo(5, 3), lePl); //On déplace le Roi situé en d2 en e3
        lePl.actualiserPortees();
        lePl.getCaseAtCoordo(5, 3).getPiece().deplacer(lePl.getCaseAtCoordo(5, 3), lePl.getCaseAtCoordo(5, 4), lePl); //On déplace le Roi situé en e3 en e4
        lePl.actualiserPortees();
        lePl.getCaseAtCoordo(5, 4).getPiece().deplacer(lePl.getCaseAtCoordo(5, 4), lePl.getCaseAtCoordo(5, 5), lePl); //On déplace le Roi situé en e4 en e5
        lePl.actualiserPortees();
        assertEquals(lePl.findTheKing(new Couleur(Couleur.BLANC)).enEchec(lePl),false); //On va chercher le Roi blanc (celui qu'on a déplacé juste avant) et on regarde si il est en échec sur sa case actuelle.
    }
    
    @Test
    void testPartie() {
        Plateau echiquier = new Plateau();
        echiquier.actualiserPortees();
        echiquier.saisirDeplacement(new Couleur(Couleur.BLANC));
        echiquier.saisirDeplacement(new Couleur(Couleur.NOIR));
    }
  
}