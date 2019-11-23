/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;
/**
 * @author Lina & Théophile
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Cette classe est une classe fille de Humain.
 * Elle possède toute les méthodes et les paramètres de ce même type tel que
 * l'acoolemie, la boissonFavorite, la boissonSecours, le qualificatif, l'addition , le numéro de table
 * 
 */

public class Client extends Humain{
    float alcoolemie, addition;
    String qualificatif,accessoire ;
    boolean exclu;
    int numTable, experienceBelote;
    Boisson boissonFavorite, boissonSecours ;
    
    
    /**
     * Cette méthode est le constructeur de Client. 
     * Elle est exécutée à la création de l'objet et permet l'initialisation de l'objet .
     * @param prenom : Stock le paramétre prénom du Client en question
     * @param nom    : Stock le paramétre nom du Client en question
     * @param sexe   : Stock le paramétre sexe du Client en question
     * @param crie   : Stock le paramétre cri du Client en question
     * @param accessoire : Stock le paramétre accessoire du Client en question
     * @param porteMonnaie : Stock le paramétre porteMonnaie du Client en question
     * @param popularite : Stock le parametre popularite du Client en question
     * @param experienceBelote : Stock le paramétre experienceBelote du Client en question
     * @param boisson_favorite : Stock le paramétre boisson_favorite du Client en question
     * @param boisson_secours : Stock le paramétre boisson_secours du Client en question
    */
    public Client(String prenom , String nom , String sexe , String crie, String accessoire  ,float porteMonnaie, int popularite,int experienceBelote, Boisson boisson_favorite , Boisson boisson_secours){
    	super(prenom,nom,sexe,crie, accessoire ,porteMonnaie);
        this.boissonFavorite = boisson_favorite ;
        this.boissonSecours = boisson_secours ;
        this.alcoolemie = 0;  
    	exclu = false;
    	this.addition = 0;
    	this.numTable = -1;
        this.popularite=0;
        this.experienceBelote=0;
        this.accessoire = accessoire; 
    }
    
    /**
     * Cette méthode permet d'ajuster le taux d'alcoolemie en fonction de la boisson consommée
     * @param consommation : Stock le paramètre de la boisson actuelle. 
    */
    @Override
    public void boire(Boisson consommation){
    	this.alcoolemie += consommation.tauxAlcoolemie;
        monterCotePopularite(1);
    }
    
    /**
     * Cette méthode permet d'ajouter la consommation à l'addition du client
     * @param consommation : Stock le paramètre de la boisson actuelle.
     */
 
    public void ajouterAddition(Boisson consommation){
    	this.addition += consommation.prix;
    }
    
    /**
     * Cette méthode permet de régler l'ardoise du client en fonction des
     * boissons qu'il a commandé. L'ardoise sera ainsi remise à 0. 
     * @return 
    */
    @Override
    public boolean payer() {
    	if(this.getPorteMonnaie() >= this.addition){
            this.setPorteMonnaie(this.getPorteMonnaie()-this.addition) ;
            this.addition = 0;
            return true;
    	}
    	return false;
    }
    
    /** 
     * Cette méthode permet d'ajuster le taux d'alcoolemie et l'addition
     * en fonction de la boisson à offrir
     * @param client : Stock le Client à qui la boisson est offerte
     */
    @Override
    public void offrir(Client client) {
	System.out.println(this.parler("Tu veux quoi "+client.getPrenom()));
	System.out.println(client.parler("un verre de  "+client.boissonFavorite.getNom()));
	this.addition += client.boissonFavorite.prix;
    }
    
   /**
    * Cette méthode permet l'ajout d'argent au porte monnaie
    * @param total : Stock l'argent a ajouter à l'utilisateur. 
    */
    public void ajouterArgent(float total){
    	this.setPorteMonnaie(this.getPorteMonnaie() + total) ;
    }
    
    /**
     * Cette méthode permet de renvoyer dans la console la présentation de l'utilisateur. 
     * @return : Le texte de la présentation. 
    */
    @Override
    public String sePresenter() {
    	return "<Client> " + this.getPrenom() + " " + this.getAccessoire() + " : " + " Bonjour, je m'appelle "+this.getNom()+" "+this.getPrenom()+"."; 
    }
    
    /**
     * Cette méthode permet de renvoyer dans la console la présentation de l'utilisateur. 
     * @return : Le texte de la présentation secondaire. 
     */
    public String sePresenterComplementaire(){
        return this.getCri()+" c'est moi "+this.getPrenom();
    }
    
    /**
     * Cette méthode permet de renvoyer les statistiques du personnage.
     * @return : les statistiques du personnages dans la console. 
     */
    @Override
    public String toString(){
        return "Prenom : "+this.getPrenom() +"\n"+ "Nom : "+this.getNom()+"\n"+ "Sexe : "+this.getSexe() +"\n" +"Portemonnaie : "+this.getPorteMonnaie() +" euros \n"+"Crie significatif : "+ this.getCri() + "\n"+ "côte de popularitié : "+this.getPopularite() +" %"+ "\n"+"taux d'alcoolemie : "+ this.alcoolemie +" gramme"+"\n"+"boisson favorite : "+ this.boissonFavorite+ "\n"+ "boisson de secours : "+ this.boissonSecours +"\n" ;     
    }
   
    /**
     * Cette méthode permet de renvoyer le texte du client. 
     * @param phrase : Stock la phrase qui va etre dite. 
     * @return : Retourne la phrase avec une certaine nomenclature. 
    */
    @Override
    public String parler(String phrase) {
	return "<Client> " + this.getPrenom() +" ("+ this.getAccessoire() + ") : " + phrase;
    }
   
    public void changerSexe(){
        String perso = this.getSexe();
        if ("homme".equals(perso)){
            this.setSexe("femme");
        } else {
            this.setSexe("homme");
        } 
    }
    
    /**
     * Cette méthode permet d'ajuster la côte de popularité.
     * @param gain : Stock le gain de popularité a ajouter. 
    */
    public void monterCotePopularite(int gain){
       this.popularite = gain + this.popularite;
    }
    
    /**
     * Cette méthode permet d'ajuster la côte de popularité.
     * @param perte : Stock la perte de popularité a ajouter. 
    */
    public void baisserCotePopularite(int perte){
        this.popularite = perte + this.popularite;
    }
    
    /*
     *Cette méthode permet de monter le niveau de belotee d'un joueur. 
    */
    public void monterNiveauBelote(int gain){
        this.experienceBelote = gain + this.experienceBelote;
    }
    
    /*
     * Cette méthode permet de sauvegarder la progression du joueur.
    */
    public void sauvegarder(){
    File file = new File("Sauvegarde_de_votre_dernière_partie.txt");
    FileWriter fw;
    try {
        fw = new FileWriter(file);
        String str ;
        str = "Prenom : "+this.getPrenom() +"\n"+ "Nom : "+this.getNom()+"\n"+ "Sexe : "+this.getSexe() +"\n" +"Portemonnaie : "+this.getPorteMonnaie() +" euros \n"+"Crie significatif : "+ this.getCri() + "\n"+ "côte de popularitié : "+this.getPopularite() + "\n"+"taux d'alcoolemie : "+this.alcoolemie+" gramme" + "\n" + "Accessoire : "+ this.accessoire + "\n" + "Niveau d'experience en belote : " + this.experienceBelote + "\n \n"+"boisson favorite - "+ this.boissonFavorite+ "\n"+ "Boisson de secours - "+ this.boissonSecours +"\n";
        fw.write(str);
        fw.close();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}
