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
 *
 * Classe Bar qui possède toute les caractéristiques
 * et méthodes nécessaire à la gestion du bar 
 * Nous citerons notemment les Listes utilisés permettant
 * de stocker le nombre de table , les boissons qui composent le bar
 * les différentes personnes non-joueur tel que les Clients, les serveurs
 * @author Théo
 */

public class Bar {
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
    
    public Bar(){
    	this.caisse = 100;
        nbTables = 4;
        int j = -1;
    	sc = new Scanner(System.in);
    	boissons = new LinkedList<Boisson>();
    	serveurs = new LinkedList<Serveur>();
        clients = new LinkedList<Client>();
    	creationPatronne();
        creationFournisseur();
        creationBarman();
    	creationBoisson();  
        creationClientPnj();
    	creationServeur();
    	creationJoueur();
        
    	this.name = "Chez "+this.patronne.getNom();
        this.clients.add(this.joueur);
    	tables = new LinkedList<Table>();
        for(int i = 0 ; i < nbTables ; i++)
        	tables.add(new Table());
        
        for(int i = 0 ; i < clients.size()-1 ; i++){
        	if(i%4 == 0)
        		j++;
        	if(j>= tables.size()){
        		tables.add(new Table());
        	}
        	this.tables.get(j).ajouterPersonne(clients.get(i));
        }
    }
    
    /**
     * méthode permettant la creation du joueur
     * et de ses différentes caractéristiques tout
     * en vérifiant que les données entrées par l'utilisateur sont des
     * données valide à l'aide de conditions et d'exceptions
     */
    
    public void creationJoueur(){
    String name,surname,cri,sexe,num;
    int indiceBoissonFavorite = 0,indiceBoissonSecour = 0;
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
    	joueur = new Client(name ,surname,sexe ,cri,porteMonnaie,boissons.get(indiceBoissonFavorite),boissons.get(indiceBoissonSecour));
    }
    
    /**
     * Méthode permettant la création de la patronne et 
     * ses différentes caractéristiques tel que le Nom, le prenom
     * son cri significatif 
     * 
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
    	patronne = new Patronne(prenom,nom,"femme",cri);
    }
    
    /**
     * Méthode permettant la création du fournisseur
     * et de ses différentes caractéristiques
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
    	fournisseur = new Fournisseur(prenom,nom,"homme",cri);
    }
    
    /**
     * Méthode permettant la création du barman
     * ses différentes caractéristiques tel que son nom , son prenom 
     * et son cri significatif
     * 
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
    	barman = new Barman(prenom,nom,"homme",cri);
    }
    
     /**
     * Méthode permettant la création de boisson de manière automatique ou bien manuel
     * et qui va faire selon les réponses entrés par l'uilisateur appel à d'autre méthode 
     * tel que la méthode creationBoissonParDefaut, creationBoissonAvecAlcool , creationBoissonsSansAlcool
     * 
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
		}else{
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
     * Méthode permettant la création de boisson sans alcool 
     * ses différentes caractéristiques tel que son prix de vente et d'achat
     * utilisation d'excpetion afin de vérifier si l'utilisateur rentre bien une donnée valide ,
     * ici un float est attendu pour fixer le prix de la boisson 
     * 
     */
    public void creationBoissonSansAlcool(){
        float prix = 0,prixAchat = 0;
    	String name;
    	System.out.println("Quel est le nom de votre boisson ?");
    	name = sc.nextLine();
    	boolean conversionFail = true;
    	while(conversionFail){
    		try
        	{
        		conversionFail = false;
    			System.out.println("Quel est le prix de votre boisson ?");
        	    prix = Float.parseFloat(sc.nextLine());
        	}catch(NumberFormatException e){
        		conversionFail = true;
        		System.out.println("Entre un float s'il vous plaît ");
        	}
    	}
    	conversionFail = true;
    	while(conversionFail){
    		try
        	{
        		conversionFail = false;
    			System.out.println("Quel est le prix d'achat de votre boisson au prés du fournisseur?");
        	    prixAchat = Float.parseFloat(sc.nextLine());
        	}catch(NumberFormatException e){
        		conversionFail = true;
        		System.out.println("Entre un float s'il vous plaît ");
        	}
    	}
    	boissons.add(new Boisson(name,prix,prixAchat));
    }
    
     /**
     * Méthode permettant la création de boisson avec alcool 
     * ses différentes caractéristiques tel que son prix de vente,d'achat et son taux d'alcoolemie
     * utilisation d'excpetion afin de vérifier si l'utilisateur rentre bien une donnée valide ,
     * ici un float est attendu pour fixer le prix de la boisson 
     * 
     */
    public void creationBoissonAvecAlcool(){
        float prix = 0,prixAchat = 0, tauxAlcoolemie = 0;
    	String name;
    	boolean conversionFail = true;
    	System.out.println("Quel est le nom de votre boisson ?");
    	name = sc.nextLine();
    	while(conversionFail){
    		try
        	{
        		conversionFail = false;
    			System.out.println("Quel est le prix de votre boisson ?");
        	    prix = Float.parseFloat(sc.nextLine());
        	}catch(NumberFormatException e){
        		conversionFail = true;
        		System.out.println("Entre un float s'il vous plaît ");
        	}
    	}
    	conversionFail = true;
    	while(conversionFail){
    		try
        	{
        		conversionFail = false;
    			System.out.println("Quel est le prix d'achat de votre boisson au prés du fournisseur?");
        	    prixAchat = Float.parseFloat(sc.nextLine());
        	}catch(NumberFormatException e){
        		conversionFail = true;
        		System.out.println("Entre un float s'il vous plaît ");
        	}
    	}
    	conversionFail = true;
    	while(conversionFail){
    		try
        	{
        		conversionFail = false;
    			System.out.println("Quel est le taux d'alccolemie de votre boisson ?");
        	    tauxAlcoolemie = Float.parseFloat(sc.nextLine());
        	}catch(NumberFormatException e){
        		conversionFail = true;
        		System.out.println("Entre un float s'il vous plaît ");
        	}
    	}
    	boissons.add(new Boisson(name,prix,tauxAlcoolemie));
    }
    
    /**
     * Méthode permettant la creation des boissons par défaut
     * Ces boissons sont ainsi ajouté à la liste des Boissons
     * Cette liste est composé de boisson avec et sans alcool
     * 
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
    
}