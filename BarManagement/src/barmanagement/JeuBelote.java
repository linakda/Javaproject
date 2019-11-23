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
class JeuBelote {
    Equipe equipe1;
    Equipe equipe2;
    Client donneur ; 
    int pointsEquipe1,pointsEquipe2;
    int sum1,sum2,sum3,sum4;
    int c, iteration; 
    List <Integer> points = new LinkedList <> ();
    List <Carte> cartesADistribuer = new LinkedList <> ();
    List <Carte> cartesJoueur1;	// Equipe 1 J1
    List <Carte> cartesJoueur2;	// Equipe 1 J2
    List <Carte> cartesJoueur3;	// Equipe 2 J1
    List <Carte> cartesJoueur4;	// Equipe 2 J2
    List <Integer> pli = new LinkedList <> ();
    List <Integer> pli1 = new LinkedList <> ();
    List <Integer> pli2 = new LinkedList <> ();
    List <Integer> pli3 = new LinkedList <> ();
    List <Integer> pli4 = new LinkedList <> ();
    String atout;
    String [] couleurs;
    String [] valeurs;
    int [] valeurcarte;
    int [] valeuratout;
    Carte [] cartes;
    Carte [] paquettemp; 
    
    
    /** Cette méthode exécutée à la création de la partie de belote permet l'initialisation de l'objet .
     * @param E1 : Stock la 1ere equipe du tournoi
     * @param E2 : Stock la 2eme equipe du tournoi
     */
    public JeuBelote (Equipe E1, Equipe E2) {
        this.sum1=0;
        this.sum2=0;
        this.sum3=0;
        this.sum4=0;
        this.c=0;
        this.iteration = 3; 
	this.equipe1 = E1;
	this.equipe2 = E2;   
	this.pointsEquipe1 = 0;
	this.pointsEquipe2 = 0;
	this.couleurs = new String [] {"Pique", "Carreau", "Trefle", "Coeur"};
	this.valeurs = new String [] {"7", "8","9", "Valet","Dame","Roi","10","As"};
        this.valeurcarte = new int []{0,0,0,2,3,4,10,11};
        this.valeuratout = new int [] {0,0,14,20,3,4,10,11};
	
	creerCartes ();
        
    }
	
    /**
     * Cette méthode permet de creer les cartes.
     * Cela va permettre d'instancier nos différentes cartes 
     * Crée les 32 cartes du jeu stockées dans un tableau 
     */
    private void creerCartes () {
	this.cartes = new Carte[couleurs.length*valeurs.length];
        for (int i = 0; i < couleurs.length; i ++) {
            for (int j = 0; j < valeurs.length; j ++) {
		this.cartes [i * valeurs.length + j] = new Carte (couleurs [i], valeurs [j], valeurcarte[j], valeuratout[j]);
                }
            } 
       
    }
    
