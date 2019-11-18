/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

/**
 * @author Lina & Théophile
 */


/**
 * Cette classe est la classe ????????.
 * Elle possède les méthodes qui permettent : 
 * - de créer : un joueur, les fournisseurs, la patronne, le barman, les boissons
 * (avec ou sans alcool), les clients (joueurs ou non), les serveurs.
 * Le tout avec l'option d'une création manuelle ou automatique.
 * Enfin, elle possède aussi les méthodes pour une présentation complémentaire,
 * pour passer une commande à un serveur ou au barman et une méthode de gestion 
 * de l'interface utilisateur. 
 * Bien sûr, les exceptions sont gérées. 
 * 
 */
public final class Bar {
    int nbTables;
    float caisse;
    Scanner sc;
    List<Serveur> serveurs;
    List<Table> tables;
    List<Client> clients;
    List<Boisson> boissons;
    String name ;
    Client joueur;
    Patronne patronne ;
    Barman barman;
    Fournisseur fournisseur;

    
    
     /**
    * Constructeur de la classe Bar.
    * Il permet de déclarer et d'initialiser les données membres de la classe.
    */
    public Bar(){
    	
        int j = -1;
    	sc = new Scanner(System.in);
    	boissons = new LinkedList<>();
    	serveurs = new LinkedList<>();
        clients = new LinkedList<>();
        this.caisse = 100;
        this.nbTables = 4;
      
    	creationPatronne();
        creationFournisseur();
        creationBarman();
    	creationBoisson();  
        creationClientPnj();
    	creationServeur();
    	creationJoueur();
        
    	this.name = "Chez " + this.patronne.getNom();
        this.clients.add(this.joueur);
    	tables = new LinkedList<>();
        for( int i = 0 ; i < nbTables ; i++ )
            tables.add(new Table());
        
        for( int i = 0 ; i < clients.size()-1 ; i++ ){
            if(i%4 == 0)
        	j++;
            if( j >= tables.size() ){
        	tables.add(new Table());
            }
        this.tables.get(j).ajouterPersonne(clients.get(i));
        }
    }

     /**
     * Cette methode permet de créer son propre joueur.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenpm, le cri significatif, l'argent dans le porte monnaie,
     * le sexe, la boisson fav1 et la boisson fav2.
    */
    public void creationJoueur(){
        String name,surname,cri,sexe,num, accessoire;
        int indiceBoissonFavorite = 0,indiceBoissonSecour = 0, popularite = 0, experienceBelote = 0;
        float porteMonnaie = 0;
        boolean numBoissonFail = true;

        System.out.println("--------------------------------------");
        System.out.println("Création de votre joueur");
        System.out.println("--------------------------------------");
        System.out.print("Nom : ");  
        surname = sc.nextLine();
        System.out.print("\nPrénom : ");
        name = sc.nextLine();
        System.out.print("\nCri significatif : ");
        cri = sc.nextLine();
        System.out.println("Choissiez un accessoire pour le client");
        System.out.println("Il est d'usage que les hommes ont un t-shirt de couleur et que les femmes ont des bijoux.");
        accessoire = sc.nextLine();
        boolean conversionFail = true;
        while(conversionFail){
            try
            {
                conversionFail = false;
                System.out.println("Combien d'argent a "+name +" dans son porte monnaie ?");
                porteMonnaie = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
                conversionFail = true;
                System.out.println("Entre un float s'il vous plaît ");
                    }
            }
            System.out.print("\nSexe(homme/femme) : ");
            sexe = sc.nextLine();
            while(!sexe.equals("homme") && !sexe.equals("femme")){
                System.out.print("saisisser homme ou femme");
                sexe = sc.nextLine();
            }
            while(numBoissonFail){
                    try{
                        System.out.println("Choissiez votre boisson favorite(Saisissez le numero correspondant)");
                        carte ();
                        num = sc.nextLine();
                        indiceBoissonFavorite = Integer.parseInt(num);
                        while(indiceBoissonFavorite < 0 || indiceBoissonFavorite >= boissons.size() ){
                            System.out.println("Ce numero ne correspond a aucune boisson disponible dans le bar,\n veuillez saisir un numéro compris entre 0 et " + (boissons.size()-1));
                            num = sc.nextLine();
                            indiceBoissonFavorite = Integer.parseInt(num);
                        }
                        numBoissonFail = false;
                    }
                    catch(NumberFormatException e){
                        System.out.println("Saissisez un entier s'il vous plaît.");
                        numBoissonFail = true;
                    }
            }
            numBoissonFail = true;
            while(numBoissonFail){
                try{
                    System.out.println("Choissiez votre boisson de secours(Saisissez le numero correspondant)");
                    carte ();
                    num = sc.nextLine();
                    indiceBoissonSecour = Integer.parseInt(num);
                    while(indiceBoissonSecour < 0 || indiceBoissonSecour >= boissons.size() ){
                        System.out.println("Ce numero ne correspond a aucune boisson disponible dans le bar,\n veuillez saisir un numéro compris entre 0 et " + (boissons.size()-1));
                        num = sc.nextLine();
                        indiceBoissonSecour = Integer.parseInt(num);
                    }
                    numBoissonFail = false;
                }
                catch(NumberFormatException e){
                    System.out.println("Saissisez un entier s'il vous plaît.");
                    numBoissonFail = true;
                }
            }
            joueur = new Client(name ,surname,sexe ,cri, accessoire ,porteMonnaie, popularite, experienceBelote, boissons.get(indiceBoissonFavorite),boissons.get(indiceBoissonSecour));
    }
    
    /**
     * Cette methode permet de créer la Patronne sois-même.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, et son cri significatif.
     */
    public void creationPatronne(){
    	System.out.println("--------------------------------------");
    	System.out.println("Création de la patronne");
    	System.out.println("--------------------------------------");
    	String nom,prenom,cri;
    	System.out.println("Nom de la patronne");
    	nom = sc.nextLine();
    	System.out.println("Prénom de la patronne");
    	prenom = sc.nextLine();
    	System.out.println("Cri de la patronne");
    	cri = sc.nextLine();
    	patronne = new Patronne(prenom,nom,"femme",cri,"bague diamant");
    }
    
