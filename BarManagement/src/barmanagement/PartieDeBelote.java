 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.*;
import java.util.Random;

/**
 * Cette classe possède toutes les méthodes et paramètres d'une partie de belotte
 * avec l'initialisation d'une partie de belotte, distribuer les cartes, jouer une partie
 * et enfin jouer une manche. 
 * @author Lina & Théophile
 */
class PartieDeBelote {
    Equipe equipe1;
    Equipe equipe2;
    Client donneur ; 
    int pointsEquipe1;
    int pointsEquipe2;
    List <Carte> cartesADistribuer = new LinkedList <> ();
    List <Carte> cartesJoueur1;	// Equipe 1
    List <Carte> cartesJoueur2;	// Equipe 2
    List <Carte> cartesJoueur3;	// Equipe 1
    List <Carte> cartesJoueur4;	// Equipe 2
    String atout;
    String [] couleurs;
    String [] valeurs;
    int [] valeursforce;
    Carte [] cartes;
    Carte [] paquettemp; 
    int NB_ITERATIONS = 3;
    
    /** Cette méthode exécutée à la création de la partie de belote permet l'initialisation de l'objet .
     * @param E1 : Stock la 1ere equipe du tournoi
     * @param E2 : Stock la 2eme equipe du tournoi
     */
    public PartieDeBelote (Equipe E1, Equipe E2) {
	equipe1 = E1;
	equipe2 = E2;   
	pointsEquipe1 = 0;
	pointsEquipe2 = 0;
	couleurs = new String [] {"Pique", "Carreau", "Trefle", "Coeur"};
	valeurs = new String [] {"As", "10","Roi", "Dame","Valet","9","8","7"};
        int [] valeursforce = {1,2,3,4,5,6,7,8};
		
	// Pour simplifier, la couleur d'atout est choisie aleatoirement
	int randomIndex = (int) (Math.random () * 4);	
	atout = couleurs [randomIndex];
	creerCartes ();
    }
	
    /**
     * Cette méthode permet de creer les cartes.
     * Cela va permettre d'instancier nos différentes cartes 
     * Crée les 32 cartes du jeu stockées dans un tableau 
     */
    private void creerCartes () {
	this.cartes = new Carte[couleurs.length*valeurs.length]; //8*4=32
        for (int i = 0; i < couleurs.length; i ++) {
            for (int j = 0; j < valeurs.length; j ++) {
		this.cartes [i * valeurs.length + j] = new Carte (couleurs [i], valeurs [j]);
                }
            } 
        melangerPaquet();
    }
    
    public void quiCommence () {
        
        cartesJoueur1 = new LinkedList <> ();
	cartesJoueur2 = new LinkedList <> ();
	cartesJoueur3 = new LinkedList <> ();
	cartesJoueur4 = new LinkedList <> ();
        
        for (int i=0 ; i < 4 ; i ++) {
            cartesJoueur1.add(this.cartes[i]);
            cartesJoueur2.add(this.cartes[i]); 
            cartesJoueur3.add(this.cartes[i]);
            cartesJoueur4.add(this.cartes[i]);
        }
        compare(cartesJoueur1,cartesJoueur2,cartesJoueur3,cartesJoueur4);
    }
    
