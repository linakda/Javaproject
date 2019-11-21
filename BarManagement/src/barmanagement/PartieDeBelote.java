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
    List <Carte> cartesJoueur1;	// Equipe 1 J1
    List <Carte> cartesJoueur2;	// Equipe 1 J2
    List <Carte> cartesJoueur3;	// Equipe 2 J1
    List <Carte> cartesJoueur4;	// Equipe 2 J2
    String atout;
    String [] couleurs;
    String [] valeurs;
    int [] valeurcarte;
    int [] valeuratout;
    Carte [] cartes;
    Carte [] paquettemp; 
    int iteration = 3;
    
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
	valeurs = new String [] {"7", "8","9", "Valet","Dame","Roi","10","As"};
        valeurcarte = new int []{0,0,0,2,3,4,10,11};
        valeuratout = new int [] {0,0,14,20,3,4,10,11};
		
	// Pour simplifier, la couleur d'atout est choisie aleatoirement
	int randomIndex = (int) (Math.random () * 4);	
	atout = couleurs [randomIndex];
	creerCartes ();
        quiCommence();
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
		this.cartes [i * valeurs.length + j] = new Carte (couleurs [i], valeurs [j], valeurcarte[j], valeuratout[j]);
                }
            } 
        /*     System.out.println("******************************"); 
        System.out.println("1ere paquet : \n \n "); 
        for (int i = 0; i < cartes .length; i ++) {
            System.out.println(cartes[i]);
        }*/
        melangerPaquet(this.cartes);
    }
    
    public void quiCommence () {
        
        cartesJoueur1 = new LinkedList <> ();
	cartesJoueur2 = new LinkedList <> ();
	cartesJoueur3 = new LinkedList <> ();
	cartesJoueur4 = new LinkedList <> ();
        
        int randomIndex = (int) (Math.random () * 32);
        int randomIndex2 = (int) (Math.random () * 32);
        int randomIndex3 = (int) (Math.random () * 32);
        int randomIndex4 = (int) (Math.random () * 32);
  
        cartesJoueur1.add(this.cartes[randomIndex]);
        cartesJoueur2.add(this.cartes[randomIndex2]); 
        cartesJoueur3.add(this.cartes[randomIndex3]);
        cartesJoueur4.add(this.cartes[randomIndex4]);
        
        trouvedonneur(cartesJoueur1,cartesJoueur2,cartesJoueur3,cartesJoueur4);
    }
    
    public void trouvedonneur(List<Carte> J1, List <Carte> J2,List<Carte> J3, List<Carte> J4){
         List <Integer> comparateur = new LinkedList<>();
         
        // On ajoute la valeur de la carte piochée par chaque joueur à la liste comparateur
        for (int i = 0 ; i < cartesJoueur1.size() ; i++) {
               comparateur.add(cartesJoueur1.get(i).valeurcarte);
               comparateur.add(cartesJoueur2.get(i).valeurcarte);
               comparateur.add(cartesJoueur3.get(i).valeurcarte);
               comparateur.add(cartesJoueur4.get(i).valeurcarte);
        }
        //On trie la liste par ordre croissant
        Collections.sort(comparateur);

        //On recupère le donneur lorsque sa carte est égale à la plus faible carte
         for (int i = 0 ; i < cartesJoueur1.size() ; i++) {
            if(cartesJoueur1.get(i).valeurcarte==comparateur.get(0))
                this.donneur = equipe1.joueur1;
            else if (cartesJoueur2.get(i).valeurcarte==comparateur.get(0))
                this.donneur = equipe1.joueur2;
            else if (cartesJoueur3.get(i).valeurcarte==comparateur.get(0))
                 this.donneur = equipe2.joueur1;
            else if (cartesJoueur4.get(i).valeurcarte==comparateur.get(0))
                 this.donneur = equipe2.joueur2;
         }
         
        /* for (int i = 0 ; i < cartesJoueur1.size() ; i++) {
        System.out.println("La carte du J1 : " + cartesJoueur1.get(i).valeurcarte +" La carte du J2: " + cartesJoueur2.get(i).valeurcarte + " La carte du J3: "
                 + cartesJoueur3.get(i).valeurcarte + " La carte du J2: " + cartesJoueur4.get(i).valeurcarte);
         }
         for (int i = 0 ; i < cartesJoueur1.size() ; i++) {
            System.out.println(" La liste triée : " + comparateur.get(i));
         }*/
         System.out.println("Le donneur est :" + donneur.getPrenom());
         
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
    public void melangerPaquet(Carte [] paquet) {  
       Random rand = new Random();
        for(int m = 0; m < iteration; m++){
            for (int i = 0; i < paquet.length; i++ ) {
            echanger(rand.nextInt(paquet.length), rand.nextInt(paquet.length));
            }        
         }
     /*   System.out.println("******************************"); 
         System.out.println("Paquet mélangé \n \n"); 
         for (int i = 0; i < paquet.length; i++ ) {
            System.out.println(paquet[i]); 
         }*/
        
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
    	
	List <Carte> cartesADistribuer = new LinkedList <> ();
	cartesJoueur1 = new LinkedList <> ();
	cartesJoueur2 = new LinkedList <> ();
	cartesJoueur3 = new LinkedList <> ();
	cartesJoueur4 = new LinkedList <> ();
	
	for (int i = 0; i < 32; i ++) //ajoute les cartes à la liste
            cartesADistribuer.add (this.cartes[i]);
        
       /*  System.out.println("******************************"); 
        System.out.println("Cartes à distribuer initial");
       for (int i = 0; i < 32; i ++) //ajoute les cartes à la liste
            System.out.println(cartesADistribuer.get(i));*/
     
      
       //  System.out.println("cartej1******************************"); 
             cartesJoueur1.add (cartesADistribuer.get(0)); //ajoute les 3 cartes 
             cartesJoueur1.add (cartesADistribuer.get(1));
             cartesJoueur1.add (cartesADistribuer.get(2));
          
       /*      for (int i = 0; i < 3; i ++) {
             System.out.println(cartesJoueur1.get(i)); } 
             
         System.out.println("cartej2******************************"); */
             cartesJoueur2.add (cartesADistribuer.get(3)); //ajoute les 3 cartes 
             cartesJoueur2.add (cartesADistribuer.get(4));
             cartesJoueur2.add (cartesADistribuer.get(5));
        
            /* for (int i = 0; i < 3; i ++) {
             System.out.println(cartesJoueur2.get(i)); } 
         System.out.println("cartej3******************************"); */
             cartesJoueur3.add (cartesADistribuer.get(6)); //ajoute les 3 cartes 
             cartesJoueur3.add (cartesADistribuer.get(7));
             cartesJoueur3.add (cartesADistribuer.get(8));
         
        /*     for (int i = 0; i < 3; i ++) {
             System.out.println(cartesJoueur3.get(i)); } 
         System.out.println("cartej4******************************"); */
             cartesJoueur4.add (cartesADistribuer.get(9)); //ajoute les 3 cartes 
             cartesJoueur4.add (cartesADistribuer.get(10));
             cartesJoueur4.add (cartesADistribuer.get(11));
         
          
             
             cartesJoueur1.add (cartesADistribuer.get(12)); //ajoute les 3 cartes 
             cartesJoueur1.add (cartesADistribuer.get(13));
            
  
             cartesJoueur2.add (cartesADistribuer.get(14)); //ajoute les 3 cartes 
             cartesJoueur2.add (cartesADistribuer.get(15));

       

             cartesJoueur3.add (cartesADistribuer.get(16)); //ajoute les 3 cartes 
             cartesJoueur3.add (cartesADistribuer.get(17));

    
             cartesJoueur4.add (cartesADistribuer.get(18)); //ajoute les 3 cartes 
             cartesJoueur4.add (cartesADistribuer.get(19));

             
             System.out.println(donneur.getPrenom() + " : La retourne est la carte " + cartesADistribuer.get(20));
             System.out.println(donneur.getPrenom() + " :Je vous propose la couleur " + cartesADistribuer.get(20).couleur + " comme atout");
           
             this.atout = cartesADistribuer.get(20).couleur;
             
        /*        System.out.println("******************************"); 
         System.out.println("Cartes a distri avec remove");
       for (int i = 0; i < cartesADistribuer.size(); i ++) //ajoute les cartes à la liste
           System.out.println(cartesADistribuer.get(i));        
                
       
         System.out.println("J1*****************************************");
       for (int i = 0; i < cartesJoueur1.size(); i ++) {
        
               System.out.println(  cartesJoueur1.get(i)); 
           }
         System.out.println("J2*****************************************");
                for (int i = 0; i < cartesJoueur1.size(); i ++) {
         
    
                System.out.println( cartesJoueur2.get(i));
           }   
                  System.out.println("J3*****************************************");
                 for (int i = 0; i < cartesJoueur1.size(); i ++) {
        
              
                 System.out.println(  cartesJoueur3.get(i));
           }
                   System.out.println("J4*****************************************");
                 for (int i = 0; i < cartesJoueur1.size(); i ++) {
           
                  System.out.println( cartesJoueur4.get(i));  
          
               }*/
               
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
        
	distribuerLesCartes (); // 5 cartes en main chacun 
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