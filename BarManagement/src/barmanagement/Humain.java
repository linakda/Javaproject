/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 * Cette classe est la classe mère Humain. 
 * Elle possède les méthodes qui permet d'initialiser un "humain", 
 * C'est de cette méthode que vont découler toute les classes filles
 * @author Théo
 */
public abstract class Humain {

    String nom, accessoire;
    private String prenom, cri, sexe ;
    int popularite = 0 ;
    private float porteMonnaie;
    
    /**
     * Cette methode permet de sauvegarder et d'organiser les variables.
     * @param prenom
     * @param nom
     * @param sexe
     * @param cri
     * @param accessoire
     * @param porteMonnaie
     */
    public Humain(String prenom , String nom , String sexe , String cri, String accessoire, float porteMonnaie) {
       this.nom = nom ;
       this.prenom = prenom ;
       this.cri = cri ;
       this.sexe = sexe ;
       this.porteMonnaie = porteMonnaie;
       this.accessoire=accessoire;
   }
   
   /**
    * Cette méthode permet de vérifier si deux humains sont identiques ou non
    * @param  a : Stock notre type humain
    * @return un boolean permettant de voir si la condition eest vérifié ou non
    */
    @Override
    public boolean equals(Object a){
        Humain b  = (Humain) a;
        if(this.nom.equals(b.nom) && this.prenom.equals(b.prenom) && this.cri.equals(b.cri))
	   return true;
        return false;   
   }
    
   /**
    * Ces methodes vont donner tous les accesseur et mutateurs
    * 
     * @return 
    */
    public String getNom(){
       return this.nom ;
    }
   
    public String getPrenom(){
       return this.prenom ;
    }
    
    public float getPorteMonnaie(){
       return this.porteMonnaie ;
    }
    public int getPopularite(){
       return this.popularite ;
    }
    
    public String getCri(){
       return this.cri ;
    }
    
    public String getAccessoire(){
        return this.accessoire;
    }
    
    public String getSexe(){
       return this.sexe ;
    }
   
    public void setNom(String nom){
       this.nom = nom ;
    }
   
    public void setPrenom(String prenom){
       this.prenom = prenom ;
    }
   
    public void setPorteMonnaie(float porteMonnaie){
       this.porteMonnaie = porteMonnaie ;
    }
   
    public void setPopularite(int popularite){
       this.popularite = popularite ;
    }
   
    public void setCrie(String cri){
       this.cri = cri ;
    }
    
    public void setAccessoire(){
        this.accessoire = accessoire;
    }
   
    public void setSexe(String sexe){
       this.sexe = sexe ;
    }
   
   
   /**
     * Ces méthodes abstraites définissant les méthodes nécessaire à avoir pour être
     * une classe ou une sous-classe de "Humain".
     * @param consommation : Parametre de la premiere fonction. 
    */
   
    abstract public void boire(Boisson consommation);
    abstract public boolean payer();
    abstract public String sePresenter(); 
    abstract public void offrir(Client client); 
    abstract public String parler(String phrase); 
}