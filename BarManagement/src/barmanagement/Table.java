/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.List;
import java.util.LinkedList;

/**
 * Cette classe possède toute les méthodes et paramètres d'une table avec
 * la table, ajouter une personne à une table, se lever d'une table. 
 * @author Théo
 */

public class Table {
    List<Humain> personne;
    
    /*
     * Cette methode permet d'initialiser une table et d'y ajouter une liste de personne. 
    */
    public Table() {
    	personne = new LinkedList<Humain>();
    }
        
    /**
     * Cette méthode permet de verifier si une personne peut s'asseoir à une table
     * @param a : Stock l'humain qui va être ajouter à la table
     * @return : Retourne s'il reste de la place dans la table
     */
    public boolean ajouterPersonne(Humain a) {
        if( this.personne.size() < 4 ) {
            this.personne.add(a);
            return true;
        }
        return false;
	}
        
        /**
         * Cette méthode permet à un humain de quitter la table
         * @param a : Stock l'humain qui va se lever de la table
         */
	public void seLever (Humain a) {
            if(this.personne.remove(a))
		System.out.println("Vous n'êtes plus assis à cette table");
            else
		System.out.println("Erreur table");
	}
}
