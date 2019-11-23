/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.List;
import java.util.LinkedList;
/**
 * @author Lina & Théophile
 */

/**
 * Cette classe possède toutes les méthodes et paramètres d'une table avec
 * la table, ajouter une personne à une table, se lever d'une table. 
 * 
 */

public class Table {
    List<Humain> personne;
    
    /*
     * Cette methode permet d'initialiser une table et d'y ajouter une liste de personne. 
    */
    public Table() {
    	personne = new LinkedList<>();
    }
        
    /**
     * Cette méthode permet de verifier si une personne peut s'asseoir à une table
     * @param personne : Stock l'humain qui va être ajouter à la table
     * @return : Retourne s'il reste de la place dans la table
     */
    public boolean ajouterPersonne(Humain personne) {
        if( this.personne.size() < 4 ) {
            this.personne.add(personne);
            return true;
        }
        return false;
	}
        
    /**
     * Cette méthode permet à un humain de quitter la table
     * @param personne : Stock l'humain qui va se lever de la table
     */
    public void seLever (Humain personne) {
        if(this.personne.remove(personne))
            System.out.println("Vous n'êtes plus assis à cette table");
        else
            System.out.println("Erreur table");
	}
}