    /**
     * Cette methode permet de créer le fournisseur sois-même.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, et son cri significatif.
     */
    public void creationFournisseur(){
    	System.out.println("--------------------------------------");
    	System.out.println("Création du fournisseur");
    	System.out.println("--------------------------------------");
    	String nom,prenom,cri;
    	System.out.println("Nom du fournisseur");
    	nom = sc.nextLine();
    	System.out.println("Prénom du fournisseur");
    	prenom = sc.nextLine();
    	System.out.println("Cri du fournisseur");
    	cri = sc.nextLine();
    	fournisseur = new Fournisseur(prenom,nom,"homme",cri,"T-shirt livraison rapido");
    }
    
    /**
     * Cette methode permet de créer le Barman sois-même.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, et son cri significatif.
     */
    public void creationBarman(){
    	System.out.println("--------------------------------------");
    	System.out.println("Création du barman");
    	System.out.println("--------------------------------------");
    	String nom,prenom,cri;
    	System.out.println("Nom du Barman");
    	nom = sc.nextLine();
    	System.out.println("Prénom du Barman");
    	prenom = sc.nextLine();
    	System.out.println("Cri du Barman");
    	cri = sc.nextLine();
    	barman = new Barman(prenom,nom,"homme",cri,"Serviette de bar");
    }
    
     /**
     * Cette methode demande à l'utilisateur de renseigner la manière dont il veut
     * que les boissons soient créees. Deux options s'offrent à lui, automatique
     * ou manuelle.
     */
    public void creationBoisson(){
        boolean continu = true;
    	String reponse;
    	System.out.println("Nous allons commencer par créér les boissons");
    	System.out.println("Voulez-vous que les boissons se créer automatiquement ?(o/n)");
	reponse = sc.nextLine();
	System.out.println(reponse);
	while(!reponse.equals("o") && !reponse.equals("n")){
            System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
            reponse = sc.nextLine();
	}
	if(reponse.equals("o")){
            creationBoissonParDefaut();
	}
        else{
            creationBoissonParDefaut();
            System.out.println("Voici les boissons déja disponible dans le bar");
            carte ();
        while(continu){
            System.out.println("Vous voulez créér une boisson sans alcool ? (o/n)");
	    reponse = sc.nextLine();
	    while(!reponse.equals("o") && !reponse.equals("n")){
	    	System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
	    	reponse = sc.nextLine();
	    }
	    if(reponse.equals("o"))
	    	creationBoissonSansAlcool();
	    else
	    	creationBoissonAvecAlcool();
	    	System.out.println("Voulez-vous créer une autre boisson ?(o/n)");
	    	reponse = sc.nextLine();
	    	while(!reponse.equals("o") && !reponse.equals("n")){
                    System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
                    reponse = sc.nextLine();
                }
                if(reponse.equals("n"))
                    continu = false;
	}
        }   
    }
    
    /**
     * Cette methode permet de créer le(s) boisson(s) sans alcool sois-même, en
     * l'ajoutantà la liste des boissons. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prix, et le prix d'achat.
     */
    public void creationBoissonSansAlcool(){
        float prix = 0,prixAchat = 0;
    	String name;
    	System.out.println("Quel est le nom de votre boisson ?");
    	name = sc.nextLine();
    	boolean conversionFail = true;
    	while(conversionFail){
            try{
        	conversionFail = false;
    		System.out.println("Quel est le prix de votre boisson ?");
        	prix = Float.parseFloat(sc.nextLine());
        	}
            catch(NumberFormatException e){
        	conversionFail = true;
        	System.out.println("Entre un float s'il vous plaît ");
            }
    	}
    	conversionFail = true;
    	while(conversionFail){
            try{
        	conversionFail = false;
    		System.out.println("Quel est le prix d'achat de votre boisson au prés du fournisseur?");
        	prixAchat = Float.parseFloat(sc.nextLine());
        	}
            catch(NumberFormatException e){
        	conversionFail = true;
        	System.out.println("Entre un float s'il vous plaît ");
            }
    	}
    	boissons.add(new Boisson(name,prix,prixAchat));
    }
    
     /**
     * Cette methode permet de créer le(s) boisson(s) alcoolisées sois-même, en
     * l'ajoutantà la liste des boissons. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prix, le prix d'achat et le taux d'alcoolémie.
     * 
     */
    public void creationBoissonAvecAlcool(){
        float prix = 0,prixAchat = 0, tauxAlcoolemie = 0;
    	String name;
    	boolean conversionFail = true;
    	System.out.println("Quel est le nom de votre boisson ?");
    	name = sc.nextLine();
    	while(conversionFail){
            try{
                conversionFail = false;
                System.out.println("Quel est le prix de votre boisson ?");
                prix = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
        	conversionFail = true;
        	System.out.println("Entre un float s'il vous plaît ");
            }
    	}
    	conversionFail = true;
    	while(conversionFail){
            try{
                conversionFail = false;
                System.out.println("Quel est le prix d'achat de votre boisson au prés du fournisseur?");
                prixAchat = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
                conversionFail = true;
                System.out.println("Entre un float s'il vous plaît ");
            }
    	}
    	conversionFail = true;
    	while(conversionFail){
            try{
        	conversionFail = false;
    		System.out.println("Quel est le taux d'alccolemie de votre boisson ?");
        	   tauxAlcoolemie = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
        	conversionFail = true;
        	System.out.println("Entre un float s'il vous plaît ");
            }
    	}
    	boissons.add(new Boisson(name,prix,tauxAlcoolemie));
    }
    
