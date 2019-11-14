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
public class Serveur extends Humain {
    float tailleBiceps,coeffCharme ;
    public Serveur(String prenom , String nom , String sexe , String crie, float tailleBiceps, float coeffCharme){
    	super(prenom,nom,sexe,crie,100);
        this.tailleBiceps = tailleBiceps ;
        this.coeffCharme = coeffCharme ;
    }
    
    /**
     * méthode permettant de renvoyer les 
     * caractéristiques du serveur en question
     * @return le nom, le prenom, le sexe, le cri significatif, le cri, la côte de popularité
     */
    @Override
    public String toString(){
        return "Prenom : "+this.getPrenom() +"\n"+ "Nom : "+this.getNom()+"\n"+ "Sexe : "+this.getSexe() +"\n"+"Crie significatif : "+ this.getCri() + "\n"+ "côte de popularitié : "+this.getPopularite()+" %"+ "\n" ;     
    }
    
    /**
     * méthode permettant au serveur de s'exprimer
     * @param phrase permet de stocker le texte dit par le serveur
     * @return  le nom du serveur qui parle et le texte en question
     */
    public String parler(String phrase) {
    	return "<Serveur> "+this.getPrenom() + " : " + phrase;
    }
    public boolean payer() {
	return false;
    }
    
    public void offrir(Client client) {	
    }
    
    
    /**
     * méthode permettant au serveur de retourner son cri significatif
     * @return 
     */
    public String sePresenter() {
	return this.getCri();
    }
    
    @Override
    public void boire(Boisson consommation) {
	// TODO Auto-generated method stub
    }   
}