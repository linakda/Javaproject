/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;
/**
 * @author Lina & Théophile
 */

/**
 * Cette classe est la classe de la carte possédant les différentes méthodes et paramètres
 pour censé être une carte, la figure de la carte et une fonction toString. 
 * 
 */

public class Carte {
    String couleur;
    String figure;
    int valeurcarte;
    int valeuratout;
      
    
    /**
     * La méthode carte est exécutée à la création de l'objet et permet l'initialisation de l'objet .
     * @param c : Stock la couleur de la carte.
     * @param v : Stock la figure de la carte en terme de figure.
     * @param vc : Stock la figure de la carte en terme de point.
     * @param va : Stock la figure de la carte comme un atout, en terme de point
     */
        
    public Carte (String c, String v , int vc, int va) {
       couleur = c;
       figure = v;
       valeurcarte = vc ;
       valeuratout = va ;
    }
    
    public int getValeurAtout (){
        return this.valeuratout;
    }
    
    public int getValeurCarte (){
        return this.valeurcarte;
    }
    
    /**
     * Cette méthode permet d'afficher le texte dans la console. 
     * @return la carte (figure et couleur) dans la console
    **/
    @Override
    public String toString () {
	return figure + " de " + couleur;
    }
   
  

}

