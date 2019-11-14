/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 *
 * @author Théo
 */
public class Equipe {
    
    Client joueur1;
    Client joueur2;
    String nomEquipe;

	
    public Equipe (String nom, Client j1, Client j2){
	nomEquipe = nom;
	joueur1 = j1;
	joueur2 = j2;
    }
	
    public String toString () {
	return nomEquipe;
    }
}