    /**
     * Cette methode permet de créer les boissons de manière automatique en 
     * les ajoutant à la liste des boissons. Leurs caractéristiques sont donc 
     * prédéfinies comme suit : nom, prix, prix achat, et taux d'alcoolemie.
     */
    public void creationBoissonParDefaut(){
    	boissons.add( new Boisson ("Mojito", 5f ,2f, 4.5f ));
    	boissons.add( new Boisson ("Vodka", 7.5f ,3f, 5f ));
    	boissons.add( new Boisson ("Bière", 2.5f ,2f, 4f) );
    	boissons.add( new Boisson ("Absinthe", 3f ,1f, 3.5f));
    	boissons.add( new Boisson ("Monaco", 3.5f ,2f ,3.5f) );
    	boissons.add( new Boisson ("Malibu", 2.5f, 1f, 3f) );
    	boissons.add( new Boisson ("Ricard", 3.5f ,2f, 2.5f) );
    	boissons.add( new Boisson("Eau", 0f,0f) );
    	boissons.add( new Boisson("Virgin Mojito", 4f,2f) );
    	boissons.add( new Boisson("Chocolat chaud",  3.5f,1.f) );
    	boissons.add( new Boisson("Sirop de Peche/Abricot", 3f,1.5f) );
    }
    
     /**
     * Cette methode demande à l'utilisateur de renseigner la manière dont il veut
     * que les clients soient crées. Deux options s'offrent à lui, automatique
     * ou manuelle.
     */
    public void creationClientPnj() {
        String reponse;
        boolean continu = true;
    	System.out.println("Nous allons maintenant créér les clients");
    	System.out.println("Voulez-vous que les clients se crééent automatiquement?(o/n)");
        reponse = sc.nextLine();
    	while(!reponse.equals("o") && !reponse.equals("n")){
            System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
            reponse = sc.nextLine();
	}
	if(reponse.equals("o") ){
            creationClientPnjDefaut();
            continu = false;
	}
        else{
            creationClientPnjDefaut();
	while(continu){
            creationClient();
	    System.out.println("Voulez-vous créer un autre client ?(o/n)");
	    reponse = sc.nextLine();
	    while(!reponse.equals("o") && !reponse.equals("n")){
	    	System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
	    	reponse = sc.nextLine();
	    }
	    if(reponse.equals("n"))
	    	continu = false;
	    }
	}
    }
    
    /**
     * Cette methode permet de créer le(s) client(s) sois-même, en l'ajoutant
     * à la liste des clients. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, le cri significatif, le sexe, l'argent du porte-monnaie
     * la boisson fav1 et la boisson fav2. 
    */
    public void creationClient(){
    	String name,surname,cri,sexe,num,accessoire,exp;
    	float porteMonnaie = 0;
    	int indiceBoissonFavorite = 0,indiceBoissonSecour = 0, popularite = 0, experienceBelote = 0;
    	
    	System.out.print("Nom : ");
    	surname = sc.nextLine();
    	System.out.print("\nPrénom : ");
    	name = sc.nextLine();
    	System.out.print("\nCri significatif : ");
    	cri = sc.nextLine();
        System.out.println("Choissiez un accessoire pour le client");
        System.out.println("Il est d'usage que les hommes ont un t-shirt de couleur et que les femmes ont des bijoux.");
        accessoire = sc.nextLine();
        boolean numExpFail = true;
    	while(numExpFail){
            try{
        	System.out.println("Niveau d'experience à la Belote : (entre 1 et 10)");
            	exp = sc.nextLine();
                experienceBelote = Integer.parseInt(exp);
        	while(experienceBelote < 0 || experienceBelote > 10){
                    System.out.println("Ce numero ne correspond pas à un niveau correct,\n veuillez saisir un numéro compris entre 0 et ");
                    exp = sc.nextLine();
                    experienceBelote = Integer.parseInt(exp);
        	}
        	numExpFail = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Saissisez un entier entre 1 et 10 s'il vous plaît");
        	numExpFail = true;
            }
    	}
    	boolean conversionFail = true;
    	while(conversionFail){
            try
            {
        	conversionFail = false;
    		System.out.println("Combien d'argent a "+name +" dans son porte monnaie ?");
        	porteMonnaie = Float.parseFloat(sc.nextLine());
            }catch(NumberFormatException e){
        	conversionFail = true;
        	System.out.println("Entre un float s'il vous plaît ");
            }
    	}
    	System.out.print("\nSexe(homme/femme) : ");
    	sexe = sc.nextLine();
    	while(!sexe.equals("homme") && !sexe.equals("femme")){
            System.out.print("saisisser homme ou femme");
            sexe = sc.nextLine();
    	}
        boolean numBoissonFail = true;
    	while(numBoissonFail){
            try{
        	System.out.println("Choissiez votre boisson favorite(Saisissez le numero correspondant)");
                carte ();
            	num = sc.nextLine();
        	indiceBoissonFavorite = Integer.parseInt(num);
        	while(indiceBoissonFavorite < 0 || indiceBoissonFavorite >= boissons.size() ){
                    System.out.println("Ce numero ne correspond a aucune boisson disponible dans le bar,\n veuillez saisir un numéro compris entre 0 et " + boissons.size());
                    num = sc.nextLine();
                    indiceBoissonFavorite = Integer.parseInt(num);
        	}
        	numBoissonFail = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Saissisez un entier s'il vous plaît.");
        	numBoissonFail = true;
            }
    	}
    	numBoissonFail = true;
    	while(numBoissonFail){
            try{
                System.out.println("Choissiez votre boisson de secours(Saisissez le numero correspondant)");
        	carte ();
            	num = sc.nextLine();
            	indiceBoissonSecour = Integer.parseInt(num);
                while(indiceBoissonSecour < 0 || indiceBoissonSecour >= boissons.size() ){
                    System.out.println("Ce numero ne correspond a aucune boisson disponible dans le bar,\n veuillez saisir un numéro compris entre 0 et " + (boissons.size()-1));
                    num = sc.nextLine();
                    indiceBoissonSecour = Integer.parseInt(num);
        	}
        	numBoissonFail = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Saissisez un entier s'il vous plaît.");
        	numBoissonFail = true;
            }
    	}
    	clients.add(new Client(name ,surname,sexe ,cri, accessoire,porteMonnaie,popularite, experienceBelote,boissons.get(indiceBoissonFavorite),boissons.get(indiceBoissonSecour)));
    }
    /** 
     * Cette methode permet de créer les clients du bar de manière automatique en 
     *les ajoutant à la liste des clients. Leurs caractéristiques sont donc prédéfinies
     *comme suit : prenom, nom, sexe, cri significatif, porte-monnaie, boisson fav1 
     * et boisson fav2. 
     */
    public void creationClientPnjDefaut(){
        clients.add( new Client("Etienne","Durand", "homme" , "Youpiii","T-shirt bleu",20,5,0, boissons.get(0) , boissons.get(1)) );
        clients.add( new Client("Jeanne","Delacroix" ,"femme","Yes ,we can","Collier",20,2,0, boissons.get(1),boissons.get(0)) );
        clients.add( new Client("Pierre","Edouard", "homme", "Un Ricard n'a jamais tuÃ© personne !","T-shirt noir",20,1,1, boissons.get(6),boissons.get(5)) );
        clients.add( new Client("Marie","Elisabeth","femme","Jamais 2 sans 3","Bague",20,0,1,boissons.get(4),boissons.get(8)) );
        clients.add( new Client("Paul","Romarin","homme","Jamais 3 sans 4","T-shirt rouge",20,0,0, boissons.get(3), boissons.get(8)) );
        clients.add( new Client("France", "Gall", "femme", "Poupé de cirree et de sonnn","Boucles d'oreilles",20,0,1, boissons.get(9),boissons.get(2)) );
    }
    
