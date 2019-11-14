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
public class Patronne extends Humain {
    public Patronne(String prenom , String nom , String sexe , String crie) {
      super(prenom,nom,sexe,crie,1000);
    }
    public void boire(Boisson consommation){
    }
    public boolean payer(){
        return false;
    }
    /**
     * méthode permettant à la patronne de se rpésenter
     * @return 
    */
    public String sePresenter(){
        return "Je suis la patronne";		
    }
    
    /**
     * méthode permettant de recuperer l'argent de la caisse 
     * @param total 
    */
    public void recuperCaisse(float total) {
        this.setPorteMonnaie(this.getPorteMonnaie() + total) ;
    }
    
    /**
     * méthode permettant la validation de l'exclusion d'un client
     * @param a 
    */
    public void exclure(Client a) {
        a.exclu = true;
    }
    
    /**
     * methode permettant à la patronne d'afficher son texte
     * @param phrase
     * @return 
     */
    public String parler(String phrase) {
        return "<Patronne> "+this.getPrenom() + " : " + phrase;
    }
    
    /**
     * méthode permettant à la patronne d'offrir
     * un verre à un client
     * @param client va nous permettre de stocker le client auquek la patronne s'adresse
    */
    public void offrir(Client client) {
        System.out.println(this.parler("Tu veux quoi "+client.getPrenom()));
  	System.out.println(client.parler("un verre de  "+client.boissonFavorite));
  	client.alcoolemie += client.boissonFavorite.tauxAlcoolemie;
    }
    
    /**
     * méthode permettant de payer au fournisseur la commande
     * @param a va permettre de stocker la boisson en rupture de stock qui a été réapprovisionner
     * @param b va permettre de stocker le fournisseur en question
     * @return 
    */
    public float payerFournisseur(Boisson a,Fournisseur b){
        float total = a.prixAchat*5;
    	System.out.println(b.parler("5 verres de "+a.getNom()+" ça fait "+total+" e s'il te plaît"));
    	b.ajouterPorteMonaie(total);
    	this.setPorteMonnaie(this.getPorteMonnaie() - total) ;
    	return total;
    }
}
