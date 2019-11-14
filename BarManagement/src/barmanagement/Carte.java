/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.Arrays;

/**
 *
 * @author Théo
 */
/**Classe carte possédant les différentes méthodes et paramètres pour censé être une carte
*et  Represente donc une carte
*/
class Carte {
    String couleur;
    String valeur;
    
    /**
     * Constructeur carte, , méthode exécutée à la création de l'objet et permettant l'initialisation de l'objet .
     * @param c définissant la couleur
     * @param v définissant la valeur
     */
        
    public Carte (String c, String v) {
    	couleur = c;
	valeur = v;
    }
    
    /**
     *Renvoie la force de la carte selon la couleur d'atout
     *@param atout permet de stocker la force d'atout
     */        
    public int getValue (String atout) {
	String [] ordreNormal = new String [] {"7", "8", "9", "Valet", "Dame", "Roi", "10", "As"};
	String [] ordreAtout = new String [] {"7", "8", "Dame", "Roi", "10", "As", "9", "Valet"}; 
		
	if (couleur.equals (atout))
            return Arrays.asList (ordreAtout).indexOf (valeur) + 10; // + 10, parce que les atouts battent toutes les cartes non-atout
        return Arrays.asList (ordreNormal).indexOf (valeur);
    }
	
    public String toString () {
	return valeur + " de " + couleur;
    }
}