    /** 
     * Cette methode demande à l'utilisateur de renseigner la manière dont il veut
     * que les serveurs soient crées. Deux options s'offrent à lui, automatique
     * ou manuelle.
     */
    public void creationServeur(){
    	String reponse;
    	boolean continu = true;
    	System.out.println("Nous allons maintenant créér les serveurs");
    	System.out.println("Voulez-vous que les serveurs se crééent automatiquement?(o/n)");
        reponse = sc.nextLine();
            while(!reponse.equals("o") && !reponse.equals("n")){
		System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
		reponse = sc.nextLine();
            }
            if(reponse.equals("o") ){
		creationServeurParDefaut();
		continu = false;
            }
            else{
	    	while(continu){
                    creationServeurPersonalise();
                    System.out.println("Voulez-vous créer un autre serveur ?(o/n)");
                    reponse = sc.nextLine();
                    while(!reponse.equals("o") && !reponse.equals("n")){
	    		System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
	    		reponse = sc.nextLine();
                    }
                    if(reponse.equals("n"))
	    		continu = false;
            }
            }
    }
    
    /** 
     * Cette methode permet de créer le(s) serveur(s) sois-même, en l'ajoutant
     * à la liste des serveurs. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, le cri, le sexe, la taille du biceps ou le charme
     */
    public void creationServeurPersonalise(){
        float tailleBiceps = 0,charme = 0;
    	String nom,prenom,cri,sexe,accessoire, exp;
        int experienceBelote = 0;
    	boolean numFail = true;
    	System.out.print("Nom : ");
    	nom = sc.nextLine();
    	System.out.print("\nPrénom : ");
    	prenom = sc.nextLine();
    	System.out.print("\nCri significatif : ");
    	cri = sc.nextLine();
    	System.out.print("\nSexe(homme/femme) : ");
    	sexe = sc.nextLine();
    	while(!sexe.equals("homme") && !sexe.equals("femme")){
            System.out.print("Saisissez homme ou femme");
            sexe = sc.nextLine();
    	}
        if (sexe.equals("homme")){
            accessoire = "cravate noire";
        }
        else{
            accessoire = "petite queue playboy";
        }
    	while(numFail){
            try{
    		System.out.print("Entrer la taille du biceps");
    		tailleBiceps = Float.parseFloat(sc.nextLine());
    		if(tailleBiceps < 0 ){
                    numFail = true;
                    System.out.println("Entrer une taille positive s'il vous plaît");
    		}
    		else
                    numFail = false; 
            }catch(NumberFormatException e){
    		numFail = true;
    		System.out.println("Saisisser un float s'il vous plait");
            }	
    	}
    	numFail = true;
    	while(numFail){
            try{
    		System.out.print("Entrer le coefficent de charme");
    		charme = Float.parseFloat(sc.nextLine());
    		if(charme < 0 ){
                    numFail = true;
                    System.out.println("Entrer une nombre positif s'il vous plaît");
    		}
    		else
                    numFail = false; 
            }catch(NumberFormatException e){
    		numFail = true;
    		System.out.println("Saissier un float s'il vous plait");
            }	
    	}
        boolean numExpFail = true;
    	while(numExpFail){
            try{
        	System.out.println("Niveau d'experience à la Belote : (entre 1 et 10)");
            	exp = sc.nextLine();
                experienceBelote = Integer.parseInt(exp);
        	while(experienceBelote < 0 || experienceBelote > 10 ){
                    System.out.println("Ce numero ne correspond pas a un niveau correct,\n veuillez saisir un numéro compris entre 0 et 10");
                    exp = sc.nextLine();
                    experienceBelote = Integer.parseInt(exp);
        	}
        	numExpFail = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Saissisez un entier entre 1 et 10 s'il vous plaît");
        	numExpFail = true;
            }
    	}
    	serveurs.add( new Serveur(prenom,nom,sexe,cri,accessoire,tailleBiceps,charme,experienceBelote));
    }
    
