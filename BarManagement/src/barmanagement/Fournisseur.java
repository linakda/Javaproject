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
public class Fournisseur extends Humain{

    public Fournisseur(String prenom, String nom, String sexe, String cri) {
	super(prenom, nom, sexe, cri,100);
    }
    
    public void boire(Boisson consommation) {	
    }
    
    
    public boolean payer() {	
	return false;
    }
    
    public void ajouterPorteMonaie(float total){
    	this.setPorteMonnaie(this.getPopularite()+total) ;
    }
    
    public String sePresenter() {
	return getCri();
    }
    
    public String parler(String phrase) {
	return "<Fournisseur> "+this.getPrenom()+" : "+phrase;
    }

    public void offrir(Client client) {

    }
    
    /**
     * méthode permettant d'afficher la commande à livrer
     * @param a va stocker la boisson en question
     * @param b va stocker l'objet barman utilisé
     */
    
    public void commande(Boisson a,Barman b){
	System.out.println(this.parler("Ok "+b.getNom()+" j'arrive de suite avec des verre de "+a.getNom()+"."));
    }
    
    /**
     * méthode permettant d'afficher le texte suite à la livraison du fournisseur
     * @param a va permettre de stocker la boisson à livrer
     * @param b va permettre de stocker le nom du barman à qui la commande va être livré
     */
    public void livrer(Boisson a,Barman b){
	System.out.println(this.parler("Salut "+b.getNom()+" je te ramène ta commande, 5 verre de"+a.getNom() +",c'est ça ?"));
    }
}
