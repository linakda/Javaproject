/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author Théo
 */

public class Table {
    List<Humain> personne;
    public Table() {
    	personne = new LinkedList<Humain>();
    }
        
    /**
     * méthode permettant de verifier si une personne 
     * peut s'asseoir à une table
     * @param a va permettre de stocker l'humain qui va être ajouter à la table
     * @return si la condition est vérifié ou non ( boolean )
     */
    public boolean ajouterPersonne(Humain a) {
        if( this.personne.size() < 4 ) {
            this.personne.add(a);
            return true;
        }
        return false;
	}
        
        /**
         * méthode permettant à un humain de quitter la table
         * @param a permettre de stocker l'humain qui va être levé de la table
         */
	public void seLever (Humain a) {
            if(this.personne.remove(a))
		System.out.println("Vous n'êtes plus assis à cette table");
            else
		System.out.println("Erreur table");
	}
}