    /**
     * Cette méthode permet d'afficher la main de l'adversaire.
     * En fonction de l'équipe sélectionnée, un utilisateur(joueur) expert peut 
     * afficher les cartes de son adversaire.
     * @param equipe : l'équipe donc on veut voir les mains. 
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
       
    /**
     * Cette méthode permet de coupet le paquet de carte.
     * Le paquet est coupé en deux, et les deux moitiées sont interverties.
     */
    public void couperpaqueten2( Carte [] paquet){
        
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
	
    /**
     * Cette methode permet d'afficher la table avec les joueurs actuellement en jeu.
     * Les joueurs sont assis en face à face de sorte que les 2 voisins d'un
     * joueurs soient les membres de l'équipe adverse.
     */
    public void voirLaTable(){
        System.out.println("\n Voici la table pour cette manche");
        System.out.println(equipe1.joueur1.getPrenom() + "\t \t"+ equipe2.joueur1.getPrenom());
        System.out.println("-------------------");
        System.out.println("|                  |");
        System.out.println("-------------------");
        System.out.println(equipe2.joueur2.getPrenom() + "\t \t"+ equipe1.joueur2.getPrenom() + "\n\n");
    
   }
    
    /**
     * Cette méthode permet de générer un nombre random
     * @return rand : le nombre random
     */
    public int randomm(){
        int rand = (int) (Math.random () * cartesJoueur4.size());
        return rand ; 
    }
     
    /**
     * Cette méthode permet de définir qui commence le jeu.
     * Elle fait tirer au hasard une carte à chaque joueur puis fait appel à 
     * la méthode qui trouve le donneur.
     */
    public void quiCommence () {
        
        cartesJoueur1 = new LinkedList <> ();
	cartesJoueur2 = new LinkedList <> ();
	cartesJoueur3 = new LinkedList <> ();
	cartesJoueur4 = new LinkedList <> ();
        
        int randomIndex = (int) (Math.random () * 32);
        int randomIndex2 = (int) (Math.random () * 32);
        int randomIndex3 = (int) (Math.random () * 32);
        int randomIndex4 = (int) (Math.random () * 32);
        //this.equipe1.joueur1.parler("J'ai pioché la carte " + this.cartes[randomIndex]) ;
       // this.equipe1.joueur2.parler("Et moi la carte "+this.cartes[randomIndex2] );
       // this.equipe2.joueur1.parler("J'ai pioché la carte " + this.cartes[randomIndex3] );
       // this.equipe2.joueur2.parler("Et moi la carte "+this.cartes[randomIndex4] );
        this.cartesJoueur1.add(this.cartes[randomIndex]);
        this.cartesJoueur2.add(this.cartes[randomIndex2]); 
        this.cartesJoueur3.add(this.cartes[randomIndex3]);
        this.cartesJoueur4.add(this.cartes[randomIndex4]);
        
        trouveDonneur(cartesJoueur1,cartesJoueur2,cartesJoueur3,cartesJoueur4);
    }
    
    /**
     * Cette méthode permet de trouver qui va être le premier donneur de la partie.
     * On ajoute la figure de la carte piochée par chaque joueur à la liste comparateur.
 On trie la liste par ordre croissant et on recupère le donneur lorsque 
 sa carte est égale à la plus faible carte, puis on affiche son prénom.
     * @param J1 : stocke la carte piochée par le joueur 1
     * @param J2 : stocke la carte piochée par le joueur 2
     * @param J3 : stocke la carte piochée par le joueur 3
     * @param J4 : stocke la carte piochée par le joueur 4
     */
    public void trouveDonneur(List<Carte> J1, List <Carte> J2,List<Carte> J3, List<Carte> J4){
         List <Integer> comparateur = new LinkedList<>();
 
        for (int i = 0 ; i < cartesJoueur1.size() ; i++) {
               comparateur.add(cartesJoueur1.get(i).valeurcarte);
               comparateur.add(cartesJoueur2.get(i).valeurcarte);
               comparateur.add(cartesJoueur3.get(i).valeurcarte);
               comparateur.add(cartesJoueur4.get(i).valeurcarte);
        }
        
        Collections.sort(comparateur);
        
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
         System.out.println("Le donneur de la manche est " + donneur.getPrenom());
    }
    
    /**
     * Cette méthode permet de distribuer les Cartes. 
     * Les joueurs auront en leur possession 5 cartes, distribuer en 3 puis 2, dans
     * l'ordre inverse des aiguilles d'une montre.
     * Elle permet également au donneur d'annoncer la retourne et de proposer l'atout.
     */
    private void distribuerLesCartes () {
        melangerPaquet(this.cartes);
        couperpaqueten2(this.cartes);
        
	cartesJoueur1.clear();
	cartesJoueur2.clear();
	cartesJoueur3.clear();
	cartesJoueur4.clear();
        
	for (int i = 0; i < 32; i ++){ //ajoute les cartes à la liste
            cartesADistribuer.add (this.cartes[i]);     
        }
         cartesJoueur1.add (cartesADistribuer.get(0)); //ajoute les 3 cartes 
         cartesJoueur1.add (cartesADistribuer.get(1));
         cartesJoueur1.add (cartesADistribuer.get(2));

         cartesJoueur2.add (cartesADistribuer.get(3)); //ajoute les 3 cartes 
         cartesJoueur2.add (cartesADistribuer.get(4));
         cartesJoueur2.add (cartesADistribuer.get(5));

         cartesJoueur3.add (cartesADistribuer.get(6)); //ajoute les 3 cartes 
         cartesJoueur3.add (cartesADistribuer.get(7));
         cartesJoueur3.add (cartesADistribuer.get(8));

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

         System.out.println(donneur.parler(" La retourne est la carte " + cartesADistribuer.get(20)));
         System.out.println(donneur.parler (" Je vous propose la couleur " + cartesADistribuer.get(20).couleur + " comme atout"));

         this.atout = cartesADistribuer.get(20).couleur;      
    }
    
    /**
     * Cette methode permet de jouer une manche.
     * Chaque joueur commence par jouer une carte de sa main, on va voir celle
 dont la figure est la plus forte. Celui dont la carte est la plus forte récolte 
 toutes les autres cartes. On repète l'action jusqu'à ce que les joueurs n'ont
 plus de cartes en main. Ensuite, un calcul des scores est fait et les perdants
 offrent à boire aux gagnants de la manche.
     * @return points : La liste contenant les points des deux equipes
     * 
     */
    public List<Integer> jouerManche(){
        int scoreEquipe1;
	int scoreEquipe2;
        int rand1=randomm();
        
       if(cartesJoueur4.size()>=1) {
                 
                   
                   
                   //Chacun joue une carte de sa main
                    pli.add(cartesJoueur4.get(rand1).valeurcarte);
                    pli.add(cartesJoueur2.get(rand1).valeurcarte);
                    pli.add(cartesJoueur3.get(rand1).valeurcarte);
                    pli.add(cartesJoueur1.get(rand1).valeurcarte);
                    
                    //On regarde quelle est la carte la plus forte
                    int Max = Collections.max(pli);
                            
                   //Le gagnant (avec la plus forte) recolte toutes les cartes
                   //Les cartes posées sont retirées des mains des joueurs.
                   if (cartesJoueur4.get(rand1).valeurcarte == Max){
                       pli4.add(cartesJoueur4.get(rand1).valeurcarte);
                       pli4.add(cartesJoueur3.get(rand1).valeurcarte);
                       pli4.add(cartesJoueur2.get(rand1).valeurcarte);
                       pli4.add(cartesJoueur1.get(rand1).valeurcarte);                     
                       this.cartesJoueur4.remove(rand1);          
                       this.cartesJoueur2.remove(rand1);
                       this.cartesJoueur3.remove(rand1);
                       this.cartesJoueur1.remove(rand1);     
                       pli.clear();
                   }
                   
                   else if (cartesJoueur2.get(rand1).valeurcarte == Max){
                       pli2.add(cartesJoueur4.get(rand1).valeurcarte);
                       pli2.add(cartesJoueur3.get(rand1).valeurcarte);
                       pli2.add(cartesJoueur2.get(rand1).valeurcarte);
                       pli2.add(cartesJoueur1.get(rand1).valeurcarte);
                       this.cartesJoueur4.remove(rand1);          
                       this.cartesJoueur2.remove(rand1);
                       this.cartesJoueur3.remove(rand1);
                       this.cartesJoueur1.remove(rand1);
                       pli.clear();
                   }
                   
                   else if (cartesJoueur3.get(rand1).valeurcarte == Max){
                       pli3.add(cartesJoueur4.get(rand1).valeurcarte);
                       pli3.add(cartesJoueur3.get(rand1).valeurcarte);
                       pli3.add(cartesJoueur2.get(rand1).valeurcarte);
                       pli3.add(cartesJoueur1.get(rand1).valeurcarte);
                       this.cartesJoueur4.remove(rand1);          
                       this.cartesJoueur2.remove(rand1);
                       this.cartesJoueur3.remove(rand1);
                       this.cartesJoueur1.remove(rand1);
                       pli.clear();
                   }
                   else if (cartesJoueur1.get(rand1).valeurcarte == Max){
                       pli1.add(cartesJoueur4.get(rand1).valeurcarte);
                       pli1.add(cartesJoueur3.get(rand1).valeurcarte);
                       pli1.add(cartesJoueur2.get(rand1).valeurcarte);
                       pli1.add(cartesJoueur1.get(rand1).valeurcarte);
                       this.cartesJoueur4.remove(rand1);          
                       this.cartesJoueur2.remove(rand1);
                       this.cartesJoueur3.remove(rand1);
                       this.cartesJoueur1.remove(rand1);  
                       pli.clear();
                   }    

                    do {
                      jouerManche();
                      }while(cartesJoueur4.size()>=1);
                    }
                    
         else {             
                   for(int i=0;i<pli1.size();i++){
                   sum1+=pli1.get(i);
                   }
                   for(int i=0;i<pli2.size();i++){
                   sum2+=pli2.get(i);
                   }
                   for(int i=0;i<pli3.size();i++){
                   sum3+=pli3.get(i);
                   }
                   for(int i=0;i<pli4.size();i++){
                   sum4+=pli4.get(i);
                   }
                   
                   scoreEquipe1 = sum1 + sum2;
                   scoreEquipe2 = sum3 + sum4;
                  
   System.out.println("Pour l'instant le score de l'equipe "+ equipe1.nomEquipe + " est " +scoreEquipe1 +" et le score de l'equipe " + equipe2.nomEquipe + " est " +scoreEquipe2);

   if (scoreEquipe1 > scoreEquipe2) {
       System.out.println("L'equipe "+ equipe1.nomEquipe +" remporte la manche ! ");
        pointsEquipe1++;
        System.out.println("********************************************");
        System.out.println("Les perdants de la manche offrent un verre !");
        equipe2.joueur1.offrir(equipe1.joueur1);
        equipe2.joueur2.offrir(equipe1.joueur2);
      }
   else {
        System.out.println("L'equipe "+ equipe2.nomEquipe +" remporte la manche ! ");
        pointsEquipe2++;
        System.out.println("********************************************");
        System.out.println("Les perdants de la manche offrent un verre !");
        equipe1.joueur1.offrir(equipe2.joueur1);
        equipe1.joueur2.offrir(equipe2.joueur2);
      }

   points.add(pointsEquipe1); 
   points.add(pointsEquipe2); 
}
   return this.points;          
    }            
    
    /**Cette méthode permet d'organiser le deroulement d'une partie de belote.
     * 
     */
    private void deroulement () {
      
        List <Integer> atoutmain1 = new LinkedList <> ();
        List <Integer> atoutmain2 = new LinkedList <> ();
        List <Integer> atoutmain3 = new LinkedList <> ();
        List <Integer> atoutmain4 = new LinkedList <> ();
        List <Integer> resultat = new LinkedList <> ();
       
        //Rempli la liste pour pouvoir trouver le Max quoi qu'il arrive
        atoutmain1.add(0);
        atoutmain2.add(0);
        atoutmain3.add(0);
        atoutmain4.add(0);
        
        voirLaTable();
        quiCommence();
	distribuerLesCartes (); // 5 cartes en main chacun 
       
        //En fonction de qui est le donneur, les cartes sont distribuées 
        if(this.donneur == equipe1.joueur1) {      
            if(Collections.max(atoutmain4) + Collections.max(atoutmain3) >= 20) { //Si la somme des plus grands atouts de l'equipe 1 > 25
                    System.out.println(equipe2.joueur2.parler("Je ramasse l'atout ! "));
                    cartesJoueur4.add(this.cartesADistribuer.get(20)); // ramasse l'atout 
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur4.add(this.cartesADistribuer.get(21));
                    cartesJoueur4.add(this.cartesADistribuer.get(22));
                    cartesJoueur2.add(this.cartesADistribuer.get(23));  
                    cartesJoueur2.add(this.cartesADistribuer.get(24));
                    cartesJoueur2.add(this.cartesADistribuer.get(25));
                    cartesJoueur3.add(this.cartesADistribuer.get(26));
                    cartesJoueur3.add(this.cartesADistribuer.get(27));
                    cartesJoueur3.add(this.cartesADistribuer.get(28));
                    cartesJoueur1.add(this.cartesADistribuer.get(29)); 
                    cartesJoueur1.add(this.cartesADistribuer.get(30));
                    cartesJoueur1.add(this.cartesADistribuer.get(31));
                    resultat=jouerManche();
                }

                
            else if(Collections.max(atoutmain2) + Collections.max(atoutmain1) >= 0) { //Si la somme des plus grands atouts de l'equipe 1 > 25
                    System.out.println(equipe1.joueur2.parler("Je ramasse l'atout ! "));
                    cartesJoueur2.add(cartesADistribuer.get(20)); // rammasse l'atout 
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur2.add(cartesADistribuer.get(21));
                    cartesJoueur2.add(cartesADistribuer.get(22));
                    cartesJoueur3.add(cartesADistribuer.get(23));  
                    cartesJoueur3.add(cartesADistribuer.get(24));
                    cartesJoueur3.add(cartesADistribuer.get(25));
                    cartesJoueur1.add(cartesADistribuer.get(26));
                    cartesJoueur1.add(cartesADistribuer.get(27));
                    cartesJoueur1.add(cartesADistribuer.get(28));
                    cartesJoueur4.add(cartesADistribuer.get(29)); 
                    cartesJoueur4.add(cartesADistribuer.get(30));
                    cartesJoueur4.add(cartesADistribuer.get(31));
                    resultat=jouerManche();
                }  
              
            else { //Si personne ne ramasse 
                    System.out.println(donneur.parler("Apparement personne ne prend au Tour 1")); 
                    cartesJoueur4.add(cartesADistribuer.get(20));
                    cartesJoueur4.add(cartesADistribuer.get(21));
                    cartesJoueur4.add(cartesADistribuer.get(22));
                    cartesJoueur2.add(cartesADistribuer.get(23));  
                    cartesJoueur2.add(cartesADistribuer.get(24));
                    cartesJoueur2.add(cartesADistribuer.get(25));
                    cartesJoueur3.add(cartesADistribuer.get(26));
                    cartesJoueur3.add(cartesADistribuer.get(27));
                    cartesJoueur3.add(cartesADistribuer.get(28));
                    cartesJoueur1.add(cartesADistribuer.get(29)); 
                    cartesJoueur1.add(cartesADistribuer.get(30));
                    cartesJoueur1.add(cartesADistribuer.get(31));
                    resultat=jouerManche();       
              }
        }
        
        else if (donneur == equipe1.joueur2){
            if(Collections.max(atoutmain3) + Collections.max(atoutmain4) >= 0) {
                    System.out.println(equipe2.joueur1.parler("Je ramasse l'atout ! "));
                    cartesJoueur3.add(cartesADistribuer.get(20)); // ramasse l'atout 
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur3.add(cartesADistribuer.get(21));
                    cartesJoueur3.add(cartesADistribuer.get(22));
                    cartesJoueur1.add(cartesADistribuer.get(23)); 
                    cartesJoueur1.add(cartesADistribuer.get(24));
                    cartesJoueur1.add(cartesADistribuer.get(25));
                    cartesJoueur4.add(cartesADistribuer.get(26));
                    cartesJoueur4.add(cartesADistribuer.get(27));
                    cartesJoueur4.add(cartesADistribuer.get(28));
                    cartesJoueur2.add(cartesADistribuer.get(29));  
                    cartesJoueur2.add(cartesADistribuer.get(30));
                    cartesJoueur2.add(cartesADistribuer.get(31));
                    resultat=jouerManche();
                    }
            else if(Collections.max(atoutmain2) + Collections.max(atoutmain1) >= 0) { //Si la somme des plus grands atouts de l'equipe 1 > 25
                    System.out.println(equipe1.joueur1.parler("Je ramasse l'atout ! "));
                    cartesJoueur1.add(cartesADistribuer.get(20)); // ramasse l'atout 
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur1.add(cartesADistribuer.get(21));
                    cartesJoueur1.add(cartesADistribuer.get(22));
                    cartesJoueur4.add(cartesADistribuer.get(23));  
                    cartesJoueur4.add(cartesADistribuer.get(24));
                    cartesJoueur4.add(cartesADistribuer.get(25));
                    cartesJoueur2.add(cartesADistribuer.get(26));
                    cartesJoueur2.add(cartesADistribuer.get(27));
                    cartesJoueur2.add(cartesADistribuer.get(28));
                    cartesJoueur3.add(cartesADistribuer.get(29)); 
                    cartesJoueur3.add(cartesADistribuer.get(30));
                    cartesJoueur3.add(cartesADistribuer.get(31));
                     resultat=jouerManche();
                     }  
            else {
                   System.out.println(donneur.parler("Apparement personne ne prend au Tour 1"));
                    cartesJoueur3.add(cartesADistribuer.get(20));
                    cartesJoueur3.add(cartesADistribuer.get(21));
                    cartesJoueur3.add(cartesADistribuer.get(22));
                    cartesJoueur1.add(cartesADistribuer.get(23));  
                    cartesJoueur1.add(cartesADistribuer.get(24));
                    cartesJoueur1.add(cartesADistribuer.get(25));
                    cartesJoueur4.add(cartesADistribuer.get(26));
                    cartesJoueur4.add(cartesADistribuer.get(27));
                    cartesJoueur4.add(cartesADistribuer.get(28));
                    cartesJoueur2.add(cartesADistribuer.get(29)); 
                    cartesJoueur2.add(cartesADistribuer.get(30));
                    cartesJoueur2.add(cartesADistribuer.get(31));
                   resultat=jouerManche();
               
           }
        }
        
        else if (donneur == equipe2.joueur1){
            if(Collections.max(atoutmain1) + Collections.max(atoutmain2) >= 14) {
                    System.out.println(equipe1.joueur1.parler("Je ramasse l'atout ! "));
                    cartesJoueur1.add(cartesADistribuer.get(20)); // rammasse l'atout 
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur1.add(cartesADistribuer.get(21));
                    cartesJoueur1.add(cartesADistribuer.get(22));
                    cartesJoueur4.add(cartesADistribuer.get(23));
                    cartesJoueur4.add(cartesADistribuer.get(24));
                    cartesJoueur4.add(cartesADistribuer.get(25));
                    cartesJoueur2.add(cartesADistribuer.get(26));  
                    cartesJoueur2.add(cartesADistribuer.get(27));
                    cartesJoueur2.add(cartesADistribuer.get(28));
                    cartesJoueur3.add(cartesADistribuer.get(29));
                    cartesJoueur3.add(cartesADistribuer.get(30));
                    cartesJoueur3.add(cartesADistribuer.get(31));
                     resultat=jouerManche();
             }
            else if(Collections.max(atoutmain4) + Collections.max(atoutmain3) >= 14) {
                    System.out.println( equipe1.joueur2.parler("Je ramasse l'atout ! "));
                    cartesJoueur4.add(cartesADistribuer.get(20)); // rammasse l'atout
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur4.add(cartesADistribuer.get(21));
                    cartesJoueur4.add(cartesADistribuer.get(22));
                    cartesJoueur2.add(cartesADistribuer.get(23));
                    cartesJoueur2.add(cartesADistribuer.get(24));
                    cartesJoueur2.add(cartesADistribuer.get(25));
                    cartesJoueur3.add(cartesADistribuer.get(26));
                    cartesJoueur3.add(cartesADistribuer.get(27));
                    cartesJoueur3.add(cartesADistribuer.get(28));
                    cartesJoueur1.add(cartesADistribuer.get(29));
                    cartesJoueur1.add(cartesADistribuer.get(30));
                    cartesJoueur1.add(cartesADistribuer.get(31));
                     resultat=jouerManche();
           }
           
            else {
                   System.out.println(donneur.parler("Apparement personne ne prend au Tour 1"));
                    cartesJoueur1.add(cartesADistribuer.get(20));
                    cartesJoueur1.add(cartesADistribuer.get(21));
                    cartesJoueur1.add(cartesADistribuer.get(22));
                    cartesJoueur4.add(cartesADistribuer.get(23));  
                    cartesJoueur4.add(cartesADistribuer.get(24));
                    cartesJoueur4.add(cartesADistribuer.get(25));
                    cartesJoueur2.add(cartesADistribuer.get(26));
                    cartesJoueur2.add(cartesADistribuer.get(27));
                    cartesJoueur2.add(cartesADistribuer.get(28));
                    cartesJoueur3.add(cartesADistribuer.get(29)); 
                    cartesJoueur3.add(cartesADistribuer.get(30));
                    cartesJoueur3.add(cartesADistribuer.get(31));
                   resultat=jouerManche();
           }
        }
        
        else if (donneur == equipe2.joueur2){
            if(Collections.max(atoutmain2) + Collections.max(atoutmain1) >= 14) {
                    System.out.println( equipe1.joueur2.parler("Je ramasse l'atout ! "));
                    cartesJoueur2.add(cartesADistribuer.get(20)); // rammasse l'atout
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur2.add(cartesADistribuer.get(21));
                    cartesJoueur2.add(cartesADistribuer.get(22));
                    cartesJoueur3.add(cartesADistribuer.get(23));
                    cartesJoueur3.add(cartesADistribuer.get(24));
                    cartesJoueur3.add(cartesADistribuer.get(25));
                    cartesJoueur1.add(cartesADistribuer.get(26));
                    cartesJoueur1.add(cartesADistribuer.get(27));
                    cartesJoueur1.add(cartesADistribuer.get(28));
                    cartesJoueur4.add(cartesADistribuer.get(29));
                    cartesJoueur4.add(cartesADistribuer.get(30));
                    cartesJoueur4.add(cartesADistribuer.get(31));
                    resultat=jouerManche();
           }
            else if(Collections.max(atoutmain3) + Collections.max(atoutmain4) >= 14) {
                    System.out.println( equipe1.joueur2.parler("Je ramasse l'atout ! "));
                    cartesJoueur3.add(cartesADistribuer.get(20)); // rammasse l'atout
                    System.out.println(donneur.parler(" Très bien, je distribue les cartes restantes"));
                    cartesJoueur3.add(cartesADistribuer.get(21));
                    cartesJoueur3.add(cartesADistribuer.get(22));
                    cartesJoueur1.add(cartesADistribuer.get(23));
                    cartesJoueur1.add(cartesADistribuer.get(24));
                    cartesJoueur1.add(cartesADistribuer.get(25));
                    cartesJoueur4.add(cartesADistribuer.get(26));
                    cartesJoueur4.add(cartesADistribuer.get(27));
                    cartesJoueur4.add(cartesADistribuer.get(28));
                    cartesJoueur2.add(cartesADistribuer.get(29));
                    cartesJoueur2.add(cartesADistribuer.get(30));
                    cartesJoueur2.add(cartesADistribuer.get(31));
                    resultat=jouerManche();
               }
            else {
                   System.out.println(donneur.parler("Apparement personne ne prend au Tour 1"));  
                    cartesJoueur2.add(cartesADistribuer.get(20));
                    cartesJoueur2.add(cartesADistribuer.get(21));
                    cartesJoueur2.add(cartesADistribuer.get(22));
                    cartesJoueur3.add(cartesADistribuer.get(23));  
                    cartesJoueur3.add(cartesADistribuer.get(24));
                    cartesJoueur3.add(cartesADistribuer.get(25));
                    cartesJoueur1.add(cartesADistribuer.get(26));
                    cartesJoueur1.add(cartesADistribuer.get(27));
                    cartesJoueur1.add(cartesADistribuer.get(28));
                    cartesJoueur4.add(cartesADistribuer.get(29)); 
                    cartesJoueur4.add(cartesADistribuer.get(30));
                    cartesJoueur4.add(cartesADistribuer.get(31));
                   resultat=jouerManche();
             }
        }

        if (resultat.get(0)>resultat.get(1)){
                System.out.println("Wahou, l'équipe " + equipe1.nomEquipe + " est en tête !");
                }                
        else {
                System.out.println("Wahou, l'équipe " + equipe2.nomEquipe + " est en tête !");
                } 
                }
    
     /** Cette méthode permet de jouer un match.
     * On cherche à faire deux points d'écart pour avoir un gagnant. 
     * @return : Retourne le score qu'a marqué chacune des équipes sous forme d'un tableau
     */
    public int [] jouer () {	
	while (pointsEquipe1 < 2 && pointsEquipe2 < 2) {
            deroulement ();
	}	
	return new int [] {pointsEquipe1, pointsEquipe2};
    }
}