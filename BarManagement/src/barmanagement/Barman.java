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
public class Barman extends Humain{
    String finDePhrase;
    public Barman (String prenom , String nom , String sexe , String crie){
        super(prenom,nom,sexe,crie,1000);
	this.finDePhrase = "coco";
    }
    
    /**
     * Méthode permettant
     * d'afficher le texte du barman
     * @param phrase permet de stocker sous la forme d'une chaîne de caractère 
     * @return   le texte pouvant être dit par le barman selon le cas de figure avec une nomenclature commune
     * 
    */
    public String parler(String phrase){
        return "<Barman> "+this.getPrenom() +" : "+ phrase + " " + this.finDePhrase;
    }
    public boolean payer(){
        return false;	
    }

    /**
     * Méthode permettant de demander
     * au barman d'offir un verre, et de réaliser cette action
     * @param client  Va permettre de stocker l'objet ( client ) à qui le verre est offert
     * l'addition et le taux d'alcoolemie sont modifiés en conséquences
    */
    public void offrir(Client client){
	System.out.println(this.parler("Tu veux quoi "+client.getPrenom()));
	System.out.println(client.parler("un verre de  "+client.boissonFavorite.getNom()));
	client.alcoolemie += client.boissonFavorite.tauxAlcoolemie;
	this.setPorteMonnaie(this.getPorteMonnaie() - client.boissonFavorite.prix );
    }
    public String sePresenter(){
        return finDePhrase;
    }
    /**
     * Méthode permettantde vérifier la disponibilité 
     * d'une boisson dans le stock du bar
     * @param a va permettre de stocker la boisson en question et ainsi vérifier sa quantité 
     * @return  un boolean qui va nous renseigner sur la disponibilité ou non de la boisson 
    */
    public boolean disponible(Boisson a){
        if(a.stock > 0){
            a.stock -= 1;
            return true;
	}
	return false;
    }
    public void boire(Boisson consommation) {
        // TODO Auto-generated method stub	
    }
        
    /**
     * Méthode permettant d'afficher textuellement la commande du barman au fournisseur
     * concernant la boisson en rupture de stock à commander
     * @param a va permettre de stocker la boisson qui est en rupture de stock 
     * @param b va permettre de stocker le fournisseur qui va réapprovisionner le stock du bar
    */
    public void commande(Boisson a,Fournisseur b){
	System.out.println(this.parler("Allo (fournisseur)"+b.getPrenom() +" je suis a cours de "+a.getNom()+ ", tu peux m'en livrer "));
    }
    /**
     * Méthode permettant d'afficher textuellement la commande du barman au fournisseur
     * concernant la boisson en rupture de stock à commander
     * @param a va permettre de stocker la boisson qui est en rupture de stock 
     * @param b va permettre de stocker le fournisseur qui va réapprovisionner le stock du bar
    */
    public void recevoirCommande(Boisson a,Fournisseur b){
	System.out.println(this.parler("Oh "+b.getPrenom() +" t'arrives pile au bon moment, oui c'est bien ça 5 verres de "+a.getNom()));
	a.stock = 5;
    }
}