    public void compare(List<Carte> J1, List <Carte> J2,List<Carte> J3, List<Carte> J4){
        for (int i = 0 ; i < valeursforce.length ; i++) {
            
        }
        //Cas As
        if ( cartesJoueur1.get(0).valeur == "As" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "As") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "As") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "As") {
             this.donneur= equipe2.joueur2;
        }
        
        //Cas 10 
        if ( cartesJoueur1.get(0).valeur == "10" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "10") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "10") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "10") {
             this.donneur= equipe2.joueur2;
        }
        
        //cas Roi 
        if ( cartesJoueur1.get(0).valeur == "Roi" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "Roi") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "Roi") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "Roi") {
             this.donneur= equipe2.joueur2;
        }
        
        //Cas Dame
        if ( cartesJoueur1.get(0).valeur == "Dame" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "Dame") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "Dame") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "Dame") {
             this.donneur= equipe2.joueur2;
        }
        
        //cas Valet
        if ( cartesJoueur1.get(0).valeur == "Valet" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "Valet") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "Valet") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "Valet") {
             this.donneur= equipe2.joueur2;
        }
        
        //Cas 9
        if ( cartesJoueur1.get(0).valeur == "9" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "9") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "9") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "9") {
             this.donneur= equipe2.joueur2;
        }
        //cas 8
        if ( cartesJoueur1.get(0).valeur == "8" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "8") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "8") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "8") {
             this.donneur= equipe2.joueur2;
        }
        // cas 7 
        if ( cartesJoueur1.get(0).valeur == "7" ) {
            this.donneur= equipe1.joueur1;
        }
        else if (cartesJoueur2.get(0).valeur == "7") {
             this.donneur= equipe1.joueur2;
        }
        else if (cartesJoueur3.get(0).valeur == "7") {
             this.donneur= equipe2.joueur1;
        }
        else if (cartesJoueur4.get(0).valeur == "7") {
             this.donneur= equipe2.joueur2;
        }
     
    }
    
    /**
     * Cette méthode permet d'afficher son jeu en main.
     */
    public void afficherLaMain() {
         for (int i = 0; i < couleurs.length; i ++) { 
            System.out.print(this.cartes[i]);
        } //Il faut voir quel joueur est l'utilisateur
    }
    
    /**
     * Cette méthode permet d'afficher la main de l'adversaire.
     * En fonction de l'équipe sélectionnée, un utilisateur(joueur) expert peut 
     * afficher les cartes de son adversaire.
     */
    public void afficherLaMainAdversaire(Equipe equipe) {
        if (equipe == equipe1) {
             System.out.print("Voici les cartes du joueur 1  ");
             for (int i = 0; i < cartesJoueur1.size(); i ++) { 
                System.out.print(cartesJoueur1.get(i));
                 }
             System.out.print("Et là les cartes du joueur 2 ");
             for (int i = 0; i < cartesJoueur2.size(); i ++) { 
                System.out.print(cartesJoueur2.get(i));
                }
        }
        else {
             System.out.print("Voici les cartes du joueur 3 ");
             for (int i = 0; i < cartesJoueur3.size(); i ++) { 
                System.out.print(cartesJoueur3.get(i));
                 }
             System.out.print("Et là les cartes du joueur 4 ");
             for (int i = 0; i < cartesJoueur4.size(); i ++) { 
                System.out.print(cartesJoueur4.get(i));
                }
        }
        
    }
    
    /**
     * Cette methode permet de melanger le paquet.
     * Pour ce faire, elle fait appel à la methode "échanger"
     */
    public void melangerPaquet() {  
       Random rand = new Random();
        for(int m = 0; m < NB_ITERATIONS; m++){
            for (int i = 0; i < this.cartes.length; i++ ) {
            echanger(rand.nextInt(this.cartes.length), rand.nextInt(this.cartes.length));
            }        
         }
    }
    
     /**
     * Cette methode permet d'échanger 2 cartes.
     */
    public void echanger(int i, int j){ 
      Carte temp;
      temp = this.cartes[i];
      this.cartes[i] = this.cartes[j];
      this.cartes[j] = temp;
   }
        
    public void ordonnerpaquet(){
        
    }
    
    /**Cette méthode permet de coupet le paquet de carte.
     * Le paquet est coupé en deux, et les deux moitiées sont interverties.
     */
    public void couperpaquet(){
        
        this.paquettemp = new Carte[this.cartes.length/2];

           //On stock la premiere moitié du paquet de carte dans une variable temp
        for(int m = 0; m < this.cartes.length/2; m++){//taille 1/2 paquet
            for(int l = 0; l < this.cartes.length/2; l++){//1ere moitié du paquet de carte 
           paquettemp[m]=cartes[l]; //la moitié 1 du paquet 
         }
        }
        
        //On stock la deuxieme moitié du paquet à la place de la 1ere partie
        for(int k = this.cartes.length/2 ; k < this.cartes.length ; k++){ //decroit de la fin à la moitié
           for(int i = 0; i < this.cartes.length/2; i++) {
               cartes[i]=cartes[k];            
           }
        }
        //On stock la 1ere partie dans la 2eme partie 
        for(int k = 0; k < this.cartes.length/2 ; k++ ){ //1ere moitié
          for(int i = this.cartes.length/2 ; i < this.cartes.length; i++) { //2eme moitié 
               cartes[i]=paquettemp[k];            
           }
        }
        
    }
	
    /**Cette méthode permet de distribuer les Cartes 
     * Ainsi, 8 cartes seront distrubuer aleatoirement.
     * De plus, les mains de chaque joueur sont stockés dans des listes de cartes propre à chacun
     */
    private void distribuerLesCartes () {
    	
	cartesJoueur1 = new LinkedList <> ();
	cartesJoueur2 = new LinkedList <> ();
	cartesJoueur3 = new LinkedList <> ();
	cartesJoueur4 = new LinkedList <> ();
	
	for (int i = 0; i < 32; i ++) //ajoute les cartes à la liste
            cartesADistribuer.add (this.cartes[i]);
        for (int i = 0; i < 32; i ++) {
          //   int randomIndex = (int) (Math.random () * cartesADistribuer.size ());		
            double j = 1 + (i % 4);
  
            if( j == 2.0) {
             cartesJoueur1.add (this.cartes [i-4]); //ajoute les 5 cartes 
             cartesJoueur1.add (this.cartes [i-3]);
             cartesJoueur1.add (this.cartes [i-2]);
             cartesJoueur1.add (this.cartes [i-1]);
             cartesJoueur1.add (this.cartes [i]);}
            
            else if( j == 3.25) {        
             cartesJoueur2.add (this.cartes [i-4]);
             cartesJoueur2.add (this.cartes [i-3]);
             cartesJoueur2.add (this.cartes [i-2]);
             cartesJoueur2.add (this.cartes [i-1]);
             cartesJoueur2.add (this.cartes [i]);}
            else if( j == 4.5) {
             cartesJoueur3.add (this.cartes [i-4]);
             cartesJoueur3.add (this.cartes [i-3]);
             cartesJoueur3.add (this.cartes [i-2]);
             cartesJoueur3.add (this.cartes [i-1]);
             cartesJoueur3.add (this.cartes [i]);}
                
             else if( j == 5.75) {
             cartesJoueur4.add (this.cartes [i-4]);
             cartesJoueur4.add (this.cartes [i-3]);
             cartesJoueur4.add (this.cartes [i-2]);
             cartesJoueur4.add (this.cartes [i-1]);
             cartesJoueur4.add (this.cartes [i]);}
            }
    }
	
    /**Cette méthode permet de jouer une manche.
     * Chaque joueur dispose ainsi de 8 cartes.
     * Chaque tour, une carte est dévoilée, le joueur qui dévoile la carte la plus
     * forte marque un point pour son equipe. Une fois les 8 cartes jouees,
     * l'equipe qui a marque le plus de points gagne la manche. S'il y a egalite la manche est nulle.
     * Les joueurs de l'equipe qui perdent la manche offrent a boire aux gagnants pour finir.
     * 
     */
    private void jouerUneManche () {
	distribuerLesCartes ();
        int scoreEquipe1 = 0;
	int scoreEquipe2 = 0;
        for (int i = 0; i < 8; i ++) {
            List <Integer> forces = new LinkedList <> ();
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
	
    /** Cette méthode permet de jouer un match.
     * On cherche à faire deux points d'écart pour avoir un gagnant. 
     * @return : Retourne le score qu'a marqué chacune des équipes sous forme d'un tableau
     */
    public int [] jouer () {	
	while (pointsEquipe1 < 2 && pointsEquipe2 < 2) {
            jouerUneManche ();
	}	
	return new int [] {pointsEquipe1, pointsEquipe2};
    }
}