/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;

import java.util.Scanner;
/**
 *
 * @author Théo
 */
public class BarManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Bonjour, bienvenue dans les Sims Bar");
        System.out.println("Pour pouvoir jouer tu vas devoir créer ton personnage, ainsi tu pourras boire des verres, faire des nouvelles connaissances, jouer aux cartes et plein d'autres choses !");
        System.out.println("Le Bar se compose d'une Patronne, de clients, de serveurs,d'un fournisseur, et biensûr de ton sims ");
        System.out.println("De plus chaque Client possède une côte de popularité, un taux d'alcoolémie, et un porte-monnaie.Ces caractéristiques évolueront au fur et à mesure des actions effectués en jeu ");
        Bar bar = new Bar();
        System.out.println(bar.name);
        bar.menu();
    }
    
}
