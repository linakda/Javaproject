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
 * Cette classee possède les methodes qui permettent d'initialiser les boissons,
 * de l'afficher et d'obtenir le nom. 
 * 
 */
public class Boisson{
    private String nom;
    int stock;
    float prix, prixAchat, tauxAlcoolemie;
    
    /*
     * Cette methode permet de sauvegarder et d'organiser les variables des boissons sans alcool.
    */
    public Boisson(String nom, float prix,float prixAchat){
        this.stock = 5;
        this.nom = nom;
	this.prix = prix;
	this.prixAchat = prixAchat;
	this.tauxAlcoolemie = 0;
    }
    
    /*
     * Cette méthode permet de sauvegarder et d'organiser les variables des boissons avec alcool. 
    */
    public Boisson(String nom, float prix,float prixAchat ,float taux){
        this.stock = 5;
	this.nom = nom;
	this.prix = prix;
	this.prixAchat = prixAchat;
	this.tauxAlcoolemie = taux; 
    }
    
    /**
     * Cette méthode permet de retourner les caractéristiques de la boisson
     * avec l'aide de la fonction pré-défini toString
     * On obtient donc son nom, son prix, son taux d'alcoolemie
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
