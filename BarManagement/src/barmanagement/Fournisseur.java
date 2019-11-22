/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 * Cette classe est une classe fille de Humain.
 * Elle possède toute les méthodes et les paramètres de ce même type tel que
 * ajouter au porte-monnaie, boire, commander, livrer, offrir, parler, payer et se présenter. 
 * @author Théo
 */
public class Fournisseur extends Humain{
    
    /*
     * Cette méthode est le constructeur du fournisseur. 
     * Elle est exécutée à la création de l'objet et permet l'initialisation de l'objet. 
    */
    public Fournisseur(String prenom, String nom, String sexe, String accessoire, String cri) {
	super(prenom, nom, sexe, cri, accessoire,100);
    }
    
    /*
     * Cette méthode permet aux fournisseurs de boire. 
     * @param consommation : Stock le paramètre de la boisson actuelle. 
    */
    @Override
    public void boire(Boisson consommation) {	
    }
    
    /**
     * Cette méthode permet aux fournisseurs de payer.
     * @return : Retourne un boolean qui dit faux car les fournisseurs ne paie pas ses consommations. 
     */
    @Override
    public boolean payer() {	
	return false;
    }
    
    /*
     * Cette méthode permet d'ajouter au porte-monnaie le paiement de la patronne. 
    */
    public void ajouterPorteMonaie(float total){
    	this.setPorteMonnaie(this.getPopularite()+total) ;
    }
    
    /**
     * Cette méthode permet de se présenter au fournisseur.
     * @return : Retourne son cri
     */
    @Override
    public String sePresenter() {
	return getCri();
    }
    
    /*
     * Cette méthode permet au fournisseur de parler 
     * @return : Retourne la phrase du fournisseur avec une certaine nomenclature. 
    */
    @Override
    public String parler(String phrase) {
	return "<Fournisseur> "+this.getPrenom()+" ("+ this.getAccessoire() + ") : "+phrase;
    }
    
    /*
     * Cette méthode permet d'offrir un verre a un client. 
    */
    @Override
    public void offrir(Client client) {
    }
    
    /**
     * Cette méthode permet d'afficher la commande à livrer
     * @param boissonLivre : Stock la boisson de la commande
     * @param barman : Stock l'objet barman qui passe la commande. 
     */
    
    public void commande(Boisson boissonLivre,Barman barman){
	System.out.println(this.parler("Ok "+barman.getNom()+" j'arrive de suite avec des verre de "+boissonLivre.getNom()+"."));
    }
    
    /**
     * Cette méthode permet d'afficher le texte suite à la livraison du fournisseur
     * @param boissonLivre : Stock la boisson à livrer
     * @param barman : Stock le nom du barman à qui la commande va être livré
     */
    public void livrer(Boisson boissonLivre,Barman barman){
	System.out.println(this.parler("Salut "+barman.getNom()+" je te ramène ta commande, 5 verre de "+boissonLivre.getNom() +",c'est ça ?"));
    }
}
