/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.Arrays;

/**
 * Cette classe est la classe de la carte possédant les différentes méthodes et paramètres
 * pour censé être une carte, la valeur de la carte et une fonction toString. 
 * @author Théo
 */

class Carte {
    String couleur;
    String valeur;
    int valeurcarte;
    int valeuratout;
      
    
    /**
     * La méthode carte est exécutée à la création de l'objet et permet l'initialisation de l'objet .
     * @param c : Stock la couleur de la carte.
     * @param v : Stock la valeur de la carte en terme de figure.
     * @param vc : Stock la valeur de la carte en terme de point.
     * @param va : Stock la valeur de la carte comme un atout, en terme de point
     */
        
    public Carte (String c, String v , int vc, int va) {
    	couleur = c;
	valeur = v;
       valeurcarte = vc ;
       valeuratout = va ;
    }
    
    /**
     *Cette méthode renvoie la force de la carte selon la couleur d'atout
     *@param atout : Stock la force d'atout
     */        
    public int getValue (String atout) {
	String [] ordreNormal = new String [] {"7", "8", "9", "Valet", "Dame", "Roi", "10", "As"};
	String [] ordreAtout = new String [] {"7", "8", "Dame", "Roi", "10", "As", "9", "Valet"}; 
        int [] cartecal = new int [] {0,0,0,2,3,4,10,11};
        int [] atoutval = new int [] {0,0,14,20,3,4,10,11};
		
	if (couleur.equals (atout))
            return Arrays.asList (ordreAtout).indexOf (valeur) + 10; // + 10, parce que les atouts battent toutes les cartes non-atout
        return Arrays.asList (ordreNormal).indexOf (valeur);
    }
    
    /*
     * Cette méthode permet d'afficher le texte dans la console. 
    */
    @Override
    public String toString () {
	return valeur + " de " + couleur;
    }
   
  

}

