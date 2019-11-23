/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmanagement;
/**
 * @author Lina & Théophile
 */


public class BarManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("*                                    Salut Cow-Boy ! Bienvenue dans notre saloon.                                     *");
        System.out.println("*            Tu vas pouvoir créer ton personnage, ainsi tu pourras boire des verres avec et sans alcool,              *");
        System.out.println("*       faire de nouvelles connaissances, jouer aux cartes, participer à un tournoi, et pleins d'autres choses !      *");
        System.out.println("*    Les personnages de notre Saloon sont : une Patronne, des clients, des serveurs, un fournisseur, et biensûr toi ! *");
        System.out.println("*    Amuse toi à trouver les moyens d'augmenter ta popularité mais gare à ne pas trop etre alcoolisé ! Garde un oeil  *");
        System.out.println("*                    sur ton porte-monnaie ;)  Et deviens le meilleur joueur de belote du Far West.                   *");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        Bar bar = new Bar();
        bar.menu();
    }
}