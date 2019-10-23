/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarManagement;
import BarManagement.Barman;

/**
 *
 * @author Théo
 */
public class Humain {
    
    enum bijoux{collier, boucle, bracelet, bague, piercing}
    enum tshirtColor {noir, bleau, vert, rouge, jaune}
   
    String prenom;
    String surnom;
    double portemonnaie;
    int popularite;
    String cri;
    
    Barman bar = new Barman();
    
    void parler(){
        
    }
    
    void boire(){
        
    }
    
    void payer(double prix){
        
        portemonnaie=portemonnaie-prix;
        System.out.println("J'ai payé mon verre.");
        bar.encaisser(prix);
        
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