    /**
    * Cette methode permet de créer le(s) serveur(s) de manière automatique en l'ajoutant
    * à la liste des serveurs. 
    * Leurs caractéristiques sont donc prédéfinies comme suit : prenom, nom, sexe, 
    * cri significatif, taille du biceps et charme. 
    */
    public void creationServeurParDefaut(){
       serveurs.add( new Serveur("Iliana","delade", "femme", "ouaaahhhh","petite queue de playmate", 20 ,90,0) );
       serveurs.add( new Serveur("Joseph","Staline", "homme","whhhraaaaaaooooo","cravate noire", 95 , 25,2) );
   }
   
    /**
    * Cette methode permet de retourner les boissons du bar.
    * @return : Retourne une chaîne de caractère qui contient le nom de la boisson
    * et son indice.
    */
       @Override
    public String toString()
    {
      String str = "" ;
      for( int i = 0 ; i < boissons.size() ; i++ )
      {
        str += ( 1 + i ) + "- " + boissons.get(i).getNom() + "\n" ;    
      }
      return str ;       
    }
    
    /**
    * Cette methode permet d'afficher la carte des boissons et leur indice associé.
    */
    public void carte ()
    {
    	for( int i=0 ; i < boissons.size() ; i++ )
            System.out.println( i + "- " + boissons.get(i).getNom() );   
    }
    
