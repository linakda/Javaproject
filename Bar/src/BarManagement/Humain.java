/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarManagement;

/**
 *
 * @author Théo
 */
public class Humain {
    
    enum bijoux{collier, boucle, bracelet, bague}
    enum tshirtColor {noir, bleau, vert, rouge, jaune}
    
    String[] boissonSansAlcool = {"eau","jus d'orange","jus d'abricot","café"};
    double[] prixSansAlcool = {1.0,2.5,3.4};
    double[] tauxAlcoolemie1 = {0,0,0};
    
    String[] boissonAlcool = {"bourbon","bière","cocktail"};
    double[] prixAlcool = {10.0,5.0,8.0};
    double[] tauxAlcoolemie2 = {10,5,5};
    
    String prenom;
    String surnom;
    double portemonnaie;
    int popularite;
    String cri;
    
    void parler(){
        
    }
    
    void boire(){
        
    }
    
    void payer(double prix){
        portemonnaie=portemonnaie-prix;
    }
    
    void sePresenter(){
        System.out.println("Bonjour ! Je m'appelle" + prenom);
    }
    
    void offrirUnVerre(){
        System.out.println("Je t'offre une binouze bébé ?");
        commanderUnVerre();
    }
    
    void commanderUnVerre(){
        System.out.println("Je voudrais un verre svp");
        //servir();
        
    }
    
    void sommePortemonnaie(){
        //random du lbw1
    }
   
}
