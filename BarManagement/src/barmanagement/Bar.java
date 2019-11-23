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
    String nomBar ;
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
        creationServeur();
    	creationBoisson();  
        creationClientPnj();
    	creationJoueur();
        
    	this.nomBar = "Chez " + this.patronne.getPrenom();
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
     * Cette methode permet de créer la Patronne soi-même.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, et son cri significatif.
     */
    public void creationPatronne(){
    	System.out.println("--------------------------------------");
    	System.out.println("*       Création de la patronne      *");
    	System.out.println("--------------------------------------");
    	String nom,prenom,cri,reponse;
        System.out.println("Voulez-vous que la Patronne soit créée automatiquement ?(o/n)");
        reponse = sc.nextLine();
        while(!reponse.equals("o") && !reponse.equals("n")){
            System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
            reponse = sc.nextLine();
	}
        if(reponse.equals("o")){
            patronne = new Patronne("Léontine","Beirnaert","femme","Vive la belote","bague diamant");
	}
        else{
            System.out.println("Nom de la patronne");
            nom = sc.nextLine();
            System.out.println("Prénom de la patronne");
            prenom = sc.nextLine();
            System.out.println("Cri de la patronne");
            cri = sc.nextLine();
            patronne = new Patronne(prenom,nom,"femme",cri,"bague diamant");
        }
    }
    
    /**
     * Cette methode permet de créer le fournisseur soi-même.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, et son cri significatif.
     */
    public void creationFournisseur(){
    	System.out.println("--------------------------------------");
    	System.out.println("*      Création du fournisseur       *");
    	System.out.println("--------------------------------------");
    	String nom,prenom,cri,reponse;
        System.out.println("Voulez-vous que le Fournisseur soit créé automatiquement ?(o/n)");
        reponse = sc.nextLine();
        while(!reponse.equals("o") && !reponse.equals("n")){
            System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
            reponse = sc.nextLine();
	}
        if(reponse.equals("o")){
            fournisseur = new Fournisseur("Géo","Trouvetout","homme","Ni une, ni deux, c'est livrer","T-shirt livraison rapido");
	}
        else{
            System.out.println("Nom du fournisseur");
            nom = sc.nextLine();
            System.out.println("Prénom du fournisseur");
            prenom = sc.nextLine();
            System.out.println("Cri du fournisseur");
            cri = sc.nextLine();
            fournisseur = new Fournisseur(prenom,nom,"homme",cri,"T-shirt livraison rapido");
        }
    }
    
    /**
     * Cette methode permet de créer le Barman soi-même.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, et son cri significatif.
     */
    public void creationBarman(){
    	System.out.println("--------------------------------------");
    	System.out.println("*         Création du barman         *");
    	System.out.println("--------------------------------------");
    	String nom,prenom,cri,reponse;
    	System.out.println("Voulez-vous que le barman soit créé automatiquement ?(o/n)");
        reponse = sc.nextLine();
	while(!reponse.equals("o") && !reponse.equals("n")){
            System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
            reponse = sc.nextLine();
	}
        if(reponse.equals("o")){
            barman = new Barman("Ginot","Plantu","homme","ATTAQUE DE BAR","Serviette de bar");
	}
        else{
            System.out.println("Nom du Barman");
            nom = sc.nextLine();
            System.out.println("Prénom du Barman");
            prenom = sc.nextLine();
            System.out.println("Cri du Barman");
            cri = sc.nextLine();
            barman = new Barman(prenom,nom,"homme",cri,"Serviette de bar");
        }
    }
    
     /**
     * Cette methode demande à l'utilisateur de renseigner la manière dont il veut
     * que les boissons soient créees. Deux options s'offrent à lui, automatique
     * ou manuelle.
     */
    public void creationBoisson(){
        boolean continu = true;
    	String reponse;
        System.out.println("--------------------------------------");
    	System.out.println("*       Création des boissons        *");
    	System.out.println("--------------------------------------");
    	System.out.println("Voulez-vous ajouter des boissons à la carte ?(o/n)");
	reponse = sc.nextLine();
	while(!reponse.equals("o") && !reponse.equals("n")){
            System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
            reponse = sc.nextLine();
	}
	if(reponse.equals("n")){
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
	    	System.out.println("Voulez-vous créer une boisson avec alcool ?(o/n)");
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
     * Cette methode permet de créer le(s) boisson(s) sans alcool soi-même, en
     * l'ajoutant à la liste des boissons. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prix, et le prix d'achat.
     */
    public void creationBoissonSansAlcool(){
        float prix = 0,prixAchat = 0;
    	String nomBoisson;
    	System.out.println("Nom de votre boisson ?");
    	nomBoisson = sc.nextLine();
    	boolean conversionError = true;
    	while(conversionError){
            try{
        	conversionError = false;
    		System.out.println("Prix de votre boisson pour les clients ?");
        	prix = Float.parseFloat(sc.nextLine());
        	}
            catch(NumberFormatException e){
        	conversionError = true;
        	System.out.println("Veuillez entrer un float ");
            }
    	}
    	conversionError = true;
    	while(conversionError){
            try{
        	conversionError = false;
    		System.out.println("Prix d'achat de votre boisson auprés du fournisseur?");
        	prixAchat = Float.parseFloat(sc.nextLine());
        	}
            catch(NumberFormatException e){
        	conversionError = true;
        	System.out.println("Veuillez entrer un float");
            }
    	}
    	boissons.add(new Boisson(nomBoisson,prix,prixAchat));
    }
    
     /**
     * Cette methode permet de créer le(s) boisson(s) alcoolisées soi-même, en
     * l'ajoutant à la liste des boissons. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prix, le prix d'achat et le taux d'alcoolémie.
     * 
     */
    public void creationBoissonAvecAlcool(){
        float prix = 0,prixAchat = 0, tauxAlcoolemie = 0;
    	String nomBoisson;
    	boolean conversionFail = true;
    	System.out.println("Nom de votre boisson ?");
    	nomBoisson = sc.nextLine();
    	while(conversionFail){
            try{
                conversionFail = false;
                System.out.println("Prix de votre boisson pour les clients ?");
                prix = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
        	conversionFail = true;
        	System.out.println("Veuillez entrer un float");
            }
    	}
    	conversionFail = true;
    	while(conversionFail){
            try{
                conversionFail = false;
                System.out.println("Prix d'achat de votre boisson au fournisseur?");
                prixAchat = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
                conversionFail = true;
                System.out.println("Veuillez entrer un float");
            }
    	}
    	conversionFail = true;
    	while(conversionFail){
            try{
        	conversionFail = false;
    		System.out.println("Taux d'alccolemie de votre boisson ?");
        	   tauxAlcoolemie = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
        	conversionFail = true;
        	System.out.println("Veuillez entrer un float");
            }
    	}
    	boissons.add(new Boisson(nomBoisson,prix,tauxAlcoolemie));
    }
    
    /**
     * Cette methode permet de créer les boissons de manière automatique en 
     * les ajoutant à la liste des boissons. Leurs caractéristiques sont donc 
     * prédéfinies comme suit : nom, prix, prix achat, et taux d'alcoolemie.
     */
    public void creationBoissonParDefaut(){
        boissons.add( new Boisson("Eau", 1f,0f) );
    	boissons.add( new Boisson("Virgin Mojito", 5f,2f) );
    	boissons.add( new Boisson("Chocolat chaud",  3.5f,1.f) );
    	boissons.add( new Boisson("Jus d'orange", 2.5f,1.5f) );
    	boissons.add( new Boisson ("Mojito", 5f ,2f, 4.5f ));
    	boissons.add( new Boisson ("Vodka", 8f ,3f, 5f ));
    	boissons.add( new Boisson ("Bière", 5f ,2f, 4f) );
    	boissons.add( new Boisson ("Monaco", 3f ,2f ,3.5f) );
    	boissons.add( new Boisson ("Wisky", 8f, 1f, 3f) );
    	boissons.add( new Boisson ("Ricard", 2f ,2f, 2.5f) );
    	
    }
    
     /**
     * Cette methode demande à l'utilisateur de renseigner la manière dont il veut
     * que les clients soient crées. Deux options s'offrent à lui, automatique
     * ou manuelle.
     */
    public void creationClientPnj() {
        String reponse;
        boolean continu = true;
    	System.out.println("--------------------------------------");
    	System.out.println("*        Création des Clients        *");
    	System.out.println("--------------------------------------");
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
            creationClientPersonalise();
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
     * Cette methode permet de créer le(s) client(s) soi-même, en l'ajoutant
     * à la liste des clients. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, le cri significatif, le sexe, l'argent du porte-monnaie
     * la boisson fav1 et la boisson fav2. 
    */
    public void creationClientPersonalise(){
    	String nomClient,surname,cri,sexe,num,accessoire,exp;
    	float porteMonnaie = 0;
    	int indiceBoissonFavorite = 0,indiceBoissonSecour = 0, popularite = 0, experienceBelote = 0;
    	System.out.print("Nom : ");
    	surname = sc.nextLine();
    	System.out.print("\nPrénom : ");
    	nomClient = sc.nextLine();
    	System.out.print("\nCri significatif : ");
    	cri = sc.nextLine();
        System.out.println("Choisissez un accessoire pour le client");
        System.out.println("Il est d'usage que les hommes ont un t-shirt de couleur et que les femmes ont des bijoux.");
        accessoire = sc.nextLine();
        boolean Error = true;
    	while(Error){
            try{
        	System.out.println("Niveau d'experience à la Belote : (entre 1 et 10)");
            	exp = sc.nextLine();
                experienceBelote = Integer.parseInt(exp);
        	while(experienceBelote < 0 || experienceBelote > 10){
                    System.out.println("Ce numero ne correspond pas à un niveau correct,\n veuillez saisir un numéro compris entre 0 et 10");
                    exp = sc.nextLine();
                    experienceBelote = Integer.parseInt(exp);
        	}
        	Error = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Veuillez saisir un entier entre 1 et 10.");
        	Error = true;
            }
    	}
    	boolean conversionError = true;
    	while(conversionError){
            try{
        	conversionError = false;
    		System.out.println("Combien d'argent a " + nomClient +" dans son porte monnaie ?");
        	porteMonnaie = Float.parseFloat(sc.nextLine());
            }catch(NumberFormatException e){
        	conversionError = true;
        	System.out.println("Veuillez un float s'il vous plaît ");
            }
    	}
    	System.out.print("\nSexe(homme/femme) : ");
    	sexe = sc.nextLine();
    	while(!sexe.equals("homme") && !sexe.equals("femme")){
            System.out.print("Veuillez saisir homme ou femme");
            sexe = sc.nextLine();
    	}
        boolean numBoissonError = true;
    	while(numBoissonError){
            try{
        	System.out.println("Veuillez choisir votre boisson favorite (Veuillez saisir le numero correspondant).");
                carte ();
            	num = sc.nextLine();
        	indiceBoissonFavorite = Integer.parseInt(num);
        	while(indiceBoissonFavorite < 0 || indiceBoissonFavorite >= boissons.size() ){
                    System.out.println("Ce numero ne correspond a aucune boisson disponible dans le bar,\n veuillez saisir un numéro compris entre 0 et " + boissons.size());
                    num = sc.nextLine();
                    indiceBoissonFavorite = Integer.parseInt(num);
        	}
        	numBoissonError = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Veuillez saisir un entier.");
        	numBoissonError = true;
            }
    	}
    	numBoissonError = true;
    	while(numBoissonError){
            try{
                System.out.println("Veuillez choisir votre boisson de secours (Veuillez choisir le numero correspondant)");
        	carte ();
            	num = sc.nextLine();
            	indiceBoissonSecour = Integer.parseInt(num);
                while(indiceBoissonSecour < 0 || indiceBoissonSecour >= boissons.size() ){
                    System.out.println("Ce numero ne correspond a aucune boisson disponible dans le bar,\n veuillez saisir un numéro compris entre 0 et " + (boissons.size()-1));
                    num = sc.nextLine();
                    indiceBoissonSecour = Integer.parseInt(num);
        	}
        	numBoissonError = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Veuillez saisir un entier s'il vous plaît.");
        	numBoissonError = true;
            }
    	}
    	clients.add(new Client(nomClient ,surname,sexe ,cri, accessoire,porteMonnaie,popularite, experienceBelote,boissons.get(indiceBoissonFavorite),boissons.get(indiceBoissonSecour)));
    }
    /** 
     * Cette methode permet de créer les clients du bar de manière automatique en 
     *les ajoutant à la liste des clients. Leurs caractéristiques sont donc prédéfinies
     *comme suit : prenom, nom, sexe, cri significatif, porte-monnaie, boisson fav1 
     * et boisson fav2. 
     */
    public void creationClientPnjDefaut(){
        clients.add( new Client("Jacques","Prévert", "homme" , "La poésie, c'est la vie.","T-shirt bleu",20,5,0, boissons.get(1) , boissons.get(3)));
        clients.add( new Client("Alissa","Smith" ,"femme","Vive l'alcool et le saucisson.","Collier",20,2,0, boissons.get(0),boissons.get(2)));
        clients.add( new Client("Joe","Dalton", "homme", "Quel idiot cet Avrel !","T-shirt noir",20,1,1, boissons.get(7),boissons.get(2)));
        clients.add( new Client("Jessica","Pearson","femme","Je suis la seule avocate du Far West !","Bague",20,0,1,boissons.get(8),boissons.get(1)));
        clients.add( new Client("Avrel","Dalton","homme","C'est pas vrai Joe ...","T-shirt rouge",20,0,0, boissons.get(2), boissons.get(5)));
        clients.add( new Client("Allie", "West", "femme", "Vive la californie !","Boucles d'oreilles",20,0,1, boissons.get(6),boissons.get(1)));
        clients.add( new Client("Rick","Grimes","homme","Jamais perdu mon colt en 15 ans de carrière.","T-shirt noir",20,0,0, boissons.get(2), boissons.get(5)));
        clients.add( new Client("Daryl","Dixon","homme","Je suis un grand solitaire moi ...","T-shirt rouge",20,0,0, boissons.get(5), boissons.get(2)));
    }
    
    /** 
     * Cette methode demande à l'utilisateur de renseigner la manière dont il veut
     * que les serveurs soient crées. Deux options s'offrent à lui, automatique
     * ou manuelle.
     */
    public void creationServeur(){
    	String reponse;
    	boolean continu = true;
    	System.out.println("--------------------------------------");
    	System.out.println("*        Création des serveurs       *");
    	System.out.println("--------------------------------------");
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
     * Cette methode permet de créer le(s) serveur(s) soi-même, en l'ajoutant
     * à la liste des serveurs. 
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenom, le cri, le sexe, la taille du biceps ou le charme
     */
    public void creationServeurPersonalise(){
        float tailleBiceps = 0,charme = 0;
    	String nom,prenom,cri,sexe,accessoire;
        int experienceBelote = 0;
    	boolean numError = true;
    	System.out.print("Nom : ");
    	nom = sc.nextLine();
    	System.out.print("\nPrénom : ");
    	prenom = sc.nextLine();
    	System.out.print("\nCri significatif : ");
    	cri = sc.nextLine();
    	System.out.print("\nSexe(homme/femme) : ");
    	sexe = sc.nextLine();
    	while(!sexe.equals("homme") && !sexe.equals("femme")){
            System.out.print("Veuillez saisir homme ou femme");
            sexe = sc.nextLine();
    	}
        if (sexe.equals("homme")){
            accessoire = "cravate noire";
        }
        else{
            accessoire = "cravate rose";
        }
    	while(numError){
            try{
    		System.out.print("Veuillez entrer la taille du biceps.");
    		tailleBiceps = Float.parseFloat(sc.nextLine());
    		if(tailleBiceps < 0 ){
                    numError = true;
                    System.out.println("Veuillez entrer une taille positive.");
    		}
    		else
                    numError = false; 
            }catch(NumberFormatException e){
    		numError = true;
    		System.out.println("Veuillez saisir un float.");
            }	
    	}
    	numError = true;
    	while(numError){
            try{
    		System.out.print("Veuillez entrer le coefficent de charme.");
    		charme = Float.parseFloat(sc.nextLine());
    		if(charme < 0 ){
                    numError = true;
                    System.out.println("Veuillez entrer une nombre positif.");
    		}
    		else
                    numError = false; 
            }catch(NumberFormatException e){
    		numError = true;
    		System.out.println("Veuillez saisir un float.");
            }	
    	}
        boolean numExpError = true;
    	while(numExpError){
            try{
        	System.out.println("Veuillez saisir le niveau d'experience à la Belote : (entre 1 et 10)");
                experienceBelote = Integer.parseInt(sc.nextLine());
        	while(experienceBelote < 0 || experienceBelote > 10 ){
                    System.out.println("Ce numero ne correspond pas a un niveau correct,\n veuillez saisir un numéro compris entre 0 et 10");
                    experienceBelote = Integer.parseInt(sc.nextLine());
        	}
        	numExpError = false;
        	}
            catch(NumberFormatException e){
        	System.out.println("Veuillez saisir un entier entre 1 et 10 s'il vous plaît");
        	numExpError = true;
            }
    	}
    	serveurs.add( new Serveur(prenom,nom,sexe,cri,accessoire,tailleBiceps,charme,experienceBelote));
    }
    
    /**
    * Cette methode permet de créer le(s) serveur(s) de manière automatique en l'ajoutant
    * à la liste des serveurs. 
    * Leurs caractéristiques sont donc prédéfinies comme suit : prenom, nom, sexe, 
    * cri significatif, taille du biceps, charme et experience belote. 
    */
    public void creationServeurParDefaut(){
       serveurs.add( new Serveur("Veronica","Lodge", "femme", " oh dear","cravate rose", 20 ,90,0) );
       serveurs.add( new Serveur("Nick","Miller", "homme","whhhraaaaaaooooo","cravate noire", 95 , 25,2) );
    }
   
    
    /**
     * Cette methode permet de créer son propre joueur.
     * L'utilisateur peut ajouter les caractéristiques qu'il souhaite à savoir :
     * le nom, le prenpm, le cri significatif, l'argent dans le porte monnaie,
     * le sexe, la boisson fav1 et la boisson fav2.
    */
    public void creationJoueur(){
        String nomJoueur,surname,cri,sexe,num, accessoire;
        int indiceBoissonFavorite = 0,indiceBoissonSecour = 0, popularite = 0, experienceBelote = 0;
        float porteMonnaie = 0;
        boolean numBoissonError = true;

        System.out.println("--------------------------------------");
        System.out.println("*         Créer votre joueur         *");
        System.out.println("--------------------------------------");
        System.out.println("Nom : ");  
        surname = sc.nextLine();
        System.out.println("Prénom : ");
        nomJoueur = sc.nextLine();
        System.out.println("Cri significatif : ");
        cri = sc.nextLine();
        System.out.println("Choisissez votre accessoire ! ");
        System.out.println("Il est d'usage que les hommes ont un t-shirt de couleur et que les femmes ont des bijoux.");
        accessoire = sc.nextLine();
        boolean conversionFail = true;
        while(conversionFail){
            try
            {
                conversionFail = false;
                System.out.println("Combien d'argent avez-vous dans votre porte monnaie ?");
                porteMonnaie = Float.parseFloat(sc.nextLine());
            }
            catch(NumberFormatException e){
                conversionFail = true;
                System.out.println("Veuillez entrer un float");
                    }
            }
            System.out.println("Sexe(homme/femme) : ");
            sexe = sc.nextLine();
            while(!sexe.equals("homme") && !sexe.equals("femme")){
                System.out.print("Veuillez saisir homme ou femme");
                sexe = sc.nextLine();
            }
            while(numBoissonError){
                    try{
                        System.out.println("Choisissez votre boisson favorite (Veuillez saisir le numero correspondant)");
                        carte ();
                        num = sc.nextLine();
                        indiceBoissonFavorite = Integer.parseInt(num);
                        while(indiceBoissonFavorite < 0 || indiceBoissonFavorite >= boissons.size() ){
                            System.out.println("Ce numero ne correspond pas à une boisson disponible dans le bar,\n Veuillez saisir un numéro compris entre 0 et " + (boissons.size()-1));
                            num = sc.nextLine();
                            indiceBoissonFavorite = Integer.parseInt(num);
                        }
                        numBoissonError = false;
                    }
                    catch(NumberFormatException e){
                        System.out.println("Veuillez saisir un entier s'il vous plaît.");
                        numBoissonError = true;
                    }
            }
            numBoissonError = true;
            while(numBoissonError){
                try{
                    System.out.println("Veuillez choisir votre boisson de secours (Veuillez saisir le numero correspondant)");
                    carte ();
                    num = sc.nextLine();
                    indiceBoissonSecour = Integer.parseInt(num);
                    while(indiceBoissonSecour < 0 || indiceBoissonSecour >= boissons.size() ){
                        System.out.println("Ce numero ne correspond a aucune boisson disponible dans le bar.\n Veuillez saisir un numéro compris entre 0 et " + (boissons.size()-1));
                        num = sc.nextLine();
                        indiceBoissonSecour = Integer.parseInt(num);
                    }
                    numBoissonError = false;
                }
                catch(NumberFormatException e){
                    System.out.println("Veuillez saisir un entier s'il vous plaît.");
                    numBoissonError = true;
                }
            }
            joueur = new Client(nomJoueur ,surname,sexe ,cri, accessoire ,porteMonnaie, popularite, experienceBelote, boissons.get(indiceBoissonFavorite),boissons.get(indiceBoissonSecour));
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
    	System.out.println("Veuillez choisir un client");
    	for( int i = 0 ; i < clients.size()-1 ; i++ )
            System.out.println( i + " - " + clients.get(i).getPrenom() );
    }
    
   
    /**
     * Cette methode permet d'afficher la liste de tous les clients du bar
     * (l'utilisateur en fait parti) et l'indice qui leur est associé.
     */
    public void joueurs(){
    	System.out.println("Veuillez choisir un client");
    	for( int i = 0 ; i < clients.size() ; i++ )
            System.out.println( i + " - " + clients.get(i).getPrenom());
    }	
    
    
    
    
    /**
     * Cette methode permet d'afficher la liste de tous les serveurs du bar 
     * et les informations qui y sont liées. 
     */
    public void serveurs(){
    	System.out.println("Veuillez choisir un serveur");
    	for( int i = 0 ; i < serveurs.size() ; i++ )
            System.out.println( i + " - " + serveurs.get(i).getPrenom()+ "(" + serveurs.get(i).getSexe() + ")");
    }
    
    
    /**
    * Cette méthode permet de se présenter de manière complémentaire.
    * Elle permet également de déclarer sa flamme au client séletionné et de
    * se faire rejeter par ce dernier. 
    */
   public void declarerSaFlamme(){
        int choix ;
        Client destinataire = null ;
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Que voulez-vous dire à cette personne ?");
        String text = sc2.nextLine();
        System.out.println("Voici les différents clients du bar !") ;
        System.out.println("A qui voulez-vous déclarer votre flamme ?");
        this.clients();
        boolean Error = true;
        while(Error){
            try{
                choix = Integer.parseInt(sc2.nextLine());
                if(choix == clients.size()-1)
                {
                    System.out.println("Désolé, ce numéro ne correspond à aucun client.");
                    Error = true ;
                    }
                else{
                    try{
                        destinataire = this.clients.get(choix);
                        Error = false;
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Désolé, ce numero ne correspond à aucun client.");
                        Error = true;
                        }
                }
            }catch(NumberFormatException e){
                System.out.println("Désolé, veuillez entrer un entier.");
                Error = true;
            }
        }    
        System.out.println(joueur.parler(text));
        System.out.println(destinataire.parler("Oula les " + joueur.getSexe() + "s, très peu pour moi... Dans une autre vie peut-être ! "));
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
    System.out.println("Veuillez choisir un serveur avec le numéro.");
    this.serveurs();
    int numServeur = Integer.parseInt(sc.nextLine());
    while(numServeur < 0 || numServeur >= serveurs.size()){
        System.out.println("Numero de serveur invalide \nEntrer un numéro valide");
        numServeur = Integer.parseInt(sc.nextLine());
    }
    System.out.println(serveurs.get(numServeur).parler("Veuillez choisir une boisson ?"));

    if(!payeur.equals(consomateur)){
        System.out.println(payeur.parler("Qu'est-ce que je peux t'offrir "+consomateur.getPrenom()+" ?"));
        System.out.println(consomateur.parler("Pour moi, ça sera un verre de "+consomateur.boissonFavorite.getNom() +" s'il te plaît."));
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
    System.out.println(serveurs.get(numServeur).parler("Barman, il me faut tout de suite un verre de "+consomateur.boissonFavorite.getNom()+" "));
    if(this.barman.disponible(consomateur.boissonFavorite)){
        System.out.println(this.barman.parler("Oui, ça part tout de suite !  "));
        System.out.println(this.barman.parler(serveurs.get(numServeur).getPrenom()+" ! Et voila ton verre est pret ! "));
        System.out.println(serveurs.get(numServeur).parler("Voici votre verre, bonne dégustation"));
        consomateur.boire(consomateur.boissonFavorite);
        payeur.ajouterAddition(consomateur.boissonFavorite);
    }else{
        System.out.println(this.barman.parler("Ah ... Désolé, j'en ai plus, je passe la commande tout de suite. "));
        this.barman.commande(consomateur.boissonFavorite,this.fournisseur);
        System.out.println(serveurs.get(numServeur).parler("Désolé mais nous n'avons plus de "+ consomateur.boissonFavorite.getNom()));
    if(!payeur.equals(consomateur)){
        System.out.println(payeur.parler("Tu ne voudrais pas autre chose à boire "+consomateur.getPrenom()+" par hasard ?"));
        System.out.println(consomateur.parler("Bahhh alors, un verre de "+consomateur.boissonFavorite.getNom() +"."));
    }
    phrase = "Alors ça sera un verre de "+consomateur.boissonSecours.getNom()+" ";
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
    System.out.println(serveurs.get(numServeur).parler("Barman, il me faudrait un verre de "+consomateur.boissonSecours.getNom()+" "));
    if(this.barman.disponible(consomateur.boissonSecours)){
        System.out.println(this.barman.parler("Oui, ça part tout de suite."));
        System.out.println(this.barman.parler(serveurs.get(numServeur).getPrenom()+" ! Et voila ton verre est pret "));
        System.out.println(serveurs.get(numServeur).parler("Voici votre verre, bonne dégustation"));
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
        System.out.println(this.barman.parler("Qu'est-ce qu'il te faut ? "));
        if(!payeur.equals(consomateur)){
            System.out.println(payeur.parler("Qu'est-ce que je peux t'offrir "+consomateur.getPrenom()+" ?"));
            System.out.println(consomateur.parler("Alors un bon verre de "+consomateur.boissonFavorite.getNom() +" s'il te plaît."));
        }
        phrase = "Barman ,un verre de "+consomateur.boissonFavorite.getNom()+" ! ";
        if(!payeur.equals(consomateur)){
            phrase += " pour ";
            phrase += consomateur.getPrenom();
        }
        System.out.println(payeur.parler(phrase));
        if(this.barman.disponible(consomateur.boissonFavorite)){
            System.out.println(this.barman.parler("Voici votre verre, bonne dégustation"));
            consomateur.boire(consomateur.boissonFavorite);
            payeur.ajouterAddition(consomateur.boissonFavorite);
        }
        else{
            System.out.println(this.barman.parler("Désolé, J'en ai plus"));
            if(!payeur.equals(consomateur)){
                System.out.println(payeur.parler("Tu veux pas autre chose à boire "+consomateur.getPrenom()+" ?"));
                System.out.println(consomateur.parler("Alors un bon verre de  "+consomateur.boissonSecours.getNom() +" s'il te plaît."));
            }
            phrase = "Alors un verre de "+consomateur.boissonSecours.getNom()+" ";
            if(!payeur.equals(consomateur)){
                phrase += " pour ";
                phrase += consomateur.getPrenom();
            }
            phrase += " s'il t'en reste ";
            System.out.println(payeur.parler(phrase));
            if(this.barman.disponible(consomateur.boissonSecours)){
                System.out.println(this.barman.parler("Voici votre verre, bonne dégustation"));
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
     * Cette methode constitue le "menu du jeu", elle permet de gérer l'interface 
     * utilisateur et d'appeler les différentes méthodes du menu.
     * Ce dernier réalise les actions suivantes :  se présenter, offrir un verre, boire un verre
     * payer la tournée, payer l'addition, voir ses statistiques, déclarer sa flamme,
     * participer au tournoi de belotte, s'assoir à une table, se lever de la table
     * et enfin quitter le jeu. 
     */
public void menu(){
    int  choix;
    boolean Error;
    Client destinataire = null;
    Scanner scan = new Scanner(System.in);
    while(true){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("*                              "+ this.nomBar+"                           *");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("|            Choisissez l'action que vous voulez réaliser.            |");
        System.out.println("|                          1- Présentez-vous                           |");
        System.out.println("|                2- Présentez-vous d'une autre manière                 |");
        System.out.println("|                           3- Offrez un verre                         |");
        System.out.println("|                           4- Buvez un verre                          |");
        System.out.println("|                          5- Payez la tournée                         |");
        System.out.println("|                          6- Payez l'addition                         |");
        System.out.println("|               7- Voir les statistiques de votre personnage           |");
        System.out.println("|      8- Voir les statistiques de tout les clients et de vous meme    |");
        if(joueur.numTable == -1)
            System.out.println("|                      9- Asseyez-vous à une table                     |");
        else
            System.out.println("|                      9- Levez vous de la table                       |");
        System.out.println("|                    10- Lancez un tournoi de belote                   |");
        System.out.println("|                  11- Déclarez votre flamme à quelqu'un               |");
        System.out.println("|                           12- Allez à l'ATM                          |");
        System.out.println("|                           0- Quittez le bar                          |");
        System.out.println("------------------------------------------------------------------------");
        try{
            choix = Integer.parseInt(scan.nextLine()); //On choisit le num de l'action
        }
        catch(NumberFormatException e){
            System.out.println("Entrez un entier s'il vous plait ");
            continue;
        }
        if(choix < 0 || choix > 12 ){
            System.out.println("Ce choix n'existe pas");
            continue;
        }
            
        switch(choix){
            case 0:
                float additionJoueur = this.joueur.addition;
                if (additionJoueur != 0 ){
                    System.out.println(this.patronne.parler("Eh tu va où comme ça, il faut payer ton addition avant de quitter le bar !"));
                }
                else{
                this.joueur.sauvegarder();
                System.out.println("Ta partie a été sauvegardé, n'hésite pas à consulter le fichier afin de voir tes statistiques lors de cette partie et essaie de les améliorer la prochaine fois !");
                return;
                }
                break;
            case 1:
                System.out.println(joueur.sePresenter());
                this.joueur.monterCotePopularite(1);
                break;
            case 2: 
                System.out.println(joueur.sePresenterComplementaire());
                this.joueur.monterCotePopularite(1);
                break;
            case 3:					
                this.clients();
                Error = true;
                while(Error){
                    try{
                        choix = Integer.parseInt(scan.nextLine());
                        if(choix == clients.size()-1){
                            System.out.println("Désolé, ce numéro ne correspond à aucun client");
                            Error = true ;
                        }
                        else{
                            try{
                                    destinataire = this.clients.get(choix);
                                    Error = false;
                                }
                                catch(IndexOutOfBoundsException e){
                                    System.out.println("Désolé, ce numero ne correspond à aucun client");
                                    Error = true;
                                }
                            }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Veuillez entrez un entier");
                        Error = true;
                    }                     
                    }
                    System.out.println("Voulez-vous commander au barman ou a un serveur ?(b/s)");
                    String reponse = scan.nextLine();	
                    while(!reponse.equals("b") && !reponse.equals("s")){
                        System.out.println("Veuillez entrer serveur ou barman.");
                        reponse = scan.nextLine();
                    }
                    if(reponse.equals("s")){
                        this.commanderServeur(this.joueur,destinataire);
                    }
                    else{
                        this.commanderBarman(this.joueur,destinataire);
                    }
                    this.joueur.monterCotePopularite(2);
                    break;
                case 4:
                    System.out.println("Voulez-vous commander au barman ou a un serveur ?(b/s)");
                    reponse = scan.nextLine();	
                    while(!reponse.equals("b") && !reponse.equals("s")){
                        System.out.println("Veuillez entrer serveur ou barman.");
                        reponse = scan.nextLine();
                    }
                    if(reponse.equals("s")){
                        this.commanderServeur(this.joueur,this.joueur);
                    }
                    else{
                        this.commanderBarman(this.joueur,this.joueur);
                    }
                    break;
                case 5:
                    System.out.println(this.barman.parler("TOURNEE GENERAL "));
                    System.out.println(this.patronne.parler("Tout va bien, les affaires reprennent"));
                    for(int i = 0 ;i < clients.size() ; i++){
                        System.out.println(clients.get(i).parler(clients.get(i).getCri()));
                    }
                    for(int i = 0 ;i < clients.size() ; i++){
                        this.commanderBarman(this.joueur,clients.get(i));
                    }
                    this.joueur.monterCotePopularite(5);
                    break;
                case 6:
                    System.out.println("Voulez-vous payer l'addition au barman ou a un serveur ?(b/s)");
                    String reponse1 = scan.nextLine();	
                    float addition = this.joueur.addition;
                    while(!reponse1.equals("b") && !reponse1.equals("s")){
                        System.out.println("Veuillez entrer b/s");
                        reponse1 = scan.nextLine();
                    }
                    if(reponse1.equals("s")){
                        System.out.println("Veuillez choisir un seveur( avec le numero)");
                        this.serveurs();
                        int numServeur = Integer.parseInt(scan.nextLine());
                        while(numServeur < 0 || numServeur >= serveurs.size()){
                            System.out.println("Numero de serveur invalide \nEntrer un numéro valide");
                            numServeur = Integer.parseInt(scan.nextLine());
                        }
                        System.out.println(this.joueur.parler("L'addition s'il vous plaît"));
                        System.out.println(this.serveurs.get(numServeur).parler("ça vous fera " + this.joueur.addition + " s'il vous plaît"));
                        System.out.println(this.joueur.parler("Et voila !"));
                    }
                    else{
                        System.out.println(this.joueur.parler("L'addition s'il vous plaît"));
                        System.out.println(this.barman.parler("ça vous fera " + this.joueur.addition));
                        System.out.println(this.joueur.parler("Et voila ! "));
                    }
                    if(this.joueur.payer()){
                        this.caisse += addition;
                        System.out.println(this.barman.parler("Les bons comptes font les bons amis."));
                    }
                    else{
                        this.joueur.parler("Je crois que j'ai pas assez de monnaie...");
                        System.out.println(this.barman.parler("Degage d'ici et que tu ne remettes pas un pied dans mon bar ! "));
                        this.patronne.exclure(this.joueur);
                        System.out.println("Vous avez été viré(e) du bar car vous n'aviez pas assez d'argent pour payer... ");
                        this.joueur.baisserCotePopularite(5);
                        this.joueur.sauvegarder();
                        return;
                    }
                    if (this.caisse > 100){
                        this.patronne.recupererCaisse(this.caisse);
                    }
                    break ;
                case 7:
                    System.out.println(this.joueur);
                    break ;
                case 8:
                    System.out.println(this.clients);
                    break ;
                case 9:
                    Error = true;
                    if(joueur.numTable == -1){
                        while(Error){	
                            try{	
                                for(int i = 0 ; i < this.tables.size() ; i ++ ) {                        
                                    System.out.println("Table n°" + i + " ( "+this.tables.get(i).personne.size() +" personne(s) sont assise(s) à cette table) ");
                                    System.out.println("Sont assis à cette table : ");
                                    for (int j = 0 ; j < this.tables.get(i).personne.size() ; j++){
                                        System.out.println(this.tables.get(i).personne.get(j).getPrenom());
                                    }
                                }
                                System.out.println("A quelle table voulez-vous aller ?");
                                int choix1 = Integer.parseInt(scan.nextLine());
                                if(choix1 < 0 || choix1 >= this.tables.size()){
                                    System.out.println("Désolé, ce numero ne correspond à aucune table.");
                                    Error = true;
                                }
                                else{
                                    if(this.tables.get(choix1).ajouterPersonne(this.joueur)){
                                        this.joueur.numTable = choix1;
                                        System.out.println("Vous êtes assis à la table " + choix1 + ".");
                                        Error = false;
                                    }
                                else{
                                    System.out.println("Désolé, la table " + choix1 + " est remplie.");
                                    Error = true;
                                }
                                }
                            }
                            catch(NumberFormatException e){
                                System.out.println("Veuillez saisir un entier s'il vous plait.");
                                Error = true;
                            }
                        }
                    }
                    else{
                        this.tables.get(this.joueur.numTable).seLever(this.joueur);
                        this.joueur.numTable = -1;
                    }
                    break; 
                case 10:		
                    TournoiBelote tournoiBelote = new TournoiBelote(10);
                    System.out.println("--------------------------------------");
                    System.out.println("*    Création de tournoi de belote   *");
                    System.out.println("--------------------------------------");
                    System.out.println(this.patronne.parler("Tournoi de BELOTE !!! 10€ l'inscription !  "));
                    System.out.println(this.patronne.parler("* Placarde des affiches *"));
                 
                    System.out.println("Bienvenue, voulez-vous que le tournoi se crée automatiquement ? (o/n)");
                    reponse = scan.nextLine();
                    while(!reponse.equals("o") && !reponse.equals("n")){
                        System.out.println("Veuillez entrer \"o\" pour oui \"n\" pour non.");
                        reponse = scan.nextLine();
                    }
                    if(reponse.equals("o")){
                        TournoiBelote tournoiBeloteAuto = new TournoiBelote(10); //coût
                        tournoiBeloteAuto.inscrire(new Equipe("Equipe 1",this.joueur,clients.get(1)));
                        tournoiBeloteAuto.inscrire(new Equipe("Equipe 2",clients.get(2),clients.get(3)));
                        tournoiBeloteAuto.inscrire(new Equipe("Equipe 3",clients.get(4),clients.get(5)));
                     //   tournoiBeloteAuto.inscrire(new Equipe("Equipe 4",clients.get(6),clients.get(7)));
                        tournoiBeloteAuto.tournoiDebut();
                    }
                    else{
                        TournoiBelote tournoiBeloteAuto = new TournoiBelote(10); //coût
                        System.out.println("--------------------------------------");
                        System.out.println("*      Création Equipe 1             *");
                        System.out.println("--------------------------------------");
                        boolean equipe = true;
                        int joueur1 = 0;
                        int joueur2 = 0;
                        while(equipe){
                            System.out.println("Nom de l'équipe");
                            String nomEquipe = scan.nextLine();  
/*-------------------CHOIX UTILISATEUR JOUEUR--------------------*/
                           /* System.out.println("Voulez-vous être un joueur ? (o/n) ");
                            reponse = scan.nextLine();
                            while(!reponse.equals("o") && !reponse.equals("n")){
                                 System.out.println("Veuillez entrer \"o\" pour oui \"n\" pour non.");
                                 reponse = scan.nextLine();
                            }
    
                            if(reponse.equals("o")){ //cas client 
                               System.out.print("Ok"); //Code pour que l'utilisateur soit Joueur 1 
                            }*/
/*-------------------CHOIX J1 si utilisateur non joueur--------------------*/
                           // else {
                            System.out.println("Premier joueur : Est-ce un client ou un serveur ? (c/s) ");
                            reponse = scan.nextLine();
                            while(!reponse.equals("c") && !reponse.equals("s")){
                                 System.out.println("Veuillez entrer \"c\" pour client \"s\" pour serveur.");
                                 reponse = scan.nextLine();
                            }
                            if(reponse.equals("c")){ //cas client 
                            this.joueurs();
                            Error = true;
                            while(Error){
                                try{
                                    choix = Integer.parseInt(scan.nextLine());
                                    try{
                                        joueur1=choix;
                                        Error = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Désolé, ce numero ne correspond à aucun client");
                                        Error = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Veuillez entrer un entier");
                                    Error = true;
                                 }
                               }
                            }
                            //cas serveur 
                            else { 
                                if (serveurs.size() >= 2 ) {
                                    this.serveurs();
                                    Error = true;
                                    while(Error){
                                        try{
                                            choix = Integer.parseInt(scan.nextLine());
                                            try{
                                                joueur1=choix;
                                                Error = false;
                                                }
                                            catch(IndexOutOfBoundsException e){
                                                System.out.println("Désolé, ce numero ne correspond à aucun serveur");
                                                Error = true;
                                            }
                                        }
                                        catch(NumberFormatException e){
                                            System.out.println("Veuillez entrer un entier");
                                            Error = true;
                                        } 
                                            }
                             }
                                else System.out.print("Désolé ce serveur ne peux pas jouer, il est le seul en service !");
                            }
                           // }
   /*-------------------CHOIX J2--------------------*/
                            System.out.println("Veuillez choisir le co-equipier : est-ce un client ou un serveur ? (c/s)");
                            reponse = scan.nextLine();
                            while(!reponse.equals("c") && !reponse.equals("s")){
                                 System.out.println("Veuillez entrer \"c\" pour client \"s\" pour serveur.");
                                 reponse = scan.nextLine();
                            }
                            if(reponse.equals("c")){ //cas client 
                            this.joueurs();
                            Error = true;
                            while(Error){
                                try{
                                    choix = Integer.parseInt(scan.nextLine());
                                    try{
                                        joueur2=choix;
                                        Error = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Déoslé, ce numero ne correspond à aucun client");
                                        Error = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Veuillez entrer un entier");
                                    Error = true;
                                 }
                               }
                            }
                            //cas serveur 
                            else { 
                                //condition pour qu'il reste au moins 1 serveur dans le bar
                                if (serveurs.size() >= 2 ) {
                            this.serveurs();
                            Error = true;
                            while(Error){
                                try{
                                    choix = Integer.parseInt(scan.nextLine());
                                    try{
                                        joueur2=choix;
                                        Error = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Désolé, ce numero ne correspond à aucun serveur");
                                        Error = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Veuillez entrer un entier");
                                    Error = true;
                                } 
                                    }
                             }
                                else System.out.print("Oula ce serveur ne peux pas jouer, il est le seul en service !");
                            }
                            tournoiBelote.inscrire(new Equipe(nomEquipe,clients.get(joueur1),clients.get(joueur2)));
                             
/***************Creation equipe 2*************************/

                        System.out.println("--------------------------------------");
                        System.out.println("*      Création Equipe 2             *");
                        System.out.println("--------------------------------------");                    
                        while(equipe){
                            System.out.println("Nom de l'équipe : ");
                            String nomEquipe2 = scan.nextLine();  

/*-------------------CHOIX J1 si utilisateur non joueur--------------------*/       
                            System.out.println("Premier joueur : Est-ce un client ou un serveur ? (c/s). ");
                            reponse = scan.nextLine();
                            while(!reponse.equals("c") && !reponse.equals("s")){
                                 System.out.println("Veuillez entrer \"c\" pour client \"s\" pour serveur.");
                                 reponse = scan.nextLine();
                            }
                            if(reponse.equals("c")){ //cas client 
                            this.joueurs();
                            Error = true;
                            while(Error){
                                try{
                                    choix = Integer.parseInt(scan.nextLine());
                                    try{
                                        joueur1=choix;
                                        Error = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Désolé, ce numero ne correspond à aucun client.");
                                        Error = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Veuillez entrer un entier.");
                                    Error = true;
                                 }
                               }
                            }
                            //cas serveur 
                            else { 
                                if (serveurs.size() >= 2 ) {
                                    this.serveurs();
                                    Error = true;
                                    while(Error){
                                        try{
                                            choix = Integer.parseInt(scan.nextLine());
                                            try{
                                                joueur1=choix;
                                                Error = false;
                                                }
                                            catch(IndexOutOfBoundsException e){
                                                System.out.println("Désolé, ce numero ne correspond à aucun serveur.");
                                                Error = true;
                                            }
                                        }
                                        catch(NumberFormatException e){
                                            System.out.println("Veuillez entrer un entier.");
                                            Error = true;
                                        } 
                                            }
                            }
                            else System.out.print("Oula ce serveur ne peux pas jouer, il est le seul en service !");
                            }
                            
   /*-------------------CHOIX J2--------------------*/
                            System.out.println("Choisissez le co-equipier : est-ce un client ou un serveur ? (c/s)");
                            reponse = scan.nextLine();
                            while(!reponse.equals("c") && !reponse.equals("s")){
                                 System.out.println("Veuillez entrer \"c\" pour client \"s\" pour serveur.");
                                 reponse = scan.nextLine();
                            }
                            if(reponse.equals("c")){ //cas client 
                            this.joueurs();
                            Error = true;
                            while(Error){
                                try{
                                    choix = Integer.parseInt(scan.nextLine());
                                    try{
                                        joueur2=choix;
                                        Error = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Désolé, ce numero ne correspond à aucun client");
                                        Error = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Veuillez entrer un entier");
                                    Error = true;
                                 }
                               }
                            }
                            //cas serveur 
                            else { 
                                //condition pour qu'il reste au moins 1 serveur dans le bar
                                if (serveurs.size() >= 2 ) {
                            this.serveurs();
                            Error = true;
                            while(Error){
                                try{
                                    choix = Integer.parseInt(scan.nextLine());
                                    try{
                                        joueur2=choix;
                                        Error = false;
                                        }
                                    catch(IndexOutOfBoundsException e){
                                        System.out.println("Désolé, ce numero ne correspond à aucun serveur");
                                        Error = true;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Veuillez entrer un entier");
                                    Error = true;
                                } 
                                    }
                             }
                                else System.out.print("Désolé ce serveur ne peux pas jouer, il est le seul en service !");
                            }
                             tournoiBeloteAuto.inscrire(new Equipe(nomEquipe2,clients.get(joueur1),clients.get(joueur2)));
                       
/*********************Creation d'une nouvelle equipe**********************/ 
                        System.out.println("--------------------------------------");
                        System.out.println("*      Création d'une autre equipe   *");
                        System.out.println("--------------------------------------");
                            System.out.println("Voulez-vous créer une autre équipe?(o/n)");
                            reponse = scan.nextLine();
                            while(!reponse.equals("o") && !reponse.equals("n")){
                                System.out.println("Entrez \"o\" pour oui \"n\" pour non.");
                                reponse = scan.nextLine();
                            }
                            tournoiBelote.inscrire(new Equipe(nomEquipe2,clients.get(joueur1),clients.get(joueur2)));
                            if(reponse.equals("n"))
                                equipe = false;
                        }
                       
                        tournoiBelote.tournoiDebut();
                    }
                    }
                    break;
                case 11 : 
                    this.declarerSaFlamme();     
                    this.joueur.baisserCotePopularite(1);
                    break ;     
                case 12 : 
                    float argent = 0;
                    boolean conversionError = true;
                    while(conversionError){
                        try{
                        conversionError = false;
                        System.out.println("-----------------------------------------------------------------------------------------------------------*");
                        System.out.println("*                                                ATM Wells Fargo                                           *");
                        System.out.println("*                              Combien d'argent voulez vous retirer de votre compte ?                      *");
                        System.out.println("-----------------------------------------------------------------------------------------------------------*");
                        argent = Float.parseFloat(sc.nextLine());
                        }
                        catch(NumberFormatException e){
                        conversionError = true;
                        System.out.println("Veuillez entrer un float");
                        }
                        }
                    this.joueur.ajouterArgent(argent);
                    //this.joueur.changerSexe();
                    break;
                default:
                    break;
                }
    }
 }
}