    /**
    * Cette methode permet d'afficher tous les clients non joueurs du bar
    * (donc utilisateur exclu) et l'indice qui leur est associé.
    */
    public void clients(){
    	System.out.println("Choisir un client");
    	for( int i = 0 ; i < clients.size()-1 ; i++ )
            System.out.println( i + " - " + clients.get(i).getPrenom() );
    }
    
   
    /**
     * Cette methode permet d'afficher la liste de tous les clients du bar
     * (l'utilisateur en fait parti) et l'indice qui leur est associé.
     */
    public void joueurs(){
    	System.out.println("Choisir un client");
    	for( int i = 0 ; i < clients.size() ; i++ )
            System.out.println( i + " - " + clients.get(i).getPrenom());
    }	
    
    
    
    
    /**
     * Cette methode permet d'afficher la liste de tous les serveurs du bar 
     * et les informations qui y sont liées. 
     */
    public void serveurs(){
    	System.out.println("Choisir un serveur");
    	for( int i = 0 ; i < serveurs.size() ; i++ )
            System.out.println( i + " - " + serveurs.get(i).getPrenom()+ "(" + serveurs.get(i).getSexe() + ")");
    }
    
    
    /**
    * Cette méthode permet de se présenter de manière complémentaire.
    * Elle permet également de déclarer sa flamme au client séletionné et de
    * se faire rejeter par ce dernier. 
    */
   public void presentationComplementaire() {
        int choix ;
        Client destinataire = null ;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Que vas-tu dire a cette personne ?");
        String text = sc.nextLine();
        System.out.println("Voici les différents clients du bar !") ;
        System.out.println("A qui veux-tu déclarer ta flamme ?");
        this.clients();
        boolean fail = true;
        while(fail){
            try{
                choix = Integer.parseInt(sc.nextLine());
                if(choix == clients.size()-1)
                {
                    System.out.println("Ce numéro ne correspond à aucun client");
                    fail = true ;
                    }
                else{
                    try{
                        destinataire = this.clients.get(choix);
                        fail = false;
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Ce numero ne correspond à aucun client");
                        fail = true;
                        }
                }
            }catch(NumberFormatException e){
                System.out.println("Entrez un entier");
                fail = true;
            }
        }    
        System.out.println(joueur.parler(text));
        System.out.println(destinataire.parler("Je ne suis absolument pas intéressé par les " + joueur.getSexe() + "s ,desolé "));
    }
    

/**
 * Cette methode permet de passer une commande auprès du serveur choisi.
 * Que ce soit pour offrir ou pour sa propre consommation, elle vérifie que
 * la boisson est disponible dans le stock, sinon la boisson de secours prend le relais. 
 * Si cette dernière n'est pas disponible, une commande auprès du fournisseur
 * est passée.
 * Elle prend aussi en compte le sexe du serveur et le taux d'alcoolémie du consommateur.
 * @param payeur : client qui va payer l'addition
 * @param consomateur : client qui va consommer la boisson
 */
public void commanderServeur(Client payeur,Client consomateur){
    
    String phrase = null ;
    System.out.println("Choissisez un serveur(numero)");
    this.serveurs();
    int numServeur = Integer.parseInt(sc.nextLine());
    while(numServeur < 0 || numServeur >= serveurs.size()){
        System.out.println("Numero de serveur invalide \nEntrer un numéro valide");
        numServeur = Integer.parseInt(sc.nextLine());
    }
    System.out.println(serveurs.get(numServeur).parler("Quel boisson vouler-vous?"));

    if(!payeur.equals(consomateur)){
        System.out.println(payeur.parler("Qu'est-ce que je t'offre "+consomateur.getPrenom()+" ?"));
        System.out.println(consomateur.parler("Un verre de "+consomateur.boissonFavorite.getNom() +" s'il te plaît."));
    }
    phrase = "Un verre de "+consomateur.boissonFavorite.getNom()+" ";
    if(!payeur.equals(consomateur)){
        phrase += " pour ";
        phrase += consomateur.getPrenom();
    }
    if(serveurs.get(numServeur).getSexe().equals("femme") && this.joueur.alcoolemie > 3 && this.joueur.getSexe().equals("homme")){
        phrase += " poupée";
    }
    else if(serveurs.get(numServeur).getSexe().equals("homme") && this.joueur.alcoolemie > 3 && this.joueur.getSexe().equals("femme")){
        phrase +=" mon grand";
    }
    System.out.println(payeur.parler(phrase));
    System.out.println(serveurs.get(numServeur).parler("Barman, il me faut un verre de "+consomateur.boissonFavorite.getNom()+" "));
    if(this.barman.disponible(consomateur.boissonFavorite)){
        System.out.println(this.barman.parler("Oui, je te prépare ça desuite "));
        System.out.println(this.barman.parler(serveurs.get(numServeur).getPrenom()+" ! il est prêt ton verre "));
        System.out.println(serveurs.get(numServeur).parler("Voici votre verre monsieur, bonne dégustation"));
        consomateur.boire(consomateur.boissonFavorite);
        payeur.ajouterAddition(consomateur.boissonFavorite);
    }else{
        System.out.println(this.barman.parler("J'en ai plus, j'en commande desuite "));
        this.barman.commande(consomateur.boissonFavorite,this.fournisseur);
        System.out.println(serveurs.get(numServeur).parler("Désolé mais nous n'avons plus de verre de "+ consomateur.boissonFavorite.getNom()));
    if(!payeur.equals(consomateur)){
        System.out.println(payeur.parler("Tu veux pas autre chose à boire "+consomateur.getPrenom()+" ?"));
        System.out.println(consomateur.parler("Behh un verre de "+consomateur.boissonFavorite.getNom() +" alors s'il te plaît."));
    }
    phrase = "Alors un verre de "+consomateur.boissonSecours.getNom()+" ";
    if(!payeur.equals(consomateur)){
        phrase += " pour ";
        phrase += consomateur.getPrenom();
    }
    if(serveurs.get(numServeur).getSexe().equals("femme") && this.joueur.alcoolemie > 3 && this.joueur.getSexe().equals("homme")){
        phrase += " poupée";
    }
    else if(serveurs.get(numServeur).getSexe().equals("homme") && this.joueur.alcoolemie > 3 && this.joueur.getSexe().equals("femme")){
        phrase +=" mon grand";
    }
    System.out.println(payeur.parler(phrase));
    System.out.println(serveurs.get(numServeur).parler("Barman, il me faut un verre de "+consomateur.boissonSecours.getNom()+" "));
    if(this.barman.disponible(consomateur.boissonSecours)){
        System.out.println(this.barman.parler("Oui, je te prépare ça desuite "));
        System.out.println(this.barman.parler(serveurs.get(numServeur).getPrenom()+" ! il est prêt ton verre "));
        System.out.println(serveurs.get(numServeur).parler("Voici votre verre monsieur, bonne dégustation"));
        consomateur.boire(consomateur.boissonSecours);
        payeur.ajouterAddition(consomateur.boissonSecours);
    }
    else{
        System.out.println(this.barman.parler("J'en ai plus"));
        this.barman.commande(consomateur.boissonSecours,this.fournisseur);
        System.out.println(serveurs.get(numServeur).parler("Désolé mais nous n'avons plus de verre de "+ consomateur.boissonSecours.getNom())); 
        this.barman.commande(consomateur.boissonSecours,this.fournisseur);
        this.fournisseur.commande(consomateur.boissonSecours,this.barman);
        this.fournisseur.livrer(consomateur.boissonSecours,this.barman);
        this.barman.recevoirCommande(consomateur.boissonSecours, this.fournisseur);
        this.fournisseur.setPorteMonnaie(this.fournisseur.getPorteMonnaie() + this.patronne.payerFournisseur(consomateur.boissonFavorite,this.fournisseur));
        this.fournisseur.parler("Merci et bonne journée");
    }
    this.barman.commande(consomateur.boissonFavorite,this.fournisseur);
    this.fournisseur.commande(consomateur.boissonFavorite,this.barman);
    this.fournisseur.livrer(consomateur.boissonFavorite,this.barman);
    this.barman.recevoirCommande(consomateur.boissonFavorite, this.fournisseur);
    this.fournisseur.setPorteMonnaie(this.fournisseur.getPorteMonnaie() + this.patronne.payerFournisseur(consomateur.boissonFavorite,this.fournisseur));
    this.fournisseur.parler("Merci et bonne journée");
    }
}

/**
 * Cette methode permet de passer une commande au Barman.
 * Que ce soit pour offrir ou pour sa propre consommation, elle vérifie que
 * la boisson est disponible dans le stock, sinon la boisson de secours prend le relais. 
 * Si cette dernière n'est pas disponible, une commande auprès du fournisseur
 * est passée.
 * @param payeur : client qui va payer l'addition
 * @param consomateur : client qui va consommer la boisson
 */
public void commanderBarman(Client payeur,Client consomateur){
        String phrase;
    	
        System.out.println(this.barman.parler("Quest-ce qu'il te faut ")); // coco ??
            
        if(!payeur.equals(consomateur)){
            System.out.println(payeur.parler("Qu'est-ce que je t'offre "+consomateur.getPrenom()+" ?"));
            System.out.println(consomateur.parler("Un verre de "+consomateur.boissonFavorite.getNom() +" s'il te plaît."));
        }
        
        phrase = "Barman ,un verre de "+consomateur.boissonFavorite.getNom()+" ";
        
        if(!payeur.equals(consomateur)){
            phrase += " pour ";
            phrase += consomateur.getPrenom();
        }
        
        System.out.println(payeur.parler(phrase));
        
        if(this.barman.disponible(consomateur.boissonFavorite)){
            System.out.println(this.barman.parler("Voici votre verre monsieur, bonne dégustation"));
            consomateur.boire(consomateur.boissonFavorite);
            payeur.ajouterAddition(consomateur.boissonFavorite);
        }
        else{
            System.out.println(this.barman.parler("J'en ai plus, désolé"));
            if(!payeur.equals(consomateur)){
                System.out.println(payeur.parler("Tu veux pas autre chose à boire "+consomateur.getPrenom()+" ?"));
                System.out.println(consomateur.parler("Behh un verre de "+consomateur.boissonSecours.getNom() +" alors s'il te plaît."));
            }
            phrase = "Alors un verre de "+consomateur.boissonSecours.getNom()+" ";
            if(!payeur.equals(consomateur)){
                phrase += " pour ";
                phrase += consomateur.getPrenom();
            }
            
            phrase += " s'il t'en reste ";
            
            System.out.println(payeur.parler(phrase));
            if(this.barman.disponible(consomateur.boissonSecours)){
                System.out.println(this.barman.parler("Voici votre verre monsieur, bonne dégustation"));
                consomateur.boire(consomateur.boissonSecours);
                payeur.ajouterAddition(consomateur.boissonSecours);
            }
            else{
                System.out.println(this.barman.parler("J'en ai plus aussi désolé"));
                this.barman.commande(consomateur.boissonSecours,this.fournisseur);
                this.fournisseur.commande(consomateur.boissonSecours,this.barman);
                this.fournisseur.livrer(consomateur.boissonSecours,this.barman);
                this.barman.recevoirCommande(consomateur.boissonSecours, this.fournisseur);
                this.fournisseur.setPorteMonnaie(this.fournisseur.getPorteMonnaie() + this.patronne.payerFournisseur(consomateur.boissonFavorite,this.fournisseur));
                this.fournisseur.parler("Merci et bonne journée");
            }
            this.barman.commande(consomateur.boissonFavorite,this.fournisseur);
            this.fournisseur.commande(consomateur.boissonFavorite,this.barman);
            this.fournisseur.livrer(consomateur.boissonFavorite,this.barman);
            this.barman.recevoirCommande(consomateur.boissonFavorite, this.fournisseur);
            this.fournisseur.setPorteMonnaie(this.fournisseur.getPorteMonnaie() + this.patronne.payerFournisseur(consomateur.boissonFavorite,this.fournisseur));
            this.fournisseur.parler("Merci et bonne journée");
        }
    }
    
