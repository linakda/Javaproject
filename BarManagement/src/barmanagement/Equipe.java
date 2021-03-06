/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 * Cette classe possède toutes les méthodes et paramètres d'une équipe avec
 * l'équipe et toString. 
 * @author Lina & Théophile
 **/
public class Equipe {
    
    Client joueur1;
    Client joueur2;
    String nomEquipe;

    /**
     * Cette méthode est le constructeur d'une équipe. 
     * Elle est exécutée à la création de l'objet et permet l'initialisation de l'objet.
     * @param nom : Stock le nom de l'équipe. 
     * @param j1 : Stock le nom du joueur 1.
     * @param j2 : Stock le nom du joueur 2. 
    */
    public Equipe (String nom, Client j1, Client j2){
	nomEquipe = nom;
	joueur1 = j1;
	joueur2 = j2;
    }
	
    /**
     * Cette méthode permet de retourner le nom d'une équipe.
     * @return : Retourne le nom de l'équipe.
    */
    @Override
    public String toString () {
	return nomEquipe;
    }
}