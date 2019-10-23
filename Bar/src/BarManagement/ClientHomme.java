/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarManagement;
import BarManagement.Boisson;

/**
 *
 * @author Théo
 */
public class ClientHomme extends Humain{
    
    Boisson Boisson1 = new Boisson();
    
    int random(){
       double random =  Math.random() * ( 6 );
        int ran = (int)random;
        return ran;
    }
    
    int aleatoire1= random();
    int aleatoire2= random();
  
    String boissonFavoriteSansAlcool = Boisson1.boissonNom[aleatoire1];
    String boissonFavoriteSansAlcool2 = Boisson1.boissonNom[aleatoire2];
    
    double alcoolemie;
    
    void alcoolemie() {
        alcoolemie=alcoolemie+Boisson1.boissonAlcoolemie[aleatoire1];
        System.out.println(alcoolemie);
        System.out.println(boissonFavoriteSansAlcool);
        System.out.println(aleatoire1);
    }
    
    void seFaireOffrirUnVerre(){
        System.out.println("Je vous offre un verre sale brigand ! ");
        //????????????????????????
    }
    
    void sePresenterComplementaire(){
        System.out.println("HiYAAAAAAA je m'appelle " + surnom);
    }
    
    void Tshirt(){
        
    }
    
}
