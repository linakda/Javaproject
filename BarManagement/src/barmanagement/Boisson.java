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
public class Boisson{
    private String nom;
    int stock;
    float prix;
    float prixAchat;
    float tauxAlcoolemie ;
    
    public Boisson(String nom, float prix,float prixAchat){
        this.stock = 5;
        this.nom = nom;
	this.prix = prix;
	this.prixAchat = prixAchat;
	this.tauxAlcoolemie = 0;
    }
    
    public Boisson(String nom, float prix,float prixAchat ,float taux){
        this.stock = 5;
	this.nom = nom;
	this.prix = prix;
	this.prixAchat = prixAchat;
	this.tauxAlcoolemie = taux; 
    }
    
    /**
     * Méthode permettant de retourner les caractéristiques de la boisson
     * à l'aide de la fonction pré-défini toString
     * notamment son nom, son prix, son taux d'alcoolemie
     * @return 
    */
    @Override
    public String toString(){
	return "Nom de la boisson : " + this.nom + "\nPrix de la boisson : "+this.prix+"\nTaux d'alcoolemie de la boisson : "+ this.tauxAlcoolemie + "\n";
    }

    public String getNom(){
        return this.nom ;
    }
}