    /**
     * 
     * Cette methode constitue le "menu du jeu", elle permet de gérer l'interface 
     * utilisateur et d'appeler les différentes méthodes du menu.
     * Ce dernier réalise les actions suivantes :  se présenter, offrir un verre, boire un verre
     * payer la tournée, payer l'addition, voir ses statistiques, déclarer sa flamme,
     * participer au tournoi de belotte, s'assoir à une table, se lever de la table
     * et enfin quitter le jeu. 
     * 
     */
public void menu()
{
    int  choix;
    boolean fail;
    Client destinataire = null;
    Scanner sc = new Scanner(System.in);

    while(true){
        System.out.println("Que veux-tu faire ?");
        System.out.println("1- Se présenter");
        System.out.println("2- Offrir un verre");
        System.out.println("3- Boire un verre");
        System.out.println("4- Payer la tournée");
        System.out.println("5- Payer l'addition");
        System.out.println("6- Voir les statistiques de mon personnage");
        
        if(joueur.numTable == -1)
            System.out.println("7- S'asseoir à une table");
        else
            System.out.println("7- Se lever de la table");
        
        System.out.println("8- Tournoi de belote");
        System.out.println("9- Déclarer sa flamme à quelqu'un");
        System.out.println("0- Exit");

        try{
            choix = Integer.parseInt(sc.nextLine()); //On choisit le num de l'action
        }
        catch(NumberFormatException e){
            System.out.println("Entrez un entier s'il vous plait ");
            continue;
        }
            if(choix < 0 || choix > 9 ){
                System.out.println("Ce choix n'existe pas");
                continue;
            }
            
           
        switch(choix){
            case 0:
                System.out.println("Oh tu nous quittes déjà ! A bientôt !");
                return;
            case 1:
                System.out.println(joueur.sePresenter());
                break;
            case 2:					
                this.clients();
                fail = true;
                while(fail){
                    try{
                        choix = Integer.parseInt(sc.nextLine());
                        if(choix == clients.size()-1){
                            System.out.println("Ce numéro ne correspond à aucun client");
                            fail = true ;
                        }
                        else{
                            try{
                                    destinataire = this.clients.get(choix);
                                    fail = false;
                                }
                                catch(IndexOutOfBoundsException e){
                                    System.out.println("Ce numero ne correspond à aucun client");
                                    fail = true;
                                }
                            }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Entrez un entier");
                        fail = true;
                    }                     
                    }
                    System.out.println("Commander au barman ou a un serveur ?(barman/serveur)");
                    String reponse = sc.nextLine();	
                    while(!reponse.equals("barman") && !reponse.equals("serveur")){
                        System.out.println("Entrez serveur ou barman.");
                        reponse = sc.nextLine();
                    }
                    if(reponse.equals("serveur")){
                        this.commanderServeur(this.joueur,destinataire);
                    }
                    else{
                        this.commanderBarman(this.joueur,destinataire);
                    }
                    break;
                case 3:
                    System.out.println("Commander au barman ou a un serveur ?(barman/serveur)");
                    reponse = sc.nextLine();	
                    while(!reponse.equals("barman") && !reponse.equals("serveur")){
                        System.out.println("Entrez serveur ou barman.");
                        reponse = sc.nextLine();
                    }
                    if(reponse.equals("serveur")){
                        this.commanderServeur(this.joueur,this.joueur);
                    }
                    else{
                        this.commanderBarman(this.joueur,this.joueur);
                    }
                    break;
                case 4:
                    System.out.println(this.barman.parler("TOURNEE GENERAL "));
                    System.out.println(this.patronne.parler("Tout va bien, les affaires reprennent"));
                    for(int i = 0 ;i < clients.size() ; i++){
                        System.out.println(clients.get(i).parler(clients.get(i).getCri()));
                    }
                    for(int i = 0 ;i < clients.size() ; i++){
                        this.commanderBarman(this.joueur,clients.get(i));
                    }
                    break;
                case 5:
                    System.out.println("Payer l'addition au barman ou a un serveur ?(barman/serveur)");
                    String reponse1 = sc.nextLine();	
                    float addition = this.joueur.addition;
                    while(!reponse1.equals("barman") && !reponse1.equals("serveur")){
                        System.out.println("Entrez serveur ou barman.");
                        reponse1 = sc.nextLine();
                    }
                    if(reponse1.equals("serveur")){
                        System.out.println("Choissisez un seveur(numero)");
                        this.serveurs();
                        int numServeur = Integer.parseInt(sc.nextLine());
                        while(numServeur < 0 || numServeur >= serveurs.size()){
                            System.out.println("Numero de serveur invalide \nEntrer un numéro valide");
                            numServeur = Integer.parseInt(sc.nextLine());
                        }
                        System.out.println(this.joueur.parler("L'addition s'il vous plaît"));
                        System.out.println(this.serveurs.get(numServeur).parler("ça vous fera " + this.joueur.addition + " s'il vous plaît"));
                    }
                    else{
                        System.out.println(this.joueur.parler("L'addition s'il vous plaît"));
                        System.out.println(this.barman.parler("ça vous fera " + this.joueur.addition));
                    }
                    if(this.joueur.payer()){
                        this.caisse += addition;
                        System.out.println(this.barman.parler("Merci"));
                    }
                    else{
                        System.out.println(this.barman.parler("Degage d'ici et que je ne te revoie plus dans les alentours"));
                        System.out.println("Vous avez été virer du bar car vous n'aviez pas assez d'argent pour payer");
                        this.patronne.exclure(this.joueur);
                        this.joueur.baisserCotePopularite(5);
                        return;
                    }
                    break ;
                case 6:
                    System.out.println(this.joueur);
                    break ;
                case 7 :
                    fail = true;
                    if(joueur.numTable == -1){
                        while(fail){	
                            try{	
                                for(int i = 0 ; i < this.tables.size() ; i ++ ) {                        
                                    System.out.println("Table n°" + i + "("+this.tables.get(i).personne.size() +" personne(s) sont assise(s) à cette table)");
                                    System.out.println("Sont assis à cette table :");
                                    for (int j = 0 ; j < this.tables.get(i).personne.size() ; j++){
                                        System.out.println(this.tables.get(i).personne.get(j).getPrenom());
                                    }
                                }
                                System.out.println("A quelle table voulez-vous vous asseoir");
                                int choix1 = Integer.parseInt(sc.nextLine());
                                if(choix1 < 0 || choix1 >= this.tables.size()){
                                    System.out.println("Ce numero ne correspond à aucune table");
                                    fail = true;
                                }
                                else{
                                    if(this.tables.get(choix1).ajouterPersonne(this.joueur)){
                                        this.joueur.numTable = choix1;
                                        System.out.println("Vous êtes assis à la table "+choix1);
                                        fail = false;
                                    }
                                else{
                                    System.out.println("La table "+choix1+" est remplie");
                                    fail = true;
                                }
                                }
                            }
                            catch(NumberFormatException e){
                                System.out.println("Saissier un entier s'il vous plait");
                                fail = true;
                            }
                        }
                    }
                    else{
                        this.tables.get(this.joueur.numTable).seLever(this.joueur);
                        System.out.println("Vous n'êtes plus assis");
                        this.joueur.numTable = -1;
                    }
                    break;
                case 8 :		
                    TournoiBelote tournoiBelote = new TournoiBelote(10);
                    System.out.println("--------------------------------------");
                    System.out.println("Création de tournoi de belote");
                    System.out.println("--------------------------------------");
                    System.out.println(this.patronne.parler("Tournoi de belote !!! le coût d'inscription est de 10 e"));
                    System.out.println("Voulez-vous que le tournoi se créer automatiquement ?(o/n)");
                    reponse = sc.nextLine();
                    while(!reponse.equals("o") && !reponse.equals("n")){
                        System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
                        reponse = sc.nextLine();
                    }
                    if(reponse.equals("o")){
                        TournoiBelote tournoiBeloteAuto = new TournoiBelote(10);
                        tournoiBeloteAuto.inscrire(new Equipe("Equipe 1",this.joueur,clients.get(1)));
                        tournoiBeloteAuto.inscrire(new Equipe("Equipe 2",clients.get(2),clients.get(3)));
                        tournoiBeloteAuto.inscrire(new Equipe("Equipe 3",clients.get(3),clients.get(4)));
                        tournoiBeloteAuto.lancerTournoi();
                    }
                    else{
                        System.out.println("Création d'équipe");
                        boolean equipe = true;
                        int joueur1 = 0;
                        int joueur2 = 0;
                        while(equipe){
                            System.out.println("Nom de l'équipe");
                            String nomEquipe = sc.nextLine();    	
                            this.joueurs();
                            System.out.println("Choissisez le premier joueur");
                            fail = true;
                            while(fail){
                                try{
                                    choix = Integer.parseInt(sc.nextLine());
                                    try{
                                        joueur1=choix;
                                        fail = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Ce numero ne correspond à aucun client");
                                        fail = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Entrez un entier");
                                    fail = true;
                                }
                            }
                            System.out.println("Choissisez le deuxième joueur");
                            fail = true;
                            while(fail){
                                try{
                                    choix = Integer.parseInt(sc.nextLine());
                                    try{
                                        joueur2 = choix;
                                        fail = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Ce numero ne correspond à aucun client");
                                        fail = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Entrez un entier");
                                    fail = true;
                                }
                            }
                            System.out.println("Voulez-vous créer une autre éuipe?(o/n)");
                            reponse = sc.nextLine();
                            while(!reponse.equals("o") && !reponse.equals("n")){
                                System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
                                reponse = sc.nextLine();
                            }
                            tournoiBelote.inscrire(new Equipe(nomEquipe,clients.get(joueur1),clients.get(joueur2)));
                            if(reponse.equals("n"))
                                equipe = false;
                        }
                        tournoiBelote.lancerTournoi();
                    }
                    break;
                case 9 : 
                    this.presentationComplementaire();            
                    break ;        
                default:
                    break;
                }
            this.patronne.recuperCaisse(this.caisse);
    }
 }
}