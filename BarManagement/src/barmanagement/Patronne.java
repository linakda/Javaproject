/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 * Cette classe est une classe fille de Humain.
 * Elle possède toute les méthodes et les paramètres de ce même type tel que
 * boire, exclure un client, offrir un verre, parler, payer son verre, payer le fournisseur,
 * recuperer la caisse et enfin se présenter. 
 * @author Lina & Théophile
 */
public class Patronne extends Humain {
    boolean nePlusServir = false;
    
    
    /**
     * Cette méthode est le constructeur de la Patronne. 
     * Elle est exécutée à la création de l'objet et permet l'initialisation de l'objet.
     * @param prenom
     * @param nom
     * @param sexe
     * @param crie
     * @param accessoire
    */
    public Patronne(String prenom , String nom , String sexe , String crie, String accessoire) {
      super(prenom,nom,sexe,crie,"bague diamant",1000);
    }
    
    /**
     * Cette méthode permet à la patronne de se pésenter
     * @return : Retourne la phrase que la patronne dit pour se présenter dans la console. 
    */
    @Override
    public String sePresenter(){
        return "Je suis la patronne et c'est mon bar ici.";		
    }    
   
    /**
     * Cette méthode permet à la patronne de boire. 
     * @param consommation : Stock le paramètre de la boisson actuelle. 
    */
    @Override
    public void boire(Boisson consommation){
    }
    
    /**
     * Cette méthode gère le paiement de la Patronne .
     * @return : Retourne un boolean qui dit faux car la patronne ne paie pas ses consommations. 
     */
    @Override
    public boolean payer(){
        return false;
    }
    

    
    /**
     * Cette méthode permet de recuperer l'argent de la caisse.
     * @param total : Stock la valeur a récupéré. 
    */
    public void recuperCaisse(float total) {
        System.out.println(this.parler("Barman ! Vide la caisse dans mon porte-monnaie s'il te plait  ! "));
        this.setPorteMonnaie(this.getPorteMonnaie() + total) ;
    }
    
    /**
     * Cette méthode permet la validation de l'exclusion d'un client du bar.
     * @param clientExclu : Stock le nom de la personne a exclure. 
    */
    public void exclure(Client clientExclu) {
        System.out.println(this.parler("Toi le serveur avec tes gros biscoteaux, met cet homme dehors !"));
        clientExclu.exclu = true;
    }
    
    /**
     * Cette méthode permet à la patronne de dire que les barmans ne servent plus un client trop alcoolisé. 
     * 
     * @param clientAlcoolise : Stock le nom du client a ne plus servir. 
     */
    public void nePlusServir(Client clientAlcoolise){
        System.out.println("Barman et serveurs, ne servez plus " + this.getPrenom() + " s'il vous plait");
        nePlusServir= true;
    }
    
    /**
     * Cette methode permet d'afficher le texte de la patronne
     * @param phrase : Stock la phrase dans une chaine de caractère.
     * @return : Retourne la phrase du barman avec une forme commune.
     */
    @Override
    public String parler(String phrase) {
        return "<Patronne> "+this.getPrenom() + " (" + this.getAccessoire()+ ") : " + phrase;
    }
    
    /**
     * Cette méthode permet à la patronne d'offrir un verre à un client.
     * @param client : Stock le client auquel la patronne offre un verre
    */
    @Override
    public void offrir(Client client) {
        System.out.println(this.parler("Tu veux quoi "+client.getPrenom()));
  	System.out.println(client.parler("un verre de "+client.boissonFavorite));
  	client.alcoolemie += client.boissonFavorite.tauxAlcoolemie;
    }
    
    /**
     * Cette méthode permet de payer le fournisseur.
     * @param boissonRupture : Stock la boisson en rupture de stock qui a été réapprovisionné
     * @param fournisseur  :Stock le fournisseur qui a livré la commande
     * @return : La somme a payer.
    */
    public float payerFournisseur(Boisson boissonRupture,Fournisseur fournisseur){
        float total = boissonRupture.prixAchat*5;
    	System.out.println(fournisseur.parler("5 verres de "+boissonRupture.getNom()+" ça fait "+total+"€ s'il te plaît"));
    	fournisseur.ajouterPorteMonaie(total);
    	this.setPorteMonnaie(this.getPorteMonnaie() - total) ;
    	return total;
    }
}
