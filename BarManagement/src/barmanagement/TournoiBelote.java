/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.*;
import java.util.List;

/**
 *Classe TournoiBelote qui va permettre d'organiser un tournoi de Belote
 *Elle possédera en paramètre un cout d'insciription, un total d'inscription 
 * les equipes seront défini à l'aide de liste d'equipe et le total et classement seront stockés dans des tableaux
 * @author Théo
 */

class TournoiBelote {
    int coutInscription; // Cout par personne (x2 pour l'equipe)
    int totalInscription;
    boolean enCours;
	
    List <Equipe> equipes;
    int [] [] scores;
    int [] total;
    int [] classement;
	
    /**
     * Constructeur TournoiBelote, méthode exécutée à la création de l'objet et permettant l'initialisation de l'objet .
     * @param c va permettre de stocker le coût de l'inscription 
     */
    public TournoiBelote (int c) {
	coutInscription = c;
	enCours = false;
	equipes = new LinkedList <Equipe> ();
    }

   
	
    /** méthode inscrire, 
     * qui va donc permettre l'inscription d'une equipe au tournoi
     * Verifier que les 2 inscrits ont suffisament d'argent
     * Faire payer les deux inscrits séparement
     * Ajout de l'argent Ã  la cagnote
     * Uniquement si les deux personnes ont assez d'argent. Sinon, renvoyer false
     * Bloquer les inscriptions et creer les équipes
     *@param E va permettre de stocker l'équipe composé de deux joueurs 
     */  
    public boolean inscrire (Equipe E) {
	if (! enCours) {
            totalInscription += 2 * coutInscription;
            equipes.add (E);
            return true; 
	}
        return false;
	}
	
        /**
         * méthode lancerTournoi
         * va permettre à l'appel de cette derniere d'initialiser le tournoi 
         * de faire appel aux différentes methodes permettant le commencement de celle-ci et d'annoné les vainqueurs
         */
	
    public void lancerTournoi () {
        enCours = true;
        scores = new int [equipes.size ()] [equipes.size ()];
        total = new int [equipes.size ()];
        classement = new int [equipes.size ()];
        jouerLesMatches ();
        Equipe gagnante = trouverEquipeGagnante ();
        // 50% du total des inscriptions va aux vainqueurs
        gagnante.joueur1.ajouterArgent (totalInscription / 4);
        gagnante.joueur2.ajouterArgent (totalInscription / 4);	
        // Les autres 50% vont Ã  la patronne
        // patronne.ajouterArgent (totalInscription / 2); <=== COMME CA ?
        System.out.println ("Fin du tournoi !");
        System.out.println ("L'Equipe " + gagnante + " est victorieuse.");
        System.out.println ("Félicitations aux joueurs " + gagnante.joueur1.getNom() + " et " + gagnante.joueur2.getNom());
    }
        
    /**
     * méthodes jouerLesMatches
     *  Joue toutes les parties successivement en modifiant 
     * les données à l'issu de chacune de ces dernières
     */
    private void jouerLesMatches () {
	for (int i = 0; i < equipes.size (); i ++) {
            for (int j = 0; j < equipes.size (); j ++) {
		if (i != j && i <= j) {
                    PartieDeBelote P = new PartieDeBelote (equipes.get (i), equipes.get (j));
                    int [] points = P.jouer ();		
                        if (points [0] > points [1]) {
			// L'equipe i gagne
                            System.out.println ("	==> Equipe " + equipes.get (i) + " bat equipe " + equipes.get (j));			
                            scores [i] [j] = 3; // 3 points pour i
                	    scores [j] [i] = points [1]; // Aucun point pour j si 2 - 0, 1 point si 2 - 1		
                            equipes.get (i).joueur1.monterCotePopularite ();
                            equipes.get (i).joueur2.monterCotePopularite ();
                        }
                        else {
			// L'equipe j gagne			
                            System.out.println ("	==> Equipe " + equipes.get (j) + " bat equipe " + equipes.get (i));			
                            scores [j] [i] = 3; // 3 points pour j
                            scores [i] [j] = points [0]; // Aucun point pour i si 2 - 0, 1 point si 2 - 1		
                            equipes.get (j).joueur1.monterCotePopularite ();
                            equipes.get (j).joueur2.monterCotePopularite ();
			}		
		actualiserScores ();
                afficherScores ();
		}
            }
	}
    }
	
    /**
     * méthode actualiserScores
     * Actualise le tableau des scores (clacule total & classement)
     * synchronise et met dans l'ordre croissant ce dernier
     * 
     */
    private void actualiserScores () {
        for (int i = 0; i < equipes.size (); i ++) {
            int s = 0;		
            for (int j = 0; j < equipes.size (); j ++) {
		s += scores [i] [j];
            }		
	total [i] = s;
	}	
        int [] tmp = total.clone ();
        Arrays.sort (tmp);	
        for (int i = 0; i < equipes.size (); i ++)
            classement [i] = getClassement (tmp, total [i]);
    }
    
    /**
     *méthode afficherScores 
     * permet l'affichage du tableau des scores
     * 
     */
    private void afficherScores () {
	p ("Equipes");	
        for (int i = 0; i < equipes.size (); i ++)
            p (equipes.get (i).toString ());
            p ("Total");
            p ("Classement");
            System.out.println ();	
        for (int i = 0; i < equipes.size (); i ++) {
            p (equipes.get (i).toString ());
                for (int j = 0; j < equipes.size (); j ++) {
                    if (i == j)
                        p ("#");
                    else
                        p (scores [i] [j] + "");
                }
            p (total [i] + "");
            p (classement [i] + "");
            System.out.println ();
	}
	System.out.println ("\n");
    }
    
    /**
     * méthode getClassement
     * Renvoie la position dans le classement de léquipe ayant son total egal a value
     * @param tab
     * @param value
     * @return 
     */
	 
    private int getClassement (int [] tab, int value) {
        for (int i = tab.length - 1; i >= 0; i --)
            if (tab [i] == value)
		return tab.length - i;		
		return -1;
    }
    
    /**
     *méthode private trouverEquipeGagnante 
     *Renvoie la premiere equipe du classement
     */
    private Equipe trouverEquipeGagnante () {
        for (int i = 0; i < equipes.size (); i ++)
            if (classement [i] == 1)
		return equipes.get (i);		
	return null; // Ne devrait jamais arriver
    }
	
    /**
     * méthode equipesInscrites
     * Affiche les equipes inscrites
     * 
    */	
    public void equipesInscrites () {
        for (int i = 0; i < equipes.size (); i ++)
            System.out.println (equipes.get (i));
    }
	
    /**
     * méthode private p 
     * Un petit raccourci pour afficher
     * 
     */
    private void p (String text) {
	System.out.print (text + "\t");
    }
}