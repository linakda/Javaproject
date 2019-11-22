/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 * Cette classe est une classe fille de Humain.
 * Elle possède toute les méthodes et les paramètres de ce même type tel que
 * boire, offrir un verre, parler, payer, se présenter et un toString. 
 * @author Lina & Théophile
 */
public class Serveur extends Humain {
    float tailleBiceps,coeffCharme;
    String accessoire;
    int experienceBelote;
    
    /**
     * Cette méthode est le constructeur du serveur. 
     * Elle est exécutée à la création de l'objet et permet l'initialisation de l'objet.
     * @param prenom
     * @param nom
     * @param sexe
     * @param crie
     * @param accessoire
     * @param tailleBiceps
     * @param coeffCharme
     * @param experienceBelote
    */
    public Serveur(String prenom , String nom , String sexe , String crie, String accessoire, float tailleBiceps, float coeffCharme, int experienceBelote){
    	super(prenom,nom,sexe,crie,accessoire,100);
        this.tailleBiceps = tailleBiceps ;
        this.coeffCharme = coeffCharme ;
        this.accessoire = accessoire;
        this.experienceBelote = 0;
    }
    
    /**
     * Cette méthode permet de renvoyer les caractéristiques du serveur.
     * @return : Retourne le nom, le prenom, le sexe, le cri significatif, le cri, la côte de popularité.
     */
    @Override
    public String toString(){
        return "Prenom : "+this.getPrenom() +"\n"+ "Nom : "+this.getNom()+"\n"+ "Sexe : "+this.getSexe() +"\n"+"Crie significatif : "+ this.getCri() + "\n"+ "côte de popularitié : "+this.getPopularite()+" %"+ "\n" ;     
    }
    
    /**
     * Cette méthode permet au serveur de s'exprimer dans la console. 
     * @param phrase : Stock le texte du serveur
     * @return : Retourne la phrase du serveur avec une certaine nomenclature. 
     */
    @Override
    public String parler(String phrase) {
    	return "<Serveur> "+this.getPrenom() + " (" + this.getAccessoire()+ ") : " + phrase;
    }
    
    /**
     * Cette méthode permet aux serveurs de payer.
     * @return : Retourne un boolean qui dit faux car les serveurs ne paie pas ses consommations. 
    */
    @Override
    public boolean payer() {
	return false;
    }
    
    /*µ
     * Cette méthode permet aux serveurs d'offrir un verre à un client.
     * @param client : Stock le client auquel la patronne offre un verre
    */
    @Override
    public void offrir(Client client) {	
        System.out.println(this.parler("Tu veux quoi "+client.getPrenom()));
  	System.out.println(client.parler("un verre de "+client.boissonFavorite));
  	client.alcoolemie += client.boissonFavorite.tauxAlcoolemie;
    }
    
    
    /**
     * Cette méthode permet au serveur de se presenter.
     * @return : Retourne son cri significatif.
     */
    @Override
    public String sePresenter() {
	return this.getCri();
    }
    
    /**
     * Cette méthode permet aux serveurs de boire. 
     * @param consommation : Stock le paramètre de la boisson actuelle. 
    */
    @Override
    public void boire(Boisson consommation) {
	Boisson boissonLegal = new Boisson("Eau", 0f,0f);
        if ( consommation != boissonLegal ){
            this.parler("Désolé je ne peux pas boire ça, je ne bois que de l'eau pendant mon service et le jus d'orange me tourne à la tete ...");
        }
        else{
            this.parler("Cette eau est trop bonne !");
        }
    }   
}