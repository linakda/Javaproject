/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 *
 * @author Théo
 */
import java.util.*;
import java.lang.*;


/** Represente une partie de belote
 * 
 */
class PartieDeBelote {
    Equipe equipe1;
    Equipe equipe2;
    int pointsEquipe1;
    int pointsEquipe2;
    List <Carte> cartesJoueur1;	// Equipe 1
    List <Carte> cartesJoueur2;	// Equipe 2
    List <Carte> cartesJoueur3;	// Equipe 1
    List <Carte> cartesJoueur4;	// Equipe 2
    String atout;
    String [] couleurs;
    String [] valeurs;
    Carte [] cartes;
    
    /** Constructeur PartiedeBelote, , méthode exécutée à la création de l'objet et permettant l'initialisation de l'objet .
     * 
     * @param E1 pour stocker la 1ere equipe du tournoi
     * @param E2 pour stocker la 2eme equipe du tournoi
     */
    public PartieDeBelote (Equipe E1, Equipe E2) {
	equipe1 = E1;
	equipe2 = E2;   
	pointsEquipe1 = 0;
	pointsEquipe2 = 0;
	couleurs = new String [] {"Pique", "Carreau", "Trèfle", "Coeur"};
	valeurs = new String [] {"7", "8", "9", "Valet", "Dame", "Roi", "10", "As"};
		
	// Pour simplifier, la couleur d'atout est choisie aleatoirement
	int randomIndex = (int) (Math.random () * 4);	
	atout = couleurs [randomIndex];
	creerCartes ();
    }
	
    /**
     * méthode private creer Cartes qui va permettre d'instancier nos différentes cartes 
     * Cree les 32 cartes du jeu stocker dans un tableau 
     * 
     */
    private void creerCartes () {
	cartes = new Carte [32];
        for (int i = 0; i < couleurs.length; i ++) {
            for (int j = 0; j < valeurs.length; j ++) {
		cartes [i * 8 + j] = new Carte (couleurs [i], valeurs [j]);
            }
	}
    }
	
    /**méthode distribuerLesCartes 
     * Distribue aleatoirement 8 cartes a chaque joueur
     * les mains de chaque joueur sont stockés dans des listes de cartes propre à chacun
     */
    private void distribuerLesCartes () {
    	List <Carte> cartesADistribuer = new LinkedList <Carte> ();
	cartesJoueur1 = new LinkedList <Carte> ();
	cartesJoueur2 = new LinkedList <Carte> ();
	cartesJoueur3 = new LinkedList <Carte> ();
	cartesJoueur4 = new LinkedList <Carte> ();
	
	for (int i = 0; i < 32; i ++)
            cartesADistribuer.add (cartes [i]);
            for (int i = 0; i < 32; i ++) {
		int randomIndex = (int) (Math.random () * cartesADistribuer.size ());		
		int j = 1 + (i % 4);
                if (j == 1)
                    cartesJoueur1.add (cartes [randomIndex]);
		else if (j == 2)
                    cartesJoueur2.add (cartes [randomIndex]);
		else if (j == 3)
                    cartesJoueur3.add (cartes [randomIndex]);
		else if (j == 4)
                    cartesJoueur4.add (cartes [randomIndex]);
        }
    }
	
    /**méthode permettant Jouer une manche
     * Chaque joueur dispose de 8 cartes
     * A chaque tour, chacun va devoiler sa premiere carte 
     * Le joueur qui devoile la carte la plus forte marque un point pour son equipe
     * Une fois les 8 cartes jouees,
     * L'equipe qui a marque le plus de points gagne la manche
     * S'il y a egalite la manche est nulle
     * Les joueurs de l'equipe perdant la manche offrent a boire aux gagnants
     * 
     */
    private void jouerUneManche () {
	distribuerLesCartes ();
        int scoreEquipe1 = 0;
	int scoreEquipe2 = 0;
        for (int i = 0; i < 8; i ++) {
            List <Integer> forces = new LinkedList <Integer> ();
            forces.add (cartesJoueur1.remove (0).getValue (atout));
            forces.add (cartesJoueur2.remove (0).getValue (atout));
            forces.add (cartesJoueur3.remove (0).getValue (atout));
            forces.add (cartesJoueur4.remove (0).getValue (atout));
            int forceMax = -1;
            int joueurGagnant = -1;	
            for (int j = 0; j < 4; j ++) {
		int f = forces.remove (0);
		if (f > forceMax) {
                    forceMax = f;
                    joueurGagnant = j;
		}
            }
            joueurGagnant ++;	
            if (joueurGagnant == 1 || joueurGagnant == 3)
		// L'equipe 1 remporte la manche
		scoreEquipe1 ++;
            else 
                // L'equipe 2 remporte la manche
		scoreEquipe2 ++;
            }
            if (scoreEquipe1 > scoreEquipe2) {
		pointsEquipe1 ++;
                equipe2.joueur1.offrir (equipe1.joueur1);
		equipe2.joueur2.offrir (equipe1.joueur2);
            }
            else if (scoreEquipe1 < scoreEquipe2) {
		pointsEquipe2 ++;
		equipe1.joueur1.offrir (equipe2.joueur1);
		equipe1.joueur2.offrir (equipe2.joueur2);
            }
    }
	
    /**méthode permettant de Jouer le match
     *  Tant qu'une equipe n'a pas gagne deux manches, on continue
     * @return le score qu'a marqué chacune des équipes sous forme d'un tableau
     */
    public int [] jouer () {	
	while (pointsEquipe1 < 2 && pointsEquipe2 < 2) {
            jouerUneManche ();
	}	
	return new int [] {pointsEquipe1, pointsEquipe2};
    }
}