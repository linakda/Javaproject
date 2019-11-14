/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.Scanner;

/**
 * Classe fille de Human
 * possède toute les méthodes et les paramètres de ce même type tel que
 * l'acoolemie, la boissonFavorite, la boissonSecours, le qualificatif, l'addition , le numéro de table
 * @author Théo
 */

public class Client extends Humain{
    float alcoolemie, addition;
    String qualificatif ;
    boolean exclu;
    int numTable;
    Boisson boissonFavorite ;
    Boisson boissonSecours ;
    
    
    /**
     * Constructeur de Client , méthode exécutée à la création de l'objet et permettant l'initialisation de l'objet .
     * @param prenom permettant de stocker le paramétre prénom du Client en question
     * @param nom    permettant de stocker le paramétre nom du Client en question
     * @param sexe   permettant de stocker le paramétre sexe du Client en question
     * @param crie   permettant de stocker le paramétre cri du Client en question
     * @param porteMonnaie permettant de stocker le paramétre porteMonnaie du Client en question
     * @param boisson_favorite permettant de stocker le paramétre boisson_favorite du Client en question
     * @param boisson_secours permettant de stocker le paramétre boisson_secours du Client en question
     */
    
    public Client(String prenom , String nom , String sexe , String crie ,float porteMonnaie, Boisson boisson_favorite , Boisson boisson_secours){
    	super(prenom,nom,sexe,crie,porteMonnaie);
        this.boissonFavorite = boisson_favorite ;
        this.boissonSecours = boisson_secours ;
        this.alcoolemie = 0;  
    	exclu = false;
    	this.addition = 0;
    	this.numTable = -1;
    }
    
    /**
     * Méthode permettant d'ajuster le taux d'alcoolemie 
     * en fonction de la boisson consommée
     * @param consommation 
    */
    public void boire(Boisson consommation){
    	this.alcoolemie += consommation.tauxAlcoolemie;
    }
    
    /**
     *   
     * Méthode permettant d'ajuster l'addition 
     * en fonction de la boisson consommée
     * @param consommation 
     */
    
    public void ajouterAddition(Boisson consommation){
    	this.addition += consommation.prix;
    }
    
    /**
     *   
     * Méthode permettant de régler l'addition 
     * en fonction des boisson consommée
     * et ainsi de réinitialiser cette dernière
    */
    public boolean payer() {
    	if(this.getPorteMonnaie() >= this.addition){
            this.setPorteMonnaie(this.getPorteMonnaie()-this.addition) ;
            this.addition = 0;
            return true;
    	}
    	return false;
    }
    
    /** 
     * Méthode permettant d'ajuster le taux d'alcoolemie 
     * et l'addition
     * en fonction de la boisson à offrir
     * 
     * @param client va permettre de stocker le type Client à qui la boisson favorite va être offerte, et ainsi ajuster son taux d'alcoolemie et l'addition de l'offreur
     */
    public void offrir(Client client) {
	System.out.println(this.parler("Tu veux quoi "+client.getPrenom()));
	System.out.println(client.parler("un verre de  "+client.boissonFavorite));
	client.alcoolemie += client.boissonFavorite.tauxAlcoolemie;
	this.addition += client.boissonFavorite.prix;
    }
    
   /**
    * Méthode permettant l'ajout d'argent utilisé
    * @param total 
    */
    public void ajouterArgent(float total){
    	this.setPorteMonnaie(this.getPorteMonnaie() + total) ;
    }
    /**
     * méthode permettant de renvoyer 
     * textuellement la présentation du sujet
     * @return 
    */
    public String sePresenter() {
    	return "Bonjour, je m'appelle "+this.getNom()+" "+this.getPrenom()+"."; 
    }
    
    
    /**
     * méthode permettant de renvoyer les statistiques du personnage
     * @return les statistiques du personnages à chaque appel de cette dernière 
     */
    @Override
    public String toString(){
        return "Prenom : "+this.getPrenom() +"\n"+ "Nom : "+this.getNom()+"\n"+ "Sexe : "+this.getSexe() +"\n" +"Portemonnaie : "+this.getPorteMonnaie() +" euros \n"+"Crie significatif : "+ this.getCri() + "\n"+ "côte de popularitié : "+this.getPopularite() +" %"+ "\n"+"taux d'alcoolemie : "+ this.alcoolemie +" gramme"+"\n"+"boisson favorite : "+ this.boissonFavorite+ "\n"+ "boisson de secours : "+ this.boissonSecours +"\n" ;     
    }
   
    /**
     * méthode permettant de renvoyer le texte dis par
     * le personnage
     * @param phrase va permettre de stocker les textes pouvant être dit
     * @return un standard d'écriture , soit le prenom et le texte censé être dit par ce dernier
    */
    public String parler(String phrase) {
	   return this.getPrenom() + " : " + phrase;
    }
   
    /**
     * méthode permettant d'ajuster la côte de popularité 
    */
    public void monterCotePopularite(){
       this.setPopularite(this.getPopularite() + 10) ;
    }
}
