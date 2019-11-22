/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.*;
import java.util.List;

/**
 * Il s'agit de la classe qui permet d'organiser un tournoi de Belotte.
 * Elle comprend les méthodes  d'inscription, de lancer du tournoi, de jeu des matchs, 
 * d'actualisation et d'affichage des scores, une methode qui donne l'équipe 
 * gagnante (et son accessor) et une methode d'affichage des equipes inscrites.
 * @author Lina & Théophile
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
     * Verifier que les 2 inscrits ont suffisament d'argent
     * Faire payer les deux inscrits séparement
     * Ajout de l'argent Ã  la cagnote
     * Uniquement si les deux personnes ont assez d'argent. Sinon, renvoyer false
     * Bloquer les inscriptions et creer les équipes
     *@param equipe va permettre de stocker l'équipe composé de deux joueurs 
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
     * Cette methode permet d'afficher le menu du jeu de belotte.
     * Il permet les actions suivantes : créer les équipes, voir les joueurs 
     * disponibles. 
     */
    public void menuBelotte(){
        int  choix = 0;
        Scanner sc = new Scanner(System.in);

        while(true){
        System.out.println("Que veux-tu faire ?");
        System.out.println("1- Créer les équipes");
        System.out.println("2- Voir les joueurs disponibles");
        
         try{
            choix = Integer.parseInt(sc.nextLine()); //On choisit le num de l'action
        }
        catch(NumberFormatException e){
            System.out.println("Entrez un entier s'il vous plait ");
            continue;
        }
            if(choix < 0 || choix > 9 ){
                System.out.println("Ce choix n'existe pas");
                continue;
            }
            
        switch(choix){

            case 1:
                continue;
 
            case 2 : 
               System.out.print("brain"); //afficherJoueurDispo avec leur niveau de jeu
                break;
    }
    }
 }
    
    /**
     * Cette methode permet d'afficher la table avec les joueurs actuellement en jeu.
     * Les joueurs sont assis en face à face de sorte que les 2 voisins d'un
     * joueurs soient les membres de l'équipe adverse.
     */
    public void voirLaTable(){
  
            System.out.println(equipes.get(0).joueur1.getPrenom() + "\t \t"+ equipes.get(1).joueur1.getPrenom());
            System.out.println("-------------------");
            System.out.println("\n -------------------");
            System.out.println(equipes.get(1).joueur2.getPrenom() + "\t \t"+ equipes.get(0).joueur2.getPrenom());
    
   }
     
    /**
     * Cette méthode permet de lancer  le tournoi de Belotte. 
     * Pour ce faire,elle va lancer les matchs avec la methode "jouerlesMatches"
     * Elle traite également la fin du tournoi, avec la paie des gagnants et
     * de la patronne, ainsi que l'annonce des gagnants par le Barman. 
     */
    public void lancerTournoi () {
        enCours = true;
        scores = new int [equipes.size ()] [equipes.size ()];
        total = new int [equipes.size ()];
        classement = new int [equipes.size ()];
        jouerLesMatches ();
        Equipe gagnante = trouverEquipeGagnante ();
        // 50% du total des inscriptions va aux vainqueurs
        //gagnante.joueur1.ajouterArgent (totalInscription / 4);
       // gagnante.joueur2.ajouterArgent (totalInscription / 4);	
        // Les autres 50% vont Ã  la patronne
        // patronne.ajouterArgent (totalInscription / 2); <=== COMME CA ?
        System.out.println ("Fin du tournoi !");
        voirLaTable();
        System.out.println ("L'Equipe " + gagnante + " est victorieuse.");
        System.out.println ("Félicitations aux joueurs " + gagnante.joueur1.getPrenom() + " et " + gagnante.joueur2.getPrenom());
    }
        
    /**
     * Cette méthode permet de lancer les matchs de belotte de manière 
     * successive. 
     * Ce fait à l'aide de PartieDeBelote, et le résultat est actalisé 
     * à chaque fin de match.La méthode gère également l'affichage de l'équipe
     * qui remporte le match, ainsi que l'augmentation de la cote de popularité
     * des vainqueurs. Enfin, elle actualise et affiche les scores.
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
                            equipes.get (i).joueur1.monterCotePopularite(5);
                            equipes.get (i).joueur2.monterCotePopularite(5);
                        }
                        else {
			// L'equipe j gagne			
                            System.out.println ("	==> Equipe " + equipes.get (j) + " bat equipe " + equipes.get (i));			
                            scores [j] [i] = 3; // 3 points pour j
                            scores [i] [j] = points [0]; // Aucun point pour i si 2 - 0, 1 point si 2 - 1		
                            equipes.get (j).joueur1.monterCotePopularite (5);
                            equipes.get (j).joueur2.monterCotePopularite (5);
			}		
		actualiserScores ();
                afficherScores ();
		}
            }
	}
    }
	
    /**
     * Cette méthode permet d'actualiser les scores.
     * Les scores sont stockés dans un tableau, qui sera alors trié. 
     * Enfin, on obtient le classement final. 
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
     * Cette méthode permet d'afficher un tableau avec les scores.
     * Conforme à l'énoncé. 
     */
    private void afficherScores () {
        System.out.print ("Equipes" + "\t");
        for (int i = 0; i < equipes.size (); i ++)
            System.out.print ( equipes.get (i).toString () + "\t");
        System.out.print ("Total" + "\t");
        System.out.print ("Classement" + "\t");
        System.out.println ();	
        for (int i = 0; i < equipes.size (); i ++) {
            System.out.print (equipes.get (i).toString () + "\t");
                for (int j = 0; j < equipes.size (); j ++) {
                    if (i == j)
                     System.out.print ("#" + "\t");
                    else 
                     System.out.print (scores [i] [j] + "" + "\t");
                }
            System.out.print (total [i] + "" + "\t");
            System.out.print (classement [i] + "" + "\t"); 
            System.out.println ();
	}
	System.out.println ("\n");
    }
      
    /**
     *Cette méthode permet de trouver l'équipe gagnante du tournoi. 
     *@return : Renvoie l'équipe en tête du classement
     */
    private Equipe trouverEquipeGagnante () {
        for (int i = 0; i < equipes.size (); i ++)
            if (classement [i] == 1)
		return equipes.get(i);		
	return null; 
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