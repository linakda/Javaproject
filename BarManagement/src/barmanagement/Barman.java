/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

/**
 * Cette classe est une classe fille de Humain.
 * Elle possède les methodes qui permettent d'initialiser le Barman, parler avec
 * le coco à la fin, payer un verre, offrir un verre, se présenter, la disponibilité des boissons,
 * boire un verre, boire une boisson, commander du stock et recevoir une commande. 
 * @author Théo
 */
public class Barman extends Humain{
    String finDePhrase;
    
    /**
     * Cette méthode est le constructeur de Barman. 
     * Elle est exécutée à la création de l'objet et permettant l'initialisation de l'objet .
     * Ainsi que la fin de phrase avec le coco demandé dans le sujet.
     * @param prenom : Stock le paramétre prénom du Barman en question
     * @param nom    : Stock le paramétre nom du Barman en question
     * @param sexe   : Stock le paramétre sexe du Barman en question
     * @param crie   : Stock le paramétre cri du Barman en question
     */
    public Barman (String prenom , String nom , String sexe , String crie){
        super(prenom,nom,sexe,crie,1000);
	this.finDePhrase = "coco";
    }
    
    /**
     * Cette méthode permet d'afficher le texte du barnan
     * @param phrase : Stock la phrase dans une chaine de caractère. 
     * @return : Retourne la phrase du barman avec une forme commune.
    */
    @Override
    public String parler(String phrase){
        return "<Barman> "+this.getPrenom() +" : "+ phrase + " " + this.finDePhrase;
    }
    
    /**
     * Cette méthode gère le paiement du barman.
     * @return : Retourne un boolean qui dit faux car le barman ne paie pas ses consommations. 
     */
    @Override
    public boolean payer(){
        return false;	
    }

    /**
     * Cette méthode permet de demander au barman d'offir un verre, ainsi que de 
     * réaliser l'action correspondante.
     * @param client : Stock le client à qui le verre est offert l'addition
     *                  et le taux d'alcoolemie sont modifiés en conséquences
    */
    @Override
    public void offrir(Client client){
	System.out.println(this.parler("Tu veux quoi "+client.getPrenom()));
	System.out.println(client.parler("un verre de  "+client.boissonFavorite.getNom()));
	client.alcoolemie += client.boissonFavorite.tauxAlcoolemie;
	this.setPorteMonnaie(this.getPorteMonnaie() - client.boissonFavorite.prix );
    }
    
    /**
     * Cette méthode permet au barman de se présenter. 
     * @return : la fonction parler avec une petite présentation. 
     */
    @Override
    public String sePresenter(){
        return parler("Ici, c'est moi qui gère le bar.");
    }
    
    /**
     * Cette méthode permet de vérifier la disponibilité d'une boisson dans le stock du bar.
     * @param a : Stock le nom de la boisson demander et vérifie si elle est disponible.  
     * @return : Retourne un boolean qui va nous dire si la boisson demandée est disponible.
    */
    public boolean disponible(Boisson a){
        if(a.stock > 0){
            a.stock -= 1;
            return true;
	}
	return false;
    }
    
    /**
     * Cette méthode permet au barman de boire sa boisson. 
     * @param consommation 
     */
    @Override
    public void boire(Boisson consommation) {
        // TODO Auto-generated method stub	
    }
        
    /**
     * Cette méthode permet d'afficher dans la console la commande du barman au 
     * fournisseur concernant la boisson en rupture de stock à commander
     * @param a : Stock la boisson qui est en rupture de stock 
     * @param b : Stock le nom du fournisseur qui va réapprovisionner le stock du bar
    */
    public void commande(Boisson a,Fournisseur b){
	System.out.println(this.parler("Allo (fournisseur)"+b.getPrenom() +" je suis a cours de "+a.getNom()+ ", tu peux m'en livrer "));
    }
    
    /**
     * Cette méthode permet d'afficher la reception de la commande du barman au
     * fournisseur concernant la boisson en rupture de stock à commander
     * @param a : Stock la boisson en rupture de stock 
     * @param b : Stock le fournisseur qui va réapprovisionner le stock du bar
    */
    public void recevoirCommande(Boisson a,Fournisseur b){
	System.out.println(this.parler("Oh "+b.getPrenom() +" t'arrives pile au bon moment, oui c'est bien ça 5 verres de "+a.getNom()));
	a.stock = 5;
    }
}
