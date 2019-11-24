/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;
/**
 * @author Lina & Théophile
 */
import java.util.*;
import java.util.List;

/**
 * Il s'agit de la classe qui permet d'organiser un tournoi de Belotte.
 * Elle comprend les méthodes  d'inscription, de lancer du tournoi, de jeu des matchs, 
 d'actualisation et d'affichage des scores, une methode qui donne l'équipe 
 winner (et son accessor) et une methode d'affichage des equipes inscrites.
 * 
 */

class TournoiBelote {
    int coutInscription; 
    int totalInscription;
    boolean enCours;	
    List <Equipe> equipes;
    int [] [] scores;
    int [] total;
    int [] classement;
    Patronne patronne;
    
    
    /**
     * Constructeur de la classe TournoiBelote.
     * Il permet de déclarer et d'initialiser les données membres de la classe.
     * @param coût :  stock le coût de l'inscription 
     */
    public TournoiBelote (int coût) {
	coutInscription = coût;
	enCours = false;
	equipes = new LinkedList <> ();
    }
	
    /** 
     * Cette methode permet d'inscrire une equipe au tournoi.
     * Ensuite on fait payer les deux inscrits, cet argent s'ajoute à la cagnotte.
     * Enfin, on ajoute l'équipe céer aux equipes participantes.
     *@param equipe va permettre de stocker l'équipe composée de deux joueurs 
     */
    public boolean inscrire (Equipe equipe) {
	if (! enCours) {
            totalInscription += 2 * coutInscription; //
            equipes.add(equipe); 
            return true; 
	}
        return false;
	}
	
     /**
     * Cette méthode permet d'afficher les équipes inscrites au tournoi. 
     * Elle affiche les élements de la liste equipes.
     * 
    */	
    public void equipesInscrites () {
        for (int i = 0; i < equipes.size (); i ++)
            System.out.println (equipes.get(i));
    }
     
    /**
     * Cette méthode permet de commencer  le tournoi de Belotte. 
     * Pour ce faire,elle va lancer les matchs avec la methode "jouerlesMatches"
     * Elle traite également la fin du tournoi, avec la paie des gagnants et
     * de la patronne, ainsi que l'annonce des gagnants. 
     */
    public void tournoiDebut () {
        enCours = true;
        scores = new int [equipes.size ()] [equipes.size ()];
        total = new int [equipes.size ()];
        classement = new int [equipes.size ()];
        jouerLesMatches ();
        Equipe winner = quiGagne ();
        winner.joueur1.ajouterArgent (totalInscription / 4);
        winner.joueur2.ajouterArgent (totalInscription / 4);	
        //patronne.recuperCaisse(totalInscription);
        System.out.println ("Et c'est la fin du tournoi les loulous !");
        System.out.println ("L'Equipe " + winner + " est la gagnante.");
        System.out.println ("Félicitations aux joueurs " + winner.joueur1.getPrenom() + " et " + winner.joueur2.getPrenom()+ "\n");
    }
        
    /**
     * Cette méthode permet de lancer les matchs de belote de manière 
     * successive. 
     * Se fait à l'aide de JeuBelote, et le résultat est actualisé 
     * à chaque fin de match.La méthode gère également l'affichage de l'équipe
     * qui remporte le match, ainsi que l'augmentation de la cote de popularité
     * des vainqueurs. Enfin, elle actualise et affiche les scores.
     */
    private void jouerLesMatches () {
	for (int i = 0; i < equipes.size (); i ++) {
            for (int j = 0; j < equipes.size (); j ++) {
		if (i != j && i <= j) {
                    JeuBelote partie = new JeuBelote (equipes.get (i), equipes.get (j));
                    int [] points = partie.jouer ();		
                        if (points [0] > points [1]) {
			// L'equipe i gagne
                            System.out.println (" ****** *   * Equipe " + equipes.get (i) + " bat equipe " + equipes.get (j)+ "*   * ****** ");			
                            scores [i] [j] = 3; 
                	    scores [j] [i] = points [1]; 		
                            equipes.get (i).joueur1.monterCotePopularite(5);
                            equipes.get (i).joueur2.monterCotePopularite(5);
                            equipes.get (i).joueur1.monterNiveauBelote(1);
                            equipes.get (i).joueur2.monterNiveauBelote(1);
                        }
                        else {
			// L'equipe j gagne			
                            System.out.println (" ****** *   * Equipe " + equipes.get (j) + " bat equipe " + equipes.get (i)+ "*   * ****** ");				
                            scores [j] [i] = 3; 
                            scores [i] [j] = points [0];		
                            equipes.get (j).joueur1.monterCotePopularite (5);
                            equipes.get (j).joueur2.monterCotePopularite (5);
                            equipes.get (j).joueur1.monterNiveauBelote(1);
                            equipes.get (j).joueur2.monterNiveauBelote(1);
			}		
		actuScores ();
                printScores ();
		}
            }
	}
    }
	
    /**
     * Cette méthode permet d'actualiser les scores.
     * Les scores sont stockés dans un tableau, qui sera alors trié. 
     * Enfin, on obtient le classement final. 
     */
    private void actuScores () {
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
     * Cette méthode permet d'afficher un tableau avec les scores.
     * Conforme à l'énoncé. 
     */
    private void printScores () {
        System.out.print ("Equipes" + "   ");
        for (int i = 0; i < equipes.size (); i ++)
            System.out.print ( equipes.get (i).toString () + "   ");
        System.out.print ("Total" + " \t");
        System.out.print ("Classement");
        System.out.println ();	
        for (int i = 0; i < equipes.size (); i ++) {
            System.out.print (equipes.get (i).toString () + "\t");
                for (int j = 0; j < equipes.size (); j ++) {
                    if (i == j)
                     System.out.print ("*" + "  \t");
                    else 
                     System.out.print (scores [i] [j] + "" + "  \t");
                }
            System.out.print ("    \t"+total [i] + "" + "\t");
            System.out.print (classement [i] + "" + "\t"); 
            System.out.println ();
	}
	System.out.println ("\n");
    }
      
    /**
     *Cette méthode permet de trouver l'équipe winner du tournoi. 
     *@return : Renvoie l'équipe en tête du classement
     */
    private Equipe quiGagne () {
        for (int i = 0; i < equipes.size (); i ++)
            if (classement [i] == 1)
		return equipes.get(i);		
	return null; 
    }

    /**
     * Cet accessor permet d'obtenir le classement du tournoi. 
     * @param tab : copie du tableau des scores trié par ordre croissant
     * @param value : ledit score dans le tableau des scores
     * @return renvoie la position dans le classement
     */
    private int getClassement (int [] tab, int value) {
        for (int i = tab.length - 1; i >= 0; i --)
            if (tab [i] == value)
		return tab.length - i;		
                return -1;
    }
    
	
    
}